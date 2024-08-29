package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.entity.RenderModEntity;
import com.unseen.nb.client.entity.model.ModelPiglinBrute;
import com.unseen.nb.client.entity.render.layer.LayerBruteItem;
import com.unseen.nb.common.entity.entities.EntityPiglinBrute;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPiglinBrute extends RenderModEntity<EntityPiglinBrute> {

    public <U extends BasicModelEntity> RenderPiglinBrute(RenderManager rendermanagerIn) {

        super(rendermanagerIn, "piglin_brute.png", new ModelPiglinBrute());
        this.addLayer(new LayerBruteItem(this));

    }
}
