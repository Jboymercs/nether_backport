package com.unseen.nb.common.entity.entities;

import com.unseen.nb.client.animation.EZAnimation;
import com.unseen.nb.client.animation.EZAnimationHandler;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.common.entity.EntityNetherBase;
import com.unseen.nb.common.entity.entities.ai.EntityTimedAttackPiglinBrute;
import com.unseen.nb.common.entity.entities.ai.IAttack;
import com.unseen.nb.config.ModCompatConfig;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import com.unseen.nb.util.integration.ModIntegration;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class EntityPiglinBrute extends EntityNetherBase implements IAnimatedEntity, IAttack {

    public static final EZAnimation ANIMATION_ATTACK_MELEE = EZAnimation.create(25);
    protected static final DataParameter<Boolean> MELEE_ATTACK = EntityDataManager.createKey(EntityPiglinBrute.class, DataSerializers.BOOLEAN);
    protected void setMeleeAttack(boolean value) {this.dataManager.set(MELEE_ATTACK, value);}
    public boolean isMeleeAttack() {return this.dataManager.get(MELEE_ATTACK);}
    private Consumer<EntityLivingBase> prevAttack;

    //used for animation system
    private int animationTick;
    //just a variable that holds what the current animation is
    private EZAnimation currentAnimation;

    public EntityPiglinBrute(World worldIn) {
        super(worldIn);
<<<<<<< Updated upstream
        if(ModIntegration.SPARTAN_WEAPONRY_LOADED && ModConfig.useMeleeSpartanWeapons) {
            for (ItemStack randStack : ModIntegration.selectBruteWeapon()) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, randStack);
=======
        if(ModIntegration.SPARTAN_WEAPONRY_LOADED && ModCompatConfig.useMeleeSpartanWeapons) {
            int randomInterval = ModRand.range(1, 5);
            if(randomInterval == 1) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.hammerGold.getDefaultInstance());
            }
            if(randomInterval == 2) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.halberdGold.getDefaultInstance());
            }
            if(randomInterval == 3) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.battleaxeGold.getDefaultInstance());
            }
            if(randomInterval == 4) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Items.GOLDEN_AXE.getDefaultInstance());
>>>>>>> Stashed changes
            }
        } else {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
        }
        this.experienceValue = 20;
        this.isImmuneToFire = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MELEE_ATTACK, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Melee_Attack", this.isMeleeAttack());

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
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

    private int dimensionCheck = 40;
    private int countDownToZombie = ModConfig.zombification_time * 20;

    public boolean convertTooZombie = false;


    @Override
    public void onUpdate() {
        super.onUpdate();

        EntityLivingBase target = this.getAttackTarget();

        if(target != null && !hasPlayedAngrySound) {
            this.playSound(ModSoundHandler.BRUTE_ANGRY, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));
            //Allows Brutes to call on nearby Piglins to aid
            List<EntityPiglin> nearbyPiglins = this.world.getEntitiesWithinAABB(EntityPiglin.class, this.getEntityBoundingBox().grow(12D), e -> !e.getIsInvulnerable());
            if(!nearbyPiglins.isEmpty()) {
                for(EntityPiglin piglin : nearbyPiglins) {
                    piglin.setAttackTarget(target);
                }
            }
            hasPlayedAngrySound = true;
        } else if (target == null) {
            hasPlayedAngrySound = false;
        }

        if(this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            if(this.isMeleeAttack()) {
                this.setAnimation(ANIMATION_ATTACK_MELEE);
            }
        }


        if(dimensionCheck < 0) {
            if(this.world.provider.getDimension() != -1) {
                //Start Zombification Process
                if(countDownToZombie < 0 && !this.convertTooZombie) {
                    this.setAttackTarget(null);
                    this.setImmovable(true);
                    this.convertTooZombie = true;
                    this.beginZombieTransformation();
                } else {
                    countDownToZombie--;
                }
            } else {
                dimensionCheck = 40;
            }
        } else {
            dimensionCheck--;
        }

        //NEVER FORGET THIS, OR ELSE ANIMATIONS WILL NOT WORK
        EZAnimationHandler.INSTANCE.updateAnimations(this);
    }



    private void beginZombieTransformation() {
        if(!world.isRemote) {
            addEvent(() -> this.playSound(ModSoundHandler.PIGLIN_CONVERTED, 1.0f, 0.8f), 75);
            addEvent(() -> {
                EntityPiglinZombie zombie = new EntityPiglinZombie(world);
                zombie.copyLocationAndAnglesFrom(this);
                zombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, this.getHeldItemMainhand());
                zombie.setPosition(this.posX, this.posY, this.posZ);
                zombie.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 1));
                this.setDead();
                this.world.spawnEntity(zombie);
            }, 100);
        }
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D * ModConfig.healthScale);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityTimedAttackPiglinBrute<>(this, 1.8D, 20, 2, 0.15F));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 1, true, false, null));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityPigZombie.class, 1, true, false, null));
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
        return 5;
    }

    @Override
    protected boolean canDespawn() {
        if(this.isInsideBastion()) {
            return false;
        }
        // Edit this to restricting them not despawning in Dungeons
        return this.ticksExisted > 20 * 60 * 20;

    }


    private final Consumer<EntityLivingBase> meleeAttackTwo = (target) -> {
        this.setFightMode(true);
        this.setMeleeAttack(true);

        addEvent(()-> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.2, 1.2, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage = (float) (10.0F * ModConfig.attackDamageScale);
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
            float damage = (float) (10.0F * ModConfig.attackDamageScale);
            ModUtils.handleAreaImpact(1.0f, (e)-> damage, this, offset, source, 0.5f, 0, false);
        }, 18);

        addEvent(()-> {
            this.setMeleeAttack( false);
            this.setFightMode(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "piglin_brute");
    @Override
    protected ResourceLocation getLootTable() {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if(source.getImmediateSource() == this || source.getImmediateSource() instanceof EntityPiglin) {
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundHandler.BRUTE_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundHandler.BRUTE_IDLE;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(ModSoundHandler.BRUTE_STEP, 0.5F, 1.0f / (rand.nextFloat() * 0.4F + 0.2f));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.BRUTE_DEATH;
    }
}
