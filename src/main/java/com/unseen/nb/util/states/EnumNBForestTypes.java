package com.unseen.nb.util.states;

import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.Block;

public enum EnumNBForestTypes {
    CRIMSON(),
    WARPED();

    public EnumNBForestTypes getOpposite() {
        switch(this) {
            case CRIMSON:
                return WARPED;
            case WARPED:
                return CRIMSON;
        }
        return null;
    }

    public Block getVegetationBlocks(String blockID) {
        switch(this) {
            case CRIMSON:
                switch(blockID) {
                    case "nylium":
                        return ModBlocks.CRIMSON_GRASS;
                    case "roots":
                        return ModBlocks.CRIMSON_ROOTS;
                    case "fungus":
                        return ModBlocks.CRIMSON_FUNGUS;
                    case "stem":
                        return ModBlocks.CRIMSON_STEM;
                    case "wart":
                        return ModBlocks.CRIMSON_WART;
                }
            case WARPED:
                switch(blockID) {
                    case "nylium":
                        return ModBlocks.WARPED_GRASS;
                    case "roots":
                        return ModBlocks.WARPED_ROOTS;
                    case "fungus":
                        return ModBlocks.WARPED_FUNGUS;
                    case "stem":
                        return ModBlocks.WARPED_STEM;
                    case "wart":
                        return ModBlocks.WARPED_WART;
                }
        }
        return null;
    }
}
