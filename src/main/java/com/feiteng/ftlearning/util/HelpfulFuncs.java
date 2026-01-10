package com.feiteng.ftlearning.util;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.item.ItemConvertible;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class HelpfulFuncs {
    public static String getSimpleIdStr(ItemConvertible item_convertible) {
        return Registries.ITEM.getId(item_convertible.asItem()).getPath();
    }

    public static String getModNamespacedSimpleIdStr(ItemConvertible item_convertible) {
        return FTLearning.MOD_ID + ':' + getSimpleIdStr(item_convertible);
    }

    public static MutableComponent getTooltipMaskText(String key_name) {
        return Component.translatable("item.ftlearning.tooltip_mask1").withStyle(ChatFormatting.AQUA)
            .append(Component.literal(key_name.toUpperCase())
                .withStyle(ChatFormatting.GOLD, ChatFormatting.UNDERLINE))
            .append(Component.translatable("item.ftlearning.tooltip_mask2").withStyle(ChatFormatting.AQUA));
    }
}
