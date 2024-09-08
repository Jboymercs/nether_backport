package com.unseen.nb.common.event;


import com.unseen.nb.Main;
import com.unseen.nb.common.enchantments.NBEnchantmentSoulSpeed;
import com.unseen.nb.common.items.netherite.ItemNBHorseArmor;
import com.unseen.nb.init.ModEnchantments;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.init.ModSoundHandler;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.ModUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class EventOnSoulSpeed {


    @SubscribeEvent
    public static void addSoulSpeed(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityIn = event.getEntityLiving();

        IAttributeInstance attributeIn = entityIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        World worldIn = entityIn.world;
        BlockPos blockPos = new BlockPos(entityIn.posX, entityIn.posY - 0.5D, entityIn.posZ);
        IBlockState state = worldIn.getBlockState(blockPos);

        if(entityIn.onGround && ModUtils.hasEnchant(entityIn)) {
            if(ModUtils.getBlocksThatCanBeUsed(state.getBlock())) {
                if(!worldIn.isRemote) {
                    int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SOUL_SPEED, entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
                    float speed = (i * 0.105F) + 1.3F;

                    if(attributeIn.getModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) attributeIn.applyModifier(new AttributeModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));
                }

                if(entityIn.motionX != 0 && entityIn.motionZ != 0 && entityIn.ticksExisted % 5 == 0) {
                    Main.proxy.spawnSoulParticle(worldIn, entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.motionX, entityIn.motionY, entityIn.motionZ);
                    float f = worldIn.rand.nextFloat() * 0.4F + worldIn.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
                    worldIn.playSound(null, blockPos, ModSoundHandler.SOUL_SAND_SCREAM, SoundCategory.AMBIENT, f, 0.6F + worldIn.rand.nextFloat() * 0.4F);
                }
            } else {
                if(attributeIn.getModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
            }
        }
    }


    @SubscribeEvent
    public static void adjustSoulSpeedUponBootChange(LivingEquipmentChangeEvent event) {
        EntityLivingBase entityIn = event.getEntityLiving();
        IAttributeInstance attributeIn = entityIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        IBlockState state = entityIn.world.getBlockState(new BlockPos(entityIn.posX, entityIn.posY - 0.5D, entityIn.posZ));

        if(event.getSlot() == EntityEquipmentSlot.FEET) {
            if(!ModUtils.hasEnchant(entityIn)) {
                if(attributeIn.getModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
            } else {
                if(ModUtils.getBlocksThatCanBeUsed(state.getBlock())) {
                    //Adjusting the speed, just incase the switched equipment has a different level than the original one
                    if(attributeIn.getModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) != null) attributeIn.removeModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER);
                    int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SOUL_SPEED, entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET));
                    float speed = (i * 0.105F) + 1.3F;

                    if(!entityIn.world.isRemote && attributeIn.getModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER) == null) {
                        attributeIn.applyModifier(new AttributeModifier(NBEnchantmentSoulSpeed.SOUL_SPEED_MODIFIER, "soul_speed_modifier", speed, 1).setSaved(false));
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void removeNetheriteHorseArmorBuffs(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityIn = event.getEntityLiving();

        if(entityIn instanceof EntityHorse) {
            EntityHorse horse = (EntityHorse)entityIn;
            if(horse.getHorseArmorType() != ModItems.NETHERITE_HORSE_ARMOR) {
                IAttributeInstance attribute = horse.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
                if(attribute.getModifier(ItemNBHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER) != null) {
                    attribute.removeModifier(ItemNBHorseArmor.KNOCKBACK_RESISTANCE_MODIFIER);
                }
            }
        }
    }
}
