package com.unseen.nb.client.animation.render.layer;

import com.unseen.nb.client.animation.render.EZRenderLiving;
import com.unseen.nb.client.entity.model.ModelPiglin;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EZLayerArmor extends EZLayerArmorBase<ModelPiglin> {


    public EZLayerArmor(EZRenderLiving<?> rendererIn)
    {
        super(rendererIn);
    }

    @Override
    protected void initArmor() {
        this.modelLeggings = new ModelPiglin(0.5F);
        this.modelArmor = new ModelPiglin(1.0F);
    }


    @SuppressWarnings("incomplete-switch")
    protected void setModelSlotVisible(ModelPiglin box, EntityEquipmentSlot slotIn)
    {


        switch (slotIn)
        {
            case HEAD:
                box.Head.showModel = true;
                break;
            case CHEST:
                box.Torso.showModel = true;
                box.RArm.showModel = true;
                box.LArm.showModel = true;
                break;
            case LEGS:
                box.Torso.showModel = true;
                box.LegR.showModel = true;
                box.LegL.showModel = true;
                break;
            case FEET:
                box.LegL.showModel =true;
                box.LegR.showModel = true;
        }
    }




}
