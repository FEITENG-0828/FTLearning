package com.feiteng.ftlearning.effect;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModEffects {
    public static final Holder<MobEffect> ATTACK_SPEED_BOOST_I = register("attack_speed_boost_i",
            new AttackSpeedBoostIEffect());

    public static final Holder<MobEffect> ATTACK_SPEED_BOOST_II = register("attack_speed_boost_ii",
            new AttackSpeedBoostIIEffect());

    public static final Holder<MobEffect> ATTACK_SPEED_BOOST_III = register("attack_speed_boost_iii",
            new AttackSpeedBoostIIIEffect());

    private static Holder<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name), effect);
    }

    public static void bootstrap() {
    }

    private static class AttackSpeedBoostIEffect extends MobEffect {
        public AttackSpeedBoostIEffect() {
            super(MobEffectCategory.BENEFICIAL, 0x00FF00);
            this.addAttributeModifier(Attributes.ATTACK_SPEED,
                    Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "attack_speed_boost_i"),
                    0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }
    }

    private static class AttackSpeedBoostIIEffect extends MobEffect {
        public AttackSpeedBoostIIEffect() {
            super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
            this.addAttributeModifier(Attributes.ATTACK_SPEED,
                    Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "attack_speed_boost_ii"),
                    1.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }
    }

    private static class AttackSpeedBoostIIIEffect extends MobEffect {
        public AttackSpeedBoostIIIEffect() {
            super(MobEffectCategory.BENEFICIAL, 0xFF0000);
            this.addAttributeModifier(Attributes.ATTACK_SPEED,
                    Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "attack_speed_boost_iii"),
                    2.0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }
    }
}