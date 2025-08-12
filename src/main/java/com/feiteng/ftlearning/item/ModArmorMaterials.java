package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements StringIdentifiable, ArmorMaterial {
    VOIDABYSS_STONE("voidabyss_stone",
        20,
        Util.make(new EnumMap<>(ArmorItem.Type.class),
            map -> {
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.BOOTS, 1);
            }),
        20,
        SoundEvents.ITEM_ARMOR_EQUIP_IRON,
        1.0F,
        0.05F, () -> Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE));

    private final String name;
    private final int durability_multiplier;
    private final EnumMap<ArmorItem.Type, Integer> protection_amounts;
    private final int enchantability;
    private final SoundEvent equip_sound;
    private final float toughness;
    private final float knockback_resistance;
    private final Supplier<Ingredient> repair_ingredient_supplier;

    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(
        new EnumMap<>(ArmorItem.Type.class),
        map -> {
            map.put(ArmorItem.Type.HELMET, 11);
            map.put(ArmorItem.Type.CHESTPLATE, 16);
            map.put(ArmorItem.Type.LEGGINGS, 15);
            map.put(ArmorItem.Type.BOOTS, 13);
    });

    ModArmorMaterials(String name,
                      int durability_multiplier,
                      EnumMap<ArmorItem.Type, Integer> protection_amounts,
                      int enchantability,
                      SoundEvent equip_sound,
                      float toughness,
                      float knockback_resistance,
                      Supplier<Ingredient> repair_ingredient_supplier) {
        this.name = name;
        this.durability_multiplier = durability_multiplier;
        this.protection_amounts = protection_amounts;
        this.enchantability = enchantability;
        this.equip_sound = equip_sound;
        this.toughness = toughness;
        this.knockback_resistance = knockback_resistance;
        this.repair_ingredient_supplier = repair_ingredient_supplier;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type) * this.durability_multiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protection_amounts.get(type);
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equip_sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repair_ingredient_supplier.get();
    }

    @Override
    public String getName() {
        return FTLearning.MOD_ID + ':' + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockback_resistance;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
