package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import anya.pizza.houseki.screen.custom.FoundryScreen;
import anya.pizza.houseki.screen.custom.FoundryScreenHandler;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import me.shedaniel.rei.api.common.util.EntryStacks;

import java.util.List;

public class HousekiREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrusherCategory());
        registry.addWorkstations(CrusherCategory.CRUSHER, EntryStacks.of(ModBlocks.CRUSHER));

        registry.add(new FoundryMeltingCategory());
        registry.addWorkstations(FoundryMeltingCategory.FOUNDRY_MELTING, EntryStacks.of(ModBlocks.FOUNDRY));

        registry.add(new FoundryCastingCategory());
        registry.addWorkstations(FoundryCastingCategory.FOUNDRY_CASTING, EntryStacks.of(ModBlocks.FOUNDRY));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78, ((screen.height - 176) / 2) + 30, 20, 25),
                CrusherScreen.class, CrusherCategory.CRUSHER);
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 49, ((screen.height - 176) / 2) + 35, 24, 16),
                FoundryScreen.class, FoundryMeltingCategory.FOUNDRY_MELTING);
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 102, ((screen.height - 176) / 2) + 35, 24, 16),
                FoundryScreen.class, FoundryCastingCategory.FOUNDRY_CASTING);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        // Crusher: input slot 0, player inventory slots 4-39
        registry.register(SimpleTransferHandler.create(
                CrusherScreenHandler.class,
                CrusherCategory.CRUSHER,
                new SimpleTransferHandler.IntRange(0, 1)
        ));

        // Foundry melting: input slot 0, player inventory slots 5-40
        registry.register(SimpleTransferHandler.create(
                FoundryScreenHandler.class,
                FoundryMeltingCategory.FOUNDRY_MELTING,
                new SimpleTransferHandler.IntRange(0, 1)
        ));

        // Foundry casting: metal goes to slot 0, cast goes to slot 2 (non-contiguous)
        registry.register(new SimpleTransferHandler() {
            @Override
            public ApplicabilityResult checkApplicable(Context context) {
                if (context.getMenu() instanceof FoundryScreenHandler
                        && context.getDisplay().getCategoryIdentifier().equals(FoundryCastingCategory.FOUNDRY_CASTING)) {
                    return ApplicabilityResult.createApplicable();
                }
                return ApplicabilityResult.createNotApplicable();
            }

            @Override
            public Iterable<SlotAccessor> getInputSlots(Context context) {
                var menu = context.getMenu();
                return List.of(
                        SlotAccessor.fromSlot(menu.slots.get(0)),  // metal input slot
                        SlotAccessor.fromSlot(menu.slots.get(2))   // cast mold slot
                );
            }

            @Override
            public Iterable<SlotAccessor> getInventorySlots(Context context) {
                var menu = context.getMenu();
                var slots = new java.util.ArrayList<SlotAccessor>();
                for (int i = 5; i < menu.slots.size(); i++) {
                    slots.add(SlotAccessor.fromSlot(menu.slots.get(i)));
                }
                return slots;
            }
        });
    }
}
