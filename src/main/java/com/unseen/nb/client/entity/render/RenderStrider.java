package com.unseen.nb.client.entity.render;

import com.unseen.nb.client.entity.model.ModelStrider;
import com.unseen.nb.client.entity.render.layer.LayerStriderSaddle;
import com.unseen.nb.common.entity.entities.EntityStrider;
import com.unseen.nb.util.ModReference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStrider extends RenderLiving<EntityStrider> {
    private static final ResourceLocation STRIDER_NORMAL = new ResourceLocation(ModReference.MOD_ID, "textures/entity/strider.png");
    private static final ResourceLocation STRIDER_COLD = new ResourceLocation(ModReference.MOD_ID, "textures/entity/strider_cold.png");

    public RenderStrider(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelStrider(), 0.5F);
        this.addLayer(new LayerStriderSaddle(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityStrider striderIn) {
        return striderIn.getIsCold() ? STRIDER_COLD : STRIDER_NORMAL;
    }

    @Override
    protected void applyRotations(EntityStrider striderIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if(striderIn.getIsCold()) rotationYaw += (float)(Math.cos((double)striderIn.ticksExisted * 3.25D) * Math.PI * 0.25D);

        super.applyRotations(striderIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityStrider striderIn, float partialTickTime) {
        if(striderIn.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            this.shadowSize = 0.25F;
        } else {
            this.shadowSize = 0.5F;
        }
    }
}
