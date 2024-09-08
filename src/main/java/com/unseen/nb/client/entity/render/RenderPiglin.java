package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.animation.render.layer.EZLayerHeldItem;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelPiglin;
import com.unseen.nb.common.entity.entities.EntityPiglin;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPiglin extends RenderModEntity<EntityPiglin> {


    public <U extends BasicModelEntity> RenderPiglin(RenderManager rendermanagerIn) {

        super(rendermanagerIn, "piglin.png", new ModelPiglin());
        this.addLayer(new EZLayerHeldItem(this));

    }

    @Override
    protected void applyRotations(EntityPiglin striderIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if(striderIn.convertTooZombie) rotationYaw += (float)(Math.cos((double)striderIn.ticksExisted * 3.25D) * Math.PI * 0.25D);

        super.applyRotations(striderIn, ageInTicks, rotationYaw, partialTicks);
    }




}
