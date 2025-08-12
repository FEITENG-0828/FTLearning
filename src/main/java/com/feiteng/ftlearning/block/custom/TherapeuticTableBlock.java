package com.feiteng.ftlearning.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TherapeuticTableBlock extends Block {
    public TherapeuticTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!world.isClient &&
            entity instanceof PlayerEntity player &&
            world.getTime() % 50 == 0) {
            player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.REGENERATION, 120, 0));
        }
    }
}
