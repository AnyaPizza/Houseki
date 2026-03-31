package anya.pizza.houseki.block.entity;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crusher_be"),
                    FabricBlockEntityTypeBuilder.create(CrusherBlockEntity::new, ModBlocks.CRUSHER).build());

    public static final BlockEntityType<FoundryBlockEntity> FOUNDRY_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "foundry_be"),
                    FabricBlockEntityTypeBuilder.create(FoundryBlockEntity::new, ModBlocks.FOUNDRY).build());


    public static void registerBlockEntities() {
        Houseki.LOGGER.info("Registering Block Entities for " + Houseki.MOD_ID);
    }
}