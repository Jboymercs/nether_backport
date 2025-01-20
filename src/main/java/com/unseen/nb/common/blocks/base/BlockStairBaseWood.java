package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nullable;

public class BlockStairBaseWood extends BlockStairs implements IHasModel {
    protected BlockStairBaseWood(IBlockState modelState) {
        super(modelState);
    }

    public BlockStairBaseWood(String name, IBlockState modelState) {
        super(modelState);
        setTranslationKey(name);
        setRegistryName(name);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockStairBaseWood(String name, IBlockState modelState, float hardness, float resistance, SoundType soundType) {
        this(name, modelState);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
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
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
