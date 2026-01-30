package com.feiteng.ftlearning.util;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.ItemLike;

public class HelpfulFuncs {
    public static String getModNamespacedIdStr(ItemLike item_like) {
        return FTLearning.MOD_ID + ':' + RecipeProvider.getItemName(item_like);
    }

    public static MutableComponent getTooltipMaskText(String key_name) {
        return Component.translatable("item.ftlearning.tooltip_mask1").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(key_name.toUpperCase())
                        .withStyle(ChatFormatting.GOLD, ChatFormatting.UNDERLINE))
                .append(Component.translatable("item.ftlearning.tooltip_mask2").withStyle(ChatFormatting.AQUA));
    }
}
