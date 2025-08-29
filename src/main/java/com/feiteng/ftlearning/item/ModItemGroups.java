package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> FTL_GROUP_CORE = getRegistryKey("ftl_group_core");
    public static final RegistryKey<ItemGroup> FTL_GROUP_COMPRESSED = getRegistryKey("ftl_group_compressed");

    private static RegistryKey<ItemGroup> getRegistryKey(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(FTLearning.MOD_ID, id));
    }

    public static void registerModItemGroups() {
        Registry.register(
            Registries.ITEM_GROUP,
            FTL_GROUP_CORE,
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ftlearning.ftl_group_core"))
                .icon(() -> new ItemStack(ModItems.FIRST_ITEM))
                .entries((display_context, entries) -> {
                    entries.add(ModItems.FIRST_ITEM);
                    entries.add(ModBlocks.FIRST_ITEM_BLOCK);

                    entries.add(ModItems.PROSPECTOR);
                    entries.add(ModItems.ADVANCED_PROSPECTOR);
                    entries.add(ModItems.AR_GLASSES);
                    entries.add(ModBlocks.THERAPEUTIC_TABLE);

                    entries.add(ModItems.INDUCTIVE_PREAMPLIFIER);
                    entries.add(ModItems.ELECTRONIC_ESSENCE);
                    entries.add(ModItems.ELECTRONIC_NUCLEUS_MATRIX);
                    entries.add(ModItems.INSIGHT_ROD);
                    entries.add(ModItems.CHROMOGENIC_LENS);

                    entries.add(ModItems.NIGHT_VISION_GOLDEN_CARROT);

                    entries.add(ModBlocks.VOIDABYSS_STONE);
                    entries.add(ModBlocks.VOIDABYSS_STONE_STAIRS);
                    entries.add(ModBlocks.VOIDABYSS_STONE_SLAB);
                    entries.add(ModBlocks.VOIDABYSS_STONE_WALL);
                    entries.add(ModBlocks.VOIDABYSS_STONE_FENCE);
                    entries.add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);
                    entries.add(ModBlocks.VOIDABYSS_STONE_BUTTON);
                    entries.add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);
                    entries.add(ModBlocks.VOIDABYSS_STONE_DOOR);
                    entries.add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

                    entries.add(ModItems.VOIDABYSS_STONE_SWORD);
                    entries.add(ModItems.VOIDABYSS_STONE_AXE);
                    entries.add(ModItems.VOIDABYSS_STONE_PICKAXE);
                    entries.add(ModItems.VOIDABYSS_STONE_SHOVEL);
                    entries.add(ModItems.VOIDABYSS_STONE_HOE);

                    entries.add(ModItems.VOIDABYSS_STONE_HELMET);
                    entries.add(ModItems.VOIDABYSS_STONE_CHESTPLATE);
                    entries.add(ModItems.VOIDABYSS_STONE_LEGGINGS);
                    entries.add(ModItems.VOIDABYSS_STONE_BOOTS);

                    entries.add(ModItems.SHUODEDAOLI);
                    entries.add(ModBlocks.SHUODEDAOLI_BLOCK);

                    entries.add(ModItems.DISC_FRAGMENT_GENERAL);
                    entries.add(ModItems.MUSIC_DISC_IGOTSMOKE);
                })
                .build());

        Registry.register(
            Registries.ITEM_GROUP,
            FTL_GROUP_COMPRESSED,
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ftlearning.ftl_group_compressed"))
                .icon(() -> new ItemStack(CompressedBlocks.getBlock(Blocks.COBBLESTONE, (short)1)))
                .entries((display_context, entries) -> {
                    CompressedBlocks.registerItemGroupAll(entries);
                })
                .build());
    }
}
