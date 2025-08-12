package com.feiteng.ftlearning.datagen;

import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter,
            RecipeCategory.MISC, ModItems.FIRST_ITEM,
            RecipeCategory.BUILDING_BLOCKS, ModBlocks.FIRST_ITEM_BLOCK,
            getModRecipeName(ModBlocks.FIRST_ITEM_BLOCK, "from",
                ModItems.FIRST_ITEM, null),
            HelpfulFuncs.getSimpleIdStr(ModBlocks.FIRST_ITEM_BLOCK),
            getModRecipeName(ModItems.FIRST_ITEM, "from",
                ModBlocks.FIRST_ITEM_BLOCK, null),
            HelpfulFuncs.getSimpleIdStr(ModItems.FIRST_ITEM));

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Items.ROTTEN_FLESH),
                RecipeCategory.MISC, Items.LEATHER,
                0.2f, 200,
                RecipeSerializer.SMELTING, SmeltingRecipe::new)
            .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
            .offerTo(exporter, getModRecipeName(
                Items.LEATHER, "from_smelting",
                Items.ROTTEN_FLESH, null)
            );

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FIRST_ITEM, 1)
            .input('N', Items.GOLD_NUGGET)
            .input('I', Items.GOLD_INGOT)
            .input('L', Items.LAPIS_LAZULI)
            .group(HelpfulFuncs.getSimpleIdStr(ModItems.FIRST_ITEM))
            .pattern("NIN")
            .pattern("ILI")
            .pattern("NIN")
            .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.INDUCTIVE_PREAMPLIFIER, 1)
            .input('C', Items.COPPER_INGOT)
            .input('R', Items.REDSTONE)
            .input('S', Items.STICK)
            .pattern("CRC")
            .pattern("CSC")
            .pattern("CRC")
            .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NIGHT_VISION_GOLDEN_CARROT, 3)
            .input('G', Items.GOLDEN_CARROT)
            .input('F', ModItems.FIRST_ITEM)
            .pattern("GGG")
            .pattern("GFG")
            .pattern("GGG")
            .criterion(hasItem(Items.GOLDEN_CARROT), conditionsFromItem(Items.GOLDEN_CARROT))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.POISONOUS_POTATO, 1)
            .input('S', Items.SPIDER_EYE)
            .input('P', Items.POTATO)
            .pattern("SSS")
            .pattern("SPS")
            .pattern("SSS")
            .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.POISONOUS_POTATO));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.PROSPECTOR, 1)
            .input('F', ModItems.FIRST_ITEM)
            .input('S', Items.STICK)
            .pattern("FF ")
            .pattern("FF ")
            .pattern("  S")
            .criterion(hasItem(ModItems.FIRST_ITEM), conditionsFromItem(ModItems.FIRST_ITEM))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.THERAPEUTIC_TABLE, 1)
            .input('I', ModItems.INDUCTIVE_PREAMPLIFIER)
            .input('F', ModItems.FIRST_ITEM)
            .input('G', Items.GHAST_TEAR)
            .input('O', Items.OBSIDIAN)
            .pattern("IFI")
            .pattern("FGF")
            .pattern("OOO")
            .criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR))
            .offerTo(exporter);

        createStairsRecipe(ModBlocks.VOIDABYSS_STONE_STAIRS, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        SingleItemRecipeJsonBuilder
            .createStonecutting(Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_STAIRS, 1)
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter, getModRecipeName(
                ModBlocks.VOIDABYSS_STONE_STAIRS, "from",
                ModBlocks.VOIDABYSS_STONE, "stonecutting")
            );

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.VOIDABYSS_STONE_SLAB, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        SingleItemRecipeJsonBuilder
            .createStonecutting(Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_SLAB, 2)
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter, getModRecipeName(
                ModBlocks.VOIDABYSS_STONE_SLAB, "from",
                ModBlocks.VOIDABYSS_STONE, "stonecutting")
            );

        getWallRecipe(RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.VOIDABYSS_STONE_WALL, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        SingleItemRecipeJsonBuilder
            .createStonecutting(Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_WALL, 1)
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter, getModRecipeName(
                ModBlocks.VOIDABYSS_STONE_WALL, "from",
                ModBlocks.VOIDABYSS_STONE, "stonecutting")
            );

        createFenceRecipe(ModBlocks.VOIDABYSS_STONE_FENCE, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        createFenceGateRecipe(ModBlocks.VOIDABYSS_STONE_FENCE_GATE, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        createTransmutationRecipe(ModBlocks.VOIDABYSS_STONE_BUTTON, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        createPressurePlateRecipe(RecipeCategory.REDSTONE,
            ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        createDoorRecipe(ModBlocks.VOIDABYSS_STONE_DOOR, Ingredient.ofItems(ModBlocks.VOIDABYSS_STONE))
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        offer2x2CompactingRecipe(exporter, RecipeCategory.REDSTONE,
            ModBlocks.VOIDABYSS_STONE_TRAPDOOR, ModBlocks.VOIDABYSS_STONE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_SWORD)
            .input('S', Items.STICK)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("V")
            .pattern("V")
            .pattern("S")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_AXE)
            .input('S', Items.STICK)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("VV")
            .pattern("VS")
            .pattern(" S")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_PICKAXE)
            .input('S', Items.STICK)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("VVV")
            .pattern(" S ")
            .pattern(" S ")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_SHOVEL)
            .input('S', Items.STICK)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("V")
            .pattern("S")
            .pattern("S")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_HOE)
            .input('S', Items.STICK)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("VV")
            .pattern(" S")
            .pattern(" S")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Items.SADDLE),
                RecipeCategory.MISC, Items.LEATHER,
                25.0f, 300,
                RecipeSerializer.SMELTING, SmeltingRecipe::new)
            .criterion(hasItem(Items.SADDLE), conditionsFromItem(Items.SADDLE))
            .offerTo(exporter, getModRecipeName(
                Items.LEATHER, "from_smelting",
                Items.SADDLE, null)
            );

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_HELMET)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("VVV")
            .pattern("V V")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_CHESTPLATE)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("V V")
            .pattern("VVV")
            .pattern("VVV")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_LEGGINGS)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("VVV")
            .pattern("V V")
            .pattern("V V")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_BOOTS)
            .input('V', ModBlocks.VOIDABYSS_STONE)
            .pattern("V V")
            .pattern("V V")
            .criterion(hasItem(ModBlocks.VOIDABYSS_STONE), conditionsFromItem(ModBlocks.VOIDABYSS_STONE))
            .offerTo(exporter);

        offerReversibleCompactingRecipes(exporter,
            RecipeCategory.MISC, ModItems.SHUODEDAOLI,
            RecipeCategory.MISC, ModBlocks.SHUODEDAOLI_BLOCK,
            getModRecipeName(ModBlocks.SHUODEDAOLI_BLOCK, "from",
                ModItems.SHUODEDAOLI, null),
            HelpfulFuncs.getSimpleIdStr(ModBlocks.SHUODEDAOLI_BLOCK),
            getModRecipeName(ModItems.SHUODEDAOLI, "from",
                ModBlocks.SHUODEDAOLI_BLOCK, null),
            HelpfulFuncs.getSimpleIdStr(ModItems.SHUODEDAOLI));

        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.COBBLESTONE, ModBlocks.COBBLESTONE_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.DIRT, ModBlocks.DIRT_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.SAND, ModBlocks.SAND_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.GRAVEL, ModBlocks.GRAVEL_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.COBBLED_DEEPSLATE, ModBlocks.COBBLED_DEEPSLATE_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.REDSTONE_BLOCK, ModBlocks.REDSTONE_BLOCK_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.LAPIS_BLOCK, ModBlocks.LAPIS_BLOCK_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_BLOCK_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.NETHERRACK, ModBlocks.NETHERRACK_COMPRESSED_BLOCKS);
        offerReversibleCompressedBlockKindRecipes(exporter,
            Blocks.SOUL_SAND, ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GHAST_TEAR, 1)
            .input('C', ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS[0])
            .input('F', ModItems.FIRST_ITEM)
            .group(HelpfulFuncs.getSimpleIdStr(Items.GHAST_TEAR))
            .pattern("CCC")
            .pattern("CFC")
            .pattern("CCC")
            .criterion(hasItem(ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS[0]),
                conditionsFromItem(ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS[0]))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.GHAST_TEAR));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHER_STAR, 2)
            .input('C', ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS[1])
            .input('F', ModBlocks.FIRST_ITEM_BLOCK)
            .input('N', Items.NETHER_STAR)
            .group(HelpfulFuncs.getSimpleIdStr(Items.NETHER_STAR))
            .pattern("FCF")
            .pattern("CNC")
            .pattern("FCF")
            .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.NETHER_STAR));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DISC_FRAGMENT_GENERAL, 9)
            .input(ItemTags.MUSIC_DISCS)
            .group(HelpfulFuncs.getSimpleIdStr(ModItems.DISC_FRAGMENT_GENERAL))
            .criterion("has_music_discs", conditionsFromTag(ItemTags.MUSIC_DISCS))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DISC_FRAGMENT_5, 1)
            .input('S', Blocks.SCULK)
            .input('D', ModItems.DISC_FRAGMENT_GENERAL)
            .group(HelpfulFuncs.getSimpleIdStr(Items.DISC_FRAGMENT_5))
            .pattern(" S ")
            .pattern("SDS")
            .pattern(" S ")
            .criterion(hasItem(Blocks.SCULK), conditionsFromItem(Blocks.SCULK))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.DISC_FRAGMENT_5));
    }

    public static String getModRecipeName(
        ItemConvertible output_item,
        String insert_str_1,
        ItemConvertible input_item,
        @Nullable String insert_str_2
    ) {
        return FTLearning.MOD_ID + ':' + HelpfulFuncs.getSimpleIdStr(output_item)
            + "_" + insert_str_1 + "_"
            + HelpfulFuncs.getSimpleIdStr(input_item)
            + (insert_str_2 != null && !insert_str_2.isEmpty() ? ("_" + insert_str_2) : "");
    }

    public static void offerReversibleCompressedBlockKindRecipes(
        RecipeExporter exporter, Block origin_block, Block[] blocks
    ) {
        for (int i = 0; i < blocks.length; ++i) {
            if (i == 0) {
                offerReversibleBlockCompressRecipes(exporter, origin_block, blocks[i]);
            } else {
                offerReversibleBlockCompressRecipes(exporter, blocks[i - 1], blocks[i]);
            }
        }
    }

    public static void offerReversibleBlockCompressRecipes(
        RecipeExporter exporter, Block base_block, Block compressed_block
    ) {
        offerReversibleCompactingRecipes(exporter,
            RecipeCategory.BUILDING_BLOCKS, base_block,
            RecipeCategory.BUILDING_BLOCKS, compressed_block,
            getModRecipeName(compressed_block, "from",
                base_block, null),
            HelpfulFuncs.getSimpleIdStr(compressed_block),
            getModRecipeName(base_block, "from",
                compressed_block, null),
            HelpfulFuncs.getSimpleIdStr(base_block));
    }
}
