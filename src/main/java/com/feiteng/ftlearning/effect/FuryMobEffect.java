package com.feiteng.ftlearning.effect;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FuryMobEffect extends MobEffect {
    protected FuryMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xE9B8B3); // TODO: find a better color
        addAttributeModifier(Attributes.ATTACK_SPEED,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "effect.fury"),
                0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
