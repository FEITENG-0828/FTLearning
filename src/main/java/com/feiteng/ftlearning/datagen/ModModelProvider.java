package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.block.ModBlockFamilies;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModEquipmentAssets;
import com.feiteng.ftlearning.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput data_output) {
        super(data_output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(ModBlocks.FIRST_ITEM_BLOCK);

        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(
                ModBlocks.THERAPEUTIC_TABLE,
                BlockModelGenerators.plainVariant(TexturedModel.CUBE_TOP_BOTTOM
                        .get(ModBlocks.THERAPEUTIC_TABLE)
                        .updateTextures(map -> map.put(TextureSlot.BOTTOM,
                                TextureMapping.getBlockTexture(Blocks.OBSIDIAN)))
                        .create(ModBlocks.THERAPEUTIC_TABLE, generator.modelOutput))));

        // TODO: door / trapdoor ?
        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> generator.family(family.getBaseBlock()).generateFor(family));

        generator.createTrivialCube(ModBlocks.SHUODEDAOLI_BLOCK);

        // CompressedBlocks.generateBlockStateModelAll(generator);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.FIRST_ITEM, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.PROSPECTOR, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.ADVANCED_PROSPECTOR, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.AR_GLASSES, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.INDUCTIVE_PREAMPLIFIER, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.ELECTRONIC_ESSENCE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.ELECTRONIC_NUCLEUS_MATRIX, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.INSIGHT_ROD, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.CHROMOGENIC_LENS, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.NIGHT_VISION_GOLDEN_CARROT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.VOIDABYSS_STONE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(ModItems.VOIDABYSS_STONE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(ModItems.VOIDABYSS_STONE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(ModItems.VOIDABYSS_STONE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(ModItems.VOIDABYSS_STONE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        generator.generateTrimmableItem(ModItems.VOIDABYSS_STONE_HELMET,
                ModEquipmentAssets.VOIDABYSS_STONE,
                ItemModelGenerators.TRIM_PREFIX_HELMET,
                false);
        generator.generateTrimmableItem(ModItems.VOIDABYSS_STONE_CHESTPLATE,
                ModEquipmentAssets.VOIDABYSS_STONE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                false);
        generator.generateTrimmableItem(ModItems.VOIDABYSS_STONE_LEGGINGS,
                ModEquipmentAssets.VOIDABYSS_STONE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                false);
        generator.generateTrimmableItem(ModItems.VOIDABYSS_STONE_BOOTS,
                ModEquipmentAssets.VOIDABYSS_STONE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS,
                false);

        generator.generateFlatItem(ModItems.SHUODEDAOLI, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(ModItems.DISC_FRAGMENT_GENERAL, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.MUSIC_DISC_IGOTSMOKE, ModelTemplates.FLAT_ITEM);
    }

    // public static VariantsBlockStateSupplier createBlockStateWithAllRandomRotations(
    //         Block block, Identifier model_id) {
    //     VariantSettings.Rotation[] rotations = {
    //             VariantSettings.Rotation.R0,
    //             VariantSettings.Rotation.R90,
    //             VariantSettings.Rotation.R180,
    //             VariantSettings.Rotation.R270
    //     };

    //     List<BlockStateVariant> variants = new ArrayList<>();
    //     for (VariantSettings.Rotation x_rotation : rotations) {
    //         for (VariantSettings.Rotation y_rotation : rotations) {
    //             var variant = BlockStateVariant.create().put(VariantSettings.MODEL,
    //                     model_id);
    //             if (x_rotation != VariantSettings.Rotation.R0) {
    //                 variant = variant.put(VariantSettings.X, x_rotation);
    //             }
    //             if (y_rotation != VariantSettings.Rotation.R0) {
    //                 variant = variant.put(VariantSettings.Y, y_rotation);
    //             }
    //             variants.add(variant);
    //         }
    //     }

    //     return VariantsBlockStateSupplier.create(block, variants.toArray(new BlockStateVariant[0]));
    // }
}
