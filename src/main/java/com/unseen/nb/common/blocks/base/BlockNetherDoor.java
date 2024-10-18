package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.common.items.ItemNetherDoor;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import java.util.Random;
/**
 * Credit goes to SmileyCorps from DeeperDepths
 */
public class BlockNetherDoor extends BlockDoor implements IHasModel, IBlockProperties
{
    private final ItemNetherDoor item;

    public BlockNetherDoor(String name, float hardness, float resistance, CreativeTabs tab, SoundType soundType) {
        super(Material.WOOD);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setSoundType(soundType);
        setHardness(hardness);
        setResistance(resistance);
        useNeighborBrightness = true;

        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        item = new ItemNetherDoor(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    { return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getItem(); }

    public Item getItem()
    { return item; }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
