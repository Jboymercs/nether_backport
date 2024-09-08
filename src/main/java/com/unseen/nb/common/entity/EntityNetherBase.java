package com.unseen.nb.common.entity;

import com.unseen.nb.common.entity.util.MobGroundNavigate;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.PriorityQueue;

/**
 * Base class for Nether Entities, this just includes some basic functions along with a boolean to check if it's in combat
 */
public abstract class EntityNetherBase extends EntityMob {

    protected float sizeScaling = 1.0F;
    protected static final DataParameter<Boolean> IMMOVABLE = EntityDataManager.createKey(EntityNetherBase.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> FIGHT_MODE = EntityDataManager.createKey(EntityNetherBase.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> INSIDE_BASTION = EntityDataManager.createKey(EntityNetherBase.class, DataSerializers.BOOLEAN);
    protected boolean isImmovable() {
        return this.dataManager == null ? false : this.dataManager.get(IMMOVABLE);
    }

    protected void setImmovable(boolean immovable) {
        this.dataManager.set(IMMOVABLE, immovable);
    }

    public boolean isFightMode() {return this.dataManager.get(FIGHT_MODE);}

    protected void setFightMode(boolean value) {this.dataManager.set(FIGHT_MODE, value);}
    public boolean isInsideBastion() {return this.dataManager.get(INSIDE_BASTION);}
    public void setInsideBastion(boolean value) {this.dataManager.set(INSIDE_BASTION, value);}
    public EntityNetherBase(World worldIn) {
        super(worldIn);
    }

    public void addEvent(Runnable runnable, int ticksFromNow) {
        events.add(new TimedEvent(runnable, this.ticksExisted + ticksFromNow));
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new MobGroundNavigate(this, worldIn);
    }

    @SideOnly(Side.CLIENT)
    protected void initAnimation() {
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        if(!this.isImmovable()) {
            super.move(type, x, y, z);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!isDead && this.getHealth() > 0) {
            boolean foundEvent = true;
            while (foundEvent) {
                TimedEvent event = events.peek();
                if (event != null && event.ticks <= this.ticksExisted) {
                    events.remove();
                    event.callback.run();
                } else {
                    foundEvent = false;
                }
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IMMOVABLE, false);
        this.dataManager.register(FIGHT_MODE, false);
        this.dataManager.register(INSIDE_BASTION, false);
    }


    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Fight_Mode", this.isFightMode());
        nbt.setBoolean("Immovable", this.isImmovable());
        nbt.setBoolean("Inside_Bastion", this.isInsideBastion());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        this.setFightMode(nbt.getBoolean("Fight_Mode"));
        this.setImmovable(nbt.getBoolean("Immovable"));
        this.setInsideBastion(nbt.getBoolean("Inside_Bastion"));
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {


        return super.attackEntityFrom(source, amount);
    }

    private PriorityQueue<TimedEvent> events = new PriorityQueue<TimedEvent>();



    public float getSizeVariation() {
        return this.sizeScaling;
    }


    public void doRender(RenderManager renderManager, double x, double y, double z, float entityYaw, float partialTicks) {
    }


    public class TimedEvent implements Comparable<TimedEvent> {
        Runnable callback;
        int ticks;

        public TimedEvent(Runnable callback, int ticks) {
            this.callback = callback;
            this.ticks = ticks;
        }

        @Override
        public int compareTo(TimedEvent event) {
            return event.ticks < ticks ? 1 : -1;
        }
    }
}
