package anya.pizza.houseki.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

/**
 * High-level structure class for the meteorite. Decides whether a meteorite should spawn
 * at a given chunk and calculates its parameters (radius, crater depth, seed).
 * The actual block placement happens in MeteoriteStructurePiece.
 */
public class MeteoriteStructure extends Structure {
    // Codec for JSON serialization. No custom fields, so we just wrap the base Structure config.
    public static final MapCodec<MeteoriteStructure> CODEC = createCodec(MeteoriteStructure::new);

    public MeteoriteStructure(Config config) {
        super(config);
    }

    /**
     * Called by Minecraft to check if a meteorite should generate in this chunk.
     * Returns empty if the location is unsuitable (too low), otherwise returns a
     * StructurePosition with a randomly sized MeteoriteStructurePiece.
     */
    @Override
    public Optional<StructurePosition> getStructurePosition(Context context) {
        // Get the block coordinates at the center of the candidate chunk
        int x = context.chunkPos().getCenterX();
        int z = context.chunkPos().getCenterZ();

        // Find the surface height at this position using the worldgen heightmap
        int surfaceY = context.chunkGenerator().getHeightOnGround(
                x, z, Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());

        // Don't generate if the surface is too close to the world bottom (void, deep cave, etc.)
        if (surfaceY <= context.world().getBottomY() + 10) {
            return Optional.empty();
        }

        Random random = context.random();
        // Pick a random radius between MIN_RADIUS (7) and MAX_RADIUS (11)
        int radius = MeteoriteStructurePiece.MIN_RADIUS
                + random.nextInt(MeteoriteStructurePiece.MAX_RADIUS - MeteoriteStructurePiece.MIN_RADIUS + 1);
        // Crater depth scales with radius so bigger meteorites make deeper craters
        int craterDepth = radius + 3 + random.nextInt(4);

        BlockPos pos = new BlockPos(x, surfaceY, z);
        // Generate a seed for the piece so it can produce deterministic results across chunk passes
        long pieceSeed = random.nextLong();

        return Optional.of(new StructurePosition(pos, collector -> {
            collector.addPiece(new MeteoriteStructurePiece(
                    x, surfaceY, z, radius, craterDepth, pieceSeed));
        }));
    }

    @Override
    public StructureType<?> getType() {
        return ModStructures.METEORITE_TYPE;
    }
}
