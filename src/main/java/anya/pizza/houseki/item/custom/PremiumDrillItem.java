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

public class PremiumDrillItem extends Item {
    /**
     * Creates a PremiumDrillItem configured with the given tool material and combat attributes.
     *
     * <p>Configures the item's properties to use the specified material, mark blocks using the
     * PREMIUM_DRILL_MINEABLE tag as mineable, and apply the provided attack damage and attack speed.
     *
     * @param material    the tool material used by this drill
     * @param attackDamage the attack damage modifier for this drill
     * @param attackSpeed  the attack speed modifier for this drill
     * @param settings     base item properties to be extended with the drill configuration
     */
    public PremiumDrillItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.tool(material, ModTags.Blocks.PREMIUM_DRILL_MINEABLE, attackDamage, attackSpeed, 3));
    }

    /**
     * Computes the square set of block positions to be destroyed by the drill based on the face the player is targeting.
     *
     * <p>Produces a (2*range+1) by (2*range+1) square of positions centered on {@code intitalBlockPos} and oriented perpendicular to the block face the player is looking at; if the player is not targeting a block, an empty list is returned.
     *
     * @param range number of blocks to extend from the center in each direction (half-size of the square)
     * @param intitalBlockPos center block position for the square
     * @param player the player whose targeting direction determines the square's orientation
     * @return a list of BlockPos for the blocks to be destroyed; empty if no block face is targeted
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