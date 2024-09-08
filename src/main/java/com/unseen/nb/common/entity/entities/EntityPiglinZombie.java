package com.unseen.nb.common.entity.entities;

import com.unseen.nb.client.animation.EZAnimation;
import com.unseen.nb.client.animation.EZAnimationHandler;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.common.entity.EntityNetherBase;
import com.unseen.nb.common.entity.entities.ai.EntityTimedAttackZombie;
import com.unseen.nb.common.entity.entities.ai.IAttack;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class EntityPiglinZombie extends EntityNetherBase implements IAnimatedEntity, IAttack {
    public static final EZAnimation ANIMATION_ATTACK_MELEE = EZAnimation.create(25);

    private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = (new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05D, 0)).setSaved(false);
    protected static final DataParameter<Boolean> MELEE_ATTACK = EntityDataManager.createKey(EntityPiglinZombie.class, DataSerializers.BOOLEAN);
    protected void setMeleeAttack(boolean value) {this.dataManager.set(MELEE_ATTACK, value);}
    public boolean isMeleeAttack() {return this.dataManager.get(MELEE_ATTACK);}
    private Consumer<EntityLivingBase> prevAttack;

    private int angerLevel;

    private UUID angerTargetUUID;

    //used for animation system
    private int animationTick;
    //just a variable that holds what the current animation is
    private EZAnimation currentAnimation;

    public EntityPiglinZombie(World worldIn) {
        super(worldIn);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
        this.experienceValue = 5;
        this.isImmuneToFire = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MELEE_ATTACK, Boolean.FALSE);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setShort("Anger", (short)this.angerLevel);
        nbt.setBoolean("Melee_Attack", this.isMeleeAttack());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.angerLevel = nbt.getShort("Anger");
        this.setMeleeAttack(nbt.getBoolean("Melee_Attack"));
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
        return new EZAnimation[]{ANIMATION_ATTACK_MELEE};
    }

    protected boolean hasPlayedAngrySound = false;

    protected void updateAITasks()
    {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (this.isAngry())
        {
            if (!this.isChild() && !iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER))
            {
                iattributeinstance.applyModifier(ATTACK_SPEED_BOOST_MODIFIER);
            }

            --this.angerLevel;
        }
        else if (iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER))
        {
            iattributeinstance.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
        }


        if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null)
        {
            EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
            this.setRevengeTarget(entityplayer);
            this.attackingPlayer = entityplayer;
            this.recentlyHit = this.getRevengeTimer();
        }

        super.updateAITasks();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        EntityLivingBase target = this.getAttackTarget();

        if(target != null && !hasPlayedAngrySound) {
            this.playSound(ModSoundHandler.ZPIG_ANGRY, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));
            hasPlayedAngrySound = true;
        } else if (target == null) {
            hasPlayedAngrySound = false;
        }

        if(this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            if(this.isMeleeAttack()) {
                this.setAnimation(ANIMATION_ATTACK_MELEE);
            }
        }

        //NEVER FORGET THIS, OR ELSE ANIMATIONS WILL NOT WORK
        EZAnimationHandler.INSTANCE.updateAnimations(this);
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D * ModConfig.healthScale);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityTimedAttackZombie<>(this, 1.8D, 20, 2, 0.15F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityPiglinZombie.AIHurtByAggressor(this));
        this.targetTasks.addTask(2, new EntityPiglinZombie.AITargetAggressor(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    }

    public boolean isAngry()
    {
        return this.angerLevel > 0;
    }

    @Override
    public int startAttack(EntityLivingBase target, float distanceSq, boolean strafingBackwards) {
        double distance = Math.sqrt(distanceSq);
        if(!this.isFightMode()) {
            List<Consumer<EntityLivingBase>> attacks = new ArrayList<>(Arrays.asList(meleeAttack, meleeAttackTwo));
            double[] weights = {
                    (distance < 3 && distance > 0) ? 1 / distance : 1,
                    (distance < 3 && distance > 0) ? 1 / distance : 2
            };
            prevAttack = ModRand.choice(attacks, rand, weights).next();

            prevAttack.accept(target);
        }
        return 20;
    }


    private final Consumer<EntityLivingBase> meleeAttackTwo = (target) -> {
        this.setFightMode(true);
        this.setMeleeAttack(true);

        addEvent(()-> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.2, 1.2, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage = (float) (7.0F * ModConfig.attackDamageScale);
            ModUtils.handleAreaImpact(1.0f, (e)-> damage, this, offset, source, 0.5f, 0, false);
        }, 18);

        addEvent(()-> {
            this.setMeleeAttack( false);
            this.setFightMode(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private final Consumer<EntityLivingBase> meleeAttack = (target) -> {
        this.setFightMode(true);
        this.setMeleeAttack(true);

        addEvent(()-> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.2, 1.2, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage = (float)(7.0F * ModConfig.attackDamageScale);
            ModUtils.handleAreaImpact(1.0f, (e)-> damage, this, offset, source, 0.5f, 0, false);
        }, 18);

        addEvent(()-> {
            this.setMeleeAttack( false);
            this.setFightMode(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.angerLevel = 400 + this.rand.nextInt(400);
        if (p_70835_1_ instanceof EntityLivingBase)
        {
            this.setRevengeTarget((EntityLivingBase)p_70835_1_);
        }
    }

    @Override
    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getTrueSource();

            if (entity instanceof EntityPlayer)
            {
                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "zpig");
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundHandler.ZPIG_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundHandler.ZPIG_IDLE;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(ModSoundHandler.PIGLIN_STEP, 0.5F, 1.0f / (rand.nextFloat() * 0.4F + 0.2f));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.ZPIG_DEATH;
    }



    static class AIHurtByAggressor extends EntityAIHurtByTarget
    {
        public AIHurtByAggressor(EntityPiglinZombie p_i45828_1_)
        {
            super(p_i45828_1_, true);
        }

        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
        {
            super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);

            if (creatureIn instanceof EntityPiglinZombie)
            {
                ((EntityPiglinZombie)creatureIn).becomeAngryAt(entityLivingBaseIn);
            }
        }
    }

    static class AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AITargetAggressor(EntityPiglinZombie p_i45829_1_)
        {
            super(p_i45829_1_, EntityPlayer.class, true);
        }

        public boolean shouldExecute()
        {
            return ((EntityPiglinZombie)this.taskOwner).isAngry() && super.shouldExecute();
        }
    }
}
