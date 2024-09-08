package com.unseen.nb.init;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;

public class NBSoundTypes {

    public static final SoundType NYLIUM = new SoundType(1, 1, ModSoundHandler.NYLIUM_BREAK, ModSoundHandler.NYLIUM_STEP,
            ModSoundHandler.NYLIUM_PLACE, ModSoundHandler.NYLIUM_BLOCK_HIT, ModSoundHandler.NYLIUM_FALL);

    public static final SoundType HYPHAE = new SoundType(1, 1, ModSoundHandler.HYPHAE_BREAK, ModSoundHandler.HYPHAE_BREAK,
            ModSoundHandler.HYPHAE_BREAK, ModSoundHandler.HYPHAE_BREAK, ModSoundHandler.HYPHAE_BREAK);

    public static final SoundType CHAIN = new SoundType(1, 1, ModSoundHandler.CHAIN_BREAK, ModSoundHandler.CHAIN_STEP,
            ModSoundHandler.CHAIN_STEP, ModSoundHandler.CHAIN_BREAK, ModSoundHandler.CHAIN_STEP);

    public static final SoundType LANTERN = new SoundType(1, 1, ModSoundHandler.SOUL_LANTERN_BREAK, ModSoundHandler.SOUL_LANTERN_PlACE,
            ModSoundHandler.SOUL_LANTERN_PlACE, ModSoundHandler.SOUL_LANTERN_BREAK, ModSoundHandler.SOUL_LANTERN_PlACE);
    public static final SoundType WART = new SoundType(1, 1, ModSoundHandler.WART_BREAK, ModSoundHandler.WART_STEP,
            ModSoundHandler.WART_PLACE, ModSoundHandler.WART_BLOCK_HIT, ModSoundHandler.WART_PLACE);

    public static final SoundType SOUL_SOIL = new SoundType(1, 1, ModSoundHandler.SOUL_SOIL_BREAK, ModSoundHandler.SOUL_SOIL_STEP,
            ModSoundHandler.SOUL_SOIL_PLACE, ModSoundHandler.SOUL_SOIL_BLOCK_HIT, ModSoundHandler.SOUL_SOIL_PLACE);

    public static final SoundType BASALT = new SoundType(1, 1, ModSoundHandler.BASALT_BREAK, ModSoundHandler.BASALT_STEP,
            ModSoundHandler.BASALT_PlACE, ModSoundHandler.BASALT_BLOCK_HIT, ModSoundHandler.BASALT_PlACE);

    public static final SoundType NETHER_ORE = new SoundType(1, 1, ModSoundHandler.NETHER_ORE_BREAK, ModSoundHandler.NETHER_ORE_STEP,
            ModSoundHandler.NETHER_ORE_PLACE, ModSoundHandler.NETHER_ORE_BLOCK_HIT, ModSoundHandler.NETHER_ORE_PLACE);

    public static final SoundType NETHERITE = new SoundType(1, 1, ModSoundHandler.NETHERITE_BREAK, ModSoundHandler.NETHERITE_STEP,
            ModSoundHandler.NETHERITE_PLACE, ModSoundHandler.NETHERITE_BLOCK_HIT, ModSoundHandler.NETHERITE_PLACE);

    public static final SoundType ANCIENT_DEBRIS = new SoundType(1, 1, ModSoundHandler.ANCIENT_DEBRIS_BREAK, ModSoundHandler.ANCIENT_DEBRIS_BREAK,
            ModSoundHandler.ANCIENT_DEBRIS_BREAK, ModSoundHandler.ANCIENT_DEBRIS_BREAK, ModSoundHandler.ANCIENT_DEBRIS_BREAK);

    public static final SoundType LODE_STONE = new SoundType(1, 1, SoundEvents.BLOCK_STONE_BREAK, SoundEvents.BLOCK_STONE_STEP,
            ModSoundHandler.LODE_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL);

    public static final SoundType SHROOM_LIGHT = new SoundType(1, 1, ModSoundHandler.SHROOM_LIGHT_BREAK, ModSoundHandler.SHROOM_LIGHT_STEP,
            ModSoundHandler.SHROOM_LIGHT_BREAK, ModSoundHandler.SHROOM_LIGHT_HIT, ModSoundHandler.SHROOM_LIGHT_PlACE);

    public static final SoundType ROOTS = new SoundType(1, 1, ModSoundHandler.ROOTS_BREAK, ModSoundHandler.ROOTS_STEP,
            ModSoundHandler.ROOTS_PLACE, ModSoundHandler.ROOTS_HIT, ModSoundHandler.ROOTS_PLACE);

    public static final SoundType SPROUT = new SoundType(1, 1, ModSoundHandler.SPROUT_BREAK, ModSoundHandler.SPROUT_BREAK,
            ModSoundHandler.SPROUT_BREAK, ModSoundHandler.SPROUT_BREAK, ModSoundHandler.SPROUT_BREAK);
}
