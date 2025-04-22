package com.unseen.nb.common.blocks;

import com.google.common.collect.Lists;
import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.config.BlocksConfig;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

public class BlockSoulSoil extends BlockBase
{
    public BlockSoulSoil(String name, Material material, float hardness, float resistance, SoundType soundType)
    { super(name, material, hardness, resistance, soundType); }

    /**
     * This event occurs whenever Soul Soil recieves a Neighbor Update, which makes it check for Lava above, and any touching blocks from the `basaltGeneratorBlock` list in the config.
     *
     * This would be better to run based on the Lava's own update, but that would require a Mixin, which we want to avoid.
     * */
    @SuppressWarnings("deprecated")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();

        if (blockAbove == Blocks.LAVA || blockAbove == Blocks.FLOWING_LAVA)
        {
            for (EnumFacing facing : Lists.newArrayList(EnumFacing.values()))
            {
                /* No need to check the down direction, as that is obviously Soul Soil. */
                if (facing == EnumFacing.DOWN) continue;

                if (ArrayUtils.contains(BlocksConfig.basaltGeneratorBlock, worldIn.getBlockState(pos.up().offset(facing)).getBlock().getRegistryName().toString()))
                {
                    preformEffects(worldIn, pos.up());
                    worldIn.setBlockState(pos.up(), ModBlocks.BASALT.getDefaultState());
                    break;
                }
            }
        }

        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    /** Pretty much copied from `BlockLiquid;triggerMixEffects`, which has private access. */
    protected void preformEffects(World worldIn, BlockPos pos)
    {
        double d0 = pos.getX();
        double d1 = pos.getY();
        double d2 = pos.getZ();
        worldIn.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        if (worldIn.isRemote)
        {
            for (int i = 0; i < 8; ++i)
            { worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D); }
        }
    }
}
