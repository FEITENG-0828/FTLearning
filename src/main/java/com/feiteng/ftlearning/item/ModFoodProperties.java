package com.feiteng.ftlearning.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties NIGHT_VISION_GOLDEN_CARROT = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(1.2F)
            .alwaysEdible()
            .build();
}
