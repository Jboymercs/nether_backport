package com.unseen.nb.common.blocks;

import com.unseen.nb.Main;
import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCryingObsidian extends BlockBase
{
    public BlockCryingObsidian(String name, Material material, float hardness, float resistance, SoundType soundType)
    { super(name, material, hardness, resistance, soundType); }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    { if (rand.nextInt(5) == 0) this.spawnSurfaceParticles(worldIn, pos); }

    /** Spawns particles to slide on the surfaces of the Crying Obsidian block. */
    public void spawnSurfaceParticles(World worldIn, BlockPos pos)
    {
        /* Get a random Facing to spawn on. */
        EnumFacing facing = EnumFacing.random(worldIn.rand);

        /* If Facing is not `UP` and if the position isn't blocked by an Opaque Block, continue. */
        if (facing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(facing)).isOpaqueCube())
        {
            double x = pos.getX() + 0.5 + (facing.getAxis() == EnumFacing.Axis.X ? 0.55 * facing.getXOffset() : ((worldIn.rand.nextDouble() * 0.8) - 0.4));
            double y = pos.getY() + 0.45 + (facing.getAxis() == EnumFacing.Axis.Y ? 0.55 * facing.getYOffset() : ((worldIn.rand.nextDouble() * 0.8) - 0.4));
            double z = pos.getZ() + 0.5 + (facing.getAxis() == EnumFacing.Axis.Z ? 0.55 * facing.getZOffset() : ((worldIn.rand.nextDouble() * 0.8) - 0.4));

            Main.proxy.spawnParticle(0, x, y, z, 0, 0, 0, 0);
        }
    }
}