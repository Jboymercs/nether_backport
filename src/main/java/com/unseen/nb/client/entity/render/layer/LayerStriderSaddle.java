package com.unseen.nb.client.entity.render.layer;


import com.unseen.nb.client.entity.model.ModelStrider;
import com.unseen.nb.client.entity.render.RenderStrider;
import com.unseen.nb.common.entity.entities.EntityStrider;
import com.unseen.nb.util.ModReference;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerStriderSaddle implements LayerRenderer<EntityStrider> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/strider_saddle.png");
    private final RenderStrider striderRenderer;
    private final ModelStrider striderModel = new ModelStrider();

    public LayerStriderSaddle(RenderStrider striderRendererIn) {
        this.striderRenderer = striderRendererIn;
    }
    @Override
    public void doRenderLayer(EntityStrider entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entitylivingbaseIn.getIsSaddled()) {
            this.striderRenderer.bindTexture(TEXTURE);
            this.striderModel.setModelAttributes(this.striderRenderer.getMainModel());
            this.striderModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
