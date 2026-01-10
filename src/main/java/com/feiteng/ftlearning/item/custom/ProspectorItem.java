package com.feiteng.ftlearning.item.custom;

import java.util.function.Consumer;

import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.sound.ModSoundEvents;
import com.feiteng.ftlearning.tag.ModBlockTags;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ProspectorItem extends Item {
    private static final int SEARCH_RADIUS = 64;

    public ProspectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player user = context.getPlayer();
        if (user == null || level.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos block_pos = context.getClickedPos();
        boolean has_found_block = false;
        for (int i = SEARCH_RADIUS; i >= -SEARCH_RADIUS; --i) {
            BlockPos check_pos = block_pos.above(i);
            BlockState block_state = level.getBlockState(check_pos);
            if (isRightBlock(block_state)) {
                has_found_block = true;
                user.displayClientMessage(Component.translatable(
                        ModItems.PROSPECTOR.getDescriptionId() + ".use.success",
                        block_state.getBlock().getName(),
                        check_pos.getX(), check_pos.getY(), check_pos.getZ()),
                        true); // TODO: ?
                level.playSound(user, user.blockPosition(),
                        ModSoundEvents.ITEM_PROSPECTOR_USE_SUCCESS, user.getSoundSource());
                break;
            }
        }
        if (!has_found_block) {
            user.displayClientMessage(Component.translatable(
                    ModItems.PROSPECTOR.getDescriptionId() + ".use.failure"),
                    true); // TODO: ?
            level.playSound(user, user.blockPosition(),
                    ModSoundEvents.ITEM_PROSPECTOR_USE_FAILURE, user.getSoundSource());
        }

        context.getItemInHand().hurtAndBreak(1, user, context.getHand());
        return InteractionResult.SUCCESS;
    }

    private boolean isRightBlock(BlockState block_state) {
        return block_state.is(ModBlockTags.PROSPECTING_LIST);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(
            ItemStack stack, Item.TooltipContext context, TooltipDisplay display,
            Consumer<Component> consumer, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, consumer, flag);

        if (!stack.getEntityRepresentation().isShiftKeyDown()) {
            consumer.accept(HelpfulFuncs.getTooltipMaskText("shift"));
        } else {
            consumer.accept(Component.translatable(
                    this.descriptionId + ".tooltip").withStyle(ChatFormatting.GRAY));
        }
    }
}
