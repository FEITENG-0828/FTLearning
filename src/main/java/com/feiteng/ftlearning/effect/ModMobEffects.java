package com.feiteng.ftlearning.effect;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class ModMobEffects {
    public static final Holder<MobEffect> FURY = register("fury", new FuryMobEffect());

    private static Holder<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name), effect);
    }

    public static void bootstrap() {
    }
}
