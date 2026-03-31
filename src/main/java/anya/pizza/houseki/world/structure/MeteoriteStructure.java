package anya.pizza.houseki.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import org.spongepowered.asm.mixin.transformer.Config;

import java.util.Optional;
import java.util.Random;

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

        return Optional.of(new GenerationStub(pos, collector -> {
            collector.addPiece(new MeteoriteStructurePiece(
                    x, surfaceY, z, radius, craterDepth, pieceSeed));
        }));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.METEORITE_TYPE;
    }
}
