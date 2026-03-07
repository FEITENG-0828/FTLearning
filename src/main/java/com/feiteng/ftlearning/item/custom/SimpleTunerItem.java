package com.feiteng.ftlearning.item.custom;

import java.util.function.Consumer;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.custom.TunableEmitterBlock;
import com.feiteng.ftlearning.component.ModDataComponents;
import com.feiteng.ftlearning.component.SimpleTunerData;
import com.feiteng.ftlearning.item.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleTunerItem extends Item {
    public SimpleTunerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }
        ItemStack stack = player.getItemInHand(hand);
        SimpleTunerData data = getOrCreateData(stack);
        if (player.isDiscrete()) {
            data = data.changeMode();
        } else {
            data = data.increase();
        }
        stack.set(ModDataComponents.SIMPLE_TUNER_DATA, data);
        player.displayClientMessage(getUseTranslatable(data).withStyle(ChatFormatting.GREEN), true);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player user = context.getPlayer();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        BlockPos block_pos = context.getClickedPos();
        BlockState block_state = level.getBlockState(block_pos);
        if (user == null || !stack.is(ModItems.SIMPLE_TUNER) ||
                !block_state.is(ModBlocks.TUNABLE_EMITTER)) {
            return InteractionResult.PASS;
        } else if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            SimpleTunerData data = getOrCreateData(stack);
            level.setBlockAndUpdate(block_pos,
                    block_state.setValue(TunableEmitterBlock.POWER, data.signal())
                            .setValue(TunableEmitterBlock.LEVEL, data.emission()));
            return InteractionResult.SUCCESS;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(
            ItemStack stack, Item.TooltipContext context, TooltipDisplay display,
            Consumer<Component> consumer, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, consumer, flag);

        SimpleTunerData data = getOrCreateData(stack);
        consumer.accept(getHoverTranslatable(data).withStyle(ChatFormatting.GOLD));
    }

    private SimpleTunerData getOrCreateData(ItemStack stack) {
        if (!stack.has(ModDataComponents.SIMPLE_TUNER_DATA)) {
            stack.set(ModDataComponents.SIMPLE_TUNER_DATA, SimpleTunerData.DEFAULT);
        }
        return stack.get(ModDataComponents.SIMPLE_TUNER_DATA);
    }

    public MutableComponent getUseTranslatable(SimpleTunerData data) {
        return Component.translatable(descriptionId + ".use",
                Component.translatable(data.getModeTranslationKey()), data.getValue());
    }

    public MutableComponent getHoverTranslatable(SimpleTunerData data) {
        return Component.translatable(descriptionId + ".hover",
                Component.translatable(SimpleTunerData.Mode.getTranslationKey(
                        SimpleTunerData.Mode.SIGNAL)), data.signal(),
                Component.translatable(SimpleTunerData.Mode.getTranslationKey(
                        SimpleTunerData.Mode.EMISSION)), data.emission());
    }
}
