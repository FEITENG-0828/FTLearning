package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.effect.ModMobEffects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> FURY = register("fury",
            new Potion("fury", new MobEffectInstance(ModMobEffects.FURY, 120 * 20)));
    public static final Holder<Potion> LONG_FURY = register("long_fury",
            new Potion("fury", new MobEffectInstance(ModMobEffects.FURY, 360 * 20)));
    public static final Holder<Potion> STRONG_FURY = register("strong_fury",
            new Potion("fury", new MobEffectInstance(ModMobEffects.FURY, 120 * 20, 2)));

    private static Holder<Potion> register(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name), potion);
    }

    public static void bootstrap() {
    }
}
