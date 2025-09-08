package anya.pizza.pizzasoat.item;

import com.google.common.base.Suppliers;
import anya.pizza.pizzasoat.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    PINKU(ModTags.Blocks.INCORRECT_FOR_PINKU_TOOL, 2580, 10, 5, 16, () -> Ingredient.ofItems(ModItems.PINKU)),
    RAINBOW_PYRITE(ModTags.Blocks.INCORRECT_FOR_RAINBOW_PYRITE_TOOL, 2000, 8, 4, 12, () -> Ingredient.ofItems(ModItems.RAINBOW_PYRITE)),
    TUNGSTEN(ModTags.Blocks.INCORRECT_FOR_TUNGSTEN_TOOL, 3000, 9, 4.5f, 15, () -> Ingredient.ofItems(ModItems.TUNGSTEN)),
    TUNGSTEN_DRILL(ModTags.Blocks.INCORRECT_FOR_TUNGSTEN_DRILL_TOOL, 5000, 45, 4.5f, 15, () -> Ingredient.ofItems(ModItems.TUNGSTEN)),
    ALUMINUM(ModTags.Blocks.INCORRECT_FOR_ALUMINUM_TOOL, 1500, 7.5f, 3, 11, () -> Ingredient.ofItems(ModItems.ALUMINUM)),
    SAPPHIRE(ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL, 3000, 9, 5, 12, () -> Ingredient.ofItems(ModItems.SAPPHIRE)),
    JADE(ModTags.Blocks.INCORRECT_FOR_JADE_TOOL, 2000, 8, 4, 12, () -> Ingredient.ofItems(ModItems.JADE)),
    PLATINUM(ModTags.Blocks.INCORRECT_FOR_PLATINUM_TOOL, 3000, 9, 4.5f, 15, () -> Ingredient.ofItems(ModItems.PLATINUM)),
    STEEL(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 2500, 8.5f, 4, 15, () -> Ingredient.ofItems(ModItems.STEEL)),
    CAST_STEEL(ModTags.Blocks.INCORRECT_FOR_CAST_STEEL_TOOL, 3000, 9, 4.5f, 15, () -> Ingredient.ofItems(ModItems.CAST_STEEL));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}

