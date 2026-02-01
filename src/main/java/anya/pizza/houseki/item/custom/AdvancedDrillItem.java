package anya.pizza.houseki.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public class AdvancedDrillItem extends Item {
    /**
     * Creates a new AdvancedDrillItem configured as a pickaxe using the given material and combat values.
     *
     * @param material the tool material defining mining durability and efficiency
     * @param attackDamage the base attack damage modifier for the item
     * @param attackSpeed the attack speed modifier for the item
     * @param settings item properties used to build the pickaxe configuration
     */
    public AdvancedDrillItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.pickaxe(material, attackDamage, attackSpeed));
    }

    /**
     * Compute the square plane of block positions to destroy centered on the given block and oriented
     * perpendicular to the face the player is targeting.
     *
     * @param range           distance from the center to the edge of the square (plane side length = 2*range + 1)
     * @param intitalBlockPos center block position for the plane
     * @param player          server player whose look direction determines which face (plane orientation) to use
     * @return                a list of BlockPos forming the oriented square plane; returns an empty list if the player is not targeting a block
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