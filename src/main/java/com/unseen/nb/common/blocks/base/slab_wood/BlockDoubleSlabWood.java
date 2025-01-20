package com.unseen.nb.common.blocks.base.slab_wood;

import com.unseen.nb.common.blocks.base.slab.BlockSlabBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;

public class BlockDoubleSlabWood extends BlockSlabBase {

    public BlockDoubleSlabWood(String name, Material material, CreativeTabs tabs, BlockSlab half, float hardness, float resistance, SoundType soundType) {
        super(name, material, half);
        this.setCreativeTab(tabs);
        setResistance(resistance);
        setSoundType(soundType);
        setHardness(hardness);
    }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState state)
    {
        return "axe";
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state)
    {
        return type != null && type.equals(getHarvestTool(state));
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}
