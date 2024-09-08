package com.unseen.nb.common.entity.entities;

import com.google.common.collect.Lists;
import com.unseen.nb.client.animation.EZAnimation;
import com.unseen.nb.client.animation.EZAnimationHandler;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.common.entity.EntityNetherBase;
import com.unseen.nb.common.entity.entities.ai.EntityTimedAttackPiglin;
import com.unseen.nb.common.entity.entities.ai.IAttack;
import com.unseen.nb.config.ModCompatConfig;
import com.unseen.nb.config.ModConfig;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModRand;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import com.unseen.nb.util.integration.ModIntegration;
import com.unseen.nb.util.integration.SpartanWeaponryIntegration;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.smileycorp.crossbows.common.CrossbowsContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class EntityPiglin extends EntityNetherBase implements IAnimatedEntity, IAttack {

    public static final EZAnimation ANIMATION_ATTACK_MELEE = EZAnimation.create(25);
    public static final EZAnimation ANIMATION_ATTACK_RANGED = EZAnimation.create(25);
    public static final EZAnimation ANIMATION_SHORT_TRADE = EZAnimation.create(120);
    public static final EZAnimation ANIMATION_LONG_TRADE = EZAnimation.create(40);


    private static final ResourceLocation LOOT_TRADE = new ResourceLocation(ModReference.MOD_ID, "piglin_trade");

    protected static final DataParameter<Boolean> MELEE_ATTACK = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> RANGED_ATTACK = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> SHORT_TRADE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> LONG_TRADE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HAS_RANGED = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HAS_MELEE = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);

    protected static final DataParameter<Boolean> HASHELMET = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HASCHEST = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HASBOOTS = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> RAND_ARMOR = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> LOADED_CROSSBOW = EntityDataManager.createKey(EntityPiglin.class, DataSerializers.BOOLEAN);

    public boolean isMeleeAttack() {
        return this.dataManager.get(MELEE_ATTACK);
    }

    public boolean isRangedAttack() {
        return this.dataManager.get(RANGED_ATTACK);
    }

    protected boolean isShortTrade() {
        return this.dataManager.get(SHORT_TRADE);
    }

    protected boolean isLongTrade() {
        return this.dataManager.get(LONG_TRADE);
    }

    public boolean isHasRanged() {
        return this.dataManager.get(HAS_RANGED);
    }

    public boolean isHasMelee() {
        return this.dataManager.get(HAS_MELEE);
    }

    public boolean isHasHelmet() {
        return this.dataManager.get(HASHELMET);
    }

    public boolean isHasChestPlate() {
        return this.dataManager.get(HASCHEST);
    }

    public boolean isHasBoots() {
        return this.dataManager.get(HASBOOTS);
    }

    public boolean isRandArmor() {
        return this.dataManager.get(RAND_ARMOR);
    }

    public boolean isLoadedACrossBow() {
        return this.dataManager.get(LOADED_CROSSBOW);
    }

    protected void setMeleeAttack(boolean value) {
        this.dataManager.set(MELEE_ATTACK, value);
    }

    protected void setRangedAttack(boolean value) {
        this.dataManager.set(RANGED_ATTACK, value);
    }

    protected void setShortTrade(boolean value) {
        this.dataManager.set(SHORT_TRADE, value);
    }

    protected void setLongTrade(boolean value) {
        this.dataManager.set(LONG_TRADE, value);
    }

    protected void setHasRanged(boolean value) {
        this.dataManager.set(HAS_RANGED, value);
    }

    protected void setHasMelee(boolean value) {
        this.dataManager.set(HAS_MELEE, value);
    }

    protected void setHasHelmet(boolean value) {
        this.dataManager.set(HASHELMET, value);
    }

    protected void setHasChest(boolean value) {
        this.dataManager.set(HASCHEST, value);
    }

    protected void setHasBoots(boolean value) {
        this.dataManager.set(HASBOOTS, value);
    }

    protected void setRandArmor(boolean value) {
        this.dataManager.set(RAND_ARMOR, value);
    }

    protected void setLoadedACrossBow(boolean value) {
        this.dataManager.set(LOADED_CROSSBOW, value);
    }


    private Consumer<EntityLivingBase> prevAttack;


    private int randomChestStat = ModRand.range(1, 10);
    private int randomLeggingsState = ModRand.range(1, 10);
    private int randomBootsStat = ModRand.range(1, 10);

    private long lootTableTradeSeed;
    protected boolean canTrade = true;

    //used for animation system
    private int animationTick;
    //just a variable that holds what the current animation is
    private EZAnimation currentAnimation;


    public EntityPiglin(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 5;
        this.isImmuneToFire = true;


        if (!this.isRandArmor()) {
            if (randomChestStat == 3 && !this.isHasChestPlate()) {
                this.setHasChest(true);
            }
            if (randomLeggingsState == 2 && !this.isHasHelmet()) {
                this.setHasHelmet(true);
            }
            if (randomBootsStat == 3 && !this.isHasBoots()) {
                this.setHasBoots(true);
            }
            this.setRandArmor(true);
        }

        if (!this.isHasMelee() && !this.isHasRanged()) {
            if (worldIn.rand.nextInt(2) == 0) {
                this.setHasRanged(true);
                if (ModIntegration.CROSSBOWS_BACKPORT_LOADED) {

                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CrossbowsContent.CROSSBOW));
                } else if (ModIntegration.SPARTAN_WEAPONRY_LOADED) {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ModIntegration.getCrossBow());
                } else {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                }
                this.initRangedAI();
            } else {
                this.setHasMelee(true);
<<<<<<< Updated upstream
                if (ModIntegration.SPARTAN_WEAPONRY_LOADED && ModConfig.useMeleeSpartanWeapons) {
                    for (ItemStack randStack : ModIntegration.selectPiglinWeapon()) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, randStack);
=======
                if(ModIntegration.SPARTAN_WEAPONRY_LOADED && ModCompatConfig.useMeleeSpartanWeapons) {
                    int randomInterval = ModRand.range(1, 8);
                    if(randomInterval == 1) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.greatswordGold.getDefaultInstance());
                    }
                    if(randomInterval == 2) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.katanaGold.getDefaultInstance());
                    }
                    if(randomInterval == 3) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,  ItemRegistrySW.rapierGold.getDefaultInstance());
                    }
                    if(randomInterval == 4) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.saberGold.getDefaultInstance());
                    }
                    if(randomInterval == 5) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,  ItemRegistrySW.scytheGold.getDefaultInstance());
                    }
                    if(randomInterval == 6) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.spearGold.getDefaultInstance());
                    }
                    if(randomInterval == 7) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Items.GOLDEN_SWORD.getDefaultInstance());
