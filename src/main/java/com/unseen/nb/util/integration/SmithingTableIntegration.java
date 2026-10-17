package com.unseen.nb.util.integration;


import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModItemsCompat;
import git.jbredwards.smithing_table.api.SmithingRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreIngredient;

public class SmithingTableIntegration {

    public static void registerRecipes() {
        addRecipe(Items.DIAMOND_HORSE_ARMOR, ModItems.NETHERITE_HORSE_ARMOR_ITEM);
        if (ModIntegration.FUTURE_MC_LOADED) return;
        Item[] diamondTools = new Item[]{Items.DIAMOND_SWORD, Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS};
        Item[] netheriteTools = new Item[]{ModItemsCompat.NETHERITE_SWORD, ModItemsCompat.NETHERITE_SHOVEL, ModItemsCompat.NETHERITE_PICKAXE, ModItemsCompat.NETHERITE_AXE, ModItemsCompat.NETHERITE_HOE, ModItemsCompat.NETHERITE_HELMET, ModItemsCompat.NETHERITE_CHESTPLATE, ModItemsCompat.NETHERITE_LEGGINGS, ModItemsCompat.NETHERITE_BOOTS};
        for (int i = 0; i < diamondTools.length; i++) addRecipe(diamondTools[i], netheriteTools[i]);
    }

    private static void addRecipe(Item diamond, Item netherite) {
        SmithingRecipe.Impl recipe = new SmithingRecipe.Impl(ItemStack.EMPTY, diamond, new OreIngredient("ingotNetherite"), new ItemStack(netherite));
        recipe.setRegistryName(netherite.getRegistryName());
        SmithingRecipe.REGISTRY.register(recipe);
    }

}
