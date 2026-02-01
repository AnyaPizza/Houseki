package anya.pizza.houseki.block.custom;

import com.mojang.serialization.MapCodec;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.MenuProvider;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class CrusherBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<CrusherBlock> CODEC = simpleCodec(CrusherBlock::new);

    /**
     * Creates a new CrusherBlock configured with the provided block properties.
     *
     * @param settings the block properties (e.g., material, hardness, luminance, etc.) used to configure this block
     */
    public CrusherBlock(Properties settings) {
        super(settings);
    }

    /**
     * Provides the MapCodec used to serialize and deserialize this block and its block-entity state.
     *
     * @return the MapCodec instance for this block
     */
    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /**
     * Applies the given rotation to this block state's horizontal facing.
     *
     * @param rotation the rotation to apply to the block's {@code FACING}
     * @return the block state with an updated {@code FACING} after rotation
     */
    @Override
    protected @NonNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /**
     * Apply the given mirror transformation to the block state's facing direction.
     *
     * @param state  the original block state
     * @param mirror the mirror operation to apply
     * @return the block state with its `FACING` value rotated according to the mirror
     */
    @Override
    protected @NonNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /**
     * Determine the initial CrusherBlock state when the block is placed.
     *
     * @param ctx the placement context; used to derive the block's horizontal facing
     * @return the BlockState with FACING set to the horizontal direction opposite the placer and LIT set to false
     */
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(LIT, false);
    }

    /**
     * Registers the block state properties for this block.
     *
     * @param builder the state definition builder to which this block's properties (FACING, LIT) are added
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /**
     * Creates a new CrusherBlockEntity for this block.
     *
     * @return a new {@code CrusherBlockEntity} instance positioned at the provided block position with the given block state
     */
    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new CrusherBlockEntity(pos, state);
    }

    /**
     * Specifies that the block is rendered using a model.
     *
     * @return `RenderShape.MODEL` indicating model-based rendering for this block
     */
    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    /**
     * Opens the crusher's container menu for the player when executed on the server.
     *
     * If the block at the given position provides a MenuProvider, this method opens that menu for the player.
     *
     * @return `InteractionResult.SUCCESS`
     */
    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (!world.isClientSide()) {
            MenuProvider screenHandlerFactory = ((CrusherBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openMenu(screenHandlerFactory);
            }
        }

        return InteractionResult.SUCCESS;
    }

    /**
     * Supplies a ticker for the crusher block entity type.
     *
     * @param <T>  the block entity subtype
     * @param world the world instance (unused by this helper)
     * @param state the current block state (unused by this helper)
     * @param type  the block entity type to match against the crusher entity
     * @return a BlockEntityTicker that invokes the crusher block entity's tick method when `type` equals ModBlockEntities.CRUSHER_BE, `null` otherwise
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level world, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.CRUSHER_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    /**
     * Displays visual and audible effects for a lit crusher block.
     *
     * When the block state's `LIT` is true, this method may play short block sounds
     * and spawns ash and smoke particles at the block face determined by `FACING`.
     * If the block entity has an item in slot 1, an item particle for that item is
     * also emitted. No effects are produced when `LIT` is false.
     */
    @Override
    public void animateTick(BlockState state, @NonNull Level world, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playLocalSound(xPos, yPos, zPos, SoundEvents.DRIPSTONE_BLOCK_BREAK, SoundSource.BLOCKS, 1.0f, 5.0f, false);
        }
        if (random.nextDouble() < 0.05) {
            world.playLocalSound(xPos, yPos, zPos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.7f, 0.5f, false);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        world.addParticle(ParticleTypes.ASH, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.2, 0.0, 0.0);
        world.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.01, -0.08, 0.0);

        if (world.getBlockEntity(pos) instanceof CrusherBlockEntity crusherBlockEntity && !crusherBlockEntity.getItem(1).isEmpty()) {
            world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, crusherBlockEntity.getItem(1).getItem()),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}