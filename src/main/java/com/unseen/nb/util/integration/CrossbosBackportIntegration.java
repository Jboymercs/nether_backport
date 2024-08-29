package com.unseen.nb.util.integration;

import com.unseen.nb.common.entity.entities.EntityPiglin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.smileycorp.crossbows.common.CrossbowsContent;
import net.smileycorp.crossbows.common.entities.ICrossbowArrow;
import net.smileycorp.crossbows.common.entities.IFireworksProjectile;
import net.smileycorp.crossbows.common.item.ItemCrossbow;

public class CrossbosBackportIntegration {

    public static void init() {

    }

    public static boolean isCrossbow(ItemStack stack) {
        return stack.getItem() instanceof ItemCrossbow;
    }

    public static ItemStack getCrossbow() {
        return new ItemStack(CrossbowsContent.CROSSBOW);
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        if (charged == false) ItemCrossbow.setCharged(stack, charged);
    }

    public static void shoot(EntityPiglin entity, ItemStack stack, float velocity) {
        ItemCrossbow.performShooting(entity.world, entity, stack, velocity, 14 - entity.getEntityWorld().getDifficulty().getId() * 4);
    }





    public static boolean isCrossbowProjectile(Entity entity) {
        return entity instanceof ICrossbowArrow && ((ICrossbowArrow)entity).shotFromCrossbow();
    }

    public static void setOwner(EntityFireworkRocket firework, EntityLivingBase owner) {
        ((IFireworksProjectile)firework).setOwner(owner);
    }

    public static float getChargeAmount(ItemStack stack, EntityLivingBase entity) {
        float f = ItemCrossbow.getChargeDuration(stack);
        float f1 = -MathHelper.clamp((float)entity.getItemInUseCount(), 0.0F, f);
        return f1/f;
    }
}
