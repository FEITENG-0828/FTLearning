package com.feiteng.ftlearning.datagen;

import java.util.Optional;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlockFamilies;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
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

        registerCompressedBlockKind(generator,
            Blocks.COBBLESTONE, ModBlocks.COBBLESTONE_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.DIRT, ModBlocks.DIRT_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.SAND, ModBlocks.SAND_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.GRAVEL, ModBlocks.GRAVEL_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.COBBLED_DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.REDSTONE_BLOCK, ModBlocks.REDSTONE_BLOCK_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.LAPIS_BLOCK, ModBlocks.LAPIS_BLOCK_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_BLOCK_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.NETHERRACK, ModBlocks.NETHERRACK_COMPRESSED_BLOCKS);
        registerCompressedBlockKind(generator,
            Blocks.SOUL_SAND, ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS);
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

    public static void registerCompressedBlockKind(
        BlockStateModelGenerator generator, Block origin_block, Block[] blocks) {
        for (int i = 0; i < blocks.length; ++i) {
            registerCompressedBlock(generator, origin_block, blocks[i], i + 1);
        }
    }

    public static void registerCompressedBlock(
        BlockStateModelGenerator generator,
        Block origin_block, Block compressed_block, int compression_level
    ) {
        final String overlay_texture_key = "overlay";
        final String overlay_texture_id = "block/compress_" + compression_level + "_overlay";

        TextureKey overlay_key = TextureKey.of(overlay_texture_key);
        TextureMap texture_map = TextureMap.of(overlay_key,
            Identifier.of(FTLearning.MOD_ID, overlay_texture_id));
        Model model = new Model(
            Optional.of(ModelIds.getBlockModelId(origin_block)), Optional.empty(), overlay_key);
        Identifier model_id = model.upload(ModelIds.getBlockModelId(compressed_block), texture_map,
            generator.modelCollector, expandJsonFactoryForCompress(model));
        generator.blockStateCollector.accept(
            BlockStateModelGenerator.createSingletonBlockState(compressed_block, model_id));
    }

    public static Model.JsonFactory expandJsonFactoryForCompress(Model model) {
        return (id, textures) -> {
            JsonObject json_object = model.createJson(id, textures);

            String overlay_element = """
                [
                    {
                        "from": [0, 0, 0],
                        "to": [16, 16, 16],
                        "faces": {
                            "up": { "uv": [0, 0, 16, 16], "texture": "#up", "cullface": "up" },
                            "down": { "uv": [0, 0, 16, 16], "texture": "#down", "cullface": "down" },
                            "north": { "uv": [0, 0, 16, 16], "texture": "#north", "cullface": "north" },
                            "south": { "uv": [0, 0, 16, 16], "texture": "#south", "cullface": "south" },
                            "west": { "uv": [0, 0, 16, 16], "texture": "#west", "cullface": "west" },
                            "east": { "uv": [0, 0, 16, 16], "texture": "#east", "cullface": "east" }
                        }
                    },
                    {
                        "from": [0, 0, 0],
                        "to": [16, 16, 16],
                        "faces": {
                            "up": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "up" },
                            "down": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "down" },
                            "north": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "north" },
                            "south": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "south" },
                            "west": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "west" },
                            "east": { "uv": [0, 0, 16, 16], "texture": "#overlay", "cullface": "east" }
                        }
                    }
                ]
            """;
            json_object.add("elements", JsonParser.parseString(overlay_element));

            return json_object;
        };
    }
}
