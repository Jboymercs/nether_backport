package com.unseen.nb.common.entity.entities;

import com.unseen.nb.client.animation.EZAnimation;
import com.unseen.nb.client.animation.EZAnimationHandler;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.common.entity.EntityNetherBase;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModRand;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class EntityPiglin extends EntityNetherBase implements IAnimatedEntity {

    public static final EZAnimation ANIMATION_ATTACK_MELEE = EZAnimation.create(40);
    public static final EZAnimation ANIMATION_ATTACK_RANGED = EZAnimation.create(40);
    public static final EZAnimation ANIMATION_SHORT_TRADE = EZAnimation.create(40);
    public static final EZAnimation ANIMATION_LONG_TRADE = EZAnimation.create(40);

    protected static final DataParameter<Boolean> MELEE_ATTACK = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> RANGED_ATTACK = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> SHORT_TRADE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> LONG_TRADE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HAS_RANGED = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HAS_MELEE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);

    protected boolean isMeleeAttack() {return this.dataManager.get(MELEE_ATTACK);}
    protected boolean isRangedAttack() {return this.dataManager.get(RANGED_ATTACK);}
    protected boolean isShortTrade() {return this.dataManager.get(SHORT_TRADE);}
    protected boolean isLongTrade() {return this.dataManager.get(LONG_TRADE);}
    protected boolean isHasRanged() {return this.dataManager.get(HAS_RANGED);}
    protected boolean isHasMelee() {return this.dataManager.get(HAS_MELEE);}
    protected void setMeleeAttack(boolean value) {this.dataManager.set(MELEE_ATTACK, Boolean.valueOf(value));}
    protected void setRangedAttack(boolean value) {this.dataManager.set(RANGED_ATTACK, Boolean.valueOf(value));}
    protected void setShortTrade(boolean value) {this.dataManager.set(SHORT_TRADE, Boolean.valueOf(value));}
    protected void setLongTrade(boolean value) {this.dataManager.set(LONG_TRADE, Boolean.valueOf(value));}
    protected void setHasRanged(boolean value) {this.dataManager.set(HAS_RANGED, Boolean.valueOf(value));}
    protected void setHasMelee(boolean value) {this.dataManager.set(HAS_MELEE, Boolean.valueOf(value));}



    private Consumer<EntityLivingBase> prevAttack;


    private int randomChestStat = ModRand.range(1, 10);
    private int randomLeggingsState = ModRand.range(1, 10);
    private int randomBootsStat = ModRand.range(1, 10);

    public boolean hasHelmet = false;
    public boolean hasChestPlate = false;
    public boolean hasBoots = false;

    protected boolean canTrade = true;

    //used for animation system
    private int animationTick;
    //just a variable that holds what the current animation is
    private EZAnimation currentAnimation;

    public EntityPiglin(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);

        if(randomChestStat == 3) {
            this.hasChestPlate = true;
        }
        if(randomLeggingsState == 2) {
            this.hasHelmet = true;
        }
        if(randomBootsStat == 3) {
            this.hasBoots = true;
        }

        if(!this.isHasMelee() && !this.isHasRanged()) {
            if(worldIn.rand.nextInt(3) == 0) {
                this.setHasRanged(true);
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Items.BOW.getDefaultInstance());
            } else {
                this.setHasMelee(true);
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Items.GOLDEN_SWORD.getDefaultInstance());
            }
        }
    }


    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MELEE_ATTACK, Boolean.valueOf(false));
        this.dataManager.register(RANGED_ATTACK, Boolean.valueOf(false));
        this.dataManager.register(SHORT_TRADE, Boolean.valueOf(false));
        this.dataManager.register(LONG_TRADE, Boolean.valueOf(false));
        this.dataManager.register(HAS_MELEE, Boolean.valueOf(false));
        this.dataManager.register(HAS_RANGED, Boolean.valueOf(false));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Melee_Attack", this.isMeleeAttack());
        nbt.setBoolean("Ranged_Mode", this.isRangedAttack());
        nbt.setBoolean("Short_Trade", this.isShortTrade());
        nbt.setBoolean("Long_Trade", this.isLongTrade());
        nbt.setBoolean("Has_Ranged", this.isHasRanged());
        nbt.setBoolean("Has_Melee", this.isHasMelee());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        this.setMeleeAttack(nbt.getBoolean("Melee_Attack"));
        this.setRangedAttack(nbt.getBoolean("Ranged_Mode"));
        this.setShortTrade(nbt.getBoolean("Short_Trade"));
        this.setLongTrade(nbt.getBoolean("Long_Trade"));
        this.setHasMelee(nbt.getBoolean("Has_Melee"));
        this.setHasRanged(nbt.getBoolean("Has_Ranged"));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            if(this.isMeleeAttack()) {
                this.setAnimation(ANIMATION_ATTACK_MELEE);
            }
            if(this.isRangedAttack()) {
                this.setAnimation(ANIMATION_ATTACK_RANGED);
            }
        } else if(canTrade && this.getAnimation() == NO_ANIMATION) {
            if(this.isShortTrade()) {
                this.setAnimation(ANIMATION_SHORT_TRADE);
            }
            if(this.isLongTrade()) {
                this.setAnimation(ANIMATION_LONG_TRADE);
            }
        }

        //NEVER FORGET THIS, OR ELSE ANIMATIONS WILL NOT WORK
        EZAnimationHandler.INSTANCE.updateAnimations(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);

    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        //this.tasks.addTask(4, new EntityAiTimedAttack<EntityEverator>(this, 1.3D, 60, 3, 0.3F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));

    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public EZAnimation getAnimation() {
        return currentAnimation;
    }

    @Override
    public void setAnimation(EZAnimation animation) {
        currentAnimation = animation;
    }

    @Override
    public EZAnimation[] getAnimations() {
     return new EZAnimation[]{ANIMATION_ATTACK_MELEE, ANIMATION_ATTACK_RANGED, ANIMATION_SHORT_TRADE, ANIMATION_LONG_TRADE};
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundHandler.PIGLIN_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
            return ModSoundHandler.PIGLIN_IDLE;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(ModSoundHandler.PIGLIN_STEP, 0.5F, 1.0f / (rand.nextFloat() * 0.4F + 0.2f));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.PIGLIN_DEATH;
    }
}
