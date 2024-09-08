package com.unseen.nb.util.integration;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.util.NBTHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SpartanWeaponryIntegration {

    public static void init() {

    }

    public static boolean isCrossbow(ItemStack stack) {
        return stack.getItem() instanceof ItemCrossbow;
    }

    public static ItemStack getCrossBow() {
        return new ItemStack(ItemRegistrySW.crossbowWood);
    }

    public static ItemStack[] selectPiglinWeapon() {
        return new ItemStack[]{new ItemStack(ItemRegistrySW.glaiveGold), new ItemStack(ItemRegistrySW.greatswordGold),
                new ItemStack(ItemRegistrySW.daggerGold), new ItemStack(ItemRegistrySW.katanaGold),
                new ItemStack(ItemRegistrySW.rapierGold), new ItemStack(ItemRegistrySW.saberGold),
                new ItemStack(ItemRegistrySW.scytheGold), new ItemStack(ItemRegistrySW.spearGold), new ItemStack(Items.GOLDEN_SWORD)};
    }

    public static ItemStack[] selectPiglinBruteWeapon() {
        return new ItemStack[]{new ItemStack(ItemRegistrySW.battleaxeGold), new ItemStack(ItemRegistrySW.clubStudded),
                new ItemStack(ItemRegistrySW.hammerGold), new ItemStack(ItemRegistrySW.halberdGold), new ItemStack(Items.GOLDEN_AXE)};
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NBTHelper.setBoolean(stack, ItemCrossbow.NBT_IS_LOADED, charged);
    }
}
