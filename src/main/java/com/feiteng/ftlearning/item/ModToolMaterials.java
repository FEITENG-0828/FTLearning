package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.block.ModBlocks;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    VOIDABYSS_STONE(MiningLevels.IRON,
        500,
        5.0f,
        1.8f,
        30,
        () -> Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE));

    private final int mining_level;
    private final int item_durability;
    private final float mining_speed;
    private final float attack_damage;
    private final int enchantability;
    private final Supplier<Ingredient> repair_ingredient;

    ModToolMaterials(int mining_level,
                     int item_durability,
                     float mining_speed,
                     float attack_damage,
                     int enchantability,
                     Supplier<Ingredient> repair_ingredient) {
        this.mining_level = mining_level;
        this.item_durability = item_durability;
        this.mining_speed = mining_speed;
        this.attack_damage = attack_damage;
        this.enchantability = enchantability;
        this.repair_ingredient = repair_ingredient;
    }

    @Override
    public int getMiningLevel() {
        return this.mining_level;
    }

    @Override
    public int getDurability() {
        return this.item_durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.mining_speed;
    }

    @Override
    public float getAttackDamage() {
        return this.attack_damage;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repair_ingredient.get();
    }
}
