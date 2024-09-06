package com.unseen.nb.init;

import com.unseen.nb.util.ModReference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModSoundHandler {

    public static SoundEvent NYLIUM_BREAK;
    public static SoundEvent NYLIUM_STEP;
    public static SoundEvent NYLIUM_PLACE;
    public static SoundEvent NYLIUM_BLOCK_HIT;
    public static SoundEvent NYLIUM_FALL;

    public static SoundEvent CHAIN_BREAK;
    public static SoundEvent CHAIN_STEP;

    public static SoundEvent SOUL_LANTERN_BREAK;
    public static SoundEvent SOUL_LANTERN_PlACE;

    public static SoundEvent HYPHAE_BREAK;

    public static SoundEvent WART_BREAK;
    public static SoundEvent WART_STEP;
    public static SoundEvent WART_PLACE;
    public static SoundEvent WART_BLOCK_HIT;

    public static SoundEvent SOUL_SOIL_BREAK;
    public static SoundEvent SOUL_SOIL_STEP;
    public static SoundEvent SOUL_SOIL_PLACE;
    public static SoundEvent SOUL_SOIL_BLOCK_HIT;

    public static SoundEvent BASALT_BREAK;
    public static SoundEvent BASALT_STEP;
    public static SoundEvent BASALT_PlACE;
    public static SoundEvent BASALT_BLOCK_HIT;

    public static SoundEvent NETHER_ORE_BREAK;
    public static SoundEvent NETHER_ORE_STEP;
    public static SoundEvent NETHER_ORE_PLACE;
    public static SoundEvent NETHER_ORE_BLOCK_HIT;

    public static SoundEvent NETHERITE_BREAK;
    public static SoundEvent NETHERITE_STEP;
    public static SoundEvent NETHERITE_PLACE;
    public static SoundEvent NETHERITE_BLOCK_HIT;

    public static SoundEvent ANCIENT_DEBRIS_BREAK;

    public static SoundEvent LODE_STONE_PLACE;

    public static SoundEvent LODES_STONE_LOCK;

    public static SoundEvent RESPAWN_ANCHOR_AMBIENT;
    public static SoundEvent RESPAWN_ANCHOR_CHARGE;
    public static SoundEvent RESPAWN_ANCHOR_DEPLETE;
    public static SoundEvent RESPAWN_ANCHOR_SET_SPAWN;

    public static SoundEvent SHROOM_LIGHT_BREAK;
    public static SoundEvent SHROOM_LIGHT_HIT;
    public static SoundEvent SHROOM_LIGHT_STEP;
    public static SoundEvent SHROOM_LIGHT_PlACE;

    public static SoundEvent ROOTS_BREAK;
    public static SoundEvent ROOTS_HIT;
    public static SoundEvent ROOTS_STEP;
    public static SoundEvent ROOTS_PLACE;

    public static SoundEvent SPROUT_BREAK;



    //ENTITIES
    public static SoundEvent PIGLIN_IDLE;

    public static SoundEvent PIGLIN_HURT;

    public static SoundEvent PIGLIN_STEP;

    public static SoundEvent PIGLIN_ADMIRE;

    public static SoundEvent PIGLIN_DEATH;

    public static SoundEvent PIGLIN_JEALOUS;
    public static SoundEvent PIGLIN_ANGRY;
    public static SoundEvent PIGLIN_CELEBRATE;
    public static SoundEvent PIGLIN_RETREAT;
    public static SoundEvent PIGLIN_CONVERTED;

    //Strider
    public static SoundEvent STRIDER_WARBLE;
    public static SoundEvent STRIDER_RETREAT;
    public static SoundEvent STRIDER_STEP_LAVA;
    public static SoundEvent STRIDER_STEP;
    public static SoundEvent STRIDER_HURT;
    public static SoundEvent STRIDER_CHIRP;
    public static SoundEvent STRIDER_DEATH;

    //Piglin Brute
    public static SoundEvent BRUTE_HURT;
    public static SoundEvent BRUTE_DEATH;
    public static SoundEvent BRUTE_IDLE;
    public static SoundEvent BRUTE_STEP;
    public static SoundEvent BRUTE_ANGRY;

    //Piglin Zombie
    public static SoundEvent ZPIG_IDLE;
    public static SoundEvent ZPIG_HURT;
    public static SoundEvent ZPIG_DEATH;
    public static SoundEvent ZPIG_ANGRY;

    //Hoglin
    public static SoundEvent HOGLIN_STEP;
    public static SoundEvent HOGLIN_HURT;
    public static SoundEvent HOGLIN_DEATH;
    public static SoundEvent HOGLIN_RETREAT;
    public static SoundEvent HOGLIN_ANGRY;
    public static SoundEvent HOGLIN_IDLE;
    public static SoundEvent HOGLIN_CONVERTED;
    public static SoundEvent HOGLIN_ATTACK;
    //Zoglin
    public static SoundEvent ZOGLIN_IDLE;
    public static SoundEvent ZOGLIN_HURT;
    public static SoundEvent ZOGLIN_DEATH;
    public static SoundEvent ZOGLIN_ANGRY;
    public static SoundEvent ZOGLIN_STEP;
    public static SoundEvent ZOGLIN_ATTACK;

    //Biome Ambience
    public static SoundEvent CRIMSON_FOREST_AMBIENT;
    public static SoundEvent CRIMSON_FOREST_AMBIENT_CONSTANT;
    public static SoundEvent WARPED_FOREST_AMBIENT;
    public static SoundEvent WARPED_FOREST_AMBIENT_CONSTANT;
    public static SoundEvent SOUL_SAND_VALLEY_AMBIENT;
    public static SoundEvent SOUL_SAND_VALLEY_AMBIENT_CONSTANT;

    //MISC
    public static SoundEvent SOUL_SAND_SCREAM;
    public static SoundEvent NETHERITE_EQUIP;
    public static SoundEvent PIG_STEP_DISC;

    public static void registerSounds() {
    NYLIUM_BREAK = registerSound("nether_grass.break", "block");
    NYLIUM_BLOCK_HIT = registerSound("nether_grass.block_hit", "block");
    NYLIUM_STEP = registerSound("nether_grass.step", "block");
    NYLIUM_PLACE = registerSound("nether_grass.place", "block");
    NYLIUM_FALL = registerSound("nether_grass.fall", "block");
    CHAIN_BREAK = registerSound("chain.break", "block");
    CHAIN_STEP = registerSound("chain.step", "block");

    HYPHAE_BREAK = registerSound("hyphae.break", "block");

    WART_BREAK = registerSound("wart.break", "block");
    WART_STEP = registerSound("wart.step", "block");
    WART_PLACE = registerSound("wart.place", "block");
    WART_BLOCK_HIT = registerSound("wart.hit", "block");

    SOUL_SOIL_BREAK = registerSound("soul_soil.break", "block");
    SOUL_SOIL_STEP = registerSound("soul_soil.step", "block");
    SOUL_SOIL_PLACE = registerSound("soul_soil.place", "block");
    SOUL_SOIL_BLOCK_HIT = registerSound("soul_soil.hit", "block");

    BASALT_BREAK = registerSound("basalt.break", "block");
    BASALT_STEP = registerSound("basalt.step", "block");
    BASALT_PlACE = registerSound("basalt.place", "block");
    BASALT_BLOCK_HIT = registerSound("basalt.hit", "block");

    NETHER_ORE_BREAK = registerSound("nether_ore.break", "block");
    NETHER_ORE_STEP = registerSound("nether_ore.step", "block");
    NETHER_ORE_PLACE = registerSound("nether_ore.place", "block");
    NETHER_ORE_BLOCK_HIT = registerSound("nether_ore.hit", "block");

    NETHERITE_BREAK = registerSound("netherite.break", "block");
    NETHERITE_STEP = registerSound("netherite.step", "block");
    NETHERITE_PLACE = registerSound("netherite.place", "block");
    NETHERITE_BLOCK_HIT = registerSound("netherite.hit", "block");

    ANCIENT_DEBRIS_BREAK = registerSound("debris.break", "block");

    LODE_STONE_PLACE = registerSound("lode_stone.place", "block");
    LODES_STONE_LOCK = registerSound("lode_stone.lock", "block");

    RESPAWN_ANCHOR_AMBIENT = registerSound("respawn_anchor.ambient", "block");
    RESPAWN_ANCHOR_CHARGE = registerSound("respawn_anchor.charge", "block");
    RESPAWN_ANCHOR_DEPLETE = registerSound("respawn_anchor.deplete", "block");
    RESPAWN_ANCHOR_SET_SPAWN = registerSound("respawn_anchor.set_spawn", "block");

    SHROOM_LIGHT_BREAK = registerSound("shroom_light.break", "block");
    SHROOM_LIGHT_STEP = registerSound("shroom_light.step", "block");
    SHROOM_LIGHT_PlACE = registerSound("shroom_light.place", "block");
    SHROOM_LIGHT_HIT = registerSound("shroom_light.hit", "block");

    ROOTS_BREAK = registerSound("roots.break", "block");
    ROOTS_STEP = registerSound("roots.step", "block");
    ROOTS_PLACE = registerSound("roots.place", "block");
    ROOTS_HIT = registerSound("roots.hit", "block");

    SPROUT_BREAK = registerSound("sprout.break", "block");

    SOUL_LANTERN_BREAK = registerSound("soul_lantern.break", "block");
    SOUL_LANTERN_PlACE = registerSound("soul_lantern.place", "block");

    //ENTITIES
        PIGLIN_STEP = registerSound("piglin.step", "entity");
        PIGLIN_HURT = registerSound("piglin.hurt", "entity");
        PIGLIN_IDLE = registerSound("piglin.idle", "entity");
        PIGLIN_DEATH = registerSound("piglin.death", "entity");
        PIGLIN_ADMIRE = registerSound("piglin.admire", "entity");
        PIGLIN_JEALOUS = registerSound("piglin.jealous", "entity");
        PIGLIN_ANGRY = registerSound("piglin.angry", "entity");
        PIGLIN_CELEBRATE = registerSound("piglin.celebrate", "entity");
        PIGLIN_RETREAT = registerSound("piglin.retreat", "entity");
        PIGLIN_CONVERTED = registerSound("piglin.converted", "entity");
     //STRIDER
     STRIDER_CHIRP = registerSound("strider.chirp", "entity"); //DONE
     STRIDER_DEATH = registerSound("strider.death", "entity"); //DONE
     STRIDER_HURT = registerSound("strider.hurt", "entity"); //DONE
     STRIDER_RETREAT = registerSound("strider.retreat", "entity"); //DONE
     STRIDER_STEP = registerSound("strider.step", "entity"); //DONE
     STRIDER_STEP_LAVA = registerSound("strider.step_lava", "entity"); //DONE
     STRIDER_WARBLE = registerSound("strider.warble", "entity"); //DONE
        //Piglin Brute
        BRUTE_DEATH = registerSound("brute.death", "entity");
        BRUTE_HURT = registerSound("brute.hurt", "entity");
        BRUTE_IDLE = registerSound("brute.idle", "entity");
        BRUTE_STEP = registerSound("brute.step", "entity");
        BRUTE_ANGRY = registerSound("brute.angry", "entity");
        //Zombie Piglin
        ZPIG_ANGRY = registerSound("zpig.angry", "entity");
        ZPIG_DEATH = registerSound("zpig.death", "entity");
        ZPIG_HURT = registerSound("zpig.hurt", "entity");
        ZPIG_IDLE = registerSound("zpig.idle", "entity");
        //Hoglin
        HOGLIN_ANGRY = registerSound("hoglin.angry", "entity"); //DONE
        HOGLIN_HURT = registerSound("hoglin.hurt", "entity"); //DONE
        HOGLIN_IDLE = registerSound("hoglin.idle", "entity"); //DONE
        HOGLIN_DEATH = registerSound("hoglin.death", "entity"); //DONE
        HOGLIN_STEP = registerSound("hoglin.step", "entity"); //DONE
        HOGLIN_RETREAT = registerSound("hoglin.retreat", "entity"); //DONE
        HOGLIN_ATTACK = registerSound("hoglin.attack", "entity"); //DONE
        HOGLIN_CONVERTED = registerSound("hoglin.converted", "entity"); //DONE
        //ZOGLIN
        ZOGLIN_ANGRY = registerSound("zoglin.angry", "entity");
        ZOGLIN_DEATH = registerSound("zoglin.death", "entity");
        ZOGLIN_HURT = registerSound("zoglin.hurt", "entity");
        ZOGLIN_IDLE = registerSound("zoglin.idle", "entity");
        ZOGLIN_STEP = registerSound("zoglin.step", "entity");
        ZOGLIN_ATTACK = registerSound("zoglin.attack", "entity");
        //Biome Ambience
        CRIMSON_FOREST_AMBIENT = registerSound("crimson_forest.ambience", "ambient");
        CRIMSON_FOREST_AMBIENT_CONSTANT = registerSound("crimson_forest.constant", "ambient");
        WARPED_FOREST_AMBIENT = registerSound("warped_forest.ambience", "ambient");
        WARPED_FOREST_AMBIENT_CONSTANT = registerSound("warped_forest.constant", "ambient");
        SOUL_SAND_VALLEY_AMBIENT = registerSound("soulsand_valley.ambience", "ambient");
        SOUL_SAND_VALLEY_AMBIENT_CONSTANT = registerSound("soulsand_valley.constant", "ambient");

        //MISC
        SOUL_SAND_SCREAM = registerSound("soul.scream", "particle");
        NETHERITE_EQUIP = registerSound("netherite.equip", "armor");
        PIG_STEP_DISC = registerSound("record.pigstep", "music");

    }


    private static SoundEvent registerSound(String name, String category) {
        String fullName = category + "." + name;
        ResourceLocation location = new ResourceLocation(ModReference.MOD_ID, fullName);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(fullName);
        ForgeRegistries.SOUND_EVENTS.register(event);

        return event;
    }
}
