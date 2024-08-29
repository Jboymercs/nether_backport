package com.unseen.nb.init;


import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

        public static void init() {
                GameRegistry.addSmelting(ModBlocks.NETHERITE_ORE, new ItemStack(ModItems.NETHERITE_SCRAP, 2), 2);
                GameRegistry.addSmelting(ModBlocks.BLACK_STONE_BRICKS, new ItemStack(ModBlocks.CRACKED_STONE_BRICKS), 1);

        }

}
