package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.Houseki;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

/**
 * Handles all registry setup for the meteorite structure.
 * Calling register() forces this class to load, which triggers the static field registrations.
 * The bootstrap methods are called during datagen to produce the worldgen JSON files.
 */
public class ModStructures {
    // Unique key for the meteorite structure in the STRUCTURE registry (resolves to "houseki:meteorite")
    public static final RegistryKey<Structure> METEORITE_KEY =
            RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(Houseki.MOD_ID, "meteorite"));

    // Key for the structure set, which controls placement frequency and spacing
    public static final RegistryKey<StructureSet> METEORITE_SET_KEY =
            RegistryKey.of(RegistryKeys.STRUCTURE_SET, Identifier.of(Houseki.MOD_ID, "meteorite"));

    // Registers the structure type with its codec so Minecraft can serialize/deserialize it
    public static final StructureType<MeteoriteStructure> METEORITE_TYPE =
            Registry.register(Registries.STRUCTURE_TYPE,
                    Identifier.of(Houseki.MOD_ID, "meteorite"),
                    () -> MeteoriteStructure.CODEC);

    // Registers the piece type. The method reference points to the NBT constructor used for deserialization.
    public static final StructurePieceType METEORITE_PIECE_TYPE =
            Registry.register(Registries.STRUCTURE_PIECE,
                    Identifier.of(Houseki.MOD_ID, "meteorite"),
                    MeteoriteStructurePiece::new);

    /**
     * Called during datagen. Creates the meteorite structure and restricts it to Overworld biomes.
     * Produces: data/houseki/worldgen/structure/meteorite.json
     */
    public static void bootstrapStructure(Registerable<Structure> context) {
        var biomes = context.getRegistryLookup(RegistryKeys.BIOME);

        context.register(METEORITE_KEY, new MeteoriteStructure(
                new Structure.Config.Builder(biomes.getOrThrow(BiomeTags.IS_OVERWORLD)).build()
        ));
    }

    /**
     * Called during datagen. Defines placement rules for the meteorite.
     * Produces: data/houseki/worldgen/structure_set/meteorite.json
     *
     * Spacing 96 = grid cell size in chunks (at most 1 meteorite per 96x96 chunk region)
     * Separation 56 = minimum distance in chunks between two meteorites
     * SpreadType.LINEAR = uniform random offset within each grid cell
     * Salt = unique seed so meteorites don't overlap with other structures
     */
    public static void bootstrapStructureSet(Registerable<StructureSet> context) {
        var structures = context.getRegistryLookup(RegistryKeys.STRUCTURE);

        context.register(METEORITE_SET_KEY, new StructureSet(
                structures.getOrThrow(METEORITE_KEY),
                new RandomSpreadStructurePlacement(96, 56, SpreadType.LINEAR, 948372615)
        ));
    }

    // Called from mod initializer to force class loading and trigger all static registrations above
    public static void register() {
        Houseki.LOGGER.info("Registering Structures for " + Houseki.MOD_ID);
    }
}
