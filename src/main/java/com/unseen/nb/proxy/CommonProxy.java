package com.unseen.nb.proxy;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.model.ModelPiglin;
import com.unseen.nb.common.event.EventOnSoulFire;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void init() {
        MinecraftForge.EVENT_BUS.register(new EventOnSoulFire());
    }



    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void handleAnimationPacket(int entityId, int index) {

    }
}
