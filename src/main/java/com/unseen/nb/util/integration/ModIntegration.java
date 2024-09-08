package com.unseen.nb.util.integration;

import com.unseen.nb.common.entity.entities.EntityPiglin;
import com.unseen.nb.config.ModCompatConfig;
import com.unseen.nb.config.ModConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class ModIntegration {

    public static boolean CROSSBOWS_BACKPORT_LOADED = Loader.isModLoaded("crossbows") && !ModCompatConfig.useSpartanWeapons;
    public static boolean NETHER_API_LOADED = Loader.isModLoaded("nether_api");
<<<<<<< Updated upstream
    public static boolean FUTURE_MC_LOADED = Loader.isModLoaded("futuremc") && ModConfig.futureMCCompat;
    public static boolean SPARTAN_WEAPONRY_LOADED = Loader.isModLoaded("spartanweaponry") && ModConfig.useSpartanWeapons;

=======
    public static boolean FUTURE_MC_LOADED = Loader.isModLoaded("futuremc") && ModCompatConfig.futureMCCompat;
    public static boolean SPARTAN_WEAPONRY_LOADED = Loader.isModLoaded("spartanweaponry") && ModCompatConfig.useSpartanWeapons;
>>>>>>> Stashed changes
    public static void init() {
        if (CROSSBOWS_BACKPORT_LOADED) CrossbosBackportIntegration.init();
        if (SPARTAN_WEAPONRY_LOADED) SpartanWeaponryIntegration.init();
    }

    public static ItemStack getCrossBow() {
        if (CROSSBOWS_BACKPORT_LOADED)
            return CrossbosBackportIntegration.getCrossbow();
        else if (SPARTAN_WEAPONRY_LOADED)
            return SpartanWeaponryIntegration.getCrossBow();
        else
            return new ItemStack(Items.BOW);
    }

    public static boolean isCrossbow(ItemStack stack) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack)) return true;
        if (SPARTAN_WEAPONRY_LOADED && SpartanWeaponryIntegration.isCrossbow(stack)) return true;
        return false;
    }

    public static ItemStack[] selectPiglinWeapon() {
        if (SPARTAN_WEAPONRY_LOADED) SpartanWeaponryIntegration.selectPiglinWeapon();

        return new ItemStack[]{new ItemStack(Items.GOLDEN_SWORD)};
    }

    public static ItemStack[] selectBruteWeapon() {
        if (SPARTAN_WEAPONRY_LOADED) SpartanWeaponryIntegration.selectPiglinBruteWeapon();

        return new ItemStack[]{new ItemStack(Items.GOLDEN_AXE)};
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack))
            CrossbosBackportIntegration.setCharged(stack, charged);
        if (SPARTAN_WEAPONRY_LOADED && SpartanWeaponryIntegration.isCrossbow(stack))
            SpartanWeaponryIntegration.setCharged(stack, charged);
    }

    public static void performShooting(EntityPiglin entity, ItemStack stack, float velocity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack))
            CrossbosBackportIntegration.shoot(entity, stack, velocity);
    }

    public static boolean isCharged(ItemStack stack, EntityPiglin entity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack))
            return entity.getItemInUseCount() < -stack.getMaxItemUseDuration();
        return false;
    }

    public static float getChargeAmount(ItemStack stack, EntityLivingBase entity) {
        if (CROSSBOWS_BACKPORT_LOADED && CrossbosBackportIntegration.isCrossbow(stack))
            return CrossbosBackportIntegration.getChargeAmount(stack, entity);
        return (float) -entity.getItemInUseCount() / (float) stack.getMaxItemUseDuration();
    }

}
