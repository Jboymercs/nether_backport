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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBaseCompat  extends Block implements IHasModel {
    public BlockBaseCompat(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);

        /* Currently tool harvesting is hard-coded, as this class is only used for the Netherite Block and Ancient Debris, which are the same tool*/
        setHarvestLevel("pickaxe", 3);
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

    /** Netherite Blocks work for Beacon Bases */
    public boolean isBeaconBase(IBlockAccess state, BlockPos pos, BlockPos beacon)
    { return this == ModBlocksCompat.NETHERITE_BLOCK; }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
