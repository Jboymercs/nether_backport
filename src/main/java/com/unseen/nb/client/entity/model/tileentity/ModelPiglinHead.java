package com.unseen.nb.client.entity.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPiglinHead extends ModelBase
{
    private final ModelRenderer head;
    private final ModelRenderer left_ear;
    private final ModelRenderer right_ear;

    public ModelPiglinHead()
    {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 0, 0, -5.0F, -8.0F, -4.0F, 10, 8, 8, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 28, 3, -2.0F, -4.0F, -5.0F, 4, 2, 1, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 28, 0, -3.0F, -2.0F, -5.0F, 6, 2, 1, 0.0F, false));

        left_ear = new ModelRenderer(this);
        left_ear.setRotationPoint(4.5F, -6.0F, 0.0F);
        head.addChild(left_ear);
        setRotationAngle(left_ear, 0.0F, 0.0F, -0.6109F);
        left_ear.cubeList.add(new ModelBox(left_ear, 16, 32, 0.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F, false));

        right_ear = new ModelRenderer(this);
        right_ear.setRotationPoint(-4.5F, -6.0F, 0.0F);
        head.addChild(right_ear);
        setRotationAngle(right_ear, 0.0F, 0.0F, 0.6109F);
        right_ear.cubeList.add(new ModelBox(right_ear, 16, 32, -1.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F, false));
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.left_ear.rotateAngleZ = (float)(-(Math.sin((double)(limbSwing * (float) Math.PI * 0.2F * 1.2F)) + 2.5)) * 0.2F;
        this.right_ear.rotateAngleZ = (float)(Math.sin((double)(limbSwing * (float) Math.PI * 0.2F)) + 2.5) * 0.2F;
        this.head.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}