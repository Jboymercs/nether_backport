package com.unseen.nb.common.event;


import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class NBBlockEvents {


    @SubscribeEvent
    public static void convertFireToSoulFire(BlockEvent event) {
        World worldIn = event.getWorld();
        BlockPos pos = event.getPos();

        if(event.getState().getBlock() == Blocks.FIRE) {
            IBlockState soil = worldIn.getBlockState(pos.down());
            if(ModUtils.getblockApplicableToSoulFire(soil.getBlock())) {
                worldIn.setBlockState(pos, ModBlocks.SOUL_FIRE.getDefaultState());
            }
        }
    }

}
