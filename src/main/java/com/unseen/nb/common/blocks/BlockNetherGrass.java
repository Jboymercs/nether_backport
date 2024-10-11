package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockNetherGrass extends BlockBase implements IGrowable
{
    public BlockNetherGrass(String name, Material material) {
        super(name, material);
    }

    public BlockNetherGrass(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);
        this.setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3)) {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
                IBlockState stateAbove = worldIn.getBlockState(pos.up());

            /* Decay only needs to occur if directly above this is a full non-opaque block. */
            if(stateAbove.isFullCube())
            { worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState()); }
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    { return true; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    { return true; }

    /** TODO: Add logic for Nether plant generation, requires research */
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {}
}
