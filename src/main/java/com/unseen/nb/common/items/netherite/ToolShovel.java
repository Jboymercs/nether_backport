package com.unseen.nb.common.items.netherite;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModItemsCompat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ToolShovel extends ItemSpade implements IHasModel {

    public ToolShovel(String name, Item.ToolMaterial material, CreativeTabs tab) {
        super(material);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(tab);
        ModItemsCompat.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
