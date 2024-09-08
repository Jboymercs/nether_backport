package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelZoglin;
import com.unseen.nb.common.entity.entities.EntityZoglin;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderZoglin extends RenderModEntity<EntityZoglin> {
    public <U extends BasicModelEntity> RenderZoglin(RenderManager rendermanagerIn) {

        super(rendermanagerIn, "zoglin.png", new ModelZoglin());

    }
}
