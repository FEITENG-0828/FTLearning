package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput data_output) {
        super(data_output);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.FIRST_ITEM_BLOCK);
        addDrop(ModBlocks.THERAPEUTIC_TABLE);

        addDrop(ModBlocks.VOIDABYSS_STONE);
        addDrop(ModBlocks.VOIDABYSS_STONE_STAIRS);
        addDrop(ModBlocks.VOIDABYSS_STONE_SLAB, this::slabDrops);
        addDrop(ModBlocks.VOIDABYSS_STONE_WALL);
        addDrop(ModBlocks.VOIDABYSS_STONE_FENCE);
        addDrop(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);
        addDrop(ModBlocks.VOIDABYSS_STONE_BUTTON);
        addDrop(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);
        addDrop(ModBlocks.VOIDABYSS_STONE_DOOR, this::doorDrops);
        addDrop(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

        addDrop(ModBlocks.SHUODEDAOLI_BLOCK);

        for (Block block : ModBlocks.COBBLESTONE_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.DIRT_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.SAND_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.GRAVEL_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.COBBLED_DEEPSLATE_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.REDSTONE_BLOCK_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.LAPIS_BLOCK_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.AMETHYST_BLOCK_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.NETHERRACK_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
        for (Block block : ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS) {
            addDrop(block);
        }
    }

    public LootTable.Builder oreDropsWithFortune(
        Block block_drop, ItemConvertible item_drop, float min, float max) {
        return dropsWithSilkTouch(block_drop,
            this.applyExplosionDecay(
                block_drop,
                ItemEntry.builder(item_drop)
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))
                    .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
            )
        );
    }
}
