package com.unseen.nb.common.event;

import com.unseen.nb.common.blocks.BlockRespawnAnchor;
import com.unseen.nb.common.capabilities.CapabilityRespawnAnchor;
import com.unseen.nb.init.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 *  Preforms all the logic and calculations for the Respawn Anchor code.
 *
 *  Primarily, it's a Capability and a teleport with lots of safety checks!
 *
 *  ALSO LATER RIP THIS OUT OF `EventBusSubscriber` MAKE IT RUN THROUGH FORGE INIT OR SOMETHING
 * */
@Mod.EventBusSubscriber
public class EventRespawnAnchor
{
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(CapabilityRespawnAnchor.ID, new CapabilityRespawnAnchor.Provider(new CapabilityRespawnAnchor.RespawnAnchorMethods(), CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null));
        }
    }

    /** Required to transfer the Capability across death, which is very required for one that deals entirely with respawning. */
    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event)
    {
        EntityPlayer playerDead = event.getOriginal();
        EntityPlayer player = event.getEntityPlayer();

        if (playerDead.hasCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null) && player.hasCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null))
        {
            CapabilityRespawnAnchor.ICapabilityRespawnAnchor oldAnchorData = playerDead.getCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null);
            CapabilityRespawnAnchor.ICapabilityRespawnAnchor newAnchorData = player.getCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null);

            newAnchorData.setUsedAnchor(oldAnchorData.getUsedAnchor());
            newAnchorData.setAnchorPos(oldAnchorData.getAnchorPos());
            newAnchorData.setPlayerSpawnPos(oldAnchorData.getPlayerSpawnPos());
            newAnchorData.setAnchorDim(oldAnchorData.getAnchorDim());
        }
    }

    /** If the player's spawn position is updated, and NOT the same as the Anchor's pos, then they overwrote it, so cancel the Anchor Code. */
    @SubscribeEvent
    public static void setSpawnCheckAnchor(PlayerSetSpawnEvent event)
    {
        if (event.getEntityPlayer().hasCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null))
        {
            CapabilityRespawnAnchor.ICapabilityRespawnAnchor capRespawnAnchor = event.getEntityPlayer().getCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null);

            if (capRespawnAnchor.getUsedAnchor())
            {
                if (event.getNewSpawn() != capRespawnAnchor.getPlayerSpawnPos())
                {
                    capRespawnAnchor.setPlayerSpawnPos(event.getNewSpawn());
                    capRespawnAnchor.setUsedAnchor(false);
                }
            }
        }
    }

    /** All the calculations used in Respawn Anchor placement... which actually just teleports the Player to the Anchor. */
    @SubscribeEvent
    public static void onRespawnPreformAnchorTeleporting(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event)
    {
        if (event.player.hasCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null))
        {
            CapabilityRespawnAnchor.ICapabilityRespawnAnchor capRespawnAnchor = event.player.getCapability(CapabilityRespawnAnchor.RESPAWN_ANCHOR_CAP, null);

            if (capRespawnAnchor.getUsedAnchor())
            {
                /* If the Player is intentionally exiting the End, don't teleport them to an End Respawn Anchor. */
                if (event.isEndConquered() && capRespawnAnchor.getAnchorDim() == 1) return;

                BlockPos anchorPos = capRespawnAnchor.getAnchorPos();
                World anchorWorld = event.player.getServer().getWorld(capRespawnAnchor.getAnchorDim());

                if (anchorWorld.getBlockState(anchorPos).getBlock() instanceof BlockRespawnAnchor)
                {
                    /* If zero Charges, cancel any further processing. */
                    if (anchorWorld.getBlockState(anchorPos).getValue(BlockRespawnAnchor.CHARGES) <= 0)
                    {
                        event.player.sendStatusMessage(new TextComponentTranslation("Your respawn anchor has no charge", new Object[0]), false);
                        capRespawnAnchor.setUsedAnchor(false);
                        return;
                    }

                    BlockPos respawnPos = getSafeExitLocation(anchorWorld, anchorPos, 0);

                    if (respawnPos != null)
                    {
                        event.player.changeDimension(capRespawnAnchor.getAnchorDim(), new CustomTeleporter(respawnPos));

                        /* Don't lower the charges if the Player is returning from the End. */
                        if (!event.isEndConquered())
                        {
                            anchorWorld.setBlockState(capRespawnAnchor.getAnchorPos(), anchorWorld.getBlockState(capRespawnAnchor.getAnchorPos()).withProperty(BlockRespawnAnchor.CHARGES, Math.max(anchorWorld.getBlockState(capRespawnAnchor.getAnchorPos()).getValue(BlockRespawnAnchor.CHARGES) - 1, 0)));
                            event.player.world.playSound(null, event.player.getPosition(), ModSoundHandler.RESPAWN_ANCHOR_DEPLETE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }
                    else
                    {
                        event.player.sendStatusMessage(new TextComponentTranslation("Your respawn anchor was obstructed", new Object[0]), false);
                        capRespawnAnchor.setUsedAnchor(false);
                    }
                }
                else
                {
                    event.player.sendStatusMessage(new TextComponentTranslation("You have no respawn anchor", new Object[0]), false);
                    capRespawnAnchor.setUsedAnchor(false);
                }
            }

        }
    }

    /** Checks the surrounding area for an open space for the Player. Mostly copied from BlockBed code. */
    public static BlockPos getSafeExitLocation(World worldIn, BlockPos pos, int tries)
    {
        for (int x =-1; x <= 1; ++x)
        {
            for (int y = -1; y <= 1; ++y)
            {
                for (int z = -1; z <= 1; ++z)
                {
                    BlockPos blockpos = pos.add(x,y,z);

                    if (hasRoomForPlayer(worldIn, blockpos))
                    {
                        if (tries <= 0)
                        {
                            return blockpos;
                        }
                        --tries;
                    }
                }
            }
        }
        return null;
    }

    protected static boolean hasRoomForPlayer(World worldIn, BlockPos pos)
    { return worldIn.getBlockState(pos.down()).isTopSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid(); }
}

/** A very stripped-down ITeleporter used for Teleporting the Player. */
class CustomTeleporter implements net.minecraftforge.common.util.ITeleporter
{
    private final BlockPos targetPos;

    public CustomTeleporter(BlockPos targetPosIn)
    {
        targetPos = targetPosIn;
    }

    @Override
    public void placeEntity(World world, Entity entity, float yaw)
    {
        entity.setLocationAndAngles(targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5, entity.rotationPitch, yaw);

        //* This... Might be required for Multiplayer? Not certain, requires testing. */
        if (entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)entity).connection.setPlayerLocation(targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
        }
    }
}