>>>>>>> Stashed changes
                    }
                } else {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
                }
                this.initMeleeAI();
            }
        } else {
            if (this.isHasRanged()) {
                if (ModIntegration.CROSSBOWS_BACKPORT_LOADED) {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CrossbowsContent.CROSSBOW));
                } else if (ModIntegration.SPARTAN_WEAPONRY_LOADED) {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ModIntegration.getCrossBow());
                } else {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                }
                this.initRangedAI();

<<<<<<< Updated upstream
            } else if (this.isHasMelee()) {
                if (ModIntegration.SPARTAN_WEAPONRY_LOADED && ModConfig.useMeleeSpartanWeapons) {
                    for (ItemStack randStack : ModIntegration.selectPiglinWeapon()) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, randStack);
=======
            } else if(this.isHasMelee()) {
                if(ModIntegration.SPARTAN_WEAPONRY_LOADED && ModCompatConfig.useMeleeSpartanWeapons) {
                    int randomInterval = ModRand.range(1, 8);
                    if(randomInterval == 1) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.greatswordGold.getDefaultInstance());
                    }
                    if(randomInterval == 2) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.katanaGold.getDefaultInstance());
                    }
                    if(randomInterval == 3) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,  ItemRegistrySW.rapierGold.getDefaultInstance());
                    }
                    if(randomInterval == 4) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.saberGold.getDefaultInstance());
                    }
                    if(randomInterval == 5) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,  ItemRegistrySW.scytheGold.getDefaultInstance());
                    }
                    if(randomInterval == 6) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemRegistrySW.spearGold.getDefaultInstance());
                    }
                    if(randomInterval == 7) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Items.GOLDEN_SWORD.getDefaultInstance());
