package com.unseen.nb.init;

import com.unseen.nb.common.items.ItemFungusOnStick;
import com.unseen.nb.common.items.ItemLodeStoneCompass;
import com.unseen.nb.common.items.ItemPigStepDisc;
import com.unseen.nb.common.items.netherite.ItemNBHorseArmor;
import com.unseen.nb.util.ModReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();


    public static final HorseArmorType NETHERITE_HORSE_ARMOR = EnumHelper.addHorseArmor("netheriteHorseArmor", ModReference.MOD_ID + ":textures/models/armor/horse/horse_armor_netherite.png", 14);
    public static final Item FUNGUS_ON_STICK = new ItemFungusOnStick("fungus_on_stick", CreativeTabs.TOOLS);
    public static final Item LODE_STONE_COMPASS = new ItemLodeStoneCompass("lode_stone_compass");

    public static final Item NETHERITE_HORSE_ARMOR_ITEM = new ItemNBHorseArmor("netherite_horse_armor", NETHERITE_HORSE_ARMOR, CreativeTabs.MISC);
    //Keeping Pigstep cause it has no generation rules in FutureMC

    public static final Item PIGSTEP_MUSIC_DISC = new ItemPigStepDisc("pig_step_disc", ModSoundHandler.PIG_STEP_DISC);


}
