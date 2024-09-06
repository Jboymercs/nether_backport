package com.unseen.nb.common.items;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModItems;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemPigStepDisc extends ItemRecord implements IHasModel {

    public ItemPigStepDisc(String name, SoundEvent soundEventIn) {
        super(name, soundEventIn);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
