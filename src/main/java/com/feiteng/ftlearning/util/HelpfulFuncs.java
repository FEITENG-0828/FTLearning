package com.feiteng.ftlearning.util;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;

public class HelpfulFuncs {
    public static final String HOVER_MASK1_KEY = "item.ftlearning.hover_mask1";
    public static final String HOVER_MASK2_KEY = "item.ftlearning.hover_mask2";

    public static String makeDescriptionId(String string, ResourceKey<?> key) {
        return Util.makeDescriptionId(string, key.identifier());
    }

    public static String getPotionTranslationKey(Holder<Potion> potion_holder) {
        return Items.POTION.getDescriptionId() + ".effect." + potion_holder.value().name();
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
