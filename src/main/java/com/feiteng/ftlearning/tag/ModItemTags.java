package com.feiteng.ftlearning.tag;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> DISC_FRAGMENTS = create("disc_fragments");
    public static final TagKey<Item> DINGZHEN_MUSIC_DISCS = create("dingzhen_music_discs");
    public static final TagKey<Item> REPAIRS_VOIDABYSS_STONE_ARMOR = create("repairs_voidabyss_stone_armor");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
    }
}
