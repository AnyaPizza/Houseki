package anya.pizza.houseki.world;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModOrePlacement {
    /**
     * Builds the standard placement modifier sequence used for ore generation.
     *
     * @param countModifier a placement modifier that controls how many ore veins are placed
     * @param heightModifier a placement modifier that controls the vertical distribution of veins
     * @return an immutable list of placement modifiers in order: `countModifier`, `InSquarePlacement.spread()`, `heightModifier`, `BiomeFilter.biome()`
     */
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    /**
     * Create a placement modifier list that applies a fixed count of placements per chunk using the provided height modifier.
     *
     * @param count the number of placement attempts per chunk
     * @param heightModifier a placement modifier that constrains vertical placement
     * @return a list of placement modifiers that applies the count, horizontal spread, the given height constraint, and biome filtering
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    /*public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }*/
}