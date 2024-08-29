package com.unseen.nb.common.entity.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PathNavigateLava extends PathNavigateGround {
    public PathNavigateLava(EntityLiving entitylivingIn, World worldIn) {
        super(entitylivingIn, worldIn);
    }

    protected PathFinder getPathFinder() {
        this.nodeProcessor = new WalkNodeProcessor();
        return new PathFinder(nodeProcessor);
    }

    public boolean canEntityStandOnPos(BlockPos pos) {
        return this.world.getBlockState(pos).getBlock() == Blocks.LAVA || super.canEntityStandOnPos(pos);
    }
}
