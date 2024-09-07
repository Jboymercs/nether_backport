package com.unseen.nb.init;


import com.unseen.nb.common.items.ItemBaseCompat;
import com.unseen.nb.common.items.netherite.*;
import com.unseen.nb.util.ModReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItemsCompat {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    private static final Item.ToolMaterial NETHERITE_SET = EnumHelper.addToolMaterial("netherite_set", 4, 2031, 10.0F, 4.0F, 15);
    private static final ItemArmor.ArmorMaterial NETHERITE_ARMOR = EnumHelper.addArmorMaterial("netherite", ModReference.MOD_ID + ":netherite", 407, new int[]{3, 6, 8, 3}, 20, ModSoundHandler.NETHERITE_EQUIP, 3F).setRepairItem(new ItemStack(ModItemsCompat.NETHERITE_INGOT));

    public static final Item NETHERITE_SCRAP = new ItemBaseCompat("nether_scrap", CreativeTabs.MISC);
    public static final Item NETHERITE_INGOT = new ItemBaseCompat("netherite_ingot", CreativeTabs.MISC);


    public static final Item NETHERITE_HELMET = new ArmorBase("netherite_helmet", NETHERITE_ARMOR, 1, EntityEquipmentSlot.HEAD, "netherite");
    public static final Item NETHERITE_CHESTPLATE = new ArmorBase("netherite_chestplate", NETHERITE_ARMOR, 1, EntityEquipmentSlot.CHEST, "netherite");
    public static final Item NETHERITE_LEGGINGS = new ArmorBase("netherite_leggings", NETHERITE_ARMOR, 2, EntityEquipmentSlot.LEGS, "netherite");
    public static final Item NETHERITE_BOOTS = new ArmorBase("netherite_boots", NETHERITE_ARMOR, 1, EntityEquipmentSlot.FEET, "netherite");
    public static final Item NETHERITE_SWORD = new ToolSword("netherite_sword", NETHERITE_SET, 7);
    public static final Item NETHERITE_SHOVEL = new ToolShovel("netherite_shovel", NETHERITE_SET, CreativeTabs.TOOLS);
    public static final Item NETHERITE_AXE = new ToolAxe("netherite_axe", NETHERITE_SET, CreativeTabs.TOOLS);
    public static final Item NETHERITE_PICKAXE = new ToolPickaxe("netherite_pickaxe", NETHERITE_SET, CreativeTabs.TOOLS);
    public static final Item NETHERITE_HOE = new ToolHoe("netherite_hoe", NETHERITE_SET, CreativeTabs.TOOLS);
}
