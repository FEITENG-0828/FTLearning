package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> FTL_GROUP_CORE = createKey("ftl_group_core");
    public static final ResourceKey<CreativeModeTab> FTL_GROUP_COMPRESSED = createKey("ftl_group_compressed");

    private static ResourceKey<CreativeModeTab> createKey(String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "ftl_group_core"));
    }

    public static void bootstrap() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                FTL_GROUP_CORE,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.ftlearning.ftl_group_core"))
                        .icon(() -> new ItemStack(ModItems.FIRST_ITEM))
                        .displayItems((display_parameters, output) -> {
                            output.accept(ModItems.FIRST_ITEM);
                            output.accept(ModBlocks.FIRST_ITEM_BLOCK);

                            output.accept(ModItems.PROSPECTOR);
                            output.accept(ModItems.ADVANCED_PROSPECTOR);
                            output.accept(ModItems.AR_GLASSES);
                            output.accept(ModBlocks.THERAPEUTIC_TABLE);

                            output.accept(ModItems.INDUCTIVE_PREAMPLIFIER);
                            output.accept(ModItems.ELECTRONIC_ESSENCE);
                            output.accept(ModItems.ELECTRONIC_NUCLEUS_MATRIX);
                            output.accept(ModItems.INSIGHT_ROD);
                            output.accept(ModItems.CHROMOGENIC_LENS);

                            output.accept(ModItems.NIGHT_VISION_GOLDEN_CARROT);

                            output.accept(ModBlocks.VOIDABYSS_STONE);
                            output.accept(ModBlocks.VOIDABYSS_STONE_STAIRS);
                            output.accept(ModBlocks.VOIDABYSS_STONE_SLAB);
                            output.accept(ModBlocks.VOIDABYSS_STONE_WALL);
                            output.accept(ModBlocks.VOIDABYSS_STONE_FENCE);
                            output.accept(ModBlocks.VOIDABYSS_STONE_FENCE_GATE);
                            output.accept(ModBlocks.VOIDABYSS_STONE_BUTTON);
                            output.accept(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE);
                            output.accept(ModBlocks.VOIDABYSS_STONE_DOOR);
                            output.accept(ModBlocks.VOIDABYSS_STONE_TRAPDOOR);

                            output.accept(ModItems.VOIDABYSS_STONE_SWORD);
                            output.accept(ModItems.VOIDABYSS_STONE_AXE);
                            output.accept(ModItems.VOIDABYSS_STONE_PICKAXE);
                            output.accept(ModItems.VOIDABYSS_STONE_SHOVEL);
                            output.accept(ModItems.VOIDABYSS_STONE_HOE);

                            output.accept(ModItems.VOIDABYSS_STONE_HELMET);
                            output.accept(ModItems.VOIDABYSS_STONE_CHESTPLATE);
                            output.accept(ModItems.VOIDABYSS_STONE_LEGGINGS);
                            output.accept(ModItems.VOIDABYSS_STONE_BOOTS);

                            output.accept(ModItems.SHUODEDAOLI);
                            output.accept(ModBlocks.SHUODEDAOLI_BLOCK);

                            output.accept(ModItems.DISC_FRAGMENT_GENERAL);
                            output.accept(ModItems.MUSIC_DISC_IGOTSMOKE);
                        })
                        .build());

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                FTL_GROUP_COMPRESSED,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.ftlearning.ftl_group_compressed"))
                        .icon(() -> new ItemStack(CompressedBlocks.getBlock(Blocks.COBBLESTONE, (short) 1)))
                        .displayItems((display_parameters, output) -> {
                            CompressedBlocks.registerItemGroupAll(output);
                        })
                        .build());
    }
}
