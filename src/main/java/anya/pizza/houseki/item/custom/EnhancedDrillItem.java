package anya.pizza.houseki.item.custom;

import anya.pizza.houseki.util.ModTags;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public class EnhancedDrillItem extends Item {
    /**
     * Creates a new EnhancedDrillItem configured with the given material and combat stats and registered as mineable against the enhanced-drill block tag.
     *
     * @param material     the tool material defining durability and mining behavior
     * @param attackDamage the item's base attack damage
     * @param attackSpeed  the item's attack speed modifier
     * @param settings     additional item properties
     */
    public EnhancedDrillItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.tool(material, ModTags.Blocks.ENHANCED_DRILL_MINEABLE, attackDamage, attackSpeed, 3));
    }

    /**
     * Builds a list of block positions forming a square (size 2*range+1 per side) centered on the given position,
     * aligned to the plane determined by the face of the block the player is currently targeting.
     *
     * @param range the radius from the center; resulting square will extend from -range to +range along both plane axes
     * @param intitalBlockPos the central block position to base the square on
     * @param player the player whose current targeted face determines the plane orientation
     * @return a list of BlockPos in the aligned square; empty if the player is not targeting a block
     */
    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos intitalBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.pick(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            if (blockHit.getDirection() == Direction.DOWN || blockHit.getDirection() == Direction.UP) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(intitalBlockPos.getX() + x, intitalBlockPos.getY(), intitalBlockPos.getZ() + y));
                    }
                }
            }
            if (blockHit.getDirection() == Direction.NORTH || blockHit.getDirection() == Direction.SOUTH) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(intitalBlockPos.getX() + x, intitalBlockPos.getY() + y, intitalBlockPos.getZ()));
                    }
                }
            }
            if (blockHit.getDirection() == Direction.EAST || blockHit.getDirection() == Direction.WEST) {
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