package com.feiteng.ftlearning.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent NIGHT_VISION_GOLDEN_CARROT = new FoodComponent.Builder()
        .hunger(5)
        .saturationModifier(1.2F)
        .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,7654, 0), 1.0F)
        .alwaysEdible()
        .build();
}
