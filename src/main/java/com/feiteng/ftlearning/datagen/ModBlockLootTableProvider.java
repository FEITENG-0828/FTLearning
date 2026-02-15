package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import com.feiteng.ftlearning.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput data_output,
            CompletableFuture<HolderLookup.Provider> lookup) {
        super(data_output, lookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.FIRST_ITEM_BLOCK);
        dropSelf(ModBlocks.THERAPEUTIC_TABLE);

        dropSelf(ModBlocks.VOIDABYSS_STONE);
        dropSelf(ModBlocks.VOIDABYSS_STONE_STAIRS);
        add(ModBlocks.VOIDABYSS_STONE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.VOIDABYSS_STONE_WALL);
        dropSelf(ModBlocks.VOIDABYSS_STONE_FENCE);
        dropSelf(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);
        dropSelf(ModBlocks.VOIDABYSS_STONE_BUTTON);
        dropSelf(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);
        add(ModBlocks.VOIDABYSS_STONE_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        dropSelf(ModBlocks.SHUODEDAOLI_BLOCK);

        // CompressedBlocks.generateLootTableAll(this);

        dropSelf(ModBlocks.VOIDABYSS_STONE_PILLAR);

        dropSelf(ModBlocks.TUNABLE_EMITTER);
    }

    // public LootTable.Builder oreDropsWithFortune(
    //         Block block_drop, ItemConvertible item_drop, float min, float max) {
    //     return dropsWithSilkTouch(block_drop,
    //             this.applyExplosionDecay(
    //                     block_drop,
    //                     ItemEntry.builder(item_drop)
    //                             .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))
    //                             .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    // }
}
