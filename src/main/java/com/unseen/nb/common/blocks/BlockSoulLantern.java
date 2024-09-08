package com.unseen.nb.common.blocks;

import com.unseen.nb.common.blocks.base.BlockBase;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSoulLantern extends BlockBase {
    public static final PropertyBool IS_HANGING = PropertyBool.create("hanging");
    protected static final AxisAlignedBB CRYSTAL_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
    public BlockSoulLantern(String name, Material material) {
        super(name, material);
    }

    public BlockSoulLantern(String name, Material material, float hardness, float resistance, SoundType soundType) {
        super(name, material, hardness, resistance, soundType);

    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && isValidBlock(worldIn, pos, worldIn.getBlockState(pos.up())) || isValidBlock(worldIn, pos, worldIn.getBlockState(pos.down()));
    }

    @Deprecated
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CRYSTAL_AABB;
    }


    @Deprecated
    public boolean isTopSolid(IBlockState state)
    {
        return false;
    }

    @Deprecated
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {   if(facing == EnumFacing.UP) {
        return getDefaultState().withProperty(IS_HANGING, false);
    }
        if(facing == EnumFacing.DOWN) {
            return getDefaultState().withProperty(IS_HANGING, true);
        }
        return this.getStateFromMeta(meta);
    }

    protected boolean isValidBlock(World world, BlockPos pos, IBlockState blockState) {
        return blockState.isSideSolid(world, pos, EnumFacing.DOWN) || blockState.isSideSolid(world, pos, EnumFacing.UP) || world.getBlockState(pos.up()) == ModBlocks.CHAINS.getDefaultState();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, IS_HANGING);
    }


    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(IS_HANGING) ? 1 : 0;
    }


    @Deprecated
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(IS_HANGING, meta == 1);
    }

}
