package com.unseen.nb.common.blocks.base;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockFenceGateBase extends BlockFenceGate implements IHasModel {


    public BlockFenceGateBase(String name, BlockPlanks.EnumType p_i46394_1_, float Hardness, float Resistance) {
        super(p_i46394_1_);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(Hardness);
        this.setResistance(Resistance);
        this.setRegistryName(name);
        this.setTranslationKey(name);

        // Add both an item as a block and the block itself
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }


    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
