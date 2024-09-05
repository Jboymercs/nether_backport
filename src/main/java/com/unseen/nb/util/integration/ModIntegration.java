package com.unseen.nb.util.integration;


import com.unseen.nb.common.entity.entities.EntityPiglin;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;


public class ModIntegration {

    public static boolean CROSSBOWS_BACKPORT_LOADED = Loader.isModLoaded("crossbows");
    public static boolean NETHER_API_LOADED = Loader.isModLoaded("nether_api");

    public static void init() {
        if (CROSSBOWS_BACKPORT_LOADED) CrossbosBackportIntegration.init();
    }

    public static boolean isCrossbow(ItemStack stack) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) return true;
        return false;
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) CrossbosBackportIntegration.setCharged(stack, charged);
    }

    public static void performShooting(EntityPiglin entity, ItemStack stack, float velocity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) CrossbosBackportIntegration.shoot(entity, stack, velocity);
    }

    public static boolean isCharged(ItemStack stack, EntityPiglin entity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) return entity.getItemInUseCount() < -stack.getMaxItemUseDuration();
        return false;
    }

    public static float getChargeAmount(ItemStack stack, EntityLivingBase entity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) return CrossbosBackportIntegration.getChargeAmount(stack, entity);
        return (float) -entity.getItemInUseCount() / (float) stack.getMaxItemUseDuration();
    }

}