>>>>>>> Stashed changes
                    }
                } else {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
                }
                this.initMeleeAI();
            }
        }
    }


    protected void initRangedAI() {
        this.tasks.addTask(2, new EntityTimedAttackPiglin<>(this, 1.8D, 60, 14, 0.3F));
    }

    protected void initMeleeAI() {
        this.tasks.addTask(2, new EntityTimedAttackPiglin<>(this, 1.8D, 60, 3, 0.2F));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MELEE_ATTACK, false);
        this.dataManager.register(RANGED_ATTACK, Boolean.FALSE);
        this.dataManager.register(SHORT_TRADE, false);
        this.dataManager.register(LONG_TRADE, false);
        this.dataManager.register(HAS_MELEE, false);
        this.dataManager.register(HAS_RANGED, false);
        this.dataManager.register(HASHELMET, false);
        this.dataManager.register(HASCHEST, false);
        this.dataManager.register(HASBOOTS, false);
        this.dataManager.register(RAND_ARMOR, false);
        this.dataManager.register(LOADED_CROSSBOW, false);
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
        nbt.setBoolean("Has_Helmet", this.isHasHelmet());
        nbt.setBoolean("Has_Chest", this.isHasChestPlate());
        nbt.setBoolean("Has_Boots", this.isHasBoots());
        nbt.setBoolean("Rand_Armor", this.isRandArmor());
        nbt.setBoolean("Loaded_C", this.isLoadedACrossBow());
        nbt.setLong("loot_table_seed", lootTableTradeSeed);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setMeleeAttack(nbt.getBoolean("Melee_Attack"));
        this.setRangedAttack(nbt.getBoolean("Ranged_Mode"));
        this.setShortTrade(nbt.getBoolean("Short_Trade"));
        this.setLongTrade(nbt.getBoolean("Long_Trade"));
        this.setHasMelee(nbt.getBoolean("Has_Melee"));
        this.setHasRanged(nbt.getBoolean("Has_Ranged"));
        this.setHasHelmet(nbt.getBoolean("Has_Helmet"));
        this.setHasChest(nbt.getBoolean("Has_Chest"));
        this.setHasBoots(nbt.getBoolean("Has_Boots"));
        this.setRandArmor(nbt.getBoolean("Rand_Armor"));
        this.setLoadedACrossBow(nbt.getBoolean("Loaded_C"));
        this.lootTableTradeSeed = nbt.getLong("loot_table_seed");

    }

    protected int trade_delay = 30;

    protected boolean foundGoldIngot = false;
    private int dimensionCheck = 40;
    private int countDownToZombie = ModConfig.zombification_time * 20;

    private EntityHoglin foodTarget = null;

    public boolean convertTooZombie = false;

    protected boolean hasPlayedAngrySound = false;

    private boolean initiateBastionAI = false;
    protected int isHungryTimer = 60;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            if (this.isMeleeAttack()) {
                this.setAnimation(ANIMATION_ATTACK_MELEE);
            }
            if (this.isRangedAttack()) {
                this.setAnimation(ANIMATION_ATTACK_RANGED);
            }

            if (this.isShortTrade()) {
                this.setAnimation(ANIMATION_SHORT_TRADE);
            }
        }

        EntityLivingBase target = this.getAttackTarget();

        if (target != null && !hasPlayedAngrySound) {
            this.playSound(ModSoundHandler.PIGLIN_ANGRY, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));
            hasPlayedAngrySound = true;
        } else if (target == null) {
            hasPlayedAngrySound = false;
        }


        if (!world.isRemote && world.rand.nextInt(12) == 0 && !this.isInsideBastion()) {
            if (this.isHungryTimer < 0) {
                List<EntityHoglin> nearbyHoglins = this.world.getEntitiesWithinAABB(EntityHoglin.class, this.getEntityBoundingBox().grow(16D), e -> !e.getIsInvulnerable());
                if (!nearbyHoglins.isEmpty() && target == null && foodTarget == null) {
                    for (EntityHoglin hoglin : nearbyHoglins) {
                        this.createTargetFor(hoglin);
                        foodTarget = hoglin;
                        this.isHungryTimer = 60;
                        List<EntityPiglin> nearbyPiglins = this.world.getEntitiesWithinAABB(EntityPiglin.class, this.getEntityBoundingBox().grow(10D), e -> !e.getIsInvulnerable());
                        if (!nearbyPiglins.isEmpty()) {
                            for (EntityPiglin piglin : nearbyPiglins) {
                                piglin.setAttackTarget(hoglin);
                            }
                        }
                    }
                }
            } else {
                this.isHungryTimer--;
            }
        }

