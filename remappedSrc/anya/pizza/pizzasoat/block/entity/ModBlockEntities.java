package anya.pizza.houseki.block.entity;

import anya.pizza.houseki.PizzasOAT;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PizzasOAT.MOD_ID, "crusher_be"),
                    BlockEntityType.Builder.create(CrusherBlockEntity::new, ModBlocks.CRUSHER).build(null));


    public static void registerBlockEntities() {
        PizzasOAT.LOGGER.info("Registering Block Entities for " + PizzasOAT.MOD_ID);
    }
}
