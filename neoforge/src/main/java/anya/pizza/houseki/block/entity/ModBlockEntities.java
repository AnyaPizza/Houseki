package anya.pizza.houseki.block.entity;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import java.util.Set;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crusher_be"),
                    new BlockEntityType<>(CrusherBlockEntity::new, Set.of(ModBlocks.CRUSHER)));

    public static final BlockEntityType<FoundryBlockEntity> FOUNDRY_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "foundry_be"),
                    new BlockEntityType<>(FoundryBlockEntity::new, Set.of(ModBlocks.FOUNDRY)));


    public static void registerBlockEntities() {
        Houseki.LOGGER.info("Registering Block Entities for " + Houseki.MOD_ID);
    }
}