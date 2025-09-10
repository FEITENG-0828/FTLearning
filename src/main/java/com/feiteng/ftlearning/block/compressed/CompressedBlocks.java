package com.feiteng.ftlearning.block.compressed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.datagen.ModBlockTagProvider;
import com.feiteng.ftlearning.datagen.ModLootTableProvider;
import com.feiteng.ftlearning.datagen.ModRecipeProvider;
import com.feiteng.ftlearning.tag.ModBlockTags;
import com.feiteng.ftlearning.util.HelpfulFuncs;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ColoredFallingBlock;
import net.minecraft.block.MossBlock;
import net.minecraft.block.NetherrackBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.OxidizableBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public final class CompressedBlocks {
    public static final float HARDNESS_MULTIPLIER = 2.0F;
    public static final float RESISTANCE_MULTIPLIER = 3.0F;
    public static final TagKey<Block> BLOCK_TAG = ModBlockTags.of("compressed_blocks");

    private static Map<String, BiFunction<String, Short, String>> TRANSLATION_FUNCTIONS =
        Util.make(new HashMap<>(), map -> {
            map.put("en_us", (base_name, level) ->
                "Compressed " + base_name + ' ' + levelToRoman(level));
            map.put("zh_cn", (base_name, level) ->
                levelToRoman(level) + "级" + "压缩" + base_name);
    });
    private static List<CompressedBlocks> SET = new ArrayList<>();

    private Block origin;
    private Block[] compressed;
    private short max_level;
    private List<TagKey<Block>> tags;
    private BiConsumer<ModLootTableProvider, Block> drop_consumer;
    private BiFunction<Block, Identifier, BlockStateSupplier> blockstate_function;
    private Map<String, String> translations;
    private RenderLayer render_layer;

    private CompressedBlocks(
        Block origin,
        Block[] compressed,
        short max_level,
        List<TagKey<Block>> tags,
        BiConsumer<ModLootTableProvider, Block> drop_consumer,
        BiFunction<Block, Identifier, BlockStateSupplier> blockstate_function,
        Map<String, String> translations,
        RenderLayer render_layer
    ) {
        this.origin = origin;
        this.compressed = compressed;
        this.max_level = max_level;
        this.tags = tags;
        this.drop_consumer = drop_consumer;
        this.blockstate_function = blockstate_function;
        this.translations = translations;
        this.render_layer = render_layer;
    }

    public static void createAll() {
        create(Blocks.DIRT,
            (short)9,
            Block::new,
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.DIRT),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createBlockStateWithRandomHorizontalRotations,
            Map.ofEntries(
                Map.entry("en_us", "Dirt"),
                Map.entry("zh_cn", "泥土")
            ),
            RenderLayer.getCutout());
        create(Blocks.SAND,
            (short)9,
            settings -> new ColoredFallingBlock(new ColorCode(14406560), settings),
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.SAND,
                BlockTags.RABBITS_SPAWNABLE_ON),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createBlockStateWithRandomHorizontalRotations,
            Map.ofEntries(
                Map.entry("en_us", "Sand"),
                Map.entry("zh_cn", "沙子")
            ),
            RenderLayer.getCutout());
        create(Blocks.RED_SAND,
            (short)9,
            settings -> new ColoredFallingBlock(new ColorCode(11098145), settings),
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.SAND),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createBlockStateWithRandomHorizontalRotations,
            Map.ofEntries(
                Map.entry("en_us", "Red Sand"),
                Map.entry("zh_cn", "红沙")
            ),
            RenderLayer.getCutout());
        create(Blocks.GRAVEL,
            (short)9,
            settings -> new ColoredFallingBlock(new ColorCode(-8356741), settings),
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.BAMBOO_PLANTABLE_ON,
                BlockTags.ANIMALS_SPAWNABLE_ON),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Gravel"),
                Map.entry("zh_cn", "沙砾")
            ),
            RenderLayer.getCutout());
        create(Blocks.STONE, // FIXME: blockstate
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.GOATS_SPAWNABLE_ON,
                BlockTags.SNAPS_GOAT_HORN),
            (provider, block) -> provider.addDrop(block,
                b -> provider.drops(b, getBlock(Blocks.COBBLESTONE, getLevel(b)))),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Stone"),
                Map.entry("zh_cn", "石头")
            ),
            RenderLayer.getCutout());
        create(Blocks.COBBLESTONE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Cobblestone"),
                Map.entry("zh_cn", "圆石")
            ),
            RenderLayer.getCutout());
        create(Blocks.GRANITE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Granite"),
                Map.entry("zh_cn", "花岗岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.DIORITE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Diorite"),
                Map.entry("zh_cn", "闪长岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.ANDESITE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Andesite"),
                Map.entry("zh_cn", "安山岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.TUFF,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Tuff"),
                Map.entry("zh_cn", "凝灰岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.MOSS_BLOCK,
            (short)9,
            settings -> new MossBlock(settings),
            List.of(BlockTags.HOE_MINEABLE,
                BlockTags.DIRT,
                BlockTags.SNIFFER_EGG_HATCH_BOOST),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Moss Block"),
                Map.entry("zh_cn", "苔藓块")
            ),
            RenderLayer.getCutout());
        create(Blocks.DEEPSLATE, // FIXME: blockstate
            (short)9,
            PillarBlock::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block,
                b -> provider.drops(b, getBlock(Blocks.COBBLED_DEEPSLATE, getLevel(b)))),
            BlockStateModelGenerator::createAxisRotatedBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Deepslate"),
                Map.entry("zh_cn", "深板岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.COBBLED_DEEPSLATE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Cobbled Deepslate"),
                Map.entry("zh_cn", "深板岩圆石")
            ),
            RenderLayer.getCutout());
        create(Blocks.COAL_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Coal"),
                Map.entry("zh_cn", "煤炭块")
            ),
            RenderLayer.getCutout());
        create(Blocks.COPPER_BLOCK, // FIXME: oxidizable and waxable
            (short)3,
            settings -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings),
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_STONE_TOOL),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Copper"),
                Map.entry("zh_cn", "铜块")
            ),
            RenderLayer.getCutout());
        create(Blocks.IRON_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_STONE_TOOL,
                BlockTags.BEACON_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Iron"),
                Map.entry("zh_cn", "铁块")
            ),
            RenderLayer.getCutout());
        create(Blocks.GOLD_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_STONE_TOOL,
                BlockTags.BEACON_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Gold"),
                Map.entry("zh_cn", "金块")
            ),
            RenderLayer.getCutout());
        create(Blocks.DIAMOND_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_IRON_TOOL,
                BlockTags.BEACON_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Diamond"),
                Map.entry("zh_cn", "钻石块")
            ),
            RenderLayer.getCutout());
        create(Blocks.NETHERITE_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_DIAMOND_TOOL,
                BlockTags.BEACON_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Netherite"),
                Map.entry("zh_cn", "下界合金块")
            ),
            RenderLayer.getCutout());
        create(Blocks.EMERALD_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_IRON_TOOL,
                BlockTags.BEACON_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Emerald"),
                Map.entry("zh_cn", "绿宝石块")
            ),
            RenderLayer.getCutout());
        create(Blocks.REDSTONE_BLOCK,
            (short)3,
            RedstoneBlock::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Redstone"),
                Map.entry("zh_cn", "红石块")
            ),
            RenderLayer.getCutout());
        create(Blocks.LAPIS_BLOCK,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_STONE_TOOL),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Lapis Lazuli"),
                Map.entry("zh_cn", "青金石块")
            ),
            RenderLayer.getCutout());
        create(Blocks.AMETHYST_BLOCK,
            (short)3,
            AmethystBlock::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.CRYSTAL_SOUND_BLOCKS,
                BlockTags.VIBRATION_RESONATORS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Block of Amethyst"),
                Map.entry("zh_cn", "紫水晶块")
            ),
            RenderLayer.getCutout());
        create(Blocks.OBSIDIAN,
            (short)3,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.NEEDS_DIAMOND_TOOL,
                BlockTags.DRAGON_IMMUNE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Obsidian"),
                Map.entry("zh_cn", "黑曜石")
            ),
            RenderLayer.getCutout());
        create(Blocks.NETHERRACK,
            (short)9,
            NetherrackBlock::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.INFINIBURN_OVERWORLD),
            (provider, block) -> provider.addDrop(block),
            (block, model_id) -> {
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

                return VariantsBlockStateSupplier.create(
                    block, variants.toArray(new BlockStateVariant[0]));
            },
            Map.ofEntries(
                Map.entry("en_us", "Netherrack"),
                Map.entry("zh_cn", "下界岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.BASALT,
            (short)9,
            PillarBlock::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createAxisRotatedBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Basalt"),
                Map.entry("zh_cn", "玄武岩")
            ),
            RenderLayer.getCutout());
        create(Blocks.BLACKSTONE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Blackstone"),
                Map.entry("zh_cn", "黑石")
            ),
            RenderLayer.getCutout());
        create(Blocks.SOUL_SAND,
            (short)9,
            SoulSandBlock::new,
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.SOUL_SPEED_BLOCKS,
                BlockTags.SOUL_FIRE_BASE_BLOCKS,
                BlockTags.SNOW_LAYER_CAN_SURVIVE_ON),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Soul Sand"),
                Map.entry("zh_cn", "灵魂沙")
            ),
            RenderLayer.getCutout());
        create(Blocks.SOUL_SOIL,
            (short)9,
            Block::new,
            List.of(BlockTags.SHOVEL_MINEABLE,
                BlockTags.SOUL_SPEED_BLOCKS,
                BlockTags.SOUL_FIRE_BASE_BLOCKS),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "Soul Soil"),
                Map.entry("zh_cn", "灵魂土")
            ),
            RenderLayer.getCutout());
        create(Blocks.END_STONE,
            (short)9,
            Block::new,
            List.of(BlockTags.PICKAXE_MINEABLE,
                BlockTags.DRAGON_IMMUNE),
            (provider, block) -> provider.addDrop(block),
            BlockStateModelGenerator::createSingletonBlockState,
            Map.ofEntries(
                Map.entry("en_us", "End Stone"),
                Map.entry("zh_cn", "末地石")
            ),
            RenderLayer.getCutout());
    }

    private static void create(
        Block origin,
        short max_level,
        Function<FabricBlockSettings, ? extends Block> block_factory,
        List<TagKey<Block>> tags,
        BiConsumer<ModLootTableProvider, Block> drop_consumer,
        BiFunction<Block, Identifier, BlockStateSupplier> blockstate_function,
        Map<String, String> translations,
        RenderLayer render_layer
    ) {
        max_level = (short)MathHelper.clamp(max_level, 1, 9);

        Block[] compressed = registerBlockType(origin, max_level, block_factory);
        List<TagKey<Block>> mutable_tags = new ArrayList<>(tags);
        mutable_tags.add(BLOCK_TAG);
        CompressedBlocks entry = new CompressedBlocks(
            origin,
            compressed,
            max_level,
            mutable_tags,
            drop_consumer,
            blockstate_function,
            translations,
            render_layer
        );
        if (!SET.contains(entry)) {
            SET.add(entry);
        }
    }

    private static Block[] registerBlockType(
        Block origin,
        short max_level,
        Function<FabricBlockSettings, ? extends Block> block_factory
    ) {
        Block[] compressed = new Block[max_level];
        for (short i = 0; i < max_level; ++i) {
            String name = HelpfulFuncs.getSimpleIdStr(origin) + "_compressed_" + (i + 1);
            if (i == 0) {
                compressed[i] = registerBlock(name, origin, block_factory);
            } else {
                compressed[i] = registerBlock(name, compressed[i - 1], block_factory);
            }
        }

        return compressed;
    }

    private static Block registerBlock(
        String name,
        Block parent,
        Function<FabricBlockSettings, ? extends Block> block_factory
    ) {
        FabricBlockSettings settings = FabricBlockSettings.copyOf(parent)
            .hardness(parent.getHardness() * HARDNESS_MULTIPLIER)
            .resistance(parent.getBlastResistance() * RESISTANCE_MULTIPLIER);

        return ModBlocks.register(name, block_factory.apply(settings));
    }

    public static void configureBlockTagAll(ModBlockTagProvider provider) {
        for (CompressedBlocks entry : SET) {
            configureBlockTag(provider, entry);
        }
    }

    private static void configureBlockTag(ModBlockTagProvider provider, CompressedBlocks entry) {
        for (TagKey<Block> tag : entry.tags) {
            provider.addTag(tag, entry.compressed);
        }
    }

    public static void translateAll(TranslationBuilder builder, String language_code) {
        for (CompressedBlocks entry : SET) {
            translate(builder, entry, language_code);
        }
    }

    private static void translate(
        TranslationBuilder builder, CompressedBlocks entry, String language_code
    ) {
        String base_name = entry.translations.get(language_code);
        if (base_name == null) {
            throw new IllegalArgumentException(
                "No translation for language code: " + language_code);
        }
        BiFunction<String, Short, String> func = TRANSLATION_FUNCTIONS.get(language_code);
        if (func == null) {
            throw new IllegalArgumentException(
                "No translation function for language code: " + language_code);
        }

        for (short i = 0; i < entry.compressed.length; ++i) {
            builder.add(entry.compressed[i], func.apply(base_name, (short)(i + 1)));
        }
    }

    private static String levelToRoman(short level) {
        return switch (level) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> throw new IllegalArgumentException("Invalid compression level: " + level);
        };
    }

    public static void generateLootTableAll(ModLootTableProvider provider) {
        for (CompressedBlocks entry : SET) {
            generateLootTable(provider, entry);
        }
    }

    private static void generateLootTable(ModLootTableProvider provider, CompressedBlocks entry) {
        for (Block block : entry.compressed) {
            entry.drop_consumer.accept(provider, block);
        }
    }

    public static void generateBlockStateModelAll(BlockStateModelGenerator generator) {
        for (CompressedBlocks entry : SET) {
            generateBlockStateModel(generator, entry);
        }
    }

    private static void generateBlockStateModel(
        BlockStateModelGenerator generator, CompressedBlocks entry
    ) {
        final String overlay_texture_key = "overlay";

        for (short i = 0; i < entry.compressed.length; ++i) {
            String overlay_texture_id = "block/compress_" + (i + 1) + "_overlay";

            TextureKey overlay_key = TextureKey.of(overlay_texture_key);
            TextureMap texture_map = TextureMap.of(overlay_key,
                Identifier.of(FTLearning.MOD_ID, overlay_texture_id));
            Model model = new Model(Optional.of(ModelIds.getBlockModelId(entry.origin)),
                Optional.empty(), overlay_key);
            Identifier model_id = model.upload(
                ModelIds.getBlockModelId(entry.compressed[i]), texture_map,
                generator.modelCollector, expandJsonFactory(model));
            generator.blockStateCollector.accept(entry.blockstate_function.apply(
                entry.compressed[i], model_id));
        }
    }

    private static Model.JsonFactory expandJsonFactory(Model model) {
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

    public static void generateRecipeAll(RecipeExporter exporter) {
        for (CompressedBlocks entry : SET) {
            generateRecipe(exporter, entry);
        }
    }

    private static void generateRecipe(RecipeExporter exporter, CompressedBlocks entry) {
        for (int i = 0; i < entry.compressed.length; ++i) {
            if (i == 0) {
                offerReversibleCompressRecipes(exporter, entry.origin, entry.compressed[i]);
            } else {
                offerReversibleCompressRecipes(exporter, entry.compressed[i - 1], entry.compressed[i]);
            }
        }
    }

    private static void offerReversibleCompressRecipes(
        RecipeExporter exporter, Block parent, Block compressed
    ) {
        ModRecipeProvider.offerReversibleCompactingRecipes(exporter,
            RecipeCategory.BUILDING_BLOCKS, parent,
            RecipeCategory.BUILDING_BLOCKS, compressed,
            ModRecipeProvider.getModRecipeName(
                compressed, "from", parent, null),
            HelpfulFuncs.getSimpleIdStr(compressed),
            ModRecipeProvider.getModRecipeName(
                parent, "from", compressed, null),
            HelpfulFuncs.getSimpleIdStr(parent));
    }

    public static void registerItemGroupAll(ItemGroup.Entries item_group_entries) {
        for (CompressedBlocks entry : SET) {
            registerItemGroup(item_group_entries, entry);
        }
    }

    private static void registerItemGroup(ItemGroup.Entries item_group_entries, CompressedBlocks entry) {
        for (Block block : entry.compressed) {
            item_group_entries.add(block);
        }
    }

    public static void mapRenderLayerAll() {
        for (CompressedBlocks entry : SET) {
            mapRenderLayer(entry);
        }
    }

    private static void mapRenderLayer(CompressedBlocks entry) {
        BlockRenderLayerMap.INSTANCE.putBlocks(entry.render_layer, entry.compressed);
    }

    @Nullable
    public static Block getBlock(Block origin, short level) {
        for (CompressedBlocks entry : SET) {
            if (entry.origin == origin && 1 <= level && level <= entry.max_level) {
                return entry.compressed[level - 1];
            }
        }
        return null;
    }

    public static short getMaxLevel(Block origin) {
        for (CompressedBlocks entry : SET) {
            if (entry.origin == origin) {
                return entry.max_level;
            }
        }
        return -1;
    }

    public static short getLevel(@NotNull Block block) {
        for (CompressedBlocks entry : SET) {
            for (short i = 0; i < entry.compressed.length; ++i) {
                if (entry.compressed[i] == block) {
                    return (short)(i + 1);
                }
            }
        }
        return -1;
    }

    static {
        createAll();
    }
}
