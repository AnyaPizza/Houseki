package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.block.ModBlocks;
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

public class MeteoriteStructurePiece extends StructurePiece {
    public static final int MIN_RADIUS = 7;
    public static final int MAX_RADIUS = 11;
    private static final int CRATER_EXTRA = 6;
    private static final int TREE_CLEAR_HEIGHT = 20;

    private final int centerX;
    private final int surfaceY;
    private final int centerZ;
    private final int meteorRadius;
    private final int craterDepth;
    private final long seed;

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

    public MeteoriteStructurePiece(StructureContext context, NbtCompound nbt) {
        super(ModStructures.METEORITE_PIECE_TYPE, nbt);
        this.centerX = nbt.getInt("cx").orElse(0);
        this.surfaceY = nbt.getInt("sy").orElse(0);
        this.centerZ = nbt.getInt("cz").orElse(0);
        this.meteorRadius = nbt.getInt("mr").orElse(7);
        this.craterDepth = nbt.getInt("cd").orElse(10);
        this.seed = nbt.getLong("seed").orElse(0L);
    }

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

    private int getCraterFloorY(double horizDist, int craterRadius) {
        if (horizDist > craterRadius) return surfaceY;
        double depthFraction = 1.0 - (horizDist / craterRadius);
        int localDepth = (int) (craterDepth * depthFraction * depthFraction);
        return surfaceY - localDepth;
    }

    @Override
    public void generate(StructureWorldAccess world, StructureAccessor structureAccessor,
                         ChunkGenerator chunkGenerator, Random chunkRandom,
                         BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        Random random = Random.create(this.seed);
        int craterRadius = meteorRadius + CRATER_EXTRA;
        int meteorCenterY = surfaceY - craterDepth;
        BlockPos meteorCenter = new BlockPos(centerX, meteorCenterY, centerZ);

        // Reject liquid locations
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
                if (horizDist > craterRadius + 2) continue;

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
                            if (!isStoneType(existing)) {
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
                        if (!isStoneType(state)) {
                            world.setBlockState(pos, getCraterLiner(random), 2);
                        }
                    }
                }
            }
        }

        // Phase 3: Place meteorite sphere
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

                BlockState rimBlock = getShellBlock(random);
                int rimRoll = random.nextInt(3);
                BlockState raisedBlock = getShellBlock(random);

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

    private boolean isStoneType(BlockState state) {
        return state.isOf(Blocks.STONE) || state.isOf(Blocks.COBBLESTONE)
                || state.isOf(Blocks.DEEPSLATE) || state.isOf(Blocks.COBBLED_DEEPSLATE)
                || state.isOf(Blocks.GRAVEL) || state.isOf(Blocks.ANDESITE)
                || state.isOf(Blocks.DIORITE) || state.isOf(Blocks.GRANITE)
                || state.isOf(Blocks.TUFF);
    }

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

    private BlockState getCraterLiner(Random random) {
        int roll = random.nextInt(10);
        if (roll < 5) return Blocks.STONE.getDefaultState();
        if (roll < 7) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 9) return Blocks.GRAVEL.getDefaultState();
        return Blocks.COBBLED_DEEPSLATE.getDefaultState();
    }

    private BlockState getShellBlock(Random random) {
        int roll = random.nextInt(10);
        if (roll < 4) return Blocks.STONE.getDefaultState();
        if (roll < 6) return Blocks.COBBLESTONE.getDefaultState();
        if (roll < 8) return Blocks.GRAVEL.getDefaultState();
        return Blocks.DEEPSLATE.getDefaultState();
    }
}
