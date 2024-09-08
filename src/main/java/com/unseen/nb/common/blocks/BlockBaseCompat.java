package com.unseen.nb.common.blocks;

import com.unseen.nb.Main;
import com.unseen.nb.handler.IHasModel;
import com.unseen.nb.init.ModBlocksCompat;
import com.unseen.nb.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBaseCompat  extends Block implements IHasModel {
    public BlockBaseCompat(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);

        // Add both an item as a block and the block itself
        ModBlocksCompat.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockBaseCompat(String name, Material material, float hardness, float resistance, SoundType soundType) {
        this(name, material);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
