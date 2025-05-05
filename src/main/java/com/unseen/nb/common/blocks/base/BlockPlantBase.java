package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockPlantBase extends BlockBush implements IHasModel, net.minecraftforge.common.IShearable
{
    protected static final AxisAlignedBB NORMAL_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
    protected static final AxisAlignedBB SMALL_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.2D, 0.9D);

    boolean isSmall;
    boolean requiresShears;

    public BlockPlantBase(String name, Material materialIn, SoundType soundType)
    {
        super(materialIn);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(soundType);
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    /** Use this to set if this plant NEEDS Shears to be obtained! */
    public BlockPlantBase setRequiresShears(boolean requiresShearsIn)
    { requiresShears = requiresShearsIn; return this; }

    /** Makes this plant use the smaller hitbox, AKA for Nether Sprouts. */
    public BlockPlantBase setIsSmall(boolean isSmallIn)
    { isSmall = isSmallIn; return this; }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND || block == ModBlocks.CRIMSON_GRASS || block == ModBlocks.WARPED_GRASS || block == ModBlocks.SOUL_SOIL;
    }

    /** Swap the Bounding Box if it is a small plant. */
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return isSmall ? SMALL_AABB : NORMAL_AABB; }

    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() { return EnumOffsetType.NONE; }

    @Override
    public void registerModels() { Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return requiresShears ? null: super.getItemDropped(state, rand, fortune); }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) { return requiresShears; }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) { return java.util.Arrays.asList(new ItemStack(this, 1)); }
}
