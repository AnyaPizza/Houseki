package anya.pizza.houseki.util;

import anya.pizza.houseki.item.custom.PremiumDrillItem;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class PDUsageEvent implements PlayerBlockBreakEvents.Before{
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    /**
     * Handles PremiumDrill extra harvesting before a block is broken.
     *
     * When the player is using a PremiumDrillItem on the target position, attempts to destroy surrounding
     * blocks within the drill's area of effect while tracking processed positions to avoid duplicate harvesting.
     *
     * @param world the level where the block break is occurring
     * @param player the player breaking the block
     * @param pos the position of the block being broken
     * @param state the BlockState of the block being broken
     * @param blockEntity the BlockEntity at the position, or null if none
     * @return `true` if the event is handled and default processing should not continue, `false` otherwise
     */
    @Override
    public boolean beforeBlockBreak(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {

        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof PremiumDrillItem pd && player instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return true;
            }
            for (BlockPos position : PremiumDrillItem.getBlocksToBeDestroyed(2, pos, serverPlayer)) { //5x5
                if (pos == position || !pd.isCorrectToolForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(position);
                serverPlayer.gameMode.destroyBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }
        return true;
    }
}