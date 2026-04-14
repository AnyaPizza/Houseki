package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * The actual block-placing piece for the meteorite structure.
 * Handles everything: crater excavation, wall lining, meteorite sphere, debris, and ejecta rim.
 *
 * Generation happens in 5 phases (see generate() for details):
 *   1. Crater excavation (bowl shape + tree clearing)
 *   2. Crater wall lining (replace exposed dirt/sand with stone)
 *   3. Meteorite sphere (iron core + stone shell with noise for roughness)
 *   4. Debris scatter (iron fragments on crater floor)
 *   5. Ejecta rim (scorched ring around the crater edge)
 */
public class MeteoriteStructurePiece extends StructurePiece {
    // Size range for the meteorite sphere (in blocks). Diameter will be 2x this.
    public static final int MIN_RADIUS = 5;
    public static final int MAX_RADIUS = 10;
    // How much wider the crater is than the meteorite itself
    private static final int CRATER_EXTRA = 25;
    // How far above the surface we clear blocks (to remove tall trees)
    private static final int TREE_CLEAR_HEIGHT = 60;

    // Biome-specific visual variants for the meteor. Core blocks (METEORIC_IRON) and
    // iron distribution stay the same across all variants; only the surrounding
    // shell, crater lining, and ejecta rim adapt to the local biome.
    private enum BiomeVariant {
        DEFAULT, DESERT, MANGROVE, SNOWY, JUNGLE, TAIGA, BADLANDS, SAVANNA, CHERRY, MUSHROOM, DARK_FOREST
    }

    private final int centerX;      // world X of the impact center
    private final int surfaceY;     // Y of the terrain surface at impact
    private final int centerZ;      // world Z of the impact center
    private final int meteorRadius; // radius of the meteorite sphere (7-11)
    private final int craterDepth;  // how deep the crater goes below surfaceY
    private final long seed;        // deterministic seed for reproducible random across chunk passes
    private final int biomeVariantId; // ordinal of BiomeVariant, determined at structure placement time

    // Primary constructor, used when the structure is first created during worldgen
    public MeteoriteStructurePiece(int centerX, int surfaceY, int centerZ,
                                   int meteorRadius, int craterDepth, long seed,
                                   int biomeVariantId) {
        super(ModStructures.METEORITE_PIECE_TYPE, 0,
                makeBounds(centerX, surfaceY, centerZ, meteorRadius, craterDepth));
        this.centerX = centerX;
        this.surfaceY = surfaceY;
        this.centerZ = centerZ;
        this.meteorRadius = meteorRadius;
        this.craterDepth = craterDepth;
        this.seed = seed;
        this.biomeVariantId = biomeVariantId;
    }

    // NBT constructor, used when loading from a saved game. orElse() provides fallback defaults.
    public MeteoriteStructurePiece(StructurePieceSerializationContext context, CompoundTag data) {
        super(ModStructures.METEORITE_PIECE_TYPE, data);
        this.centerX = data.getInt("cx").orElse(0);
        this.surfaceY = data.getInt("sy").orElse(0);
        this.centerZ = data.getInt("cz").orElse(0);
        this.meteorRadius = data.getInt("mr").orElse(7);
        this.craterDepth = data.getInt("cd").orElse(10);
        this.seed = data.getLong("seed").orElse(0L);
        this.biomeVariantId = data.getInt("bv").orElse(0);
    }

    // Computes the bounding box that fully encloses the structure.
    // Horizontal: meteorite + crater + enough buffer for the full vegetation
    // clearing zone (floatClearRadius = craterRadius + 15) plus ejecta rim.
    // Vertical: deep enough for the sphere at crater bottom, high enough to clear trees.
    private static BoundingBox makeBounds(int cx, int sy, int cz, int r, int depth) {
        int extent = r + CRATER_EXTRA + 17;
        return new BoundingBox(cx - extent, sy - depth - r - 2, cz - extent,
                cx + extent, sy + TREE_CLEAR_HEIGHT, cz + extent);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag data) {
        data.putInt("cx", centerX);
        data.putInt("sy", surfaceY);
        data.putInt("cz", centerZ);
        data.putInt("mr", meteorRadius);
        data.putInt("cd", craterDepth);
        data.putLong("seed", seed);
        data.putInt("bv", biomeVariantId);
    }

