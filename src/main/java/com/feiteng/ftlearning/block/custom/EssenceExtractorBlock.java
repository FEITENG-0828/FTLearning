package com.feiteng.ftlearning.block.custom;

import org.jspecify.annotations.Nullable;

import com.feiteng.ftlearning.block.ModBlockEntityTypes;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class EssenceExtractorBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PROCESSING = BlockStateProperties.LIT;

    public static final MapCodec<EssenceExtractorBlock> CODEC = simpleCodec(EssenceExtractorBlock::new);

    public EssenceExtractorBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(FACING, Direction.NORTH).setValue(PROCESSING, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos block_pos, BlockState block_state) {
        return new EssenceExtractorBlockEntity(block_pos, block_state);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState block_state, ServerLevel level,
            BlockPos block_pos, boolean bool) {
        Containers.updateNeighboursAfterDestroy(block_state, level, block_pos);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState block_state, Level level,
            BlockPos block_pos, Player player, BlockHitResult hit_result) {
        if (!level.isClientSide() &&
                level.getBlockEntity(block_pos) instanceof EssenceExtractorBlockEntity block_entity) {
            player.openMenu(block_entity);
            // player.awardStat(Stats.INTERACT_WITH_FURNACE); TODO
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState block_state,
            BlockEntityType<T> type) {
        return level.isClientSide() ? null
                : createTickerHelper(type, ModBlockEntityTypes.ESSENCE_EXTRACTOR,
                        EssenceExtractorBlockEntity::serverTick);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext block_place_context) {
        return defaultBlockState().setValue(FACING,
                block_place_context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState block_state, Rotation rotation) {
        return block_state.setValue(FACING, rotation.rotate(block_state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState block_state, Mirror mirror) {
        return block_state.rotate(mirror.getRotation(block_state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PROCESSING);
    }
}
