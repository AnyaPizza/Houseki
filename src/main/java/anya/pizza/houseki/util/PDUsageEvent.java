package anya.pizza.houseki.util;

import anya.pizza.houseki.item.custom.PremiumDrillItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;

public class PDUsageEvent {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    public static void breakExtraBlocks(Level world, Player player, BlockPos pos) {
        ItemStack mainHandItem = player.getMainHandItem();
        if (mainHandItem.getItem() instanceof PremiumDrillItem pd && player instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return;
            }
            for (BlockPos position : PremiumDrillItem.getBlocksToBeDestroyed(2, pos, serverPlayer)) { //5x5
                if (pos.equals(position) || !pd.isCorrectToolForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(position);
                serverPlayer.gameMode.destroyBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }
    }
}