    // Returns the Y of the crater floor at a given horizontal distance from center.
    // Uses the local terrain height so the bowl blends into the surrounding
    // landscape. On flat terrain this is identical to using surfaceY. On hillsides
    // the bowl naturally carves into the hill instead of creating a cliff face.
    private int getCraterFloorY(double horizDist, int craterRadius, int localTerrainY) {
        if (horizDist > craterRadius) return localTerrainY;
        double ratio = horizDist / craterRadius;
        double bowlCurve = Math.cos(Math.PI * 0.5 * ratio);
        bowlCurve = bowlCurve * bowlCurve;
        int centerFloor = surfaceY - craterDepth;
        // Blend: at center (bowlCurve=1) -> centerFloor
        //        at rim   (bowlCurve=0) -> localTerrainY
        return localTerrainY + (int) (bowlCurve * (centerFloor - localTerrainY));
    }

    // Convenience overload that uses surfaceY as the local terrain height.
    // Gives the same result as the old formula on flat terrain and is safe to
    // call from phases where the original terrain height is no longer available.
    private int getCraterFloorY(double horizDist, int craterRadius) {
        return getCraterFloorY(horizDist, craterRadius, surfaceY);
    }

    /**
     * Maps a biome holder to its variant ordinal. Called from MeteoriteStructure during
     * findGenerationPoint, where the full biome source is safely accessible.
     */
    public static int resolveBiomeVariantId(Holder<Biome> biome) {
        if (biome.is(Biomes.DESERT)) {
            return BiomeVariant.DESERT.ordinal();
        }
        if (biome.is(Biomes.MANGROVE_SWAMP) || biome.is(Biomes.SWAMP)) {
            return BiomeVariant.MANGROVE.ordinal();
        }
        if (biome.is(Biomes.SNOWY_PLAINS) || biome.is(Biomes.SNOWY_TAIGA)
                || biome.is(Biomes.SNOWY_BEACH) || biome.is(Biomes.SNOWY_SLOPES)
                || biome.is(Biomes.FROZEN_PEAKS) || biome.is(Biomes.ICE_SPIKES)
                || biome.is(Biomes.GROVE)) {
            return BiomeVariant.SNOWY.ordinal();
        }
        if (biome.is(Biomes.JUNGLE) || biome.is(Biomes.SPARSE_JUNGLE)
                || biome.is(Biomes.BAMBOO_JUNGLE)) {
            return BiomeVariant.JUNGLE.ordinal();
        }
        if (biome.is(Biomes.TAIGA) || biome.is(Biomes.OLD_GROWTH_PINE_TAIGA)
                || biome.is(Biomes.OLD_GROWTH_SPRUCE_TAIGA)) {
            return BiomeVariant.TAIGA.ordinal();
        }
        if (biome.is(Biomes.BADLANDS) || biome.is(Biomes.ERODED_BADLANDS)
                || biome.is(Biomes.WOODED_BADLANDS)) {
            return BiomeVariant.BADLANDS.ordinal();
        }
        if (biome.is(Biomes.SAVANNA) || biome.is(Biomes.SAVANNA_PLATEAU)
                || biome.is(Biomes.WINDSWEPT_SAVANNA)) {
            return BiomeVariant.SAVANNA.ordinal();
        }
        if (biome.is(Biomes.CHERRY_GROVE)) {
            return BiomeVariant.CHERRY.ordinal();
        }
        if (biome.is(Biomes.MUSHROOM_FIELDS)) {
            return BiomeVariant.MUSHROOM.ordinal();
        }
        if (biome.is(Biomes.DARK_FOREST)) {
            return BiomeVariant.DARK_FOREST.ordinal();
        }
        return BiomeVariant.DEFAULT.ordinal();
    }

    private BiomeVariant getVariant() {
        BiomeVariant[] values = BiomeVariant.values();
        if (biomeVariantId >= 0 && biomeVariantId < values.length) {
            return values[biomeVariantId];
        }
        return BiomeVariant.DEFAULT;
    }

    /**
     * Main generation method. Called once per chunk that overlaps this piece's bounding box.
     * Uses a deterministic seed so the output is identical regardless of chunk load order.
     *
     * Phases:
     *   1. Crater excavation - dig the bowl and clear trees/vegetation
     *   1.5 Float cleanup - remove leftover vines and leaves
     *   2. Cave sealing - fill cave/ravine gaps beneath the crater
     *   3. Meteorite sphere - place the iron core and stone shell
     *   4. Debris scatter - drop iron fragments on the crater floor
     *   5. Ejecta rim - scorched ring with occasional raised blocks at the crater edge
     *   6. Fallen trees - biome-specific logs on the crater floor
     *   7. Impact heat - fire and lava near the core for a fresh-impact look
     */
    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager,
                         ChunkGenerator generator, RandomSource randomSource,
                         BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        // Deterministic random from stored seed - critical for cross-chunk consistency
        Random random = Random.from(RandomGenerator.getDefault());
        int craterRadius = meteorRadius + CRATER_EXTRA;
        int settleAmount = 5;
        int meteorCenterY = (surfaceY - craterDepth) + meteorRadius - settleAmount;
        BlockPos meteorCenter = new BlockPos(centerX, meteorCenterY, centerZ);
        BiomeVariant variant = getVariant();

