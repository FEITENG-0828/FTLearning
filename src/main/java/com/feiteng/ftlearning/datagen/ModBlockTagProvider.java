package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.tag.ModBlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput data_output,
            CompletableFuture<HolderLookup.Provider> lookup) {
        super(data_output, lookup);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapper_lookup) {
        valueLookupBuilder(ModBlockTags.PROSPECTING_LIST)
                .addOptionalTag(BlockTags.DIAMOND_ORES)
                .add(Blocks.ANCIENT_DEBRIS);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
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
                .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR)
                .add(ModBlocks.VOIDABYSS_STONE_PILLAR)
                .add(ModBlocks.ESSENCE_EXTRACTOR);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
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
                .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR)
                .add(ModBlocks.VOIDABYSS_STONE_PILLAR)
                .add(ModBlocks.ESSENCE_EXTRACTOR);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.THERAPEUTIC_TABLE);

        valueLookupBuilder(BlockTags.STAIRS)
                .add(ModBlocks.VOIDABYSS_STONE_STAIRS);

        valueLookupBuilder(BlockTags.SLABS)
                .add(ModBlocks.VOIDABYSS_STONE_SLAB);

        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.VOIDABYSS_STONE_WALL);

        valueLookupBuilder(BlockTags.FENCES)
                .add(ModBlocks.VOIDABYSS_STONE_FENCE);

        valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);

        valueLookupBuilder(BlockTags.STONE_BUTTONS)
                .add(ModBlocks.VOIDABYSS_STONE_BUTTON);

        valueLookupBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);

        valueLookupBuilder(BlockTags.DOORS)
                .add(ModBlocks.VOIDABYSS_STONE_DOOR);

        valueLookupBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        // CompressedBlocks.configureBlockTagAll(this);
    }

    // public void addTag(TagKey<Block> tag, Block... blocks) {
    //     this.valueLookupBuilder(tag).add(blocks);
    // }
}
