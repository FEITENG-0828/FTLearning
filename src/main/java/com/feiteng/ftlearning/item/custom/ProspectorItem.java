package com.feiteng.ftlearning.item.custom;

import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.sound.ModSoundEvents;
import com.feiteng.ftlearning.tag.ModBlockTags;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProspectorItem extends Item {
    private static final int SEARCH_RADIUS = 64;

    public ProspectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            BlockPos block_pos = context.getBlockPos();
            boolean has_found_block = false;
            for (int i = SEARCH_RADIUS; i >= -SEARCH_RADIUS; --i) {
                BlockPos check_pos = block_pos.up(i);
                BlockState state = world.getBlockState(check_pos);
                if (isRightBlock(state)) {
                    has_found_block = true;
                    player.sendMessage(Text.translatable(
                        ModItems.PROSPECTOR.getTranslationKey() + ".use.success",
                        state.getBlock().asItem().getName().getString(),
                        check_pos.getX(), check_pos.getY(), check_pos.getZ()));
                    world.playSound(null, block_pos,
                        ModSoundEvents.ITEM_PROSPECTOR_USE_SUCCESS, player.getSoundCategory());
                    break;
                }
            }
            if (!has_found_block) {
                player.sendMessage(Text.translatable(
                    ModItems.PROSPECTOR.getTranslationKey() + ".use.failure"));
                world.playSound(null, block_pos,
                    ModSoundEvents.ITEM_PROSPECTOR_USE_FAILURE, player.getSoundCategory());
            }
        }
        context.getStack().damage(1, player,
            player_entity ->
                player_entity.sendToolBreakStatus(player_entity.getActiveHand())
        );
        return ActionResult.SUCCESS;
    }

    private boolean isRightBlock(BlockState state) {
        return state.isIn(ModBlockTags.PROSPECTOR_LIST);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world,
                              List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!Screen.hasShiftDown()) {
            tooltip.add(HelpfulFuncs.getTooltipMaskText("shift"));
        } else {
            tooltip.add(Text.translatable(
                ModItems.PROSPECTOR.getTranslationKey() + ".tooltip").formatted(Formatting.GRAY));
        }
    }
}
