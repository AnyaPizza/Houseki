package anya.pizza.houseki.util;

import anya.pizza.houseki.item.custom.EnhancedDrillItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;

public class EDUsageEvent {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    public static void breakExtraBlocks(Level world, Player player, BlockPos pos) {
        ItemStack mainHandItem = player.getMainHandItem();
        if (mainHandItem.getItem() instanceof EnhancedDrillItem ed && player instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return;
            }
            for (BlockPos position : EnhancedDrillItem.getBlocksToBeDestroyed(1, pos, serverPlayer)) { //3x3
                if (pos.equals(position) || !ed.isCorrectToolForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(position);
                serverPlayer.gameMode.destroyBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }
    }
}
