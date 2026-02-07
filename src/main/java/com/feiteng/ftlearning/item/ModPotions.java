package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> FURY = register("fury",
            new Potion("fury", new MobEffectInstance(MobEffects.GLOWING, 3600))); // TODO
    // TODO: strong / long

    private static Holder<Potion> register(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name), potion);
    }

    public static void bootstrap() {
    }
}
