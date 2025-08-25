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

    public static String compressLevelToRoman(int level) {
        return switch (level) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> throw new IllegalArgumentException("Invalid compression level");
        };
    }

    public static String getCompressedBlockName(String base_name, int level, String language_code) {
        if (level < 1 || level > 9) {
            throw new IllegalArgumentException("Invalid compression level");
        }
        return switch (language_code) {
            case "en_us" -> "Compressed " + base_name + ' ' + compressLevelToRoman(level);
            case "zh_cn" -> compressLevelToRoman(level) + "级" + "压缩" + base_name;
            default -> "Compressed " + base_name + ' ' + compressLevelToRoman(level);
        };
    }
}
