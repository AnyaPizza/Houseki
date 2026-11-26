package anya.pizza.houseki.compat.atlas;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.platform.ClientMachineRecipes;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import anya.pizza.houseki.recipe.FoundryMeltingRecipe;
import anya.pizza.houseki.recipe.FoundryRecipes;
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
import java.util.Optional;

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
            registerCrushingRecipes(ClientMachineRecipes.get(ModTypes.CRUSHER_TYPE));
        } else {
            registerCrushingRecipes(server.getRecipeManager().getRecipes());
        }
        registerFoundryRecipes();
    }

    private static void registerCrushingRecipes(Collection<? extends RecipeHolder<?>> recipes) {
        for (var holder : recipes) {
            if (holder.value() instanceof CrusherRecipe recipe) {
                registerCrushing(holder.id().identifier().toString(), recipe);
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

    private static void registerFoundryRecipes() {
        registerMetal("steel", ModItems.STEEL);
        registerMetal("meteoric_iron", ModItems.METEORIC_IRON_INGOT);
    }

    private static void registerMetal(String metal, Item ingot) {
        var molten = IngredientKey.fluid("houseki:molten_" + metal);
        var input = IngredientKey.item(item(ingot).id());
        AtlasApi.recipes(MELTING).id("houseki:foundry/melting/" + metal)
                .display(input).output(new EntryKey(molten.type(), molten.id()), 1)
                .time(FoundryMeltingRecipe.DEFAULT_MELT_TIME).register();

        FoundryRecipes.CASTS.forEach((mold, result) -> {
            var cast = item(mold);
            var output = metal.equals("steel") ? result.steel() : result.meteoricIron();
            AtlasApi.recipes(CASTING).id("houseki:foundry/casting/" + metal + "/" + cast.path())
                    .display(IngredientKey.item(cast.id()), molten).output(item(output), 1)
                    .time(FoundryCastingRecipe.DEFAULT_CAST_TIME + FoundryCastingRecipe.DEFAULT_COOLING_TIME).register();
        });
    }

    private static RecipeRegistration recipe(String category, String id, IngredientKey input) {
        return AtlasApi.recipes(category).id(id + "#" + input.id()).display(input);
    }

    private static EntryKey item(Item item) {
        return new EntryKey("item", BuiltInRegistries.ITEM.getKey(item).toString());
    }

    private static List<IngredientKey> inputs(Ingredient ingredient) {
        if (ingredient.display() instanceof SlotDisplay.TagSlotDisplay slot) {
            var key = Optional.of(slot.tag());
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
