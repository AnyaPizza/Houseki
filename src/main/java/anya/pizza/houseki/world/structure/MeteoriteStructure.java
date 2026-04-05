package anya.pizza.houseki.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class MeteoriteStructure extends Structure {
    public static final MapCodec<MeteoriteStructure> CODEC = createCodec(MeteoriteStructure::new);

    public MeteoriteStructure(Config config) {
        super(config);
    }

    @Override
    public Optional<StructurePosition> getStructurePosition(Context context) {
        int x = context.chunkPos().getCenterX();
        int z = context.chunkPos().getCenterZ();

        int surfaceY = context.chunkGenerator().getHeightOnGround(
                x, z, Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());

        if (surfaceY <= context.world().getBottomY() + 10) {
            return Optional.empty();
        }

        Random random = context.random();
        int radius = MeteoriteStructurePiece.MIN_RADIUS
                + random.nextInt(MeteoriteStructurePiece.MAX_RADIUS - MeteoriteStructurePiece.MIN_RADIUS + 1);
        int craterDepth = radius + 3 + random.nextInt(4);

        BlockPos pos = new BlockPos(x, surfaceY, z);
        long pieceSeed = random.nextLong();

        MultiNoiseUtil.MultiNoiseSampler sampler = context.noiseConfig().getMultiNoiseSampler();
        RegistryEntry<Biome> biome = context.biomeSource().getBiome(
                x >> 2, surfaceY >> 2, z >> 2, sampler);

        // Reject unsuitable biomes
        if (biome.isIn(BiomeTags.IS_OCEAN) || biome.isIn(BiomeTags.IS_RIVER)
                || biome.isIn(BiomeTags.IS_BEACH)
                || biome.matchesKey(BiomeKeys.STONY_SHORE)) {
            return Optional.empty();
        }

        // Terrain variance and water presence checks across the crater footprint
        int craterRadius = radius + 25;
        int vegClearRadius = craterRadius + 12;
        int innerDist = craterRadius / 2;
        int outerDist = vegClearRadius;
        int[][] offsets = {
                {0, 0},
                {innerDist, 0}, {-innerDist, 0}, {0, innerDist}, {0, -innerDist},
                {innerDist, innerDist}, {innerDist, -innerDist}, {-innerDist, innerDist}, {-innerDist, -innerDist},
                {outerDist, 0}, {-outerDist, 0}, {0, outerDist}, {0, -outerDist},
                {outerDist, outerDist}, {outerDist, -outerDist}, {-outerDist, outerDist}, {-outerDist, -outerDist}
        };
        for (int[] off : offsets) {
            int sx = x + off[0];
            int sz = z + off[1];
            int sampleSurface = context.chunkGenerator().getHeightOnGround(
                    sx, sz, Heightmap.Type.WORLD_SURFACE_WG,
                    context.world(), context.noiseConfig());
            int sampleFloor = context.chunkGenerator().getHeightOnGround(
                    sx, sz, Heightmap.Type.OCEAN_FLOOR_WG,
                    context.world(), context.noiseConfig());
            if (sampleSurface - sampleFloor > 1) {
                return Optional.empty();
            }
            if (Math.abs(sampleSurface - surfaceY) > 8) {
                return Optional.empty();
            }
        }

        int biomeVariantId = MeteoriteStructurePiece.resolveBiomeVariantId(biome);

        return Optional.of(new StructurePosition(pos, collector -> {
            collector.addPiece(new MeteoriteStructurePiece(
                    x, surfaceY, z, radius, craterDepth, pieceSeed, biomeVariantId));
        }));
    }

    @Override
    public StructureType<?> getType() {
        return ModStructures.METEORITE_TYPE;
    }
}
