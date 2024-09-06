package com.unseen.nb.util;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;
import java.util.Random;

/**
 * Big thanks to SmileyCorps for this one as well, helps with adding soul speed enchantments from piglin trades as well
 * as locations to find it
 */
public class LootTableExtendedFunc extends LootFunction {
    private final Map<Enchantment, RandomValueRange> enchantments;

    protected LootTableExtendedFunc(LootCondition[] conditions, Map<Enchantment, RandomValueRange> enchantments) {
        super(conditions);
        this.enchantments = enchantments;
    }

    @Override
    public ItemStack apply(ItemStack stack, Random random, LootContext lootContext) {
        if (stack.getItem() == Items.BOOK) stack = new ItemStack(Items.ENCHANTED_BOOK);
        Map<Enchantment, Integer> enchantments = Maps.newHashMap();
        for (Map.Entry<Enchantment, RandomValueRange> entry : this.enchantments.entrySet())
            enchantments.put(entry.getKey(), entry.getValue().generateInt(random));
        EnchantmentHelper.setEnchantments(enchantments, stack);
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<LootTableExtendedFunc> {

        public Serializer() {
            super(ModReference.loc("apply_enchantments"), LootTableExtendedFunc.class);
        }

        public void serialize(JsonObject json, LootTableExtendedFunc instance, JsonSerializationContext ctx) {
            if (!instance.enchantments.isEmpty()) {
                JsonObject obj = new JsonObject();
                for (Map.Entry<Enchantment, RandomValueRange> entry : instance.enchantments.entrySet())
                    obj.add(entry.getKey().getRegistryName().toString(), ctx.serialize(entry.getValue()));
                json.add("enchantments", obj);
            }
        }

        public LootTableExtendedFunc deserialize(JsonObject json, JsonDeserializationContext ctx, LootCondition[] conditions) {
            Map<Enchantment, RandomValueRange> enchantments = Maps.newHashMap();
            if (json.has("enchantments")) {
                JsonObject obj = json.get("enchantments").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                    try {
                        enchantments.put(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(entry.getKey())),
                                JsonUtils.deserializeClass(entry.getValue(), "count", ctx, RandomValueRange.class));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Don't know how to serialize entry " + entry);
                    }
                }
            }
            return new LootTableExtendedFunc(conditions, enchantments);
        }
    }
}