<<<<<<< Updated upstream
        if (dimensionCheck < 0) {
            if (this.world.provider.getDimension() != -1) {
=======
        if(foodTarget != null) {
            if(foodTarget.getHealth() == 0) {
                this.setAttackTarget(null);
                foodTarget = null;
            }
        }

        if(dimensionCheck < 0) {
            if(this.world.provider.getDimension() != -1) {
>>>>>>> Stashed changes
                //Start Zombification Process
                if (countDownToZombie < 0 && !this.convertTooZombie) {
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
        if (this.canTrade && !this.isInsideBastion()) {
            if (trade_delay < 0) {
                List<EntityItem> nearbyItems = this.world.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(5D), e -> !e.getIsInvulnerable());
                if (!nearbyItems.isEmpty()) {
                    for (EntityItem item : nearbyItems) {
                        ItemStack itemStack = item.getItem();
                        if (itemStack.getItem() == Items.GOLD_INGOT && !foundGoldIngot) {
                            double distSq = this.getDistanceSq(item.posX, item.getEntityBoundingBox().minY, item.posZ);
                            this.getNavigator().tryMoveToEntityLiving(item, 1.2D);
                            if (distSq < 2) {
                                item.getItem().shrink(1);
                                this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.GOLD_INGOT));
                                foundGoldIngot = true;
                                this.doTrade();
                                trade_delay = ModConfig.piglins_trade_cooldown;
                            }

                        } else {
                            trade_delay = 30;
                        }

                    }
                } else {
                    trade_delay = 30;
                }

            }
            trade_delay--;

        }
        //Check For Players With Gold Armor nearby
        List<EntityPlayer> nearbyEntities = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(14D), e -> !e.getIsInvulnerable());
        if (!nearbyEntities.isEmpty() && !this.isInsideBastion()) {
            for (EntityPlayer player : nearbyEntities) {
                if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != Items.GOLDEN_HELMET && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.GOLDEN_CHESTPLATE &&
                        player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() != Items.GOLDEN_LEGGINGS && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() != Items.GOLDEN_BOOTS) {
                    if (this.getAttackTarget() != player) {
                        if (!player.isSpectator() && !player.isCreative() && this.getAttackTarget() == null) {
                            canTrade = false;
                            this.createTargetFor(player);
                            this.setAttackTarget(player);
                        }
                    }
                } else {
                    if (this.getAttackTarget() == player && !this.isInsideBastion()) {
                        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items.GOLDEN_HELMET || player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.GOLDEN_CHESTPLATE ||
                                player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items.GOLDEN_LEGGINGS || player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items.GOLDEN_BOOTS) {
                            this.setAttackTarget(null);
                        }
                    }
                    if (this.getAttackTarget() == null && !this.isInsideBastion()) {
                        canTrade = true;
                    }

                }
            }
        }
        if (!ModConfig.piglins_are_aggro && !initiateBastionAI) {
            this.setInsideBastion(false);
            this.initiateBastionAI = true;
        } else if (this.isInsideBastion() && !initiateBastionAI) {
            this.addBastionChanges();
        }

        //NEVER FORGET THIS, OR ELSE ANIMATIONS WILL NOT WORK
        EZAnimationHandler.INSTANCE.updateAnimations(this);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if (source.getImmediateSource() == this || source.getImmediateSource() instanceof EntityPiglinBrute) {
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }


    private void beginZombieTransformation() {
        if (!world.isRemote) {
            addEvent(() -> this.playSound(ModSoundHandler.PIGLIN_CONVERTED, 1.0f, 1.0f), 75);
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

    protected void doTrade() {
        this.setFightMode(true);
        this.setShortTrade(true);
        this.playSound(ModSoundHandler.PIGLIN_ADMIRE, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));

        addEvent(this::getPiglinLootTable, 110);
        addEvent(() -> {
            this.playSound(ModSoundHandler.PIGLIN_IDLE, 1.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.5f));
        }, 110);

        addEvent(() -> {
            this.setFightMode(false);
            this.setShortTrade(false);
            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
            this.setAnimation(NO_ANIMATION);
            this.foundGoldIngot = false;
        }, 120);
    }

    protected void createTargetFor(EntityLivingBase player) {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, player.getClass(), 1, true, false, null));
    }


    protected void addBastionChanges() {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 1, true, false, null));
        this.initiateBastionAI = true;
    }

    @Override
    protected boolean canDespawn() {
        if (this.isInsideBastion()) {
            return false;
        }
        // Edit this to restricting them not despawning in Dungeons
        return this.ticksExisted > 20 * 60 * 20;

    }

    private List<ItemStack> trade_items = Lists.newArrayList();

    protected void getPiglinLootTable() {
        if (!world.isRemote) {
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer) this.world)).withLootedEntity(this);
            trade_items = this.world.getLootTableManager().getLootTableFromLocation(LOOT_TRADE).generateLootForPools(this.lootTableTradeSeed == 0 ? new Random() : new Random(this.lootTableTradeSeed), lootcontext$builder.build());
            for (ItemStack item : trade_items) {
                this.entityDropItem(item, 1.5F);
                //this.world.getLootTableManager().reloadLootTables();
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D * ModConfig.healthScale);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 9.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityPiglinZombie.class, 12F, 1.1D, 1.5D));
    }


    @Override
    public int startAttack(EntityLivingBase target, float distanceSq, boolean strafingBackwards) {
        if (!this.isFightMode() && this.getAnimation() == NO_ANIMATION) {
            double distance = Math.sqrt(distanceSq);
            if (this.isHasRanged()) {
                if (!this.isLoadedACrossBow()) {
                    this.setFightMode(true);
                    this.setRangedAttack(true);
                    this.setImmovable(true);
                    this.setActiveHand(EnumHand.MAIN_HAND);

                    addEvent(() -> {
                        ItemStack stack = getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                        //loads Crossbow
                        this.setLoadedACrossBow(true);
                        stack.onPlayerStoppedUsing(world, this, 0);
                        if (ModIntegration.isCrossbow(stack)) {
                            ModIntegration.setCharged(stack, true);

                        }
                    }, 15);
                    addEvent(() -> {
                        this.setFightMode(false);
                        this.setImmovable(false);
                        this.setRangedAttack(false);
                        this.setAnimation(NO_ANIMATION);
                    }, 25);
                } else {
                    //Shoots Crossbow
                    this.setFightMode(true);

                    addEvent(() -> {
                        EntityArrow arrow = new EntityTippedArrow(world, this);
                        double d0 = target.posX - posX;
                        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3f) - arrow.posY;
                        double d2 = target.posZ - posZ;
                        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
                        arrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6f, (float) (14 - world.getDifficulty().getId() * 4));
                        playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1, 1f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
                        arrow.setDamage(11D * ModConfig.attackDamageScale);
                        world.spawnEntity(arrow);
                        //Unloads the crossbow
                        ItemStack stack = getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                        this.resetActiveHand();
                        if (ModIntegration.isCrossbow(stack)) {
                            ModIntegration.setCharged(stack, false);
                        }
                    }, 5);
                    addEvent(() -> {
                        this.setFightMode(false);
                        this.setLoadedACrossBow(false);
                        this.setAnimation(NO_ANIMATION);
                    }, 10);
                }

            }
            if (this.isHasMelee()) {
                List<Consumer<EntityLivingBase>> attacks = new ArrayList<>(Arrays.asList(meleeAttack, meleeAttackTwo));
                double[] weights = {
                        (distance <= 3 && distance > 0) ? 1 / distance : 1,
                        (distance <= 3 && distance > 0) ? 1 / distance : 2
                };
                prevAttack = ModRand.choice(attacks, rand, weights).next();

                prevAttack.accept(target);
            }
        }
        return this.isHasMelee() ? 5 : this.isHasRanged() ? 8 : 6;
    }

    private final Consumer<EntityLivingBase> meleeAttackTwo = (target) -> {
        this.setFightMode(true);
        this.setMeleeAttack(true);

        addEvent(() -> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.2, 1.2, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage = (float) (8.0F * ModConfig.attackDamageScale);
            ModUtils.handleAreaImpact(1.0f, (e) -> damage, this, offset, source, 0.5f, 0, false);
        }, 18);

        addEvent(() -> {
            this.setMeleeAttack(false);
            this.setFightMode(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private final Consumer<EntityLivingBase> meleeAttack = (target) -> {
        this.setFightMode(true);
        this.setMeleeAttack(true);

        addEvent(() -> {
            Vec3d offset = this.getPositionVector().add(ModUtils.getRelativeOffset(this, new Vec3d(1.2, 1.2, 0)));
            DamageSource source = DamageSource.causeMobDamage(this);
            float damage = (float) (8.0F * ModConfig.attackDamageScale);
            ModUtils.handleAreaImpact(1.0f, (e) -> damage, this, offset, source, 0.5f, 0, false);
        }, 18);

        addEvent(() -> {
            this.setMeleeAttack(false);
            this.setFightMode(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private final Consumer<EntityLivingBase> load_crossbow = (target) -> {
        this.setFightMode(true);
        this.setRangedAttack(true);
        this.setImmovable(true);

        addEvent(() -> {
            ItemStack stack = getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            //loads Crossbow
            this.setLoadedACrossBow(true);
            if (ModIntegration.isCrossbow(stack)) {
                ModIntegration.setCharged(stack, true);
            }
        }, 15);
        addEvent(() -> {
            this.setFightMode(false);
            this.setImmovable(false);
            this.setRangedAttack(false);
            this.setAnimation(NO_ANIMATION);
        }, 25);
    };

    private final Consumer<EntityLivingBase> shoot_crossbow = (target) -> {
        this.setFightMode(true);

        addEvent(() -> {
            EntityArrow arrow = new EntityTippedArrow(world, this);
            double d0 = target.posX - posX;
            double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3f) - arrow.posY;
            double d2 = target.posZ - posZ;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            arrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6f, (float) (14 - world.getDifficulty().getId() * 4));
            playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1, 1f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
            arrow.setDamage(11D);
            world.spawnEntity(arrow);
            //Unloads the crossbow
            ItemStack stack = getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            if (ModIntegration.isCrossbow(stack)) {
                ModIntegration.setCharged(stack, false);
            }
        }, 5);
        addEvent(() -> {
            this.setFightMode(false);
            this.setLoadedACrossBow(false);
            this.setAnimation(NO_ANIMATION);
        }, 10);

    };

    protected EntityArrow getArrow(float distance) {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, distance);
        return entitytippedarrow;
    }


    @Override
    public boolean canPickUpLoot() {
        return true;
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

    private static final ResourceLocation LOOT = new ResourceLocation(ModReference.MOD_ID, "piglin");

    @Override
    protected ResourceLocation getLootTable() {
        return null;
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(ModSoundHandler.PIGLIN_STEP, 0.5F, 1.0f / (rand.nextFloat() * 0.4F + 0.2f));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundHandler.PIGLIN_DEATH;
    }


}