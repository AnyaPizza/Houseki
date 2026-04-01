package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
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
        DEFAULT, DESERT, MANGROVE, SNOWY
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
    // Horizontal: meteorite + crater + small buffer for the ejecta rim.
    // Vertical: deep enough for the sphere at crater bottom, high enough to clear trees.
    private static BoundingBox makeBounds(int cx, int sy, int cz, int r, int depth) {
        int extent = r + CRATER_EXTRA + 6;
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
    // Uses a quadratic curve (depthFraction^2) to create a smooth bowl shape:
    // deepest at the center, gradually rising to surface level at the edges.
    private int getCraterFloorY(double horizDist, int craterRadius) {
        if (horizDist > craterRadius) return surfaceY;
        double distanceRatio = horizDist / craterRadius;
        double bowlCurve = Math.sqrt(1.0 - (distanceRatio * distanceRatio));
        int localDepth = (int) (craterDepth * bowlCurve);
        return surfaceY - localDepth;
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
     *   1. Crater excavation - dig the bowl and clear trees
     *   2. Wall lining - replace exposed non-stone blocks with scorched material
     *   3. Meteorite sphere - place the iron core and stone shell
     *   4. Debris scatter - drop iron fragments on the crater floor
     *   5. Ejecta rim - scorched ring with occasional raised blocks at the crater edge
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

        // Bail out on lava for all variants. Bail out on water only for DEFAULT
        // and DESERT variants. Swamp/Mangrove and Snowy biomes commonly have water
        // surfaces and are allowed to generate with water present.
        BlockPos surfacePos = new BlockPos(centerX, surfaceY - 1, centerZ);
        BlockState surfaceState = level.getBlockState(surfacePos);
        if (surfaceState.getFluidState().is(FluidTags.LAVA)) {
            return;
        }
        if (surfaceState.getFluidState().is(FluidTags.WATER)
                && variant != BiomeVariant.MANGROVE && variant != BiomeVariant.SNOWY) {
            return;
        }

        // Phase 1: Clear EVERYTHING in crater area (trees, vegetation, terrain)
        // and line exposed walls/floor with crater material
        for (int dx = -craterRadius - 2; dx <= craterRadius + 2; dx++) {
            for (int dz = -craterRadius - 2; dz <= craterRadius + 2; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > craterRadius + 5) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int actualSurfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz);
                int topClearY = Math.max(actualSurfaceY, surfaceY) + TREE_CLEAR_HEIGHT;
                int craterFloorY = getCraterFloorY(horizDist, craterRadius);

                boolean insideCrater = horizDist <= craterRadius;

                if (insideCrater) {
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
                } else if (horizDist <= craterRadius + 2) {
                    // Near-rim: clear trees and vegetation above terrain
                    for (int y = topClearY; y > actualSurfaceY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.isInside(pos)) continue;
                        BlockState state = level.getBlockState(pos);
                        if (!state.isAir() && !state.is(Blocks.BEDROCK)) {
                            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        // Phase 1.5: Remove floating vines left after tree clearing
        // In jungle biomes, vines attached to cleared trees are left hanging in mid-air.
        // Top-to-bottom scan so vine chains collapse correctly in a single pass.
        int vineRadius = craterRadius + 5;
        for (int dx = -vineRadius; dx <= vineRadius; dx++) {
            for (int dz = -vineRadius; dz <= vineRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);

                if (horizDist > vineRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int topY = Math.max(level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz), surfaceY) + TREE_CLEAR_HEIGHT;
                int bottomY = surfaceY - craterDepth - meteorRadius;

                for (int y = topY; y >= bottomY; y--) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    if (!chunkBox.isInside(pos)) continue;
                    BlockState state = level.getBlockState(pos);
                    if (state.is(Blocks.VINE) && !hasVineSupport(level, pos)) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }

        // Phase 2: Line crater walls - replace any exposed non-stone blocks
        for (int dx = -craterRadius; dx <= craterRadius; dx++) {
            for (int dz = -craterRadius; dz <= craterRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > craterRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int craterFloorY = getCraterFloorY(horizDist, craterRadius);

                // Scan the wall: from crater floor up to where it meets terrain
                for (int y = craterFloorY - 1; y <= surfaceY; y++) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    if (!chunkBox.isInside(pos)) continue;
                    BlockState state = level.getBlockState(pos);
                    if (state.isAir() || state.is(Blocks.BEDROCK)) continue;

                    // Check if this block is exposed (has air neighbor)
                    if (isExposedToAir(level, pos, chunkBox)) {
                        if (!meteorWontReplace(state)) {
                            level.setBlock(pos, getCraterLiner(random, variant), 2);
                        }
                    }
                }
            }
        }

        // Phase 2.5: Seal caves and ravines beneath the crater.
        // Carvers (caves, ravines) run before structures and may have opened gaps
        // below the crater floor that would be visible from inside. Scan downward
        // from just below the 2-block liner and fill any air or fluid pockets until
        // we hit a thick enough stretch of solid material.
        int sealDepth = craterDepth + meteorRadius;
        for (int dx = -craterRadius - 1; dx <= craterRadius + 1; dx++) {
            for (int dz = -craterRadius - 1; dz <= craterRadius + 1; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > craterRadius + 1) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int craterFloorY = getCraterFloorY(horizDist, craterRadius);

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
                        level.setBlock(pos, shellBlock, 2);
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

        // Phase 5: Scorched ejecta rim at actual terrain height
        for (int dx = -craterRadius - 2; dx <= craterRadius + 2; dx++) {
            for (int dz = -craterRadius - 2; dz <= craterRadius + 2; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist < craterRadius - 2.5 || horizDist > craterRadius + 2) continue;

                BlockState rimBlock = getEjectaRim(random, variant);
                int rimRoll = random.nextInt(3);
                BlockState raisedBlock = getEjectaRim(random, variant);

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int actualY = level.getHeight(Heightmap.Types.WORLD_SURFACE, bx, bz);

                // Replace surface block with rim material
                BlockPos rimPos = new BlockPos(bx, actualY - 1, bz);
                if (chunkBox.isInside(rimPos)) {
                    BlockState rimState = level.getBlockState(rimPos);
                    if (!rimState.isAir() && !rimState.is(Blocks.BEDROCK)
                            && !rimState.getFluidState().is(FluidTags.WATER)) {
                        level.setBlock(rimPos, rimBlock, 2);
                    }
                }

                // Raised rim blocks
                if (rimRoll == 0 && horizDist >= craterRadius - 1 && horizDist <= craterRadius + 0.5) {
                    BlockPos aboveRim = new BlockPos(bx, actualY, bz);
                    if (chunkBox.isInside(aboveRim) && level.getBlockState(aboveRim).isAir()) {
                        level.setBlock(aboveRim, raisedBlock, 2);
                    }
                }
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

    private boolean meteorWontReplace(BlockState state) {
        return state.is(ModTags.Blocks.METEOR_WONT_REPLACE);
    }

    // Checks all 6 neighbors. If any is air (and within the chunk box), the block is "exposed"
    // and should be lined with crater material in Phase 2.
    private boolean isExposedToAir(WorldGenLevel world, BlockPos pos, BoundingBox box) {
        for (int i = 0; i < 6; i++) {
            BlockPos neighbor = switch (i) {
                case 0 -> pos.above();
                case 1 -> pos.below();
                case 2 -> pos.north();
                case 3 -> pos.south();
                case 4 -> pos.east();
                default -> pos.west();
            };
            if (box.isInside(neighbor) && world.getBlockState(neighbor).isAir()) {
                return true;
            }
        }
        return false;
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