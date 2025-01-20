package com.unseen.nb.common.blocks.base.slab_wood;

import com.unseen.nb.common.blocks.base.slab.BlockSlabBase;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

import javax.annotation.Nullable;

public class BlockHalfSlabWood extends BlockSlabBase {

    public BlockHalfSlabWood(String name, Material maeterialIn, CreativeTabs tabs, BlockSlab half, BlockSlab doubleSlab, float hardness, float resistance, SoundType soundType) {

        super(name, maeterialIn, half);
        this.setCreativeTab(tabs);
        ModItems.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);

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
        return false;
    }
}
