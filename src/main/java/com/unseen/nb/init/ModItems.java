package com.unseen.nb.init;

import com.unseen.nb.common.items.ItemBase;
import com.unseen.nb.common.items.ItemFungusOnStick;
import com.unseen.nb.common.items.ItemLodeStoneCompass;
import com.unseen.nb.common.items.ItemNetherDoor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item FUNGUS_ON_STICK = new ItemFungusOnStick("fungus_on_stick", CreativeTabs.TOOLS);
    public static final Item NETHERITE_SCRAP = new ItemBase("nether_scrap", CreativeTabs.MISC);
    public static final Item NETHERITE_INGOT = new ItemBase("netherite_ingot", CreativeTabs.MISC);
    public static final Item LODE_STONE_COMPASS = new ItemLodeStoneCompass("lode_stone_compass");

}
