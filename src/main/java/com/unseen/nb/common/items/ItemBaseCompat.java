package com.unseen.nb.common.items;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModItemsCompat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBaseCompat extends Item implements IHasModel {
    public ItemBaseCompat(String name, CreativeTabs tab) {
        setTranslationKey(name);
        setRegistryName(name);
        if (tab != null) {
            setCreativeTab(tab);
        }

        ModItemsCompat.ITEMS.add(this);
    }

    public ItemBaseCompat(String name) {
        this(name, CreativeTabs.MISC);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
