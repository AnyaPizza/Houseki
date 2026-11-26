package anya.pizza.houseki.compat.atlas;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.platform.ClientMachineRecipes;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import anya.pizza.houseki.recipe.FoundryMeltingRecipe;
import anya.pizza.houseki.recipe.ModTypes;
import dev.atlasmod.api.AtlasApi;
import dev.atlasmod.api.plugin.AtlasEntrypoint;
import dev.atlasmod.api.plugin.AtlasPlugin;
import dev.atlasmod.api.registration.RecipeRegistration;
import dev.atlasmod.core.entry.EntryKey;
import dev.atlasmod.core.entry.IngredientKey;
import dev.atlasmod.core.recipe.AcquisitionSource;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

@AtlasEntrypoint
public final class HousekiAtlasPlugin implements AtlasPlugin {
    private static final String CRUSHING = "houseki:crushing";
    private static final String MELTING = "houseki:melting";
    private static final String CASTING = "houseki:casting";
    private static final String FOUNDRY = "houseki:foundry";
    private static final String CRUSHER = "houseki:crusher";

    @Override
    public void onAtlasReady() {
        AtlasApi.category(CRUSHING).name("Crushing").icon(CRUSHER).station(CRUSHER).register();
        AtlasApi.category(MELTING).name("Foundry Melting").icon(FOUNDRY).station(FOUNDRY).register();
        AtlasApi.category(CASTING).name("Foundry Casting").icon(FOUNDRY).station(FOUNDRY).register();
        AtlasApi.info("houseki:crusher_fuel").title("Crusher fuel")
                .line("The Crusher runs on iron ingots.").forItems(CRUSHER).source("Houseki").register();
        AtlasApi.info("houseki:foundry_metal").title("Foundry metal")
                .line("Melting fills the Foundry tank. Casting uses one charge of molten metal and consumes the cast.")
                .forItems(FOUNDRY).source("Houseki").register();
        registerRecipeRefresh();
    }

    @Override
    public void onWorldLoad() {
        var server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null) {
            registerSyncedRecipes();
        } else {
            registerRecipes(server.getRecipeManager().getRecipes());
        }
    }

    private static void registerSyncedRecipes() {
        registerRecipes(ClientMachineRecipes.get(ModTypes.CRUSHER_TYPE));
        registerRecipes(ClientMachineRecipes.get(ModTypes.MELTING_FOUNDRY_TYPE));
        registerRecipes(ClientMachineRecipes.get(ModTypes.CASTING_FOUNDRY_TYPE));
    }

    private static void registerRecipes(Collection<? extends RecipeHolder<?>> recipes) {
        for (var holder : recipes) {
            String id = holder.id().identifier().toString();
            switch (holder.value()) {
                case CrusherRecipe recipe -> registerCrushing(id, recipe);
                case FoundryMeltingRecipe recipe -> registerMelting(id, recipe);
                case FoundryCastingRecipe recipe -> registerCasting(id, recipe);
                default -> { }
            }
        }
    }

    private static void registerCrushing(String id, CrusherRecipe recipe) {
        for (var input : inputs(recipe.inputItem())) {
            var node = recipe(CRUSHING, id, input).output(item(recipe.output()), 1).time(recipe.crushingTime());
            recipe.auxiliaryOutput().ifPresent(output -> {
                if (recipe.auxiliaryChance() >= 1) node.output(item(output));
                if (recipe.auxiliaryChance() > 0) {
                    String chance = NumberFormat.getPercentInstance().format(recipe.auxiliaryChance());
                    node.source(new AcquisitionSource(AcquisitionSource.SourceType.MACHINE,
                            "Crusher byproduct", chance + " chance of " + new ItemStack(output).getHoverName().getString()));
                }
            });
            node.register();
        }
    }

    private static void registerMelting(String id, FoundryMeltingRecipe recipe) {
        if (recipe.output() != ModItems.STEEL && recipe.output() != ModItems.METEORIC_IRON_INGOT) return;
        var melt = moltenMetal(recipe.output() == ModItems.STEEL);
        for (var input : inputs(recipe.inputMeltingItem())) {
            recipe(MELTING, id, input).output(new EntryKey(melt.type(), melt.id()), 1)
                    .time(recipe.meltTime()).register();
        }
    }

    private static void registerCasting(String id, FoundryCastingRecipe recipe) {
        var metal = moltenMetal(recipe.activeMetalType() == FoundryBlockEntity.METAL_STEEL);
        for (var cast : inputs(recipe.inputCastingItem())) {
            recipe(CASTING, id, cast).display(metal).output(item(recipe.output()), 1)
                    .time(recipe.castTime() + recipe.coolingTime()).register();
        }
    }

    private static IngredientKey moltenMetal(boolean steel) {
        return IngredientKey.fluid(steel ? "houseki:molten_steel" : "houseki:molten_meteoric_iron");
    }

    private static RecipeRegistration recipe(String category, String id, IngredientKey input) {
        return AtlasApi.recipes(category).id(id + "#" + input.id()).display(input);
    }

    private static EntryKey item(Item item) {
        return new EntryKey("item", BuiltInRegistries.ITEM.getKey(item).toString());
    }

    private static List<IngredientKey> inputs(Ingredient ingredient) {
        if (ingredient.display() instanceof SlotDisplay.TagSlotDisplay slot) {
            var key = slot.tag().unwrapKey();
            if (key.isPresent()) return List.of(IngredientKey.tag(key.get().location().toString()));
        }
        return ingredient.items().map(Holder::value).map(HousekiAtlasPlugin::item)
                .map(EntryKey::id).distinct().map(IngredientKey::item).toList();
    }

    private static void registerRecipeRefresh() {
        try {
            // Atlas 0.3.1 has no public recipe refresh API.
            Method reload = Class.forName("dev.atlasmod.client.AtlasClient").getMethod("reload");
            ClientMachineRecipes.onSync(() -> {
                Minecraft client = Minecraft.getInstance();
                client.schedule(() -> {
                    if (client.level == null) return;
                    try {
                        reload.invoke(null);
                    } catch (ReflectiveOperationException e) {
                        Houseki.LOGGER.warn("Could not refresh Atlas recipes", e);
                    }
                });
            });
        } catch (ReflectiveOperationException e) {
            Houseki.LOGGER.debug("Atlas recipe refresh is unavailable", e);
        }
    }
}
