package anya.pizza.houseki.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
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
