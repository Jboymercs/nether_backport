package com.unseen.nb.common.world.ore;

import com.unseen.nb.common.world.base.WorldGenOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Credit To SmileyCorps from DeeperDepths for this Ore Generator
 */
public class WorldGenNetherOre extends WorldGenOre {

    private final IBlockState state;

    public WorldGenNetherOre(int num, IBlockState state) {
        super(num);
        this.state = state;
    }

    @Override
    protected IBlockState getState(World world, Random rand, BlockPos pos) {
        return state;
    }
}
