package com.feiteng.ftlearning.enchantment;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record MireEnchantmentEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<MireEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(LevelBasedValue.CODEC.fieldOf("amount").forGetter(
                    MireEnchantmentEffect::amount))
                    .apply(instance, MireEnchantmentEffect::new));

    @Override
    public void apply(ServerLevel server_level, int level,
            EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim &&
                context.owner() != null && context.owner() instanceof Player) {
            for (int i = 0; i < level; ++i) {
                victim.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 2 * 20, i));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
