package com.feiteng.ftlearning.tag;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static TagKey<Block> PROSPECTING_LIST = of("prospecting_list");

    public static TagKey<Block> of(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(FTLearning.MOD_ID, name));
    }
}
