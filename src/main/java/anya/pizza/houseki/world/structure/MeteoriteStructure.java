package anya.pizza.houseki.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.Optional;

/**
 * High-level structure class for the meteorite. Decides whether a meteorite should spawn
 * at a given chunk and calculates its parameters (radius, crater depth, seed).
 * The actual block placement happens in MeteoriteStructurePiece.
 */
public class MeteoriteStructure extends Structure {
    // Codec for JSON serialization. No custom fields, so we just wrap the base Structure config.
    public static final MapCodec<MeteoriteStructure> CODEC = simpleCodec(MeteoriteStructure::new);

    public MeteoriteStructure(StructureSettings config) {
        super(config);
    }

    /**
     * Called by Minecraft to check if a meteorite should generate in this chunk.
     * Returns empty if the location is unsuitable (too low), otherwise returns a
     * StructurePosition with a randomly sized MeteoriteStructurePiece.
     */
    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        // Get the block coordinates at the center of the candidate chunk
        int x = context.chunkPos().getMiddleBlockX();
        int z = context.chunkPos().getMiddleBlockZ();

        // Find the surface height at this position using the worldgen heightmap
        int surfaceY = context.chunkGenerator().getBaseHeight(
                x, z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        // Don't generate if the surface is too close to the world bottom (void, deep cave, etc.)
        if (surfaceY <= context.heightAccessor().getMinY() + 10) {
            return Optional.empty();
        }

        WorldgenRandom random = context.random();
        // Pick a random radius between MIN_RADIUS and MAX_RADIUS
        int radius = MeteoriteStructurePiece.MIN_RADIUS
                + random.nextInt(MeteoriteStructurePiece.MAX_RADIUS - MeteoriteStructurePiece.MIN_RADIUS + 1);
        // Crater depth scales with radius so bigger meteorites make deeper craters
        int craterDepth = radius + 3 + random.nextInt(4);

        BlockPos pos = new BlockPos(x, surfaceY, z);
        // Generate a seed for the piece so it can produce deterministic results across chunk passes
        long pieceSeed = random.nextLong();

        // Determine biome variant now, while the full biome source is accessible.
        // During postProcess only nearby chunks are available, so sampling the biome
        // at the meteor center from there would crash for large structures.
        Climate.Sampler sampler = context.randomState().sampler();
        Holder<Biome> biome = context.biomeSource().getNoiseBiome(
                x >> 2, surfaceY >> 2, z >> 2, sampler);

        // Reject ocean, river, beach and shore biomes outright. Without this,
        // the structure is registered (visible to /locate) but postProcess skips it
        // silently, creating ghost entries that players can never find.
        if (biome.is(BiomeTags.IS_OCEAN) || biome.is(BiomeTags.IS_RIVER)
                || biome.is(BiomeTags.IS_BEACH)
                || biome.is(Biomes.STONY_SHORE)) {
            return Optional.empty();
        }

        // Check terrain height variance and water presence around the impact site.
        // Sample at three distances: center, half crater radius, and full clearing
        // radius. Using only half-radius missed water bodies near the crater edge,
        // causing hard vertical cutoffs where the bowl meets the water.
        // Reject if:
        //   - any point's height differs by >8 blocks from center (steep terrain)
        //   - any point has water (WORLD_SURFACE_WG != OCEAN_FLOOR_WG)
        int craterRadius = radius + 25; // same as CRATER_EXTRA in MeteoriteStructurePiece
        int vegClearRadius = craterRadius + 12;
        int innerDist = craterRadius / 2;
        int outerDist = vegClearRadius;
        int[][] offsets = {
                {0, 0},
                // Inner ring at half crater radius
                {innerDist, 0}, {-innerDist, 0}, {0, innerDist}, {0, -innerDist},
                {innerDist, innerDist}, {innerDist, -innerDist}, {-innerDist, innerDist}, {-innerDist, -innerDist},
                // Outer ring at full vegetation clearing radius
                {outerDist, 0}, {-outerDist, 0}, {0, outerDist}, {0, -outerDist},
                {outerDist, outerDist}, {outerDist, -outerDist}, {-outerDist, outerDist}, {-outerDist, -outerDist}
        };
        for (int[] off : offsets) {
            int sx = x + off[0];
            int sz = z + off[1];
            int sampleSurface = context.chunkGenerator().getBaseHeight(
                    sx, sz, Heightmap.Types.WORLD_SURFACE_WG,
                    context.heightAccessor(), context.randomState());
            int sampleFloor = context.chunkGenerator().getBaseHeight(
                    sx, sz, Heightmap.Types.OCEAN_FLOOR_WG,
                    context.heightAccessor(), context.randomState());
            // Water detected: surface is above the ocean floor
            if (sampleSurface - sampleFloor > 1) {
                return Optional.empty();
            }
            if (Math.abs(sampleSurface - surfaceY) > 8) {
                return Optional.empty();
            }
        }

        int biomeVariantId = MeteoriteStructurePiece.resolveBiomeVariantId(biome);

        return Optional.of(new GenerationStub(pos, collector -> {
            collector.addPiece(new MeteoriteStructurePiece(
                    x, surfaceY, z, radius, craterDepth, pieceSeed, biomeVariantId));
        }));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.METEORITE_TYPE;
    }
}
