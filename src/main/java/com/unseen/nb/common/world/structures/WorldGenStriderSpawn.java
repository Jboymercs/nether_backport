package com.unseen.nb.common.world.structures;

import com.unseen.nb.common.entity.entities.EntityStrider;
import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.config.NBEntitiesConfig;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenStriderSpawn extends WorldGenNB {

    private int spacing = 0;
    public WorldGenStriderSpawn(String structureName) {
        super("misc/" + structureName);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        BlockPos newPos = new BlockPos(position.getX(), 32, position.getZ());

        if(worldIn.getBlockState(newPos.down()) == Blocks.LAVA.getDefaultState() && worldIn.isAirBlock(newPos.up()) &&
        worldIn.isAirBlock(newPos.add(1,0,0)) && worldIn.isAirBlock(newPos.add(-1,0,0)) && worldIn.isAirBlock(newPos.add(0,0,1)) &&
        worldIn.isAirBlock(newPos.add(0,0,-1))) {
            if(spacing > NBEntitiesConfig.strider_lava_spawn_rate) {
                spacing = 0;
                return super.generate(worldIn, rand, newPos);
            }
        }
        spacing++;
        return false;
    }
    @Override
    protected void handleDataMarker(String function, BlockPos pos, World world, Random random) {
        if(function.startsWith("mob")) {
            EntityStrider striderSpawn = new EntityStrider(world);
            striderSpawn.setPosition(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(striderSpawn);
            world.setBlockToAir(pos);
        }
    }
}
