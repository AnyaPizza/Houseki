package anya.pizza.houseki.world.structure;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class MeteoriteStructurePiece extends StructurePiece {
    public static final int MIN_RADIUS = 5;
    public static final int MAX_RADIUS = 10;
    private static final int CRATER_EXTRA = 25;
    private static final int TREE_CLEAR_HEIGHT = 60;

    private enum BiomeVariant {
        DEFAULT, DESERT, MANGROVE, SNOWY, JUNGLE, TAIGA, BADLANDS, SAVANNA, CHERRY, MUSHROOM, DARK_FOREST
    }

    private final int centerX;
    private final int surfaceY;
    private final int centerZ;
    private final int meteorRadius;
    private final int craterDepth;
    private final long seed;
    private final int biomeVariantId;

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

    public MeteoriteStructurePiece(StructureContext context, NbtCompound nbt) {
        super(ModStructures.METEORITE_PIECE_TYPE, nbt);
        this.centerX = nbt.getInt("cx").orElse(0);
        this.surfaceY = nbt.getInt("sy").orElse(0);
        this.centerZ = nbt.getInt("cz").orElse(0);
        this.meteorRadius = nbt.getInt("mr").orElse(7);
        this.craterDepth = nbt.getInt("cd").orElse(10);
        this.seed = nbt.getLong("seed").orElse(0L);
        this.biomeVariantId = nbt.getInt("bv").orElse(0);
    }

    private static BlockBox makeBounds(int cx, int sy, int cz, int r, int depth) {
        int extent = r + CRATER_EXTRA + 17;
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
        nbt.putInt("bv", biomeVariantId);
    }

    private int getCraterFloorY(double horizDist, int craterRadius, int localTerrainY) {
        if (horizDist > craterRadius) return localTerrainY;
        double ratio = horizDist / craterRadius;
        double bowlCurve = Math.cos(Math.PI * 0.5 * ratio);
        bowlCurve = bowlCurve * bowlCurve;
        int centerFloor = surfaceY - craterDepth;
        return localTerrainY + (int) (bowlCurve * (centerFloor - localTerrainY));
    }

    private int getCraterFloorY(double horizDist, int craterRadius) {
        return getCraterFloorY(horizDist, craterRadius, surfaceY);
    }

    public static int resolveBiomeVariantId(RegistryEntry<Biome> biome) {
        if (biome.matchesKey(BiomeKeys.DESERT)) return BiomeVariant.DESERT.ordinal();
        if (biome.matchesKey(BiomeKeys.MANGROVE_SWAMP) || biome.matchesKey(BiomeKeys.SWAMP))
            return BiomeVariant.MANGROVE.ordinal();
        if (biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.matchesKey(BiomeKeys.SNOWY_TAIGA)
                || biome.matchesKey(BiomeKeys.SNOWY_BEACH) || biome.matchesKey(BiomeKeys.SNOWY_SLOPES)
                || biome.matchesKey(BiomeKeys.FROZEN_PEAKS) || biome.matchesKey(BiomeKeys.ICE_SPIKES)
                || biome.matchesKey(BiomeKeys.GROVE))
            return BiomeVariant.SNOWY.ordinal();
        if (biome.matchesKey(BiomeKeys.JUNGLE) || biome.matchesKey(BiomeKeys.SPARSE_JUNGLE)
                || biome.matchesKey(BiomeKeys.BAMBOO_JUNGLE))
            return BiomeVariant.JUNGLE.ordinal();
        if (biome.matchesKey(BiomeKeys.TAIGA) || biome.matchesKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
                || biome.matchesKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA))
            return BiomeVariant.TAIGA.ordinal();
        if (biome.matchesKey(BiomeKeys.BADLANDS) || biome.matchesKey(BiomeKeys.ERODED_BADLANDS)
                || biome.matchesKey(BiomeKeys.WOODED_BADLANDS))
            return BiomeVariant.BADLANDS.ordinal();
        if (biome.matchesKey(BiomeKeys.SAVANNA) || biome.matchesKey(BiomeKeys.SAVANNA_PLATEAU)
                || biome.matchesKey(BiomeKeys.WINDSWEPT_SAVANNA))
            return BiomeVariant.SAVANNA.ordinal();
        if (biome.matchesKey(BiomeKeys.CHERRY_GROVE)) return BiomeVariant.CHERRY.ordinal();
        if (biome.matchesKey(BiomeKeys.MUSHROOM_FIELDS)) return BiomeVariant.MUSHROOM.ordinal();
        if (biome.matchesKey(BiomeKeys.DARK_FOREST)) return BiomeVariant.DARK_FOREST.ordinal();
        return BiomeVariant.DEFAULT.ordinal();
    }

    private BiomeVariant getVariant() {
        BiomeVariant[] values = BiomeVariant.values();
        if (biomeVariantId >= 0 && biomeVariantId < values.length) return values[biomeVariantId];
        return BiomeVariant.DEFAULT;
    }

    @Override
    public void generate(StructureWorldAccess world, StructureAccessor structureAccessor,
                         ChunkGenerator chunkGenerator, Random chunkRandom,
                         BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        Random random = Random.create(this.seed);
        int craterRadius = meteorRadius + CRATER_EXTRA;
        int settleAmount = 5;
        int meteorCenterY = (surfaceY - craterDepth) + meteorRadius - settleAmount;
        BlockPos meteorCenter = new BlockPos(centerX, meteorCenterY, centerZ);
        BiomeVariant variant = getVariant();

        // Water/lava safety check at 5 points
        int waterCheckDist = craterRadius / 2;
        int[][] waterChecks = {{0, 0}, {waterCheckDist, 0}, {-waterCheckDist, 0},
                {0, waterCheckDist}, {0, -waterCheckDist}};
        for (int[] wc : waterChecks) {
            int wx = centerX + wc[0];
            int wz = centerZ + wc[1];
            int wy = world.getTopY(Heightmap.Type.WORLD_SURFACE, wx, wz) - 1;
            BlockState wcState = world.getBlockState(new BlockPos(wx, wy, wz));
            if (wcState.getFluidState().isIn(FluidTags.LAVA)) return;
            if (wcState.getFluidState().isIn(FluidTags.WATER)) return;
        }

        // Phase 1: Terrain-adaptive crater excavation and vegetation clearing
        int vegClearRadius = craterRadius + 12;
        for (int dx = -vegClearRadius; dx <= vegClearRadius; dx++) {
            for (int dz = -vegClearRadius; dz <= vegClearRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > vegClearRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int rawSurfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz);
                int groundY = getGroundLevel(world, bx, bz, rawSurfaceY);
                int topClearY = Math.max(rawSurfaceY, surfaceY) + TREE_CLEAR_HEIGHT;
                boolean insideCrater = horizDist <= craterRadius;

                if (insideCrater) {
                    int craterFloorY = getCraterFloorY(horizDist, craterRadius, groundY);

                    for (int y = topClearY; y >= craterFloorY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.contains(pos)) continue;
                        BlockState state = world.getBlockState(pos);
                        if (state.isOf(Blocks.BEDROCK)) continue;
                        if (!state.isAir()) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                        }
                    }

                    for (int depth = 0; depth < 2; depth++) {
                        BlockPos floorPos = new BlockPos(bx, craterFloorY - 1 - depth, bz);
                        if (!chunkBox.contains(floorPos)) continue;
                        BlockState existing = world.getBlockState(floorPos);
                        if (existing.isOf(Blocks.BEDROCK)) continue;
                        if (!existing.isAir() && !meteorWontReplace(existing)) {
                            world.setBlockState(floorPos, getCraterLiner(random, variant), 2);
                        }
                    }
                } else {
                    for (int y = topClearY; y >= groundY; y--) {
                        BlockPos pos = new BlockPos(bx, y, bz);
                        if (!chunkBox.contains(pos)) continue;
                        BlockState state = world.getBlockState(pos);
                        if (!state.isAir() && !state.isOf(Blocks.BEDROCK) && isVegetation(state)) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        // Phase 1.5: Float cleanup - remove all floating vegetation
        int floatClearRadius = craterRadius + 15;
        for (int dx = -floatClearRadius; dx <= floatClearRadius; dx++) {
            for (int dz = -floatClearRadius; dz <= floatClearRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > floatClearRadius) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int topY = Math.max(world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz), surfaceY) + TREE_CLEAR_HEIGHT;
                int bottomY = surfaceY - craterDepth - meteorRadius;

                for (int y = topY; y >= bottomY; y--) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    BlockState state = world.getBlockState(pos);
                    if (state.isOf(Blocks.VINE) && !hasVineSupport(world, pos)) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                        continue;
                    }
                    if (isVegetation(state)) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                    }
                }
            }
        }

        // Phase 2: Cave sealing
        int sealDepth = craterDepth + meteorRadius;
        for (int dx = -craterRadius - 1; dx <= craterRadius + 1; dx++) {
            for (int dz = -craterRadius - 1; dz <= craterRadius + 1; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > craterRadius + 1) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int rawSY = world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz);
                int groundY = getGroundLevel(world, bx, bz, rawSY);

                if (surfaceY - groundY > 6) continue;

                int craterFloorY = getCraterFloorY(horizDist, craterRadius, groundY);

                int solidStreak = 0;
                for (int y = craterFloorY - 3; y >= craterFloorY - sealDepth; y--) {
                    BlockPos pos = new BlockPos(bx, y, bz);
                    if (!chunkBox.contains(pos)) continue;
                    BlockState state = world.getBlockState(pos);
                    if (state.isOf(Blocks.BEDROCK)) break;
                    if (state.isAir() || !state.getFluidState().isEmpty()) {
                        world.setBlockState(pos, getCraterLiner(random, variant), 2);
                        solidStreak = 0;
                    } else {
                        solidStreak++;
                        if (solidStreak >= 5) break;
                    }
                }
            }
        }

        // Phase 3: Meteorite sphere
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

        // Phase 4: Debris scatter
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

        // Phase 5: Ejecta rim
        for (int dx = -craterRadius - 2; dx <= craterRadius + 2; dx++) {
            for (int dz = -craterRadius - 2; dz <= craterRadius + 2; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist < craterRadius - 2.5 || horizDist > craterRadius + 2) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int actualY = world.getTopY(Heightmap.Type.WORLD_SURFACE, bx, bz);

                if (Math.abs(actualY - surfaceY) > 4) continue;

                BlockState rimBlock = getEjectaRim(random, variant);

                BlockPos rimPos = new BlockPos(bx, actualY - 1, bz);
                if (chunkBox.contains(rimPos)) {
                    BlockState rimState = world.getBlockState(rimPos);
                    if (!rimState.isAir() && !rimState.isOf(Blocks.BEDROCK)
                            && !rimState.getFluidState().isIn(FluidTags.WATER)) {
                        world.setBlockState(rimPos, rimBlock, 2);
                    }
                }
            }
        }

        // Phase 6: Fallen trees
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

                boolean alongX = random.nextBoolean();
                Direction.Axis axis = alongX ? Direction.Axis.X : Direction.Axis.Z;
                BlockState log = fallenLog.with(PillarBlock.AXIS, axis);

                int length = 3 + random.nextInt(5);
                for (int i = 0; i < length; i++) {
                    int lx = alongX ? tx + i : tx;
                    int lz = alongX ? tz : tz + i;

                    double ld = Math.sqrt((lx - centerX) * (lx - centerX) + (lz - centerZ) * (lz - centerZ));
                    if (ld > craterRadius) break;

                    int ly = getCraterFloorY(ld, craterRadius);
                    BlockPos logPos = new BlockPos(lx, ly, lz);
                    if (!chunkBox.contains(logPos)) continue;

                    BlockState existing = world.getBlockState(logPos);
                    if (existing.isAir()) {
                        BlockState below = world.getBlockState(logPos.down());
                        if (!below.isAir() && below.getFluidState().isEmpty()) {
                            world.setBlockState(logPos, log, 2);
                        }
                    }
                }
            }
        }

        // Phase 7: Fire and lava pools
        int fireRadius = (int) (craterRadius * 0.6);
        for (int dx = -fireRadius; dx <= fireRadius; dx++) {
            for (int dz = -fireRadius; dz <= fireRadius; dz++) {
                double horizDist = Math.sqrt(dx * dx + dz * dz);
                if (horizDist > fireRadius) continue;
                if (random.nextInt(100) >= 15) continue;

                int bx = centerX + dx;
                int bz = centerZ + dz;
                int floorY = getCraterFloorY(horizDist, craterRadius);
                if (floorY >= surfaceY - 1) continue;

                BlockPos firePos = new BlockPos(bx, floorY, bz);
                if (!chunkBox.contains(firePos)) continue;

                BlockState atPos = world.getBlockState(firePos);
                BlockState below = world.getBlockState(firePos.down());
                if (atPos.isAir() && !below.isAir() && below.getFluidState().isEmpty()) {
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState(), 2);
                }
            }
        }

        // Small lava pools near the meteor core
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

            BlockPos lavaPos = null;
            for (int scanY = floorY; scanY < floorY + 10; scanY++) {
                BlockPos candidate = new BlockPos(lx, scanY, lz);
                if (!chunkBox.contains(candidate)) break;
                BlockState atScan = world.getBlockState(candidate);
                BlockState belowScan = world.getBlockState(candidate.down());
                if (atScan.isAir() && !belowScan.isAir()
                        && belowScan.getFluidState().isEmpty()) {
                    lavaPos = candidate;
                    break;
                }
            }
            if (lavaPos != null) {
                world.setBlockState(lavaPos, Blocks.LAVA.getDefaultState(), 2);
            }
        }
    }

    private boolean hasVineSupport(StructureWorldAccess world, BlockPos pos) {
        for (BlockPos neighbor : new BlockPos[]{pos.north(), pos.east(), pos.south(), pos.west()}) {
            BlockState s = world.getBlockState(neighbor);
            if (!s.isAir() && !s.isOf(Blocks.VINE)) return true;
        }
        return !world.getBlockState(pos.up()).isAir();
    }

    private boolean isVegetation(BlockState state) {
        return state.isIn(BlockTags.LEAVES) || state.isIn(BlockTags.LOGS)
                || state.isOf(Blocks.VINE) || state.isOf(Blocks.MOSS_CARPET)
                || state.isOf(Blocks.SHORT_GRASS) || state.isOf(Blocks.TALL_GRASS)
                || state.isOf(Blocks.FERN) || state.isOf(Blocks.LARGE_FERN)
                || state.isOf(Blocks.DEAD_BUSH) || state.isOf(Blocks.BAMBOO)
                || state.isOf(Blocks.SUGAR_CANE) || state.isOf(Blocks.COCOA)
                || state.isOf(Blocks.MUSHROOM_STEM)
                || state.isOf(Blocks.BROWN_MUSHROOM_BLOCK)
                || state.isOf(Blocks.RED_MUSHROOM_BLOCK)
                || state.isOf(Blocks.BEE_NEST)
                || state.isOf(Blocks.MANGROVE_ROOTS)
                || state.isOf(Blocks.HANGING_ROOTS);
    }

    private int getGroundLevel(StructureWorldAccess world, int x, int z, int rawSurfaceY) {
        for (int y = rawSurfaceY - 1; y > world.getBottomY(); y--) {
            BlockState state = world.getBlockState(new BlockPos(x, y, z));
            if (state.isAir()) continue;
            if (isVegetation(state)) continue;
            return y + 1;
        }
        return rawSurfaceY;
    }

    private boolean meteorWontReplace(BlockState state) {
        return state.isIn(ModTags.Blocks.METEOR_WONT_REPLACE);
    }

    private BlockState getFallenLogBlock(BiomeVariant variant) {
        return switch (variant) {
            case DEFAULT -> Blocks.OAK_LOG.getDefaultState();
            case DESERT -> null;
            case MANGROVE -> Blocks.MANGROVE_LOG.getDefaultState();
            case SNOWY -> Blocks.SPRUCE_LOG.getDefaultState();
            case JUNGLE -> Blocks.JUNGLE_LOG.getDefaultState();
            case TAIGA -> Blocks.SPRUCE_LOG.getDefaultState();
            case BADLANDS -> null;
            case SAVANNA -> Blocks.ACACIA_LOG.getDefaultState();
            case CHERRY -> Blocks.CHERRY_LOG.getDefaultState();
            case MUSHROOM -> null;
            case DARK_FOREST -> Blocks.DARK_OAK_LOG.getDefaultState();
        };
    }

    private BlockState getCraterLiner(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 5) yield Blocks.SANDSTONE.getDefaultState();
                if (roll < 7) yield Blocks.SMOOTH_SANDSTONE.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.TERRACOTTA.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.RED_SANDSTONE.getDefaultState();
            }
            case MANGROVE -> {
                if (roll < 5) yield Blocks.MUD.getDefaultState();
                if (roll < 7) yield Blocks.PACKED_MUD.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.CLAY.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.MUDDY_MANGROVE_ROOTS.getDefaultState();
            }
            case SNOWY -> {
                if (roll < 4) yield Blocks.SNOW_BLOCK.getDefaultState();
                if (roll < 6) yield Blocks.PACKED_ICE.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
            case JUNGLE -> {
                if (roll < 4) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 6) yield Blocks.MUD.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.ROOTED_DIRT.getDefaultState();
            }
            case TAIGA -> {
                if (roll < 4) yield Blocks.PODZOL.getDefaultState();
                if (roll < 6) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.getDefaultState();
                if (roll < 5) yield Blocks.ORANGE_TERRACOTTA.getDefaultState();
                if (roll < 7) yield Blocks.RED_SANDSTONE.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.YELLOW_TERRACOTTA.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.BROWN_TERRACOTTA.getDefaultState();
            }
            case SAVANNA -> {
                if (roll < 4) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 6) yield Blocks.STONE.getDefaultState();
                if (roll < 8) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
            case CHERRY -> {
                if (roll < 4) yield Blocks.STONE.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 7) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.CLAY.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
            case MUSHROOM -> {
                if (roll < 4) yield Blocks.MYCELIUM.getDefaultState();
                if (roll < 6) yield Blocks.STONE.getDefaultState();
                if (roll < 8) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
            case DARK_FOREST -> {
                if (roll < 4) yield Blocks.ROOTED_DIRT.getDefaultState();
                if (roll < 6) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 9) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
            }
            default -> {
                if (roll < 5) yield Blocks.STONE.getDefaultState();
                if (roll < 7) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.GRAVEL.getDefaultState();
            }
        };
    }

    private BlockState getShellBlock(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 3) yield Blocks.SANDSTONE.getDefaultState();
                if (roll < 5) yield Blocks.SMOOTH_SANDSTONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.TERRACOTTA.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case MANGROVE -> {
                if (roll < 3) yield Blocks.PACKED_MUD.getDefaultState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.MUD.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case SNOWY -> {
                if (roll < 3) yield Blocks.PACKED_ICE.getDefaultState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.BLUE_ICE.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case JUNGLE, DARK_FOREST -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 5) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.STONE.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case TAIGA -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 5) yield Blocks.STONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COBBLESTONE.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.getDefaultState();
                if (roll < 5) yield Blocks.RED_SANDSTONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.ORANGE_TERRACOTTA.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case SAVANNA -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 5) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.STONE.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case CHERRY -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 5) yield Blocks.STONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.CLAY.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case MUSHROOM -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 5) yield Blocks.STONE.getDefaultState();
                if (roll < 7) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 9) yield Blocks.COAL_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COBBLESTONE.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            default -> {
                if (roll < 4) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.MAGMA_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
        };
    }

    private BlockState getEjectaRim(Random random, BiomeVariant variant) {
        int roll = random.nextInt(11);
        return switch (variant) {
            case DESERT -> {
                if (roll < 3) yield Blocks.TERRACOTTA.getDefaultState();
                if (roll < 4) yield Blocks.RED_SANDSTONE.getDefaultState();
                if (roll < 5) yield Blocks.SANDSTONE.getDefaultState();
                if (roll < 6) yield Blocks.SMOOTH_SANDSTONE.getDefaultState();
                if (roll < 8) yield Blocks.CUT_SANDSTONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case MANGROVE -> {
                if (roll < 3) yield Blocks.MUD.getDefaultState();
                if (roll < 4) yield Blocks.PACKED_MUD.getDefaultState();
                if (roll < 5) yield Blocks.CLAY.getDefaultState();
                if (roll < 6) yield Blocks.MUDDY_MANGROVE_ROOTS.getDefaultState();
                if (roll < 8) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case SNOWY -> {
                if (roll < 3) yield Blocks.SNOW_BLOCK.getDefaultState();
                if (roll < 5) yield Blocks.PACKED_ICE.getDefaultState();
                if (roll < 7) yield Blocks.STONE.getDefaultState();
                if (roll < 8) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case JUNGLE -> {
                if (roll < 3) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 4) yield Blocks.MUD.getDefaultState();
                if (roll < 5) yield Blocks.ROOTED_DIRT.getDefaultState();
                if (roll < 6) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case TAIGA -> {
                if (roll < 3) yield Blocks.PODZOL.getDefaultState();
                if (roll < 4) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 5) yield Blocks.STONE.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.GRAVEL.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case BADLANDS -> {
                if (roll < 3) yield Blocks.TERRACOTTA.getDefaultState();
                if (roll < 4) yield Blocks.ORANGE_TERRACOTTA.getDefaultState();
                if (roll < 5) yield Blocks.YELLOW_TERRACOTTA.getDefaultState();
                if (roll < 6) yield Blocks.RED_SANDSTONE.getDefaultState();
                if (roll < 8) yield Blocks.BROWN_TERRACOTTA.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case SAVANNA -> {
                if (roll < 3) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 4) yield Blocks.GRAVEL.getDefaultState();
                if (roll < 5) yield Blocks.DIRT.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case CHERRY -> {
                if (roll < 3) yield Blocks.STONE.getDefaultState();
                if (roll < 4) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 5) yield Blocks.CLAY.getDefaultState();
                if (roll < 6) yield Blocks.MOSS_BLOCK.getDefaultState();
                if (roll < 8) yield Blocks.GRAVEL.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case MUSHROOM -> {
                if (roll < 3) yield Blocks.MYCELIUM.getDefaultState();
                if (roll < 4) yield Blocks.STONE.getDefaultState();
                if (roll < 5) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 6) yield Blocks.GRAVEL.getDefaultState();
                if (roll < 8) yield Blocks.DIRT.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            case DARK_FOREST -> {
                if (roll < 3) yield Blocks.ROOTED_DIRT.getDefaultState();
                if (roll < 4) yield Blocks.COARSE_DIRT.getDefaultState();
                if (roll < 5) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
            default -> {
                if (roll < 3) yield Blocks.COBBLED_DEEPSLATE.getDefaultState();
                if (roll < 4) yield Blocks.GRAVEL.getDefaultState();
                if (roll < 5) yield Blocks.DIRT.getDefaultState();
                if (roll < 6) yield Blocks.COBBLESTONE.getDefaultState();
                if (roll < 8) yield Blocks.STONE.getDefaultState();
                if (roll < 10) yield Blocks.COAL_BLOCK.getDefaultState();
                yield Blocks.OBSIDIAN.getDefaultState();
            }
        };
    }
}