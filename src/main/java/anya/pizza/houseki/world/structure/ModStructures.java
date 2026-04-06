package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.Houseki;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

/**
 * Handles all registry setup for the meteorite structure.
 * Calling register() forces this class to load, which triggers the static field registrations.
 * The bootstrap methods are called during datagen to produce the worldgen JSON files.
 */
public class ModStructures {
    // Unique key for the meteorite structure in the STRUCTURE registry (resolves to "houseki:meteorite")
    public static final ResourceKey<Structure> METEORITE_KEY =
            ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "meteorite"));

    // Key for the structure set, which controls placement frequency and spacing
    public static final ResourceKey<StructureSet> METEORITE_SET_KEY =
            ResourceKey.create(Registries.STRUCTURE_SET, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "meteorite"));

    // Registers the structure type with its codec so Minecraft can serialize/deserialize it
    public static final StructureType<MeteoriteStructure> METEORITE_TYPE =
            Registry.register(BuiltInRegistries.STRUCTURE_TYPE,
                    Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "meteorite"),
                    () -> MeteoriteStructure.CODEC);

    // Registers the piece type. The method reference points to the NBT constructor used for deserialization.
    public static final StructurePieceType METEORITE_PIECE_TYPE =
            Registry.register(BuiltInRegistries.STRUCTURE_PIECE,
                    Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "meteorite"),
                    MeteoriteStructurePiece::new);

    /**
     * Called during datagen. Creates the meteorite structure and restricts it to Overworld biomes.
     * Uses TOP_LAYER_MODIFICATION step so the crater carving runs after all features
     * (including trees from VEGETAL_DECORATION), preventing vegetation from spawning
     * inside the crater.
     * Produces: data/houseki/worldgen/structure/meteorite.json
     */
    public static void bootstrapStructure(BootstrapContext<Structure> context) {
        var biomes = context.lookup(Registries.BIOME);

        context.register(METEORITE_KEY, new MeteoriteStructure(
                new Structure.StructureSettings.Builder(biomes.getOrThrow(BiomeTags.IS_OVERWORLD))
                        .generationStep(GenerationStep.Decoration.TOP_LAYER_MODIFICATION)
                        .build()
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
    public static void bootstrapStructureSet(BootstrapContext<StructureSet> context) {
        var structures = context.lookup(Registries.STRUCTURE);

        context.register(METEORITE_SET_KEY, new StructureSet(
                structures.getOrThrow(METEORITE_KEY),
                new RandomSpreadStructurePlacement(96, 56, RandomSpreadType.LINEAR, 948372615)
        ));
    }

    // Called from mod initializer to force class loading and trigger all static registrations above
    public static void register() {
        Houseki.LOGGER.info("Registering Structures for " + Houseki.MOD_ID);
    }
}
