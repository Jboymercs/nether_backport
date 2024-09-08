package com.unseen.nb.common.entity.entities;

import com.google.common.collect.Sets;
import com.unseen.nb.client.animation.EZAnimation;
import com.unseen.nb.client.animation.EZAnimationHandler;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.common.entity.EntityNetherAnimalBase;
import com.unseen.nb.common.entity.entities.ai.EntityTimedAttackZoglin;
import com.unseen.nb.common.entity.entities.ai.IAttack;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityZoglin extends EntityNetherAnimalBase implements IAttack, IAnimatedEntity {
    public static final EZAnimation ANIMATION_ATTACK_MELEE = EZAnimation.create(20);

    //used for animation system
    private int animationTick;
    //just a variable that holds what the current animation is
    private EZAnimation currentAnimation;


    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Item.getItemFromBlock(ModBlocks.CRIMSON_FUNGUS));


    public EntityZoglin(World worldIn) {
        super(worldIn);
        this.setSize(1.9F, 1.8F);
        this.experienceValue = 9;
        this.isImmuneToFire = true;
    }


    private boolean hasPlayedAngrySound = false;
    @Override
    public void onUpdate() {
        super.onUpdate();
        //Animation stuff
        if(this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(ANIMATION_ATTACK_MELEE);
        }

        EntityLivingBase target = this.getAttackTarget();

        if(target != null && !hasPlayedAngrySound) {
            this.playSound(ModSoundHandler.ZOGLIN_ANGRY, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));
            hasPlayedAngrySound = true;
        } else if (target == null) {
            hasPlayedAngrySound = false;
        }



        //NEVER FORGET THIS, OR ELSE ANIMATIONS WILL NOT WORK
        EZAnimationHandler.INSTANCE.updateAnimations(this);
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D * ModConfig.healthScale);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.6D);
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityTimedAttackZoglin<>(this, 1.6D, 60, 3, 0.3F));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 1, true, false, null));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true, new Class[0]));

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


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundHandler.ZOGLIN_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundHandler.ZOGLIN_IDLE;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(ModSoundHandler.ZOGLIN_STEP, 0.5F, 1.0f / (rand.nextFloat() * 0.4F + 0.2f));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.ZOGLIN_DEATH;
    }

    @Override
    public int startAttack(EntityLivingBase target, float distanceSq, boolean strafingBackwards) {
        this.setFightMode(true);
        addEvent(()-> this.playSound(ModSoundHandler.ZOGLIN_ATTACK, 1.0f, 1.0f), 10);
        addEvent(()-> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.0, 0.5, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage;
            if(this.isChild()) {
                damage = (float)(1F * ModConfig.attackDamageScale);
            } else {
                damage = (float)(8F * ModConfig.attackDamageScale);
            }
            ModUtils.handleAreaImpact(1.0f, (e)-> damage, this, offset, source, 0.9f, 0, false);
        }, 13);
        addEvent(()-> this.setFightMode(false), 20);
        return 20;
    }




    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "zoglin");

    @Override
    protected ResourceLocation getLootTable() {
        if(!this.isChild()) {
            return LOOT;
        }
        return null;
    }





    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}
