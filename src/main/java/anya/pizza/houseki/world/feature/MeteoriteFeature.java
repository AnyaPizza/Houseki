package anya.pizza.houseki.world.feature;

import anya.pizza.houseki.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class MeteoriteFeature extends Feature<DefaultFeatureConfig> {
    private static final int MIN_RADIUS = 3;
    private static final int MAX_RADIUS = 5;

    public MeteoriteFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        int surfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, origin.getX(), origin.getZ());
        if (surfaceY <= world.getBottomY() + 10) return false;

        int radius = MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1);
        // Center the meteorite partially buried: center is 1-2 blocks below surface
        int buryDepth = 1 + random.nextInt(2);
        BlockPos center = new BlockPos(origin.getX(), surfaceY - buryDepth, origin.getZ());

        int coreRadius = Math.max(1, radius / 2);
        int placed = 0;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    // Add slight noise to make the sphere irregular
                    double noisyDist = dist + (random.nextDouble() - 0.5) * 0.8;
                    if (noisyDist > radius) continue;

                    BlockPos pos = center.add(dx, dy, dz);
                    BlockState existing = world.getBlockState(pos);

                    // Don't replace bedrock or other meteorites
                    if (existing.isOf(Blocks.BEDROCK) || existing.isOf(ModBlocks.METEORIC_IRON)) continue;

                    if (noisyDist <= coreRadius) {
                        // Core: meteoric iron
                        world.setBlockState(pos, ModBlocks.METEORIC_IRON.getDefaultState(), 2);
                    } else {
                        // Shell: mix of stone variants representing impact-fused material
                        BlockState shell = getShellBlock(random);
                        world.setBlockState(pos, shell, 2);
                    }
                    placed++;
                }
            }
        }

        // Clear some air above for the exposed part (small crater)
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > radius - 0.5) continue;
                for (int dy = 1; dy <= buryDepth + 1; dy++) {
                    BlockPos pos = center.add(dx, surfaceY - center.getY() + dy, dz);
                    BlockState state = world.getBlockState(pos);
                    if (!state.isAir() && !state.isOf(Blocks.WATER) && !state.isOf(Blocks.BEDROCK)
                            && !state.isOf(ModBlocks.METEORIC_IRON)) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                    }
                }
            }
        }

        return placed > 0;
    }

    private BlockState getShellBlock(Random random) {
        int roll = random.nextInt(10);
        if (roll < 4) return Blocks.STONE.getDefaultState();
        if (roll < 6) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 8) return Blocks.GRAVEL.getDefaultState();
        return Blocks.DEEPSLATE.getDefaultState();
    }
}
