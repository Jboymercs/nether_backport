package com.unseen.nb.init;

import com.unseen.nb.util.integration.ModIntegration;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.integration.SmithingTableIntegration;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ModRecipes {

        public static void init() {
                //Smelting Recipes

                GameRegistry.addSmelting(ModBlocksCompat.NETHERITE_ORE, new ItemStack(ModItemsCompat.NETHERITE_SCRAP, 1), 2);
                GameRegistry.addSmelting(ModBlocks.NETHER_GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1);
                GameRegistry.addSmelting(ModBlocks.BLACK_STONE_BRICKS, new ItemStack(ModBlocks.CRACKED_STONE_BRICKS), 1);
                GameRegistry.addSmelting(ModBlocks.BASALT, new ItemStack(ModBlocks.SMOOTH_BASALT), 1);
                //OreRegistry
                //Stone
                OreDictionary.registerOre("cobblestone", ModBlocks.BLACK_STONE);
                //Wood

                OreDictionary.registerOre("plankWood", ModBlocks.CRIMSON_PLANKS);
                OreDictionary.registerOre("stairWood", ModBlocks.CRIMSON_STAIRS);
                OreDictionary.registerOre("slabWood", ModBlocks.CRIMSON_SLAB_HALF);
                OreDictionary.registerOre("slabWood", ModBlocks.CRIMSON_SLAB_DOUBLE);
                OreDictionary.registerOre("doorWood", ModBlocks.CRIMSON_DOOR);
                OreDictionary.registerOre("trapDoorWood", ModBlocks.CRIMSON_TRAPDOOR);
                OreDictionary.registerOre("fenceWood", ModBlocks.CRIMSON_FENCE);
                OreDictionary.registerOre("fenceGateWood", ModBlocks.CRIMSON_GATE);
                OreDictionary.registerOre("buttonWood", ModBlocks.CRIMSON_BUTTON);
                OreDictionary.registerOre("pressurePlateWood", ModBlocks.CRIMSON_PRESSURE_PLATE);
                OreDictionary.registerOre("logWood", ModBlocks.CRIMSON_STEM);
                OreDictionary.registerOre("logWood", ModBlocks.CRIMSON_HYPHAE);
                OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CRIMSON_STEM);
                OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CRIMSON_HYPHAE);

                OreDictionary.registerOre("plankWood", ModBlocks.WARPED_PLANKS);
                OreDictionary.registerOre("stairWood", ModBlocks.WARPED_STAIRS);
                OreDictionary.registerOre("slabWood", ModBlocks.WARPED_SLAB_HALF);
                OreDictionary.registerOre("slabWood", ModBlocks.WARPED_SLAB_DOUBLE);
                OreDictionary.registerOre("doorWood", ModBlocks.WARPED_DOOR);
                OreDictionary.registerOre("trapDoorWood", ModBlocks.WARPED_TRAPDOOR);
                OreDictionary.registerOre("fenceWood", ModBlocks.WARPED_FENCE);
                OreDictionary.registerOre("fenceGateWood", ModBlocks.WARPED_GATE);
                OreDictionary.registerOre("buttonWood", ModBlocks.WARPED_BUTTON);
                OreDictionary.registerOre("pressurePlateWood", ModBlocks.WARPED_PRESSURE_PLATE);
                OreDictionary.registerOre("logWood", ModBlocks.WARPED_STEM);
                OreDictionary.registerOre("logWood", ModBlocks.WARPED_HYPHAE);
                OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_WARPED_STEM);
                OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_WARPED_HYPHAE);

                //Material
                if(!ModIntegration.FUTURE_MC_LOADED) {
                        OreDictionary.registerOre("ingotNetherite", ModItemsCompat.NETHERITE_INGOT);
                        OreDictionary.registerOre("blockNetherite", ModBlocksCompat.NETHERITE_BLOCK);
                }


                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "crimson_trapdoor"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.CRIMSON_TRAPDOOR, 2),
                        "AAA",
                        "AAA",
                        'A', new ItemStack(ModBlocks.CRIMSON_PLANKS)
                );

                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "crimson_pressure_plate"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.CRIMSON_PRESSURE_PLATE, 1),
                        "AA",
                        'A', new ItemStack(ModBlocks.CRIMSON_PLANKS)
                );

                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "crimson_button"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.CRIMSON_BUTTON, 1),
                        "A",
                        'A', new ItemStack(ModBlocks.CRIMSON_PLANKS)
                );
                
                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "warped_trapdoor"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.WARPED_TRAPDOOR, 2),
                        "AAA",
                        "AAA",
                        'A', new ItemStack(ModBlocks.WARPED_PLANKS)
                );

                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "warped_pressure_plate"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.WARPED_PRESSURE_PLATE, 1),
                        "AA",
                        'A', new ItemStack(ModBlocks.WARPED_PLANKS)
                );

                GameRegistry.addShapedRecipe(
                        new ResourceLocation(ModReference.MOD_ID, "warped_button"),
                        new ResourceLocation(ModReference.MOD_ID),
                        new ItemStack(ModBlocks.WARPED_BUTTON, 1),
                        "A",
                        'A', new ItemStack(ModBlocks.WARPED_PLANKS)
                );

                //Re-add the recipe
                OreIngredient woodStack = new OreIngredient("plankWood");
                List<Item> itemList = new ArrayList<Item>();
                itemList.add(Item.getByNameOrId("minecraft:trapdoor"));
                itemList.add(Item.getByNameOrId("minecraft:wooden_button"));
                itemList.add(Item.getByNameOrId("minecraft:wooden_pressure_plate"));

                //Get the recipe registry for the current registration
                IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>) GameRegistry.findRegistry(IRecipe.class);

                //Delete the recipe
                int count = 0;
                for (IRecipe recipe : (Iterable<IRecipe>) modRegistry) {
                        for (Item item : itemList) {
                                if (ItemStack.areItemsEqual(recipe.getRecipeOutput(), new ItemStack(item))) {
                                        modRegistry.remove(recipe.getRegistryName());
                                        count++;
                                }
                                if (count == itemList.size()) {
                                        break;
                                }
                        }
                }
                // Trapdoor
                GameRegistry.addShapedRecipe(
                        new ResourceLocation("minecraft", "oak_trapdoor"),
                        new ResourceLocation("minecraft"),
                        new ItemStack(Item.getByNameOrId("minecraft:trapdoor"), 2),
                        "AAA",
                        "AAA",
                        'A', woodStack
                );

                // Button
                GameRegistry.addShapedRecipe(
                        new ResourceLocation("minecraft", "oak_button"),
                        new ResourceLocation("minecraft"),
                        new ItemStack(Item.getByNameOrId("minecraft:wooden_button"), 1),
                        "A",
                        'A', woodStack
                );

                // Pressure Plate
                GameRegistry.addShapedRecipe(
                        new ResourceLocation("minecraft", "oak_pressure_plate"),
                        new ResourceLocation("minecraft"),
                        new ItemStack(Item.getByNameOrId("minecraft:wooden_pressure_plate"), 1),
                        "AA",
                        'A', woodStack
                );

                if (ModIntegration.SMITHING_TABLE_LOADED &! OreDictionary.getOres("ingotNetherite", false).isEmpty())
                        SmithingTableIntegration.registerRecipes();
        }

}
