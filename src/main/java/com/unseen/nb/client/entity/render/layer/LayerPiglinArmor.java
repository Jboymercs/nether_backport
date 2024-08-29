package com.unseen.nb.client.entity.render.layer;

import com.unseen.nb.client.animation.render.EZRenderLiving;
import com.unseen.nb.client.entity.model.ModelPiglin;
import com.unseen.nb.common.entity.entities.EntityPiglin;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayerPiglinArmor extends ModelPiglin implements LayerRenderer<EntityPiglin> {
    protected EZRenderLiving<?> renderLiving;

    public LayerPiglinArmor(EZRenderLiving<?> renderer) {
        this.renderLiving = renderer;
    }

    @Override
    public void doRenderLayer(EntityPiglin entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

            if((entitylivingbaseIn).isHasHelmet()) {
                this.Helmet.isHidden =false;
            }
            if((entitylivingbaseIn).isHasChestPlate()) {
                this.ChestArmor.isHidden = false;
                this.LShoulderPad.isHidden = false;
                this.RShoulderPad.isHidden = false;
            }
            if((entitylivingbaseIn).isHasBoots()) {
                this.LBoot.isHidden = false;
                this.RBoot.isHidden = false;
            }

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
