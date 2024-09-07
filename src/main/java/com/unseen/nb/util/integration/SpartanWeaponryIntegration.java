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

    public static ItemStack[] selectPiglinWeapon() {
        return new ItemStack[]{ItemRegistrySW.glaiveGold.getDefaultInstance(), ItemRegistrySW.greatswordGold.getDefaultInstance(),
                ItemRegistrySW.daggerGold.getDefaultInstance(), ItemRegistrySW.katanaGold.getDefaultInstance(),
                ItemRegistrySW.rapierGold.getDefaultInstance(), ItemRegistrySW.saberGold.getDefaultInstance(),
                ItemRegistrySW.scytheGold.getDefaultInstance(), ItemRegistrySW.spearGold.getDefaultInstance(), Items.GOLDEN_SWORD.getDefaultInstance()};
    }

    public static ItemStack[] selectPiglinBruteWeapon() {
        return new ItemStack[]{ItemRegistrySW.battleaxeGold.getDefaultInstance(), ItemRegistrySW.clubStudded.getDefaultInstance(),
                ItemRegistrySW.hammerGold.getDefaultInstance(), ItemRegistrySW.halberdGold.getDefaultInstance(), Items.GOLDEN_AXE.getDefaultInstance()};
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NBTHelper.setBoolean(stack, ItemCrossbow.NBT_IS_LOADED, charged);
    }
}
