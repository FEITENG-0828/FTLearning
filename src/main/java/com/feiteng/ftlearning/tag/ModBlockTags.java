package com.feiteng.ftlearning.tag;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static TagKey<Block> PROSPECTING_LIST = create("prospecting_list");

    public static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
    }
}
