package com.unseen.nb.proxy;

import com.unseen.nb.common.event.EventOnSoulFire;
import com.unseen.nb.handler.RenderHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void init() {
        MinecraftForge.EVENT_BUS.register(new EventOnSoulFire());
        //Render Handler for Entities
        RenderHandler.registerEntityRenderers();
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }
}
