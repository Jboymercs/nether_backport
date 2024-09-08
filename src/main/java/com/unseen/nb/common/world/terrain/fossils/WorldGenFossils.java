package com.unseen.nb.common.world.terrain.fossils;

import com.unseen.nb.common.world.base.WorldGenNB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenFossils extends WorldGenNB {

    public WorldGenFossils(String structureName) {
        super("fossils/" + structureName);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if(!worldIn.isAirBlock(position.add(4, -1, 4))) {
            return super.generate(worldIn, rand, position);
        }
        return false;
    }
}
