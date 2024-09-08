package com.unseen.nb.client.entity.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStrider extends ModelBase {
    private final ModelRenderer field_239118_a_;
    private final ModelRenderer field_239119_b_;
    private final ModelRenderer field_239120_f_;
    private final ModelRenderer field_239121_g_;
    private final ModelRenderer field_239122_h_;
    private final ModelRenderer field_239123_i_;
    private final ModelRenderer field_239124_j_;
    private final ModelRenderer field_239125_k_;
    private final ModelRenderer field_239126_l_;

    public ModelStrider() {
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.field_239118_a_ = new ModelRenderer(this, 0, 32);
        this.field_239118_a_.setRotationPoint(-4.0F, 8.0F, 0.0F);
        this.field_239118_a_.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);

        this.field_239119_b_ = new ModelRenderer(this, 0, 55);
        this.field_239119_b_.setRotationPoint(4.0F, 8.0F, 0.0F);
        this.field_239119_b_.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);

        this.field_239120_f_ = new ModelRenderer(this, 0, 0);
        this.field_239120_f_.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.field_239120_f_.addBox(-8.0F, -6.0F, -8.0F, 16, 14, 16, 0.0F);

        this.field_239121_g_ = new ModelRenderer(this, 16, 65);
        this.field_239121_g_.setRotationPoint(-8.0F, 4.0F, -8.0F);
        this.field_239121_g_.addBox(-12.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);
        this.field_239121_g_.mirror = true;

        this.rotate(this.field_239121_g_, 0.0F, 0.0F, -1.2217305F);
        this.field_239122_h_ = new ModelRenderer(this, 16, 49);
        this.field_239122_h_.setRotationPoint(-8.0F, -1.0F, -8.0F);
        this.field_239122_h_.addBox(-12.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);
        this.field_239122_h_.mirror = true;

        this.rotate(this.field_239122_h_, 0.0F, 0.0F, -1.134464F);
        this.field_239123_i_ = new ModelRenderer(this, 16, 33);
        this.field_239123_i_.setRotationPoint(-8.0F, -5.0F, -8.0F);
        this.field_239123_i_.addBox(-12.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);

        this.rotate(this.field_239123_i_, 0.0F, 0.0F, -0.87266463F);
        this.field_239124_j_ = new ModelRenderer(this, 16, 33);
        this.field_239124_j_.setRotationPoint(8.0F, -6.0F, -8.0F);
        this.field_239124_j_.addBox(0.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);

        this.rotate(this.field_239124_j_, 0.0F, 0.0F, 0.87266463F);
        this.field_239125_k_ = new ModelRenderer(this, 16, 49);
        this.field_239125_k_.setRotationPoint(8.0F, -2.0F, -8.0F);
        this.field_239125_k_.addBox(0.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);

        this.rotate(this.field_239125_k_, 0.0F, 0.0F, 1.134464F);
        this.field_239126_l_ = new ModelRenderer(this, 16, 65);
        this.field_239126_l_.setRotationPoint(8.0F, 3.0F, -8.0F);
        this.field_239126_l_.addBox(0.0F, 0.0F, 0.0F, 12, 0, 16, 0.0F);

        this.rotate(this.field_239126_l_, 0.0F, 0.0F, 1.2217305F);

        this.field_239120_f_.addChild(this.field_239121_g_);
        this.field_239120_f_.addChild(this.field_239122_h_);
        this.field_239120_f_.addChild(this.field_239123_i_);
        this.field_239120_f_.addChild(this.field_239124_j_);
        this.field_239120_f_.addChild(this.field_239125_k_);
        this.field_239120_f_.addChild(this.field_239126_l_);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
        super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
        this.setRotationAngles(limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);

        this.field_239118_a_.render(unitPixel);
        this.field_239119_b_.render(unitPixel);
        this.field_239120_f_.render(unitPixel);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        limbSwingAmount = Math.min(0.25F, limbSwingAmount);
        if (entityIn.getPassengers().size() <= 0) {
            this.field_239120_f_.rotateAngleX = headPitch * ((float)Math.PI / 180F);
            this.field_239120_f_.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        } else {
            this.field_239120_f_.rotateAngleX = 0.0F;
            this.field_239120_f_.rotateAngleY = 0.0F;
        }

        this.field_239120_f_.rotateAngleZ = 0.1F * MathHelper.sin(limbSwing * 1.5F) * 4.0F * limbSwingAmount;
        this.field_239120_f_.rotationPointY = 2.0F;
        this.field_239120_f_.rotationPointY -= 2.0F * MathHelper.cos(limbSwing * 1.5F) * 2.0F * limbSwingAmount;
        this.field_239119_b_.rotateAngleX = MathHelper.sin(limbSwing * 1.5F * 0.5F) * 2.0F * limbSwingAmount;
        this.field_239118_a_.rotateAngleX = MathHelper.sin(limbSwing * 1.5F * 0.5F + (float)Math.PI) * 2.0F * limbSwingAmount;
        this.field_239119_b_.rotateAngleZ = 0.17453292F * MathHelper.cos(limbSwing * 1.5F * 0.5F) * limbSwingAmount;
        this.field_239118_a_.rotateAngleZ = 0.17453292F * MathHelper.cos(limbSwing * 1.5F * 0.5F + (float)Math.PI) * limbSwingAmount;
        this.field_239119_b_.rotationPointY = 8.0F + 2.0F * MathHelper.sin(limbSwing * 1.5F * 0.5F + (float)Math.PI) * 2.0F * limbSwingAmount;
        this.field_239118_a_.rotationPointY = 8.0F + 2.0F * MathHelper.sin(limbSwing * 1.5F * 0.5F) * 2.0F * limbSwingAmount;
        this.field_239121_g_.rotateAngleZ = -1.2217305F;
        this.field_239122_h_.rotateAngleZ = -1.134464F;
        this.field_239123_i_.rotateAngleZ = -0.87266463F;
        this.field_239124_j_.rotateAngleZ = 0.87266463F;
        this.field_239125_k_.rotateAngleZ = 1.134464F;
        this.field_239126_l_.rotateAngleZ = 1.2217305F;
        float f1 = MathHelper.cos(limbSwing * 1.5F + (float)Math.PI) * limbSwingAmount;
        this.field_239121_g_.rotateAngleZ += f1 * 1.3F;
        this.field_239122_h_.rotateAngleZ += f1 * 1.2F;
        this.field_239123_i_.rotateAngleZ += f1 * 0.6F;
        this.field_239124_j_.rotateAngleZ += f1 * 0.6F;
        this.field_239125_k_.rotateAngleZ += f1 * 1.2F;
        this.field_239126_l_.rotateAngleZ += f1 * 1.3F;
        this.field_239121_g_.rotateAngleZ += 0.05F * MathHelper.sin(ageInTicks * 1.0F * -0.4F);
        this.field_239122_h_.rotateAngleZ += 0.1F * MathHelper.sin(ageInTicks * 1.0F * 0.2F);
        this.field_239123_i_.rotateAngleZ += 0.1F * MathHelper.sin(ageInTicks * 1.0F * 0.4F);
        this.field_239124_j_.rotateAngleZ += 0.1F * MathHelper.sin(ageInTicks * 1.0F * 0.4F);
        this.field_239125_k_.rotateAngleZ += 0.1F * MathHelper.sin(ageInTicks * 1.0F * 0.2F);
        this.field_239126_l_.rotateAngleZ += 0.05F * MathHelper.sin(ageInTicks * 1.0F * -0.4F);
    }

    private void rotate(ModelRenderer modelIn, float x, float y, float z) {
        modelIn.rotateAngleX = x;
        modelIn.rotateAngleY = y;
        modelIn.rotateAngleZ = z;
    }
}
