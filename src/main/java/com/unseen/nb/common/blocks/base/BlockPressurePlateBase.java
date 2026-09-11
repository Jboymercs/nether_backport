package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockPressurePlateBase extends BlockPressurePlate implements IHasModel {
    private boolean wood;

    public BlockPressurePlateBase(String name, BlockPressurePlate.Sensitivity sensitivity) {
        super(Material.GROUND, sensitivity);
        setTranslationKey(name);
        setRegistryName(name);
        this.setDefaultState(this.blockState.getBaseState());
        this.setLightOpacity(0);
        this.setTickRandomly(true);
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockPressurePlateBase(String name, float hardness, float resistance, CreativeTabs tab, BlockPressurePlate.Sensitivity sensitivity, boolean wood, SoundType soundType) {
        this(name, sensitivity);
        this.wood = wood;
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(tab);
        setSoundType(soundType);
    }

    /** We are Overriding the material, as inheriting from `BlockLog` tries making it `Material.WOOD`, but we don't want the flammability of that! */
    @Override
    public Material getMaterial(IBlockState state)
    { return Material.GROUND; }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState state)
    {
        if (wood)
            return "axe";
        else 
            return "pickaxe";
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

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return BlockPlanks.EnumType.SPRUCE.getMapColor();
    }
}
