package com.unseen.nb.common.world.structures;

import com.unseen.nb.common.world.base.WorldGenNB;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenRuinedPortals extends WorldGenNB {
    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "ruined_portals");
    public WorldGenRuinedPortals(String structureName) {
        super("portals/" + structureName);
    }


    @Override
    protected void handleDataMarker(String function, BlockPos pos, World world, Random random) {
        if(function.startsWith("chest")) {
            BlockPos blockPos = pos.down();
                TileEntity tileEntity = world.getTileEntity(blockPos);
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                if (tileEntity instanceof TileEntityChest) {
                    TileEntityChest chest = (TileEntityChest) tileEntity;
                    chest.setLootTable(LOOT, random.nextLong());
                } else {
                world.setBlockToAir(pos);
                world.setBlockToAir(pos.down());
            }
        }
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
            ModUtils.createNetherrackCorruption(worldIn, ModRand.range(7, 9), position.getX() -2, position.getY() -1, position.getZ() - 2);
            return super.generate(worldIn, rand, position.add(-2, 0, -2));

    }

}
