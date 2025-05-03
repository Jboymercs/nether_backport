package com.unseen.nb.common.blocks.base.slab;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockHalfSlab extends BlockSlabBase
{
    public BlockHalfSlab(String name, Material maeterialIn, CreativeTabs tabs, BlockSlab half, float hardness, float resistance, SoundType soundType)
    {
        super(name, maeterialIn, half);
        this.setCreativeTab(tabs);
        /* Item registry is now handled directly within `RegistryHandler`, this is so the Slab and Double Slab can both properly be registered before the item! */
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    @Override
    public boolean isDouble() { return false; }
}
