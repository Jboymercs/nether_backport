package com.unseen.nb.common.event;

import com.unseen.nb.init.ModItems;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class AnvilNetherite {


    @SubscribeEvent
    public static void addNetheriteAnvilRecipes(AnvilUpdateEvent event) {

            ItemStack leftInput = event.getLeft();
            ItemStack rightInput = event.getRight();
            ItemStack output = event.getOutput();

            if(rightInput.getItem() == ModItems.NETHERITE_INGOT) {
                Item[] diamondTools = new Item[] {Items.DIAMOND_SWORD, Items.DIAMOND_SHOVEL, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS, Items.DIAMOND_HORSE_ARMOR};

                for(int i = 0; i < diamondTools.length; i++) {
                    if(leftInput.getItem() == diamondTools[i]) {
                        Item[] netheriteTools = new Item[] {ModItems.NETHERITE_SWORD, ModItems.NETHERITE_SHOVEL, ModItems.NETHERITE_PICKAXE, ModItems.NETHERITE_AXE, ModItems.NETHERITE_HOE, ModItems.NETHERITE_HELMET, ModItems.NETHERITE_CHESTPLATE, ModItems.NETHERITE_LEGGINGS, ModItems.NETHERITE_BOOTS, ModItems.NETHERITE_HORSE_ARMOR_ITEM};
                        output = new ItemStack(netheriteTools[i]);
                        NBTTagCompound tags = leftInput.getTagCompound();
                        output.setTagCompound(tags);
                        int itemDamage = (int) ModUtils.getPercentageOf(leftInput.getMaxDamage(), leftInput.getItemDamage());
                        int calculatedDamage = (int)ModUtils.calculateValueWithPrecentage(output.getMaxDamage(), itemDamage);
                        output.setItemDamage(calculatedDamage);
                        event.setOutput(output);
                        event.setMaterialCost(1);
                        event.setCost(8);
                    }
                }
            }

    }
}
