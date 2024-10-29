package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.handler.RegistryHandler;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.util.mapper.AdvancedStateMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockVineUpBase extends BlockBush implements IGrowable, IHasModel, RegistryHandler.IStateMappedBlock, net.minecraftforge.common.IShearable{
    protected static final AxisAlignedBB TWISTING_MID = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    protected static final AxisAlignedBB TWISTING_TOP = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.9375D, 0.75D);

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

    /** 33% chance to drop, +11% per Fortune level, Fortune 3 or above sets to 100% chance */
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = 33 + fortune * 11;
        if (fortune >= 3) chance = 100;

        if (rand.nextInt(100) < chance)
        {
            ItemStack drop = new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state));
            if (!drop.isEmpty()) drops.add(drop);
        }
    }

    /** Allows obtaining via Shears */
    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos)
    { return true; }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    { return java.util.Arrays.asList(new ItemStack(this, 1)); }

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

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();

        if ((Integer)state.getValue(AGE).intValue() != 15 && isTop(worldIn, pos))
        {
            if (item instanceof ItemShears)
            {
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, 15), 2);
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int currentAge = state.getValue(AGE);
        if(isTop(worldIn, pos) && currentAge < 15 && rand.nextInt(16) == 0 && worldIn.isAirBlock(pos.up()))
        {
            growUp(worldIn, rand, pos, state, false);
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    { return isTop(source, pos) ? TWISTING_TOP : TWISTING_MID; }

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

    /** Bonemeal Growing **/

    /** Finds the position at the TOP of the Twisted Vines*/
    public BlockPos findTopPos(World worldIn, BlockPos pos)
    {
        BlockPos next = pos;

        while (worldIn.getBlockState(next).getBlock() == this && !isTop(worldIn, next))
        { next = next.up(); }

        return next;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    { return worldIn.isAirBlock(findTopPos(worldIn, pos).up()); }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    { return worldIn.isAirBlock(findTopPos(worldIn, pos).up()); }

    /** How much height is added when Bonemealed. */
    protected int getBonemealHeightIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 1, 8);
    }

    /** This is used specifically for Bonemeal Growth */
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        for (int i = 0; i < getBonemealHeightIncrease(worldIn); i++)
        {
            BlockPos growthPos = pos;
            /* If growth is occurring NOT at a Vine top (via Bonemeal), climb to and preform the growth at said top. */
            if (!isTop(worldIn, pos)) growthPos = findTopPos(worldIn, pos);

            growUp(worldIn, rand, growthPos, state, true);
        }
    }

    /**
     * Grows the Twisted Vine up by 1.
     * `randomizeAge` bool sets if the age of the added part is either Randomized, or the below Vine's Age +1
     * */
    public void growUp(World worldIn, Random rand, BlockPos pos, IBlockState state, boolean randomizeAge)
    {
        int currentAge = worldIn.getBlockState(pos).getValue(AGE);

        if(worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos, state.withProperty(AGE, 0));

            if (randomizeAge)
            { worldIn.setBlockState(pos.up(), state.withProperty(AGE, Integer.valueOf(worldIn.rand.nextInt(14 + 1))), 2); }
            else
            { worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, currentAge + 1), 2); }
        }
    }
}
