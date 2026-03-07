package com.feiteng.ftlearning.block.custom;

import java.util.function.ToIntFunction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TunableEmitterBlock extends Block {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;

    public static final ToIntFunction<BlockState> LIGHT_EMISSION = state -> state.getValue(LEVEL);

    public TunableEmitterBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(POWER, 0).setValue(LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER, LEVEL);
    }

    @Override
    protected int getSignal(BlockState block_state, BlockGetter block_getter,
            BlockPos block_pos, Direction direction) {
        return block_state.getValue(POWER);
    }

    @Override
    protected boolean isSignalSource(BlockState block_state) {
        return true;
    }
}
