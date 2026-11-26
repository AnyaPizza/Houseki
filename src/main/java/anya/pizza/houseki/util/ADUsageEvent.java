package anya.pizza.houseki.util;

import anya.pizza.houseki.item.custom.AdvancedDrillItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;

public class ADUsageEvent {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    public static void breakExtraBlocks(Level world, Player player, BlockPos pos) {
        ItemStack mainHandItem = player.getMainHandItem();
        if (mainHandItem.getItem() instanceof AdvancedDrillItem ad && player instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return;
            }
            for (BlockPos position : AdvancedDrillItem.getBlocksToBeDestroyed(2, pos, serverPlayer)) { //5x5
                if (pos.equals(position) || !ad.isCorrectToolForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(position);
                serverPlayer.gameMode.destroyBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }
    }
}