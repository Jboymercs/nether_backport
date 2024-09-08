package com.unseen.nb.common.entity.entities;

import com.google.common.collect.Sets;
import com.unseen.nb.common.entity.entities.ai.EntityAIMoveToLava;
import com.unseen.nb.common.entity.util.PathNavigateLava;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.BiomeRegister;
import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Many thanks to Netherized for the Strider and it's mechanics
 * @link https://www.curseforge.com/minecraft/mc-mods/netherized
 */
public class EntityStrider extends EntityAnimal {
    private static final DataParameter<Boolean> IS_COLD = EntityDataManager.createKey(EntityStrider.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.createKey(EntityStrider.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(EntityStrider.class, DataSerializers.VARINT);
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Item.getItemFromBlock(ModBlocks.WARPED_FUNGUS));
    private boolean boosting;
    private int boostTime;
    private int totalBoostTime;

    private EntityAIPanic panicAI;
    private EntityAITempt temptationAI;

    public EntityStrider(World worldIn) {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 0.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.isImmuneToFire = true;
        this.setSize(0.9F, 1.7F);
        this.experienceValue = 2;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BOOST_TIME, 0);
        this.dataManager.register(IS_COLD, false);
        this.dataManager.register(IS_SADDLED, false);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.isTempted() && this.rand.nextInt(140) == 0) {
            this.playSound(ModSoundHandler.STRIDER_WARBLE, 1.0F, this.getSoundPitch());
        } else if (this.isPanicing() && this.rand.nextInt(60) == 0) {
            this.playSound(ModSoundHandler.STRIDER_RETREAT, 1.0F, this.getSoundPitch());
        }

        if(this.getLavaCheck()) {
            this.motionY = 0.0F;
            this.onGround = true;
        }

