package com.unseen.nb.common.blocks.base.slab;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public abstract class BlockSlabBase extends BlockSlab implements IHasModel {
    Block half;
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);

    public BlockSlabBase(String name, Material materialIn, BlockSlab half) {
        super(materialIn);
        setTranslationKey(name);
        setRegistryName(name);
        this.useNeighborBrightness = !this.isDouble();
        this.setLightOpacity(255);
        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);

        this.half = half;

        ModBlocks.BLOCKS.add(this);
    }

    /** An easy method for setting up harvesting per individual block that extends this */
    public BlockSlabBase setHarvestInfo(String tool, int level)
    {
        this.setHarvestLevel(tool, level);
        return this;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if (!this.isDouble()) state = state.withProperty(HALF, ((meta&8) !=0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        return state;
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) meta |= 8;
        return meta;
    }

    @Override
    protected BlockStateContainer createBlockState()
    { return new BlockStateContainer(this, this.isDouble() ? new IProperty[]{VARIANT} : new IProperty[]{VARIANT, HALF}); }

    @Override
    public String getTranslationKey(int meta) { return super.getTranslationKey(); }
    @Override
    public IProperty<?> getVariantProperty() { return VARIANT; }
    @Override
    public Comparable<?> getTypeForItem(ItemStack itemStack) { return Variant.DEFAULT;  }

    public static enum Variant implements IStringSerializable
    {
        DEFAULT;
        @Override
        public String getName() {  return "default"; }
    }

    @Override
    public void registerModels()
    { Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }
}
