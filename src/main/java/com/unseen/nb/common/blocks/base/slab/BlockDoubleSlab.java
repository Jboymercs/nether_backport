package com.unseen.nb.common.blocks.base.slab;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDoubleSlab extends BlockSlabBase
{
    public BlockDoubleSlab(String name, Material material, CreativeTabs tabs, BlockSlab half, float hardness, float resistance, SoundType soundType)
    {
        super(name, material, half);
        this.setCreativeTab(tabs);
        setResistance(resistance);
        setSoundType(soundType);
        setHardness(hardness);
    }

    @Override
    public boolean isDouble() { return true; }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    { return Item.getItemFromBlock(half); }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) { return new ItemStack(half); }
}
