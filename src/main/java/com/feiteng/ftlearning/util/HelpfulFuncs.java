package com.feiteng.ftlearning.util;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.item.ItemConvertible;
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

    public static MutableText getTooltipMaskText(String key_name) {
        return Text.translatable("item.ftlearning.tooltip_mask1").formatted(Formatting.AQUA)
            .append(Text.literal(key_name.toUpperCase())
                .formatted(Formatting.GOLD, Formatting.UNDERLINE))
            .append(Text.translatable("item.ftlearning.tooltip_mask2").formatted(Formatting.AQUA));
    }
}
