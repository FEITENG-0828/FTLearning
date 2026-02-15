package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.effect.ModEffects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> FURY = register("glowing",
            new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 3600)));
    public static final Holder<Potion> FURY_STRONG = register("strong_glowing",
            new Potion("strong_glowing", new MobEffectInstance(MobEffects.GLOWING, 3600, 1)));
    public static final Holder<Potion> FURY_LONG = register("long_glowing",
            new Potion("long_glowing", new MobEffectInstance(MobEffects.GLOWING, 9600)));
    public static final Holder<Potion> FURY_SPLASH = register("splash_glowing",
            new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 3600)));
    public static final Holder<Potion> FURY_LINGERING = register("lingering_glowing",
            new Potion("glowing", new MobEffectInstance(MobEffects.GLOWING, 3600)));
    public static final Holder<Potion> ATTACK_SPEED_I = register("attack_speed_i",
            new Potion("attack_speed_i", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_I, 3600)));
    public static final Holder<Potion> ATTACK_SPEED_II = register("attack_speed_ii",
            new Potion("attack_speed_ii", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_II, 3600)));
    public static final Holder<Potion> ATTACK_SPEED_III = register("attack_speed_iii",
            new Potion("attack_speed_iii", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_III, 3600)));
    public static final Holder<Potion> ATTACK_SPEED_LONG = register("attack_speed_long",
            new Potion("attack_speed_long", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_I, 9600)));
    public static final Holder<Potion> ATTACK_SPEED_SPLASH = register("splash_attack_speed_i",
            new Potion("attack_speed_i", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_I, 3600)));
    public static final Holder<Potion> ATTACK_SPEED_LINGERING = register("lingering_attack_speed_i",
            new Potion("attack_speed_i", new MobEffectInstance(ModEffects.ATTACK_SPEED_BOOST_I, 3600)));

    private static Holder<Potion> register(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name), potion);
    }

    public static void bootstrap() {
    }
}
