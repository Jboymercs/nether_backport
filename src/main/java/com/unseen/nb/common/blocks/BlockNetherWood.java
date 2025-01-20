package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import javax.annotation.Nullable;

public class BlockNetherWood extends BlockBase {


    public BlockNetherWood(String name, Material material) {
        super(name, material);
    }

    public BlockNetherWood(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);
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
}
