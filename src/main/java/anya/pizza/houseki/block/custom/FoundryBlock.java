package anya.pizza.houseki.block.custom;

import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FoundryBlock extends BlockWithEntity {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final MapCodec<FoundryBlock> CODEC = createCodec(FoundryBlock::new);

    /**
     * Creates a new FoundryBlock configured with the given block settings.
     *
     * @param settings block configuration (material, hardness, luminance, etc.) used to initialize this block
     */
    public FoundryBlock(Settings settings) {
        super(settings);
    }

    /**
     * Provides the codec used to serialize and deserialize this block-with-entity type.
     *
     * @return the MapCodec for this BlockWithEntity implementation
     */
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    /**
     * Produce a BlockState with its FACING property rotated according to the given BlockRotation.
     *
     * @param state the original block state
     * @param rotation the rotation to apply
     * @return the block state with the FACING property rotated by the provided rotation
     */
    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    /**
     * Compute the block state after applying the given mirror transformation.
     *
     * @param state  the current block state
     * @param mirror the mirror operation to apply
     * @return the block state rotated to reflect the specified mirror (updates the `FACING` axis)
     */
    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    /**
     * Determine the initial block state when the block is placed by a player.
     *
     * @param ctx the placement context used to derive the player's horizontal facing
     * @return the default block state with `FACING` set to the horizontal direction opposite the player and `LIT` set to `false`
     */
    @Override
    public @org.jetbrains.annotations.Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(LIT, false);
    }

    /**
     * Adds this block's configurable state properties to the provided state manager builder.
     *
     * @param builder the state manager builder to register this block's properties with
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /**
     * Create a new FoundryBlockEntity for the block at the given position and state.
     *
     * @param pos   the world position where the block entity will be placed
     * @param state the block state used to initialize the block entity
     * @return      a new FoundryBlockEntity instance for the specified position and state
     */
    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryBlockEntity(pos, state);
    }

    /**
     * Specifies the rendering strategy for this block.
     *
     * @return `BlockRenderType.MODEL` indicating the block is rendered using the standard block model.
     */
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * Opens the foundry's screen for the interacting player on the server.
     *
     * @param stack the item stack used to interact with the block
     * @param state the current block state
     * @param world the world containing the block
     * @param pos the block position
     * @param player the player performing the interaction
     * @param hand the hand used for the interaction
     * @param hit the hit result describing where the block was clicked
     * @return ActionResult.SUCCESS
     */
    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((FoundryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    /**
     * Provide a ticker for the foundry block entity that invokes its tick method each tick when the block entity type matches.
     *
     * @return a BlockEntityTicker that calls the foundry block entity's `tick` method when `type` matches ModBlockEntities.FOUNDRY_BE, or `null` otherwise.
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FOUNDRY_BE, (world1, pos, state1, blockEntity)
                -> blockEntity.tick(world1, pos, state1));
    }

    /**
     * Emit visual and auditory effects for a lit foundry block during random client ticks.
     *
     * <p>If the block's `LIT` property is false, no effects are produced. When lit, the method
     * may play ambient sound effects and spawns `ASH` and `SMOKE` particles slightly offset
     * toward the block's facing direction. If the block entity at the position contains an item
     * in slot 1, an item particle for that stack is also spawned.
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playSoundClient(xPos, yPos, zPos, SoundEvents.BLOCK_DRIPSTONE_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0f, 5.0f, false);
        }
        if (random.nextDouble() < 0.05) {
            world.playSoundClient(xPos, yPos, zPos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 0.7f, 0.5f, false);
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : defaultOffset;

        world.addParticleClient(ParticleTypes.ASH, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.2, 0.0, 0.0);
        world.addParticleClient(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.01, -0.08, 0.0);

        if (world.getBlockEntity(pos) instanceof FoundryBlockEntity foundryBlockEntity && !foundryBlockEntity.getStack(1).isEmpty()) {
            world.addParticleClient(new ItemStackParticleEffect(ParticleTypes.ITEM, foundryBlockEntity.getStack(1)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
    /*public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final MapCodec<FoundryBlock> CODEC = createCodec(FoundryBlock::new);

    protected FoundryBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(LIT, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((FoundryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FOUNDRY_BE, ((world1, pos, state1, blockEntity)
                -> blockEntity.tick(world1, pos, state1)));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playSoundClient(xPos, yPos, zPos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 0.5F, false);
        }
        if (random.nextDouble() < 0.05) {
            world.playSoundClient(xPos, yPos, zPos, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.7F, 0.5F, false);
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffset = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : defaultOffset;

        world.addParticleClient(ParticleTypes.ASH, xPos + xOffset, yPos = yOffset, zPos + zOffset, 0.2, 1.0, 0.0);
        world.addParticleClient(ParticleTypes.LAVA, xPos + xOffset, yPos = yOffset, zPos + zOffset, 0.1, 0.0, 0.0);

        if (world.getBlockEntity(pos) instanceof FoundryBlockEntity foundryBlockEntity && !foundryBlockEntity.getStack(1).isEmpty()) {
            world.addParticleClient(new ItemStackParticleEffect(ParticleTypes.ITEM, foundryBlockEntity.getStack(1)),
                    xPos + xOffset, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryBlockEntity(pos, state);
    }
}
*/