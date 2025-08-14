package anya.pizza.pizzasoat.datagen;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINKU_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_PINKU);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOLFRAMITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAINBOW_PYRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SCHEELITE_ORE);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TRONA_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_TUNGSTEN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BAUXITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_BAUXITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALUMINUM_GLASS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_ALUMINUM);
        blockStateModelGenerator.registerDoor(ModBlocks.ALUMINUM_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.ALUMINUM_TRAPDOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_SAPPHIRE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_NEPHRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NEPHRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_JADE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_PLATINUM);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PLATINUM_ORE);
        BlockStateModelGenerator.BlockTexturePool limestoneBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool limestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE);
        BlockStateModelGenerator.BlockTexturePool polishedLimestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_LIMESTONE);
        BlockStateModelGenerator.BlockTexturePool slateTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE_TILES);
        BlockStateModelGenerator.BlockTexturePool slatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLATE);
        BlockStateModelGenerator.BlockTexturePool polishedSlatePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_SLATE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_SLATE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLACKSTONE_SULFUR_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SULFUR_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_SULFUR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_STEEL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_CAST_STEEL);

        blockStateModelGenerator.registerSingleton(
                ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE,
                new TextureMap()
                        .put(TextureKey.BOTTOM, Identifier.of("pizzasoat", "block/sandstone_rainbow_pyrite_ore_bottom"))
                        .put(TextureKey.TOP, Identifier.of("pizzasoat", "block/sandstone_rainbow_pyrite_ore_top"))
                        .put(TextureKey.SIDE, Identifier.of("pizzasoat", "block/sandstone_rainbow_pyrite_ore")),
                Models.CUBE_BOTTOM_TOP
        );

        blockStateModelGenerator.registerCooker(ModBlocks.CRUSHER, TexturedModel.ORIENTABLE);

        limestonePool.stairs(ModBlocks.LIMESTONE_STAIRS);
        limestoneBrickPool.stairs(ModBlocks.LIMESTONE_BRICK_STAIRS);
        polishedLimestonePool.stairs(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        limestonePool.slab(ModBlocks.LIMESTONE_SLAB);
        limestoneBrickPool.slab(ModBlocks.LIMESTONE_BRICK_SLAB);
        polishedLimestonePool.slab(ModBlocks.POLISHED_LIMESTONE_SLAB);
        limestonePool.wall(ModBlocks.LIMESTONE_WALL);
        limestoneBrickPool.wall(ModBlocks.LIMESTONE_BRICK_WALL);
        polishedLimestonePool.wall(ModBlocks.POLISHED_LIMESTONE_WALL);
        slatePool.stairs(ModBlocks.SLATE_STAIRS);
        slateTilesPool.stairs(ModBlocks.SLATE_TILE_STAIRS);
        polishedSlatePool.stairs(ModBlocks.POLISHED_SLATE_STAIRS);
        slatePool.slab(ModBlocks.SLATE_SLAB);
        slateTilesPool.slab(ModBlocks.SLATE_TILE_SLAB);
        polishedSlatePool.slab(ModBlocks.POLISHED_SLATE_SLAB);
        slatePool.wall(ModBlocks.SLATE_WALL);
        slateTilesPool.wall(ModBlocks.SLATE_TILE_WALL);
        polishedSlatePool.wall(ModBlocks.POLISHED_SLATE_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINKU, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINKU_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOLFRAMITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCHEELITE, Models.GENERATED);
        //itemModelGenerator.register(ModItems.TRONA, Models.GENERATED);
        //itemModelGenerator.register(ModItems.SODIUM_CARBONATE, Models.GENERATED);
        //itemModelGenerator.register(ModItems.PURE_TUNGSTEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAUXITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMINUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NEPHRITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.JADEITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SULFUR, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_IRON, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAST_STEEL, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINKU_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINKU_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINKU_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINKU_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINKU_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ALUMINUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ALUMINUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ALUMINUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ALUMINUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ALUMINUM_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JADE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JADE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JADE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JADE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.JADE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATINUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATINUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATINUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATINUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATINUM_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAST_STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAST_STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAST_STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAST_STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAST_STEEL_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINKU_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINKU_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINKU_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINKU_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RAINBOW_PYRITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RAINBOW_PYRITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RAINBOW_PYRITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RAINBOW_PYRITE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.TUNGSTEN_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.TUNGSTEN_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.TUNGSTEN_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.TUNGSTEN_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ALUMINUM_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ALUMINUM_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ALUMINUM_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ALUMINUM_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SAPPHIRE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SAPPHIRE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SAPPHIRE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SAPPHIRE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.JADE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.JADE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.JADE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.JADE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLATINUM_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLATINUM_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLATINUM_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLATINUM_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.STEEL_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.STEEL_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.STEEL_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.STEEL_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CAST_STEEL_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CAST_STEEL_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CAST_STEEL_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CAST_STEEL_BOOTS));

        itemModelGenerator.register(ModItems.PINKU_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAINBOW_PYRITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALUMINUM_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.PLATINUM_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAST_STEEL_HORSE_ARMOR, Models.GENERATED);


        itemModelGenerator.register(
                ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE.asItem(),
                new Model(Optional.of(Identifier.of("pizzasoat", "block/sandstone_rainbow_pyrite_ore")),
                        Optional.empty())
        );
    }
}
