package com.feiteng.ftlearning.tag;

import com.feiteng.ftlearning.FTLearning;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static TagKey<Item> DISC_FRAGMENTS = of("disc_fragments");
    public static TagKey<Item> DINGZHEN_MUSIC_DISCS = of("dingzhen_music_discs");

    private static TagKey<Item> of(String name) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(FTLearning.MOD_ID, name));
    }
}
