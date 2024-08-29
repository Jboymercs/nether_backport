package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelPiglinZombie;
import com.unseen.nb.client.entity.render.layer.LayerPiglinZombie;
import com.unseen.nb.common.entity.entities.EntityPiglinZombie;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPiglinZombie extends RenderModEntity<EntityPiglinZombie> {

    public <U extends BasicModelEntity> RenderPiglinZombie(RenderManager rendermanagerIn) {

        super(rendermanagerIn, "piglin_zombie.png", new ModelPiglinZombie());
        this.addLayer(new LayerPiglinZombie(this));

    }
}
