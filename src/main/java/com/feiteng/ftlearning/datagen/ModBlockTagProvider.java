package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.tag.ModBlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
//import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
//import net.minecraft.registry.tag.TagKey;
//import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries_future) {
        super(output, registries_future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapper_lookup) {
        getOrCreateTagBuilder(ModBlockTags.PROSPECTING_LIST)
            .forceAddTag(BlockTags.DIAMOND_ORES)
            .add(Blocks.ANCIENT_DEBRIS);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.FIRST_ITEM_BLOCK)
            .add(ModBlocks.THERAPEUTIC_TABLE)
            .add(ModBlocks.VOIDABYSS_STONE)
            .add(ModBlocks.VOIDABYSS_STONE_STAIRS)
            .add(ModBlocks.VOIDABYSS_STONE_SLAB)
            .add(ModBlocks.VOIDABYSS_STONE_WALL)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE)
            .add(ModBlocks.VOIDABYSS_STONE_BUTTON)
            .add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE)
            .add(ModBlocks.VOIDABYSS_STONE_DOOR)
            .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.FIRST_ITEM_BLOCK)
            .add(ModBlocks.VOIDABYSS_STONE)
            .add(ModBlocks.VOIDABYSS_STONE_STAIRS)
            .add(ModBlocks.VOIDABYSS_STONE_SLAB)
            .add(ModBlocks.VOIDABYSS_STONE_WALL)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE)
            .add(ModBlocks.VOIDABYSS_STONE_BUTTON)
            .add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE)
            .add(ModBlocks.VOIDABYSS_STONE_DOOR)
            .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.THERAPEUTIC_TABLE);

//        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
//            .add(Blocks.REINFORCED_DEEPSLATE);

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(ModBlocks.VOIDABYSS_STONE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(ModBlocks.VOIDABYSS_STONE_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(ModBlocks.VOIDABYSS_STONE_WALL);

        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.STONE_BUTTONS)
            .add(ModBlocks.VOIDABYSS_STONE_BUTTON);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
            .add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.DOORS)
            .add(ModBlocks.VOIDABYSS_STONE_DOOR);

        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
            .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        CompressedBlocks.configureBlockTagAll(this);
    }

    public void addTag(TagKey<Block> tag, Block... blocks) {
        this.getOrCreateTagBuilder(tag).add(blocks);
    }
}
