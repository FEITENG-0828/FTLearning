package com.feiteng.ftlearning.util;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;

public class HelpfulFuncs {
    public static String getSimpleIdStr(ItemConvertible item_convertible) {
        return Registries.ITEM.getId(item_convertible.asItem()).getPath();
    }

    public static String getModNamespacedSimpleIdStr(ItemConvertible item_convertible) {
        return FTLearning.MOD_ID + ':' + getSimpleIdStr(item_convertible);
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
            case "zh_cn" -> "压缩" + base_name + compressLevelToRoman(level) + "级";
            default -> "Compressed " + base_name + ' ' + compressLevelToRoman(level);
        };
    }
}
