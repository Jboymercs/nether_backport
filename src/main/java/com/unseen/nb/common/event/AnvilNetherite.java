package com.unseen.nb.common.event;

import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModItemsCompat;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import com.unseen.nb.util.integration.ModIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class AnvilNetherite {

    public static boolean isOreMatching(ItemStack stack, String target) {
            for (ItemStack ore : OreDictionary.getOres(target, false)) {
                if (ItemStack.areItemsEqual(ore, stack)) {
                    return true;
                }
            }
        return false;
    }

    @SubscribeEvent
    public static void addNetheriteAnvilRecipes(AnvilUpdateEvent event) {

        ItemStack leftInput = event.getLeft();
        ItemStack rightInput = event.getRight();
        ItemStack output = event.getOutput();

        if (isOreMatching(rightInput, "ingotNetherite")) {
            if (!ModIntegration.FUTURE_MC_LOADED) {
                Item[] diamondTools = new Item[]{Items.DIAMOND_SWORD, Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS, Items.DIAMOND_HORSE_ARMOR};

                for (int i = 0; i < diamondTools.length; i++) {
                    if (leftInput.getItem() == diamondTools[i]) {
                        Item[] netheriteTools = new Item[]{ModItemsCompat.NETHERITE_SWORD, ModItemsCompat.NETHERITE_SHOVEL, ModItemsCompat.NETHERITE_PICKAXE, ModItemsCompat.NETHERITE_AXE, ModItemsCompat.NETHERITE_HOE, ModItemsCompat.NETHERITE_HELMET, ModItemsCompat.NETHERITE_CHESTPLATE, ModItemsCompat.NETHERITE_LEGGINGS, ModItemsCompat.NETHERITE_BOOTS, ModItems.NETHERITE_HORSE_ARMOR_ITEM};
                        output = new ItemStack(netheriteTools[i]);
                        NBTTagCompound tags = leftInput.getTagCompound();
                        output.setTagCompound(tags);
                        int itemDamage = (int) ModUtils.getPercentageOf(leftInput.getMaxDamage(), leftInput.getItemDamage());
                        int calculatedDamage = (int) ModUtils.calculateValueWithPrecentage(output.getMaxDamage(), itemDamage);
                        output.setItemDamage(calculatedDamage);
                        event.setOutput(output);
                        event.setMaterialCost(1);
                        event.setCost(8);
                    }
                }
            } else {
                Item[] diamondTools = new Item[]{Items.DIAMOND_HORSE_ARMOR};

                for (int i = 0; i < diamondTools.length; i++) {
                    if (leftInput.getItem() == diamondTools[i]) {
                        Item[] netheriteTools = new Item[]{ModItems.NETHERITE_HORSE_ARMOR_ITEM};
                        output = new ItemStack(netheriteTools[i]);
                        NBTTagCompound tags = leftInput.getTagCompound();
                        output.setTagCompound(tags);
                        int itemDamage = (int) ModUtils.getPercentageOf(leftInput.getMaxDamage(), leftInput.getItemDamage());
                        int calculatedDamage = (int) ModUtils.calculateValueWithPrecentage(output.getMaxDamage(), itemDamage);
                        output.setItemDamage(calculatedDamage);
                        event.setOutput(output);
                        event.setMaterialCost(1);
                        event.setCost(8);
                    }
                }
            }

        }
    }
}
