package com.unseen.nb.common.blocks;

import com.unseen.nb.Main;
import com.unseen.nb.client.color.ModColors;
import com.unseen.nb.client.particles.ParticleObsidianTear;
import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.proxy.ClientProxy;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCryingObsidian extends BlockBase {
    public BlockCryingObsidian(String name, Material material) {
        super(name, material);
    }

    public BlockCryingObsidian(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) pos.getX();
        double d1 = (double) pos.getY();
        double d2 = (double) pos.getZ();
        if (rand.nextInt(10) == 0)
        {
            double d3 = d0 + (double)rand.nextFloat();
            double d5 = d1 - 0.05;
            double d7 = d2 + (double)rand.nextFloat();
            //Insert Particles for Crying Obsidian
            //worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d3, d5, d7, 0.0D, 0.0D, 0.0D);
           // ModParticles.spawnColoredDrip(worldIn, new Vec3d(d3, d5, d7), ModColors.PURPLE, new Vec3d(0,0,0));
            Main.proxy.spawnParticle(1, d3, d5, d7, 0,0,0);


        }
    }
}
