package com.feiteng.ftlearning.util;

import java.util.HashMap;
import java.util.Map;

import com.feiteng.ftlearning.FTLearning;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;

public class HelpfulFuncs {
    public static final String HOVER_MASK1_KEY = "item.ftlearning.hover_mask1";
    public static final String HOVER_MASK2_KEY = "item.ftlearning.hover_mask2";

    public static final Map<String, Map<Item, String>> POTION_TRANSLATIONS = Util.make(
            new HashMap<>(), map -> {
                map.put("en_us", Util.make(new HashMap<>(), m -> {
                    m.put(Items.POTION, "Potion of %s");
                    m.put(Items.SPLASH_POTION, "Splash Potion of %s");
                    m.put(Items.LINGERING_POTION, "Lingering Potion of %s");
                }));
                map.put("zh_cn", Util.make(new HashMap<>(), m -> {
                    m.put(Items.POTION, "%s药水");
                    m.put(Items.SPLASH_POTION, "喷溅型%s药水");
                    m.put(Items.LINGERING_POTION, "滞留型%s药水");
                }));
            });

    public static void translatePotion(TranslationBuilder builder, String lang,
            String translation, Holder<Potion> holder) { // FIXME: IGNORE unsafe vararg ?
        POTION_TRANSLATIONS.get(lang).forEach((potion_item, format) -> builder.add(
                getPotionTranslationKey(potion_item, holder), String.format(format, translation)));
    }

    private static String getPotionTranslationKey(Item potion_item, Holder<Potion> potion_holder) {
        return potion_item.getDescriptionId() + ".effect." + potion_holder.value().name();
    }

    public static String makeDescriptionId(String string, ResourceKey<?> key) {
        return Util.makeDescriptionId(string, key.identifier());
    }

    public static String getModNamespacedIdStr(ItemLike item_like) {
        return FTLearning.MOD_ID + ':' + RecipeProvider.getItemName(item_like);
    }

    public static MutableComponent getHoverMaskComponent(String key_name) {
        return Component.translatable(HOVER_MASK1_KEY).withStyle(ChatFormatting.AQUA)
                .append(Component.literal(key_name.toUpperCase())
                        .withStyle(ChatFormatting.GOLD, ChatFormatting.UNDERLINE))
                .append(Component.translatable(HOVER_MASK2_KEY).withStyle(ChatFormatting.AQUA));
    }
}
