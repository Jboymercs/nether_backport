package com.unseen.nb.init;


import com.unseen.nb.util.ModReference;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

        public static void init() {
                //Smelting Recipes
                GameRegistry.addSmelting(ModBlocks.NETHERITE_ORE, new ItemStack(ModItems.NETHERITE_SCRAP, 2), 2);
                GameRegistry.addSmelting(ModBlocks.BLACK_STONE_BRICKS, new ItemStack(ModBlocks.CRACKED_STONE_BRICKS), 1);
                GameRegistry.addSmelting(ModBlocks.BASALT, new ItemStack(ModBlocks.SMOOTH_BASALT), 1);
                //OreRegistry
                //Stone
                OreDictionary.registerOre("cobblestone", ModBlocks.BLACK_STONE);
                //Wood
                OreDictionary.registerOre("logWood", ModBlocks.CRIMSON_STEM);
                OreDictionary.registerOre("logWood", ModBlocks.WARPED_STEM);
                OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.CRIMSON_PLANKS, 1, 0));
                OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.WARPED_PLANKS, 1,0));
                OreDictionary.registerOre("stairWood", ModBlocks.CRIMSON_STAIRS);
                OreDictionary.registerOre("stairWood", ModBlocks.WARPED_STAIRS);

                OreDictionary.registerOre("trapDoor", ModBlocks.CRIMSON_TRAPDOOR);
                OreDictionary.registerOre("trapDoor", ModBlocks.WARPED_TRAPDOOR);
                OreDictionary.registerOre("trapDoor", Blocks.TRAPDOOR);
        }

}
