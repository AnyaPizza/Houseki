package anya.pizza.houseki.block.custom;

import anya.pizza.houseki.block.entity.ModBlockEntities;
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

    public FoundryBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
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
    public @org.jetbrains.annotations.Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(LIT, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (world.getBlockEntity(pos) instanceof FoundryBlockEntity foundryBlockEntity) {
                player.openHandledScreen(foundryBlockEntity);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FOUNDRY_BE, (world1, pos, state1, blockEntity)
                -> blockEntity.tick(world1, pos, state1));
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