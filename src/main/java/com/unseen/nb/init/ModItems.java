package com.unseen.nb.init;

import com.unseen.nb.common.items.ItemFungusOnStick;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item FUNGUS_ON_STICK = new ItemFungusOnStick("fungus_on_stick", CreativeTabs.TOOLS);
}
