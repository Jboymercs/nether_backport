package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.handler.RegistryHandler;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.util.mapper.AdvancedStateMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockVineUpBase extends BlockBush implements IHasModel, RegistryHandler.IStateMappedBlock{
    protected static final AxisAlignedBB CRYSTAL_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    public static final PropertyBool IS_TOP = PropertyBool.create("is_top");
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

    public BlockVineUpBase(String name, Material materialIn, SoundType soundType) {
        super(materialIn);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(soundType);
        this.setTickRandomly(true);
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(IS_TOP, true));
    }

    /** Randomize the Age when placed. */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        Random rand = worldIn.rand;
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(rand.nextInt(14 + 1)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AGE, IS_TOP});
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    { return state.withProperty(IS_TOP, isTop(worldIn, pos)); }

    public boolean isTop(IBlockAccess worldIn, BlockPos pos)
    { return worldIn.getBlockState(pos.up()).getBlock() != this; }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return null; }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    { return canBlockStay(world, pos, world.getBlockState(pos)); }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    { return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.DOWN) || worldIn.getBlockState(pos.down()).getBlock() == this; }

    /** If this becomes a Top, randomize the age. */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (isTop(worldIn, pos))
        {
            if (state.getValue(AGE) == 0) worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(worldIn.rand.nextInt(14 + 1))), 2);
        }
        else
        { worldIn.setBlockState(pos, state.withProperty(AGE, 0), 2); }
        this.checkAndDropBlock(worldIn, pos, state);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int currentAge = state.getValue(AGE);
        if(isTop(worldIn, pos) && currentAge < 15 && rand.nextInt(16) == 0 && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos, state.withProperty(AGE, 0));
            worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, currentAge + 1));
        }
    }

    // TODO: Test in Multiplayer/over a Server!
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;

            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
            {
                player.motionY = 0.2D;
                player.fallDistance = 0.0F;
            }
        }
    }

    @Override public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) { return true; }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CRYSTAL_AABB;
    }

    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.NONE;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public int getMetaFromState(IBlockState state)
    { return ((Integer)state.getValue(AGE)); }

    @Override
    public IBlockState getStateFromMeta(int meta)
    { return this.getDefaultState().withProperty(AGE, meta); }

    @Override
    public void setStateMapper(AdvancedStateMap.Builder builder) {
        builder.ignore(AGE);
    }
}
