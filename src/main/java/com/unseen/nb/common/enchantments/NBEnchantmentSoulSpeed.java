package com.unseen.nb.common.enchantments;

import com.unseen.nb.util.ModReference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.UUID;

public class NBEnchantmentSoulSpeed extends Enchantment {

    public static final UUID SOUL_SPEED_MODIFIER = UUID.fromString("0483aa4a-af8d-36a2-8693-22bec9caa265");

    public NBEnchantmentSoulSpeed(String nameIn, Rarity rarityIn, EnumEnchantmentType typeIn) {
        super(rarityIn, typeIn, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
        this.setRegistryName(ModReference.MOD_ID, nameIn);
        this.setName(nameIn);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
