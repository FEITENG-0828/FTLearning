package com.feiteng.ftlearning.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TherapeuticTableBlock extends Block {
    public TherapeuticTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        if (!level.isClientSide() &&
                entity instanceof LivingEntity living_entity &&
                level.getLevelData().getGameTime() % 50 == 0) {
            living_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 0));
        }
    }
}
