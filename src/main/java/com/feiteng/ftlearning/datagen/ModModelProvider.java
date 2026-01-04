package com.feiteng.ftlearning.datagen;

import java.util.ArrayList;
import java.util.List;

import com.feiteng.ftlearning.block.ModBlockFamilies;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(ModBlocks.FIRST_ITEM_BLOCK);

        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(
            ModBlocks.THERAPEUTIC_TABLE,
            TexturedModel.CUBE_BOTTOM_TOP.get(ModBlocks.THERAPEUTIC_TABLE)
                .textures(map -> map.put(TextureKey.BOTTOM, TextureMap.getId(Blocks.OBSIDIAN)))
                .upload(ModBlocks.THERAPEUTIC_TABLE, generator.modelCollector)
        ));

        ModBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels)
            .forEach(family -> generator.registerCubeAllModelTexturePool(family.getBaseBlock())
                .family(family)
            );

        generator.registerSimpleCubeAll(ModBlocks.SHUODEDAOLI_BLOCK);

        CompressedBlocks.generateBlockStateModelAll(generator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ModItems.FIRST_ITEM, Models.GENERATED);
        generator.register(ModItems.PROSPECTOR, Models.GENERATED);
        generator.register(ModItems.ADVANCED_PROSPECTOR, Models.GENERATED);
        generator.register(ModItems.AR_GLASSES, Models.GENERATED);

        generator.register(ModItems.INDUCTIVE_PREAMPLIFIER, Models.GENERATED);
        generator.register(ModItems.ELECTRONIC_ESSENCE, Models.GENERATED);
        generator.register(ModItems.ELECTRONIC_NUCLEUS_MATRIX, Models.GENERATED);
        generator.register(ModItems.INSIGHT_ROD, Models.GENERATED);
        generator.register(ModItems.CHROMOGENIC_LENS, Models.GENERATED);

        generator.register(ModItems.NIGHT_VISION_GOLDEN_CARROT, Models.GENERATED);

        generator.register(ModItems.VOIDABYSS_STONE_SWORD, Models.HANDHELD);
        generator.register(ModItems.VOIDABYSS_STONE_AXE, Models.HANDHELD);
        generator.register(ModItems.VOIDABYSS_STONE_PICKAXE, Models.HANDHELD);
        generator.register(ModItems.VOIDABYSS_STONE_SHOVEL, Models.HANDHELD);
        generator.register(ModItems.VOIDABYSS_STONE_HOE, Models.HANDHELD);

        generator.registerArmor((ArmorItem) ModItems.VOIDABYSS_STONE_HELMET);
        generator.registerArmor((ArmorItem) ModItems.VOIDABYSS_STONE_CHESTPLATE);
        generator.registerArmor((ArmorItem) ModItems.VOIDABYSS_STONE_LEGGINGS);
        generator.registerArmor((ArmorItem) ModItems.VOIDABYSS_STONE_BOOTS);

        generator.register(ModItems.SHUODEDAOLI, Models.GENERATED);

        generator.register(ModItems.DISC_FRAGMENT_GENERAL, Models.GENERATED);
        generator.register(ModItems.MUSIC_DISC_IGOTSMOKE, Models.GENERATED);
    }

    public static VariantsBlockStateSupplier createBlockStateWithAllRandomRotations(
        Block block, Identifier model_id
    ) {
        VariantSettings.Rotation[] rotations = {
            VariantSettings.Rotation.R0,
            VariantSettings.Rotation.R90,
            VariantSettings.Rotation.R180,
            VariantSettings.Rotation.R270
        };

        List<BlockStateVariant> variants = new ArrayList<>();
        for (VariantSettings.Rotation x_rotation : rotations) {
            for (VariantSettings.Rotation y_rotation : rotations) {
                var variant = BlockStateVariant.create().put(VariantSettings.MODEL, model_id);
                if (x_rotation != VariantSettings.Rotation.R0) {
                    variant = variant.put(VariantSettings.X, x_rotation);
                }
                if (y_rotation != VariantSettings.Rotation.R0) {
                    variant = variant.put(VariantSettings.Y, y_rotation);
                }
                variants.add(variant);
            }
        }

        return VariantsBlockStateSupplier.create(block, variants.toArray(new BlockStateVariant[0]));
    }
}