        // Bail out on lava or water for all variants.
        // Check center and 4 cardinal points at half crater radius to catch
        // nearby lakes/rivers that the biome check can't detect.
        int waterCheckDist = craterRadius / 2;
        int[][] waterChecks = {{0, 0}, {waterCheckDist, 0}, {-waterCheckDist, 0},
                {0, waterCheckDist}, {0, -waterCheckDist}};
        for (int[] wc : waterChecks) {
            int wx = centerX + wc[0];
            int wz = centerZ + wc[1];
            int wy = level.getHeight(Heightmap.Types.WORLD_SURFACE, wx, wz) - 1;
            BlockState wcState = level.getBlockState(new BlockPos(wx, wy, wz));
            if (wcState.getFluidState().is(FluidTags.LAVA)) {
                return;
            }
            if (wcState.getFluidState().is(FluidTags.WATER)) {
                return;
            }
        }

        // Phase 1: Terrain-adaptive crater excavation and vegetation clearing.
        // The bowl depth is computed relative to the local terrain height so the
        // crater blends smoothly into hillsides instead of creating cliff faces.
        // Vegetation (trees, leaves, etc.) is cleared in a wider zone around the
        // crater to prevent floating canopies.
        int vegClearRadius = craterRadius + 12;
        for (int dx = -vegClearRadius; dx <= vegClearRadius; dx++) {
            for (int dz = -vegClearRadius; dz <= vegClearRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > vegClearRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int rawSurfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz);
                int groundY = getGroundLevel(level, bx, bz, rawSurfaceY);
                int topClearY = Math.max(rawSurfaceY, surfaceY) + TREE_CLEAR_HEIGHT;

                boolean insideCrater = horizDist <= craterRadius;

                if (insideCrater) {
                    int craterFloorY = getCraterFloorY(horizDist, craterRadius, groundY);

                    // Clear everything from high above (trees!) down to crater floor
                    for (int y = topClearY; y >= craterFloorY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.isInside(pos)) continue;
                        BlockState state = level.getBlockState(pos);
                        if (state.is(Blocks.BEDROCK)) continue;
                        if (!state.isAir()) {
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }

                    // Line the crater floor (1-2 blocks deep) with scorched material
                    for (int depth = 0; depth < 2; depth++) {
                        BlockPos floorPos = new BlockPos(bx, craterFloorY - 1 - depth, bz);
                        if (!chunkBox.isInside(floorPos)) continue;
                        BlockState existing = level.getBlockState(floorPos);
                        if (existing.is(Blocks.BEDROCK)) continue;
                        if (!existing.isAir()) {
                            // Replace dirt, sand, grass, etc. with crater material
                            if (!meteorWontReplace(existing)) {
                                level.setBlock(floorPos, getCraterLiner(random, variant), 2);
                            }
                        }
                    }
                } else {
                    // Outside crater: clear all vegetation above ground level.
                    // This prevents floating canopies from trees partially inside
                    // the crater or near the rim.
                    for (int y = topClearY; y >= groundY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.isInside(pos)) continue;
                        BlockState state = level.getBlockState(pos);
                        if (!state.isAir() && !state.is(Blocks.BEDROCK)
                                && isVegetation(state)) {
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        // Phase 1.5: Remove ALL floating vegetation left after tree clearing.
        // Not just vines and leaves -- also logs, mushroom blocks, bamboo, and
        // any other vegetation that can end up hovering after the crater carves
        // away the terrain beneath. Trees from adjacent chunks can place blocks
        // into already-processed chunks, so this pass does NOT respect chunkBox
        // boundaries. Setting blocks to air in loaded adjacent chunks is safe
        // during postProcess.
        // Top-to-bottom scan so vine chains collapse correctly in a single pass.
        int floatClearRadius = craterRadius + 15;
        for (int dx = -floatClearRadius; dx <= floatClearRadius; dx++) {
            for (int dz = -floatClearRadius; dz <= floatClearRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);

                if (horizDist > floatClearRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int topY = Math.max(level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz), surfaceY) + TREE_CLEAR_HEIGHT;
                int bottomY = surfaceY - craterDepth - meteorRadius;

                for (int y = topY; y >= bottomY; y--) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    BlockState state = level.getBlockState(pos);
                    // Remove unsupported vines
                    if (state.is(Blocks.VINE) && !hasVineSupport(level, pos)) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        continue;
                    }
                    // Remove all leaves, logs, mushroom blocks, bamboo, and
                    // other vegetation unconditionally in the clearing zone.
                    // These should not be floating above the impact site.
                    if (isVegetation(state)) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }

        // Phase 2: Seal caves and ravines beneath the crater.
        // Carvers (caves, ravines) run before structures and may have opened gaps
        // below the crater floor that would be visible from inside. Scan downward
        // from just below the 2-block liner and fill any air or fluid pockets until
        // we hit a thick enough stretch of solid material.
        // Only runs where local terrain is within 6 blocks of surfaceY to avoid
        // filling natural terrain gaps near water/cliff edges.
        int sealDepth = craterDepth + meteorRadius;
        for (int dx = -craterRadius - 1; dx <= craterRadius + 1; dx++) {
            for (int dz = -craterRadius - 1; dz <= craterRadius + 1; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > craterRadius + 1) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int rawSY = level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz);
                int groundY = getGroundLevel(level, bx, bz, rawSY);

                // Skip columns where terrain is much lower than surfaceY.
                // These are natural terrain drops (water edges, cliffs) — not
                // caves that need sealing. Filling them creates artificial walls.
                if (surfaceY - groundY > 6) continue;

                int craterFloorY = getCraterFloorY(horizDist, craterRadius, groundY);

                int solidStreak = 0;
                for (int y = craterFloorY - 3; y >= craterFloorY - sealDepth; y--) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    if (!chunkBox.isInside(pos)) continue;
                    BlockState state = level.getBlockState(pos);
                    if (state.is(Blocks.BEDROCK)) break;
                    if (state.isAir() || !state.getFluidState().isEmpty()) {
                        level.setBlock(pos, getCraterLiner(random, variant), 2);
                        solidStreak = 0;
                    } else {
                        solidStreak++;
                        // Five consecutive solid blocks means we are past the opening
                        if (solidStreak >= 5) break;
                    }
                }
            }
        }

        // Phase 3: Place meteorite sphere
        // The sphere has two zones: an inner core of METEORIC_IRON and an outer shell of mixed stone.
        // Noise is added to each block's distance to create an irregular, natural-looking surface.
        int coreRadius = Math.max(2, meteorRadius / 2);
        for (int dx = -meteorRadius; dx <= meteorRadius; dx++) {
            for (int dy = -meteorRadius; dy <= meteorRadius; dy++) {
                for (int dz = -meteorRadius; dz <= meteorRadius; dz++) {
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    if (dist > meteorRadius + 0.5) continue;

                    double noise = (random.nextDouble() - 0.5) * 0.8;
                    BlockState shellBlock = getShellBlock(random, variant);

                    double noisyDist = dist + noise;
                    if (noisyDist > meteorRadius) continue;

                    BlockPos pos = meteorCenter.offset(dx, dy, dz);
                    if (!chunkBox.isInside(pos)) continue;
                    BlockState existing = level.getBlockState(pos);
                    if (existing.is(Blocks.BEDROCK)) continue;

                    if (noisyDist <= coreRadius) {
                        level.setBlock(pos, ModBlocks.METEORIC_IRON.defaultBlockState(), 2);
                    } else {
                        // Rare platinum ore deposits carried within the meteorite shell.
                        // ~5% of shell blocks are replaced with platinum ore.
                        if (random.nextInt(20) == 0) {
                            level.setBlock(pos, getPlatinumOre(random), 2);
                        } else {
                            level.setBlock(pos, shellBlock, 2);
                        }
                    }
                }
            }
        }

        // Phase 4: Scatter meteoric iron debris on crater floor
        int debrisCount = 3 + random.nextInt(5);
        for (int i = 0; i < debrisCount; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double d = 2.0 + random.nextDouble() * (craterRadius * 0.65);
            int dx = (int) Math.round(Math.cos(angle) * d);
            int dz = (int) Math.round(Math.sin(angle) * d);

            double horizDist = Math.sqrt(dx * dx + dz * dz);
            int craterFloorY = getCraterFloorY(horizDist, craterRadius);
            if (craterFloorY >= surfaceY - 1) continue;

            BlockPos debrisPos = new BlockPos(centerX + dx, craterFloorY, centerZ + dz);
            if (!chunkBox.isInside(debrisPos)) continue;
            if (level.getBlockState(debrisPos).isAir()) {
                level.setBlock(debrisPos, ModBlocks.METEORIC_IRON.defaultBlockState(), 2);
            }
        }

        // Phase 4.5: Platinum ore deposits embedded in the crater walls and floor.
        // Impact mineralization: 1-3 small clusters of 1-3 blocks each, placed
        // below the crater floor surface so players must mine to find them.
        int platinumClusterCount = 1 + random.nextInt(3);
        for (int c = 0; c < platinumClusterCount; c++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double dist = meteorRadius + random.nextDouble() * (craterRadius * 0.6);
            int px = centerX + (int) Math.round(Math.cos(angle) * dist);
            int pz = centerZ + (int) Math.round(Math.sin(angle) * dist);

            double horizDist = Math.sqrt((px - centerX) * (px - centerX)
                    + (pz - centerZ) * (pz - centerZ));
            int floorY = getCraterFloorY(horizDist, craterRadius);
            if (floorY >= surfaceY - 1) continue;

            // Place cluster 2-4 blocks below the crater floor
            int depth = 2 + random.nextInt(3);
            int clusterSize = 1 + random.nextInt(3);
            for (int b = 0; b < clusterSize; b++) {
                int ox = (b == 0) ? 0 : random.nextInt(3) - 1;
                int oy = (b == 0) ? 0 : random.nextInt(2);
                int oz = (b == 0) ? 0 : random.nextInt(3) - 1;
                BlockPos orePos = new BlockPos(px + ox, floorY - depth + oy, pz + oz);
                if (!chunkBox.isInside(orePos)) continue;
                BlockState existing = level.getBlockState(orePos);
                if (existing.is(Blocks.BEDROCK) || existing.isAir()) continue;
                if (!meteorWontReplace(existing)) {
                    level.setBlock(orePos, getPlatinumOre(random), 2);
                }
            }
        }

        // Phase 5: Scorched ejecta rim at actual terrain height.
        // Only applied where terrain is roughly level with the impact center.
        // Skipped on steep hillsides to avoid artificial-looking material bands.
        for (int dx = -craterRadius - 2; dx <= craterRadius + 2; dx++) {
            for (int dz = -craterRadius - 2; dz <= craterRadius + 2; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist < craterRadius - 2.5 || horizDist > craterRadius + 2) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int actualY = level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz);

                // Skip rim on steep terrain to avoid artificial bands on hillsides
                if (Math.abs(actualY - surfaceY) > 4) continue;

                BlockState rimBlock = getEjectaRim(random, variant);

                // Replace surface block with rim material
                BlockPos rimPos = new BlockPos(bx, actualY - 1, bz);
                if (chunkBox.isInside(rimPos)) {
                    BlockState rimState = level.getBlockState(rimPos);
                    if (!rimState.isAir() && !rimState.is(Blocks.BEDROCK)
                            && !rimState.getFluidState().is(FluidTags.WATER)) {
                        level.setBlock(rimPos, rimBlock, 2);
                    }
                }
            }
        }

        // Phase 6: Place fallen trees for biome flavor.
        // Only for biomes that naturally have trees. Logs lie flat on the crater
        // floor/slope as if knocked down by the impact.
        BlockState fallenLog = getFallenLogBlock(variant);
        if (fallenLog != null) {
            int treeCount = 1 + random.nextInt(3);
            for (int t = 0; t < treeCount; t++) {
                double angle = random.nextDouble() * Math.PI * 2;
                double dist = craterRadius * 0.3 + random.nextDouble() * craterRadius * 0.5;
                int tx = centerX + (int) Math.round(Math.cos(angle) * dist);
                int tz = centerZ + (int) Math.round(Math.sin(angle) * dist);

                double hd = Math.sqrt((tx - centerX) * (tx - centerX) + (tz - centerZ) * (tz - centerZ));
                int floorY = getCraterFloorY(hd, craterRadius);
                if (floorY >= surfaceY - 1) continue;

                // Pick a random horizontal direction for the log
                boolean alongX = random.nextBoolean();
                Direction.Axis axis = alongX ? Direction.Axis.X : Direction.Axis.Z;
                BlockState log = fallenLog.setValue(RotatedPillarBlock.AXIS, axis);

                int length = 3 + random.nextInt(5);
                for (int i = 0; i < length; i++) {
                    int lx = alongX ? tx + i : tx;
                    int lz = alongX ? tz : tz + i;

                    double ld = Math.sqrt((lx - centerX) * (lx - centerX) + (lz - centerZ) * (lz - centerZ));
                    if (ld > craterRadius) break;

                    int ly = getCraterFloorY(ld, craterRadius);
                    BlockPos logPos = new BlockPos(lx, ly, lz);
                    if (!chunkBox.isInside(logPos)) continue;

                    BlockState existing = level.getBlockState(logPos);
                    if (existing.isAir()) {
                        // Verify the block below is solid so the log does not float
                        BlockState below = level.getBlockState(logPos.below());
                        if (!below.isAir() && below.getFluidState().isEmpty()) {
                            level.setBlock(logPos, log, 2);
                        }
                    }
                }
            }
        }

        // Phase 7: Impact heat effects - fire and lava for a fresh-impact look.
        // Fire is placed on the crater floor within the inner portion of the bowl.
        // On magma blocks the fire stays permanently; on other blocks it dies out
        // naturally over time, creating a sense of recency on discovery.
        int fireRadius = (int) (craterRadius * 0.6);
        for (int dx = -fireRadius; dx <= fireRadius; dx++) {
            for (int dz = -fireRadius; dz <= fireRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > fireRadius) continue;
                // Sparse placement - roughly 15% of eligible positions get fire
                if (random.nextInt(100) >= 15) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int floorY = getCraterFloorY(horizDist, craterRadius);
                if (floorY >= surfaceY - 1) continue;

                BlockPos firePos = new BlockPos(bx, floorY, bz);
                if (!chunkBox.isInside(firePos)) continue;

                BlockState atPos = level.getBlockState(firePos);
                BlockState below = level.getBlockState(firePos.below());
                // Place fire only in air on top of a solid, non-fluid block
                if (atPos.isAir() && !below.isAir() && below.getFluidState().isEmpty()) {
                    level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 2);
                }
            }
        }

        // Small lava pools near the meteor core for extreme-heat visuals.
        // Place just outside the meteorite sphere so the lava ends up on the
        // crater floor next to the rock. The previous approach tried to place
        // inside the sphere radius where all positions are solid, so lava never
        // appeared. Scan upward from floor to find the first air block above
        // solid ground, which handles any debris or sphere surface blocks.
        int lavaCount = 2 + random.nextInt(3);
        for (int i = 0; i < lavaCount; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double dist = meteorRadius + 1.0 + random.nextDouble() * meteorRadius * 0.6;
            int lx = centerX + (int) Math.round(Math.cos(angle) * dist);
            int lz = centerZ + (int) Math.round(Math.sin(angle) * dist);

            double horizDist = Math.sqrt((lx - centerX) * (lx - centerX)
                    + (lz - centerZ) * (lz - centerZ));
            int floorY = getCraterFloorY(horizDist, craterRadius);
            if (floorY >= surfaceY - 1) continue;

            // Scan upward to find the first air block above solid ground.
            // This handles cases where debris or the sphere surface is present.
            BlockPos lavaPos = null;
            for (int scanY = floorY; scanY < floorY + 10; scanY++) {
                BlockPos candidate = new BlockPos(lx, scanY, lz);
                if (!chunkBox.isInside(candidate)) break;
                BlockState atScan = level.getBlockState(candidate);
                BlockState belowScan = level.getBlockState(candidate.below());
                if (atScan.isAir() && !belowScan.isAir()
                        && belowScan.getFluidState().isEmpty()) {
                    lavaPos = candidate;
                    break;
                }
            }
            if (lavaPos != null) {
                level.setBlock(lavaPos, Blocks.LAVA.defaultBlockState(), 2);
            }
        }
    }

    // A vine is supported if it has a solid (non-air, non-vine) block on any
    // horizontal side, or any non-air block directly above (vine chain or solid).
    // During a top-to-bottom scan, already-removed vines above read as air,
    // so unsupported chains collapse correctly in one pass.
    private boolean hasVineSupport(WorldGenLevel level, BlockPos pos) {
        for (BlockPos neighbor : new BlockPos[]{pos.north(), pos.east(), pos.south(), pos.west()}) {
            BlockState s = level.getBlockState(neighbor);
            if (!s.isAir() && !s.is(Blocks.VINE)) return true;
        }
        return !level.getBlockState(pos.above()).isAir();
    }

    // A leaf block is considered supported if it has at least one non-air,
    // non-leaf neighbor (i.e. a log, solid terrain, or similar). During a
    // top-to-bottom scan, previously removed leaves above read as air, so
    // unsupported clusters collapse correctly.
    private boolean hasLeafSupport(WorldGenLevel level, BlockPos pos) {
        for (BlockPos neighbor : new BlockPos[]{
                pos.above(), pos.below(),
                pos.north(), pos.east(), pos.south(), pos.west()}) {
            BlockState s = level.getBlockState(neighbor);
            if (!s.isAir() && !s.is(BlockTags.LEAVES)) return true;
        }
        return false;
    }

    // Returns true if the block is part of a tree or vegetation (leaves, logs,
    // vines, grass, flowers, mushroom blocks, etc.). Used by the ground-level
    // scanner and the outer vegetation clearing zone.
    private boolean isVegetation(BlockState state) {
        return state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)
                || state.is(Blocks.VINE) || state.is(Blocks.MOSS_CARPET)
                || state.is(Blocks.SHORT_GRASS) || state.is(Blocks.TALL_GRASS)
                || state.is(Blocks.FERN) || state.is(Blocks.LARGE_FERN)
                || state.is(Blocks.DEAD_BUSH) || state.is(Blocks.BAMBOO)
                || state.is(Blocks.SUGAR_CANE) || state.is(Blocks.COCOA)
                || state.is(Blocks.MUSHROOM_STEM)
                || state.is(Blocks.BROWN_MUSHROOM_BLOCK)
                || state.is(Blocks.RED_MUSHROOM_BLOCK)
                || state.is(Blocks.BEE_NEST)
                || state.is(Blocks.MANGROVE_ROOTS)
                || state.is(Blocks.HANGING_ROOTS);
    }

    // Finds the actual ground level at a position, skipping tree canopies and
    // other vegetation. Returns the Y of the topmost non-vegetation solid block
    // plus one (the first air block above ground).
    private int getGroundLevel(WorldGenLevel level, int x, int z, int rawSurfaceY) {
        for (int y = rawSurfaceY - 1; y > level.getMinY(); y--) {
            BlockState state = level.getBlockState(new BlockPos(x, y, z));
            if (state.isAir()) continue;
            if (isVegetation(state)) continue;
            return y + 1;
        }
        return rawSurfaceY;
    }

    private boolean meteorWontReplace(BlockState state) {
        return state.is(ModTags.Blocks.METEOR_WONT_REPLACE);
    }

    // Returns a random platinum ore variant: 70% regular, 30% deepslate.
    private BlockState getPlatinumOre(Random random) {
        if (random.nextInt(10) < 3) {
            return ModBlocks.DEEPSLATE_PLATINUM_ORE.defaultBlockState();
        }
        return ModBlocks.PLATINUM_ORE.defaultBlockState();
    }

    // Returns the appropriate log block for fallen trees, or null if the biome
    // should not have fallen trees (arid or treeless biomes).
    private BlockState getFallenLogBlock(BiomeVariant variant) {
        return switch (variant) {
            case DEFAULT -> Blocks.OAK_LOG.defaultBlockState();
            case DESERT -> null;
            case MANGROVE -> Blocks.MANGROVE_LOG.defaultBlockState();
            case SNOWY -> Blocks.SPRUCE_LOG.defaultBlockState();
            case JUNGLE -> Blocks.JUNGLE_LOG.defaultBlockState();
            case TAIGA -> Blocks.SPRUCE_LOG.defaultBlockState();
            case BADLANDS -> null;
            case SAVANNA -> Blocks.ACACIA_LOG.defaultBlockState();
            case CHERRY -> Blocks.CHERRY_LOG.defaultBlockState();
            case MUSHROOM -> null;
            case DARK_FOREST -> Blocks.DARK_OAK_LOG.defaultBlockState();
        };
    }

    // Weighted random palette for crater floors and walls, adapted to biome variant.
    private BlockState getCraterLiner(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 5) yield Blocks.SANDSTONE.defaultBlockState();
                if (roll < 7) yield Blocks.SMOOTH_SANDSTONE.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.TERRACOTTA.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.RED_SANDSTONE.defaultBlockState();
            }
            case MANGROVE -> {
                if (roll < 5) yield Blocks.MUD.defaultBlockState();
                if (roll < 7) yield Blocks.PACKED_MUD.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.CLAY.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState();
            }
            case SNOWY -> {
                if (roll < 4) yield Blocks.SNOW_BLOCK.defaultBlockState();
                if (roll < 6) yield Blocks.PACKED_ICE.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
            case JUNGLE -> {
                if (roll < 4) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 6) yield Blocks.MUD.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.ROOTED_DIRT.defaultBlockState();
            }
            case TAIGA -> {
                if (roll < 4) yield Blocks.PODZOL.defaultBlockState();
                if (roll < 6) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.defaultBlockState();
                if (roll < 5) yield Blocks.ORANGE_TERRACOTTA.defaultBlockState();
                if (roll < 7) yield Blocks.RED_SANDSTONE.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.YELLOW_TERRACOTTA.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.BROWN_TERRACOTTA.defaultBlockState();
            }
            case SAVANNA -> {
                if (roll < 4) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 6) yield Blocks.STONE.defaultBlockState();
                if (roll < 8) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
            case CHERRY -> {
                if (roll < 4) yield Blocks.STONE.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 7) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.CLAY.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
            case MUSHROOM -> {
                if (roll < 4) yield Blocks.MYCELIUM.defaultBlockState();
                if (roll < 6) yield Blocks.STONE.defaultBlockState();
                if (roll < 8) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
            case DARK_FOREST -> {
                if (roll < 4) yield Blocks.ROOTED_DIRT.defaultBlockState();
                if (roll < 6) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
            }
            default -> {
                if (roll < 5) yield Blocks.STONE.defaultBlockState();
                if (roll < 7) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.GRAVEL.defaultBlockState();
            }
        };
    }

    // Weighted random palette for the meteorite shell, adapted to biome variant.
    // Core blocks (METEORIC_IRON) are unaffected; only the outer shell changes.
    // Represents local geology fused into the meteor shell on impact.
    private BlockState getShellBlock(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 3) yield Blocks.SANDSTONE.defaultBlockState();
                if (roll < 5) yield Blocks.SMOOTH_SANDSTONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.TERRACOTTA.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case MANGROVE -> {
                if (roll < 3) yield Blocks.PACKED_MUD.defaultBlockState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.MUD.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case SNOWY -> {
                if (roll < 3) yield Blocks.PACKED_ICE.defaultBlockState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.BLUE_ICE.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case JUNGLE, DARK_FOREST -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 5) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.STONE.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case TAIGA -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 5) yield Blocks.STONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COBBLESTONE.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.defaultBlockState();
                if (roll < 5) yield Blocks.RED_SANDSTONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.ORANGE_TERRACOTTA.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case SAVANNA -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 5) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.STONE.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case CHERRY -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 5) yield Blocks.STONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.CLAY.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case MUSHROOM -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 5) yield Blocks.STONE.defaultBlockState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 9) yield Blocks.COAL_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COBBLESTONE.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            default -> {
                if (roll < 4) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
        };
    }

    // Weighted random palette for the ejecta rim around the crater, adapted to biome variant.
    private BlockState getEjectaRim(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 3) yield Blocks.TERRACOTTA.defaultBlockState();
                if (roll < 4) yield Blocks.RED_SANDSTONE.defaultBlockState();
                if (roll < 5) yield Blocks.SANDSTONE.defaultBlockState();
                if (roll < 6) yield Blocks.SMOOTH_SANDSTONE.defaultBlockState();
                if (roll < 8) yield Blocks.CUT_SANDSTONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case MANGROVE -> {
                if (roll < 3) yield Blocks.MUD.defaultBlockState();
                if (roll < 4) yield Blocks.PACKED_MUD.defaultBlockState();
                if (roll < 5) yield Blocks.CLAY.defaultBlockState();
                if (roll < 6) yield Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState();
                if (roll < 8) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case SNOWY -> {
                if (roll < 3) yield Blocks.SNOW_BLOCK.defaultBlockState();
                if (roll < 5) yield Blocks.PACKED_ICE.defaultBlockState();
                if (roll < 7) yield Blocks.STONE.defaultBlockState();
                if (roll < 8) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case JUNGLE -> {
                if (roll < 3) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 4) yield Blocks.MUD.defaultBlockState();
                if (roll < 5) yield Blocks.ROOTED_DIRT.defaultBlockState();
                if (roll < 6) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case TAIGA -> {
                if (roll < 3) yield Blocks.PODZOL.defaultBlockState();
                if (roll < 4) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 5) yield Blocks.STONE.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.GRAVEL.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.defaultBlockState();
                if (roll < 4) yield Blocks.ORANGE_TERRACOTTA.defaultBlockState();
                if (roll < 5) yield Blocks.YELLOW_TERRACOTTA.defaultBlockState();
                if (roll < 6) yield Blocks.RED_SANDSTONE.defaultBlockState();
                if (roll < 8) yield Blocks.BROWN_TERRACOTTA.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case SAVANNA -> {
                if (roll < 3) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 4) yield Blocks.GRAVEL.defaultBlockState();
                if (roll < 5) yield Blocks.DIRT.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case CHERRY -> {
                if (roll < 3) yield Blocks.STONE.defaultBlockState();
                if (roll < 4) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 5) yield Blocks.CLAY.defaultBlockState();
                if (roll < 6) yield Blocks.MOSS_BLOCK.defaultBlockState();
                if (roll < 8) yield Blocks.GRAVEL.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case MUSHROOM -> {
                if (roll < 3) yield Blocks.MYCELIUM.defaultBlockState();
                if (roll < 4) yield Blocks.STONE.defaultBlockState();
                if (roll < 5) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 6) yield Blocks.GRAVEL.defaultBlockState();
                if (roll < 8) yield Blocks.DIRT.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            case DARK_FOREST -> {
                if (roll < 3) yield Blocks.ROOTED_DIRT.defaultBlockState();
                if (roll < 4) yield Blocks.COARSE_DIRT.defaultBlockState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
            default -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.defaultBlockState();
                if (roll < 4) yield Blocks.GRAVEL.defaultBlockState();
                if (roll < 5) yield Blocks.DIRT.defaultBlockState();
                if (roll < 6) yield Blocks.COBBLESTONE.defaultBlockState();
                if (roll < 8) yield Blocks.STONE.defaultBlockState();
                if (roll < 10) yield Blocks.COAL_BLOCK.defaultBlockState();
                yield Blocks.OBSIDIAN.defaultBlockState();
            }
        };
    }
}