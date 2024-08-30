package com.unseen.nb.common.capabilities;

import com.unseen.nb.util.ModReference;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class CapabilityRespawnAnchor
{
    @CapabilityInject(ICapabilityRespawnAnchor.class)
    public static Capability<ICapabilityRespawnAnchor> RESPAWN_ANCHOR_CAP;
    public static final ResourceLocation ID = new ResourceLocation(ModReference.MOD_ID, "respawnAnchor");

    /** True if the player has set, and should be using a Respawn Anchor. */
    private static final String SET_TAG = "hasAnchor";
    /** The position of the Anchor is saved, cut into 3 tags being `X`, `Y`, `Z` */
    /** The player's last set Spawn Position is saved, cut into 3 tags being `PlayerSpawnX`, `PlayerSpawnY`, `PlayerSpawnZ` */
    /** The ID of the dimension the Anchor exists within. */
    private static final String ANCHOR_DIM_TAG = "anchorDim";

    public interface ICapabilityRespawnAnchor
    {
        boolean getUsedAnchor();
        void setUsedAnchor(boolean value);

        BlockPos getAnchorPos();
        void setAnchorPos(BlockPos value);

        BlockPos getPlayerSpawnPos();
        void setPlayerSpawnPos(BlockPos value);

        int getAnchorDim();
        void setAnchorDim(int value);
    }

    public static class RespawnAnchorMethods implements ICapabilityRespawnAnchor
    {
        private boolean usedAnchor = false;
        private BlockPos getBlockPos = new BlockPos(0,0,0);
        private BlockPos playerSpawnPos = new BlockPos(0,0,0);
        private int getDimension = 0;

        @Override
        public boolean getUsedAnchor()
        { return usedAnchor; }

        @Override
        public void setUsedAnchor(boolean value)
        { usedAnchor = value; }

        @Override
        public BlockPos getAnchorPos()
        { return getBlockPos; }

        @Override
        public void setAnchorPos(BlockPos value)
        { getBlockPos = value; }

        @Override
        public BlockPos getPlayerSpawnPos()
        { return playerSpawnPos; }

        @Override
        public void setPlayerSpawnPos(BlockPos value)
        { playerSpawnPos = value; }

        @Override
        public int getAnchorDim()
        { return getDimension; }

        @Override
        public void setAnchorDim(int value)
        { getDimension = value; }
    }

    public static class Storage implements Capability.IStorage<ICapabilityRespawnAnchor>
    {
        @Override
        public NBTBase writeNBT(Capability<ICapabilityRespawnAnchor> capability, ICapabilityRespawnAnchor instance, EnumFacing side)
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setBoolean(SET_TAG, instance.getUsedAnchor());
            NBTUtil.createPosTag(instance.getAnchorPos());
            createPosTagFromSpawn(instance.getPlayerSpawnPos());
            compound.setInteger(ANCHOR_DIM_TAG, instance.getAnchorDim());
            return compound;
        }

        @Override
        public void readNBT(Capability<ICapabilityRespawnAnchor> capability, ICapabilityRespawnAnchor instance, EnumFacing side, NBTBase nbt)
        {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            instance.setUsedAnchor(compound.getBoolean(SET_TAG));
            instance.setAnchorPos(NBTUtil.getPosFromTag(compound));
            instance.setPlayerSpawnPos(getPosFromTagFromSpawn(compound));
            instance.setAnchorDim(compound.getInteger(ANCHOR_DIM_TAG));
        }

        public static BlockPos getPosFromTagFromSpawn(NBTTagCompound tag)
        {
            return new BlockPos(tag.getInteger("PlayerSpawnX"), tag.getInteger("PlayerSpawnY"), tag.getInteger("PlayerSpawnZ"));
        }

        public static NBTTagCompound createPosTagFromSpawn(BlockPos pos)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInteger("PlayerSpawnX", pos.getX());
            nbttagcompound.setInteger("PlayerSpawnY", pos.getY());
            nbttagcompound.setInteger("PlayerSpawnZ", pos.getZ());
            return nbttagcompound;
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTBase>
    {
        final Capability<ICapabilityRespawnAnchor> capability;
        final EnumFacing facing;
        final ICapabilityRespawnAnchor instance;

        public Provider(final ICapabilityRespawnAnchor instance, final Capability<ICapabilityRespawnAnchor> capability, @Nullable final EnumFacing facing)
        {
            this.instance = instance;
            this.capability = capability;
            this.facing = facing;
        }

        @Override
        public boolean hasCapability(@Nullable final Capability<?> capability, final EnumFacing facing)
        { return capability == getCapability(); }

        @Override
        public <T> T getCapability(@Nullable Capability<T> capability, EnumFacing facing)
        { return capability == getCapability() ? getCapability().cast(this.instance) : null; }

        final Capability<ICapabilityRespawnAnchor> getCapability()
        { return capability; }

        EnumFacing getFacing()
        { return facing; }

        final ICapabilityRespawnAnchor getInstance()
        { return instance; }

        @Override
        public NBTBase serializeNBT()
        { return getCapability().writeNBT(getInstance(), getFacing()); }

        @Override
        public void deserializeNBT(NBTBase nbt)
        { getCapability().readNBT(getInstance(), getFacing(), nbt); }
    }
}