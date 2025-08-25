package com.feiteng.ftlearning.tag;

import com.feiteng.ftlearning.FTLearning;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static TagKey<Block> PROSPECTING_LIST = of("prospecting_list");
    public static TagKey<Block> COMPRESSED_BLOCKS = of("compressed_blocks");

    private static TagKey<Block> of(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(FTLearning.MOD_ID, name));
    }
}
