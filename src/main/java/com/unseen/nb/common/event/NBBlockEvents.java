package com.unseen.nb.common.event;


import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    /** If the player is hitting a face that Soul Fire is on, extinguish the Soul Fire. */
    @SubscribeEvent
    public static void extinguishSoulFire(PlayerInteractEvent.LeftClickBlock event)
    {
        if (event.getFace() == null) return;

        BlockPos firePos = event.getPos().offset(event.getFace());

        if (event.getWorld().getBlockState(firePos).getBlock() == ModBlocks.SOUL_FIRE)
        {
            event.getWorld().playEvent(event.getEntityPlayer(), 1009, firePos, 0);
            event.getWorld().setBlockToAir(firePos);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBonemealNetherrack(BonemealEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block =  world.getBlockState(pos).getBlock();

        if(block == Blocks.NETHERRACK)
        {
            /* If a solid cube is above, don't bother trying. */
            if (world.getBlockState(pos.up()).isFullCube()) return;

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
            {
                Block nearbyBlock = world.getBlockState(blockpos$mutableblockpos).getBlock();

                if (nearbyBlock == ModBlocks.CRIMSON_GRASS || nearbyBlock == ModBlocks.WARPED_GRASS)
                {
                    if (!world.isRemote) world.playEvent(2005, pos, 0);
                    trySpreadNylium(world, pos);
                    event.setResult(Event.Result.ALLOW);
                }
            }
        }
    }

    public static void trySpreadNylium(World worldIn, BlockPos pos)
    {
        Random rand = worldIn.rand;
        /* Generates a list of any nearby blocks, then scrambles it. */
        List<BlockPos> positionsToCheck = new ArrayList<>();
        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
        { positionsToCheck.add(blockpos$mutableblockpos.toImmutable()); }
        Collections.shuffle(positionsToCheck, rand);

        for (BlockPos checkPos : positionsToCheck)
        {
            /* Ignore checking the position we know the Netherrack is at. */
            if (checkPos == pos) continue;

            IBlockState state =  worldIn.getBlockState(checkPos);
            Block block =  state.getBlock();

            if (block == ModBlocks.CRIMSON_GRASS || block == ModBlocks.WARPED_GRASS)
            {
                worldIn.setBlockState(pos, state);
                break;
            }
        }
    }
}
