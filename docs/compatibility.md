# Optional integrations

JEI 31.17 or later displays Crusher, Foundry Melting and Foundry Casting recipes.
JEI is optional and is not bundled with Houseki. The development client includes
JEI through a runtime dependency.

Recipes are synchronized by Fabric on login and after data pack reloads. Foundry
recipes live under `data/houseki/recipe/foundry`. Melting produces 100 mB per item;
casting consumes 100 mB and one cast. The melting result identifies the tank:
`houseki:steel` or `houseki:meteoric_iron_ingot`. Casting selects the tank with
`activeMetalType` (0 for steel, 1 for meteoric iron). JEI transfers the cast;
select the matching metal in the Foundry tank before casting.

Every Houseki block is registered with a vanilla BlockItem under the same ID.
The BlockItem is also added to the vanilla block-to-item map, so integrations
such as Sulfur Cube can resolve it through `Block.asItem()`.

## In-game checks

- Start a client without JEI and a dedicated server without JEI.
- Start a JEI client, join the server, and open recipes from both machine arrows.
- Check Crusher primary outputs, auxiliary chances and processing times.
- Melt steel and meteoric iron, then cast an ingot, tool head and armor piece
  from each metal. Check casting, cooling, fuel use and cast consumption.
- Use JEI transfer for each category. Check the cast slot stays locked while
  casting or cooling, and fuel and output slots are left alone.
- Change a recipe in a data pack and run `/reload`. Check both machine behavior
  and JEI refresh. Disconnect and join another world to check recipes refresh.
- Place Houseki blocks with Sulfur Cube installed, including ores, glass, panes,
  stairs, slabs, walls, the door, trapdoor and both machines.
