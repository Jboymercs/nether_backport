package com.unseen.nb.init;


import com.unseen.nb.common.enchantments.NBEnchantmentSoulSpeed;
import com.unseen.nb.util.ModReference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class ModEnchantments {
    public static final List<Enchantment> ENCHANTMENT_LIST = new ArrayList<Enchantment>();

    public static final Enchantment SOUL_SPEED = addEnchantment(new NBEnchantmentSoulSpeed("soul_speed", Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET));

    private static Enchantment addEnchantment(Enchantment enchantmentIn) {
        ModEnchantments.ENCHANTMENT_LIST.add(enchantmentIn);
        return enchantmentIn;
    }

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        for(Enchantment enchantmentIn : ENCHANTMENT_LIST) {
            event.getRegistry().register(enchantmentIn);
        }
    }
}
