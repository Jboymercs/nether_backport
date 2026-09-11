package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.config.BlocksConfig;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModSoundHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;

public class BlockLogBase extends BlockLog implements IHasModel {
    public BlockLogBase(String name) {
        super();
        setTranslationKey(name);
        setRegistryName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockLogBase(String name, float hardness, float resistance, SoundType soundType) {
        this(name);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    public BlockLogBase(String name, float hardness, float resistance, SoundType soundType, Block log) {
        this(name, hardness, resistance, soundType);
        if (log != null) ((BlockLogBase)log).setStrippedLog(this);
    }

    private BlockLogBase strippedLog;
    private void setStrippedLog(BlockLogBase strippedLog) {
        if (this.strippedLog == null) this.strippedLog = strippedLog;
    }

    /** We are Overriding the material, as inheriting from `BlockLog` tries making it `Material.WOOD`, but we don't want the flammability of that! */
    @Override
    public Material getMaterial(IBlockState state)
    { return Material.GROUND; }

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

    /**
     * Makes the block able to sustain leaves
     *
     * @param state
     * @param world
     * @param pos
     * @return
     */
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return BlockPlanks.EnumType.SPRUCE.getMapColor();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{LOG_AXIS});
    }

    //block striping
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!BlocksConfig.stripLog) return false;

        BlockLogBase block = (BlockLogBase) state.getBlock();

        if (block.strippedLog != null) {
            if (hand == EnumHand.MAIN_HAND) {
                ItemStack itemStack = player.getHeldItem(hand);
                Item item = itemStack.getItem();
                if (item != null && item instanceof ItemAxe) {
                    IBlockState newState = block.strippedLog.getDefaultState().withProperty(LOG_AXIS, state.getValue(LOG_AXIS));
                    world.setBlockState(pos, newState);

                    world.playSound(player, pos, ModSoundHandler.AXE_STRIP, SoundCategory.PLAYERS, 1F, 1F);

                    int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, itemStack);
                    boolean damage = true;
                    if (efficiencyLevel > 0) {
                        if (Math.random() * 10 < efficiencyLevel) {
                            damage = false;
                        }
                    }
                    if (damage) {
                        itemStack.damageItem(1, player);
                    }
                    return true;
                }
            }
        }

        return false;
    }
}
