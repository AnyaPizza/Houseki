package anya.pizza.houseki.item.custom;

import anya.pizza.houseki.util.ModTags;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class EnhancedDrillItem extends Item {
    public EnhancedDrillItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.tool(material, ModTags.Blocks.ENHANCED_DRILL_MINEABLE, attackDamage, attackSpeed, 3));
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos intitalBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            if (blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(intitalBlockPos.getX() + x, intitalBlockPos.getY(), intitalBlockPos.getZ() + y));
                    }
                }
            }
            if (blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(intitalBlockPos.getX() + x, intitalBlockPos.getY() + y, intitalBlockPos.getZ()));
                    }
                }
            }
            if (blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(intitalBlockPos.getX(), intitalBlockPos.getY() + y, intitalBlockPos.getZ() + x));
                    }
                }
            }
        }
        return positions;
    }
}
