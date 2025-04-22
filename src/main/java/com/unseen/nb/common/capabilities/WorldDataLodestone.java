package com.unseen.nb.common.capabilities;

import com.google.common.collect.Sets;
import com.unseen.nb.util.ModReference;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldSavedData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class WorldDataLodestone extends WorldSavedData
{
    public static final String DATA = ModReference.MOD_ID + "_lightning_rods";
    private final HashSet<BlockPos> positions = Sets.newHashSet();
    
    public WorldDataLodestone(String data) {
        super(data);
    }
    
    public void add(BlockPos pos)
    { if (positions.add(pos)) markDirty(); }
    
    public void remove(BlockPos pos)
    { if (positions.remove(pos)) markDirty();  }

    /** Checks if the given position is contained in the World Data. */
    public boolean checkPosition(BlockPos posIn)
    { return positions.contains(posIn); }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("positions"))
        {
            positions.clear();
            for (NBTBase nbt : compound.getTagList("positions", 10)) positions.add(NBTUtil.getPosFromTag((NBTTagCompound) nbt));
        }
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        NBTTagList positions = new NBTTagList();
        for (BlockPos pos : this.positions) positions.appendTag(NBTUtil.createPosTag(pos));
        compound.setTag("positions", positions);
        return compound;
    }
    
    public static WorldDataLodestone get(WorldServer world)
    {
        WorldDataLodestone data = (WorldDataLodestone) world.getMapStorage().getOrLoadData(WorldDataLodestone.class, DATA);
        if (data == null)
        {
            data = new WorldDataLodestone(DATA);
            world.getMapStorage().setData(DATA, data);
        }
        return data;
    }
}
