package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelHoglin;
import com.unseen.nb.common.entity.entities.EntityHoglin;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHoglin extends RenderModEntity<EntityHoglin> {

    public <U extends BasicModelEntity> RenderHoglin(RenderManager rendermanagerIn) {

        super(rendermanagerIn, "hoglin.png", new ModelHoglin());

    }


    @Override
    protected void applyRotations(EntityHoglin striderIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if(striderIn.convertTooZombie) rotationYaw += (float)(Math.cos((double)striderIn.ticksExisted * 3.25D) * Math.PI * 0.25D);

        super.applyRotations(striderIn, ageInTicks, rotationYaw, partialTicks);
    }
}
