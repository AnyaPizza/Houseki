package anya.pizza.houseki.block.custom;

import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public class FoundryBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<FoundryBlock> CODEC = simpleCodec(FoundryBlock::new);

    public FoundryBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    /**
     * Opens the foundry's handled screen for the player when invoked on the server and the block at the target position has a FoundryBlockEntity.
     *
     * @return ActionResult.SUCCESS
     */
    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            MenuProvider screenHandlerFactory = ((FoundryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openMenu(screenHandlerFactory);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.FOUNDRY_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playLocalSound(xPos, yPos, zPos, SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 1.0f, 5.0f, false);
        }
        if (random.nextDouble() < 0.05) {
            world.playLocalSound(xPos, yPos, zPos, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.7f, 0.5f, false);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        world.addParticle(ParticleTypes.ASH, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.2, 0.0, 0.0);
        world.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.01, -0.08, 0.0);

        if (world.getBlockEntity(pos) instanceof FoundryBlockEntity foundryBlockEntity && !foundryBlockEntity.getItem(1).isEmpty()) {
            world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, foundryBlockEntity.getItem(1).getItem()),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}