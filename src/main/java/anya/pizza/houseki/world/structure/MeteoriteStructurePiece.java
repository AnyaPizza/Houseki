package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

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

    private final int centerX;      // world X of the impact center
    private final int surfaceY;     // Y of the terrain surface at impact
    private final int centerZ;      // world Z of the impact center
    private final int meteorRadius; // radius of the meteorite sphere (7-11)
    private final int craterDepth;  // how deep the crater goes below surfaceY
    private final long seed;        // deterministic seed for reproducible random across chunk passes

    // Primary constructor, used when the structure is first created during worldgen
    public MeteoriteStructurePiece(int centerX, int surfaceY, int centerZ,
                                   int meteorRadius, int craterDepth, long seed) {
        super(ModStructures.METEORITE_PIECE_TYPE, 0,
                makeBounds(centerX, surfaceY, centerZ, meteorRadius, craterDepth));
        this.centerX = centerX;
        this.surfaceY = surfaceY;
        this.centerZ = centerZ;
        this.meteorRadius = meteorRadius;
        this.craterDepth = craterDepth;
        this.seed = seed;
    }

    // NBT constructor, used when loading from a saved game. orElse() provides fallback defaults.
    public MeteoriteStructurePiece(StructureContext context, NbtCompound nbt) {
        super(ModStructures.METEORITE_PIECE_TYPE, nbt);
        this.centerX = nbt.getInt("cx").orElse(0);
        this.surfaceY = nbt.getInt("sy").orElse(0);
        this.centerZ = nbt.getInt("cz").orElse(0);
        this.meteorRadius = nbt.getInt("mr").orElse(7);
        this.craterDepth = nbt.getInt("cd").orElse(10);
        this.seed = nbt.getLong("seed").orElse(0L);
    }

    // Computes the bounding box that fully encloses the structure.
    // Horizontal: meteorite + crater + small buffer for the ejecta rim.
    // Vertical: deep enough for the sphere at crater bottom, high enough to clear trees.
    private static BlockBox makeBounds(int cx, int sy, int cz, int r, int depth) {
        int extent = r + CRATER_EXTRA + 3;
        return new BlockBox(cx - extent, sy - depth - r - 2, cz - extent,
                cx + extent, sy + TREE_CLEAR_HEIGHT, cz + extent);
    }

    @Override
    protected void writeNbt(StructureContext context, NbtCompound nbt) {
        nbt.putInt("cx", centerX);
        nbt.putInt("sy", surfaceY);
        nbt.putInt("cz", centerZ);
        nbt.putInt("mr", meteorRadius);
        nbt.putInt("cd", craterDepth);
        nbt.putLong("seed", seed);
    }

    // Returns the Y of the crater floor at a given horizontal distance from center.
    // Uses a quadratic curve (depthFraction^2) to create a smooth bowl shape:
    // deepest at the center, gradually rising to surface level at the edges.
    private int getCraterFloorY(double horizDist, int craterRadius) {
        if (horizDist > craterRadius) return surfaceY;
        //double depthFraction = 1.0 - (horizDist / craterRadius);
        double distanceRatio = horizDist / craterRadius;
        double bowlCurve = Math.sqrt(1.0 - (distanceRatio * distanceRatio));
        //int localDepth = (int) (craterDepth * depthFraction * depthFraction);
        int localDepth = (int) (craterDepth * bowlCurve);
        return surfaceY - localDepth;
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
    public void generate(StructureWorldAccess world, StructureAccessor structureAccessor,
                         ChunkGenerator chunkGenerator, Random chunkRandom,
                         BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        // Deterministic random from stored seed - critical for cross-chunk consistency
        Random random = Random.create(this.seed);
        int craterRadius = meteorRadius + CRATER_EXTRA;
        int settleAmount = 5;
        int meteorCenterY = (surfaceY - craterDepth) + meteorRadius - settleAmount;
        BlockPos meteorCenter = new BlockPos(centerX, meteorCenterY, centerZ);

        // Bail out if the center is in water or lava - meteorites in oceans look bad
        BlockPos surfacePos = new BlockPos(centerX, surfaceY - 1, centerZ);
        BlockState surfaceState = world.getBlockState(surfacePos);
        if (surfaceState.getFluidState().isIn(FluidTags.WATER)
                || surfaceState.getFluidState().isIn(FluidTags.LAVA)) {
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
                int actualSurfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz);
                int topClearY = Math.max(actualSurfaceY, surfaceY) + TREE_CLEAR_HEIGHT;
                int craterFloorY = getCraterFloorY(horizDist, craterRadius);

                boolean insideCrater = horizDist <= craterRadius;

                if (insideCrater) {
                    // Clear everything from high above (trees!) down to crater floor
                    for (int y = topClearY; y >= craterFloorY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.contains(pos)) continue;
                        BlockState state = world.getBlockState(pos);
                        if (state.isOf(Blocks.BEDROCK)) continue;
                        if (!state.isAir()) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                        }
                    }

                    // Line the crater floor (1-2 blocks deep) with scorched material
                    for (int depth = 0; depth < 2; depth++) {
                        BlockPos floorPos = new BlockPos(bx, craterFloorY - 1 - depth, bz);
                        if (!chunkBox.contains(floorPos)) continue;
                        BlockState existing = world.getBlockState(floorPos);
                        if (existing.isOf(Blocks.BEDROCK)) continue;
                        if (!existing.isAir()) {
                            // Replace dirt, sand, grass, etc. with crater material
                            if (!meteorWontReplace(existing)) {
                                world.setBlockState(floorPos, getCraterLiner(random), 2);
                            }
                        }
                    }
                } else if (horizDist <= craterRadius + 2) {
                    // Near-rim: clear trees and vegetation above terrain
                    for (int y = topClearY; y > actualSurfaceY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.contains(pos)) continue;
                        BlockState state = world.getBlockState(pos);
                        if (!state.isAir() && !state.isOf(Blocks.BEDROCK)) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                        }
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
                    if (!chunkBox.contains(pos)) continue;
                    BlockState state = world.getBlockState(pos);
                    if (state.isAir() || state.isOf(Blocks.BEDROCK)) continue;

                    // Check if this block is exposed (has air neighbor)
                    if (isExposedToAir(world, pos, chunkBox)) {
                        if (!meteorWontReplace(state)) {
                            world.setBlockState(pos, getCraterLiner(random), 2);
                        }
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
                    BlockState shellBlock = getShellBlock(random);

                    double noisyDist = dist + noise;
                    if (noisyDist > meteorRadius) continue;

                    BlockPos pos = meteorCenter.add(dx, dy, dz);
                    if (!chunkBox.contains(pos)) continue;
                    BlockState existing = world.getBlockState(pos);
                    if (existing.isOf(Blocks.BEDROCK)) continue;

                    if (noisyDist <= coreRadius) {
                        world.setBlockState(pos, ModBlocks.METEORIC_IRON.getDefaultState(), 2);
                    } else {
                        world.setBlockState(pos, shellBlock, 2);
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
            if (!chunkBox.contains(debrisPos)) continue;
            if (world.getBlockState(debrisPos).isAir()) {
                world.setBlockState(debrisPos, ModBlocks.METEORIC_IRON.getDefaultState(), 2);
            }
        }

        // Phase 5: Scorched ejecta rim at actual terrain height
        for (int dx = -craterRadius - 2; dx <= craterRadius + 2; dx++) {
            for (int dz = -craterRadius - 2; dz <= craterRadius + 2; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist < craterRadius - 2.5 || horizDist > craterRadius + 2) continue;

                BlockState rimBlock = getEjectaRim(random);
                int rimRoll = random.nextInt(3);
                BlockState raisedBlock = getEjectaRim(random);

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int actualY = world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz);

                // Replace surface block with rim material
                BlockPos rimPos = new BlockPos(bx, actualY - 1, bz);
                if (chunkBox.contains(rimPos)) {
                    BlockState rimState = world.getBlockState(rimPos);
                    if (!rimState.isAir() && !rimState.isOf(Blocks.BEDROCK)
                            && !rimState.getFluidState().isIn(FluidTags.WATER)) {
                        world.setBlockState(rimPos, rimBlock, 2);
                    }
                }

                // Raised rim blocks
                if (rimRoll == 0 && horizDist >= craterRadius - 1 && horizDist <= craterRadius + 0.5) {
                    BlockPos aboveRim = new BlockPos(bx, actualY, bz);
                    if (chunkBox.contains(aboveRim) && world.getBlockState(aboveRim).isAir()) {
                        world.setBlockState(aboveRim, raisedBlock, 2);
                    }
                }
            }
        }
    }

    private boolean meteorWontReplace(BlockState state) {
        return state.isIn(ModTags.Blocks.METEOR_WONT_REPLACE);
    }

    // Checks all 6 neighbors. If any is air (and within the chunk box), the block is "exposed"
    // and should be lined with crater material in Phase 2.
    private boolean isExposedToAir(StructureWorldAccess world, BlockPos pos, BlockBox box) {
        for (int i = 0; i < 6; i++) {
            BlockPos neighbor = switch (i) {
                case 0 -> pos.up();
                case 1 -> pos.down();
                case 2 -> pos.north();
                case 3 -> pos.south();
                case 4 -> pos.east();
                default -> pos.west();
            };
            if (box.contains(neighbor) && world.getBlockState(neighbor).isAir()) {
                return true;
            }
        }
        return false;
    }

    // Weighted random palette for crater floors and walls
    private BlockState getCraterLiner(Random random) {
        int roll = random.nextInt(10);
        if (roll < 6) return Blocks.STONE.getDefaultState();
        if (roll < 7) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 8) return Blocks.MAGMA_BLOCK.getDefaultState();
        if (roll < 9) return Blocks.COBBLED_DEEPSLATE.getDefaultState();
        if (roll < 10) return Blocks.COAL_BLOCK.getDefaultState();
        return Blocks.GRAVEL.getDefaultState();
    }

    // Weighted random palette for the meteorite shell
    private BlockState getShellBlock(Random random) {
        int roll = random.nextInt(10);
        if (roll < 4) return Blocks.COBBLED_DEEPSLATE.getDefaultState();
        if (roll < 6) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 8) return Blocks.MAGMA_BLOCK.getDefaultState();
        if (roll < 10) return Blocks.COAL_BLOCK.getDefaultState();
        return Blocks.OBSIDIAN.getDefaultState();
    }

    private BlockState getEjectaRim(Random random) {
        int roll = random.nextInt(10);
        if (roll < 3) return Blocks.COBBLED_DEEPSLATE.getDefaultState();
        if (roll < 4) return Blocks.GRAVEL.getDefaultState();
        if (roll < 5) return Blocks.DIRT.getDefaultState();
        if (roll < 6) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 8) return Blocks.STONE.getDefaultState();
        if (roll < 10) return Blocks.COAL_BLOCK.getDefaultState();
        return Blocks.OBSIDIAN.getDefaultState();
    }
}