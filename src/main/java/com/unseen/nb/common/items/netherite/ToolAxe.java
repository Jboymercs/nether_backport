package com.unseen.nb.common.items.netherite;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModItemsCompat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel {

    public ToolAxe(String name, ToolMaterial materialIn, CreativeTabs tab) {
        super(materialIn, materialIn.getAttackDamage() + 5, -3.0F);
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