        this.doBlockCollisions();
    }

    private boolean getLavaCheck() {
        return this.getEntityWorld().getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getMaterial() == Material.LAVA;
    }

    private boolean isPanicing() {
        return this.panicAI != null && this.panicAI.shouldExecute();
    }

    private boolean isTempted() {
        return this.temptationAI != null && this.temptationAI.isRunning();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }

        this.setIsCold(!this.getLavaCheck());
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
        this.doBlockCollisions();
        if(this.isInLava()) {
            this.fallDistance = 0F;
        } else {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (BOOST_TIME.equals(key) && this.world.isRemote) {
            this.boosting = true;
            this.boostTime = 0;
            this.totalBoostTime = this.dataManager.get(BOOST_TIME);
        }
        super.notifyDataManagerChange(key);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Saddle", this.getIsSaddled());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setIsSaddled(compound.getBoolean("Saddle"));
    }

    @Override
    protected void initEntityAI() {
        this.panicAI = new EntityAIPanic(this, 1.65D);
        this.tasks.addTask(0, this.panicAI);
        this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
        this.temptationAI = new EntityAITempt(this, 1.4D, false, TEMPTATION_ITEMS);
        this.tasks.addTask(2, temptationAI);
        this.tasks.addTask(3, new EntityAIMoveToLava(this, 1.5F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1F));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityStrider.class, 12.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateLava(this, worldIn);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0F * ModConfig.healthScale);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.175F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!super.processInteract(player, hand)) {
            ItemStack itemstack = player.getHeldItem(hand);

            if (itemstack.getItem() == Items.NAME_TAG) {
                itemstack.interactWithEntity(player, this, hand);
                return true;
            } else if (this.getIsSaddled() && !this.isBeingRidden()) {
                if (!this.world.isRemote) {
                    player.startRiding(this);
                }
                return true;
            } else if (itemstack.getItem() == Items.SADDLE) {
                itemstack.interactWithEntity(player, this, hand);
                this.world.playSound(null, this.posX, this.posY + 0.5F, this.posZ, SoundEvents.ENTITY_PIG_SADDLE, this.getSoundCategory(), 1.0F, 1.0F);
                this.setIsSaddled(true);
                itemstack.shrink(1);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static void registerFixesStrider(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityStrider.class);
    }

    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getLavaCheck() ? ModSoundHandler.STRIDER_STEP_LAVA : ModSoundHandler.STRIDER_STEP, 1.0F, 1.0F);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyIn, @Nullable IEntityLivingData livingData) {
        if(this.isChild()) {
            return super.onInitialSpawn(difficultyIn, livingData);
        } else {
            if(!this.world.isRemote) {
                if(this.rand.nextInt(30) == 0) {
                    EntityPiglinZombie zombifiedPiglin = new EntityPiglinZombie(this.world);
                    livingData = this.addRider(difficultyIn, zombifiedPiglin, livingData);
                } else if(this.rand.nextInt(10) == 0) {
                    EntityStrider strider = new EntityStrider(this.world);
                    strider.setGrowingAge(-24000);
                    livingData = this.addRider(difficultyIn, strider, livingData);
                }
                return super.onInitialSpawn(difficultyIn, livingData);
            }
            return super.onInitialSpawn(difficultyIn, livingData);
        }
    }

    private IEntityLivingData addRider(DifficultyInstance difficultyIn, EntityLiving entityIn, @Nullable IEntityLivingData livingData) {
        entityIn.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
        entityIn.onInitialSpawn(difficultyIn, livingData);
        this.world.spawnEntity(entityIn);
        entityIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.FUNGUS_ON_STICK));
        entityIn.startRiding(this);
        return livingData;
    }

    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();

        if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            return entityplayer.getHeldItemMainhand().getItem() == ModItems.FUNGUS_ON_STICK || entityplayer.getHeldItemOffhand().getItem() == ModItems.FUNGUS_ON_STICK;
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        // Check if it's in the Basalt Deltas, we want these to spawn at any height in the Deltas
        if(world.getBiomeForCoordsBody(getPosition()) == BiomeRegister.BASALT_DELTAS) {
            return true;
        }
        // Check For Lava
        if(this.posY <= 38) {
            return true;
        }

        return super.getCanSpawnHere();
    }

    @Override
    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!this.world.isRemote) {
            if (this.getIsSaddled()) {
                this.dropItem(Items.SADDLE, 1);
            }
        }
    }

    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "strider");

    @Override
    protected ResourceLocation getLootTable() {
        if(!this.isChild()) {
            return LOOT;
        }
        return null;
    }



    @Override
    public void travel(float strafe, float vertical, float forward) {
        this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * (this.getIsCold() ? 0.66F : 1.0F));

        Entity entity = this.getControllingPassenger();

        if (this.isBeingRidden() && this.canBeSteered()) {
            this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.rotationYaw;
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (this.boosting && this.boostTime++ > this.totalBoostTime) {
                this.boosting = false;
            }

            if (this.canPassengerSteer()) {
                float f = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * (this.getIsCold() ? 0.23F : 0.55F);

                if (this.boosting) {
                    f += f * 1.15F * MathHelper.sin((float)this.boostTime / (float)this.totalBoostTime * (float)Math.PI);
                }

                this.setAIMoveSpeed(f);
                super.travel(0.0F, 0.0F, 0.5F);
            } else {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f1 > 1.0F) {
                f1 = 1.0F;
            }

            this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.travel(strafe, vertical, forward);
        }
    }

    public boolean boost() {
        if (this.boosting) {
            return false;
        } else {
            this.boosting = true;
            this.boostTime = 0;
            this.totalBoostTime = this.getRNG().nextInt(841) + 140;
            this.getDataManager().set(BOOST_TIME, this.totalBoostTime);
            return true;
        }
    }

    public double getMountedYOffset() {
        float f = Math.min(0.25F, this.limbSwingAmount);
        float f1 = this.limbSwing;
        return (double)this.height - 0.19D + (double)(0.12F * MathHelper.cos(f1 * 1.5F) * 2.0F * f);
    }

    public boolean getIsSaddled() {
        return this.dataManager.get(IS_SADDLED);
    }

    public void setIsSaddled(boolean saddled) {
        this.dataManager.set(IS_SADDLED, saddled);
    }

    public boolean getIsCold() {
        return this.dataManager.get(IS_COLD);
    }

    public void setIsCold(boolean isCold) {
        this.dataManager.set(IS_COLD, isCold);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }



    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return !this.isPanicing() && !this.isTempted() ? ModSoundHandler.STRIDER_CHIRP : null;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundHandler.STRIDER_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.STRIDER_DEATH;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new EntityStrider(this.world);
    }
}
