package com.feiteng.ftlearning.datagen;

import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
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

        offerReversibleCompactingRecipes(exporter,
            RecipeCategory.MISC, ModItems.FIRST_ITEM,
            RecipeCategory.BUILDING_BLOCKS, ModBlocks.FIRST_ITEM_BLOCK,
            getModRecipeName(ModBlocks.FIRST_ITEM_BLOCK, "from",
                ModItems.FIRST_ITEM, null),
            HelpfulFuncs.getSimpleIdStr(ModBlocks.FIRST_ITEM_BLOCK),
            getModRecipeName(ModItems.FIRST_ITEM, "from",
                ModBlocks.FIRST_ITEM_BLOCK, null),
            HelpfulFuncs.getSimpleIdStr(ModItems.FIRST_ITEM));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.POISONOUS_POTATO, 1)
            .input('S', Items.SPIDER_EYE)
            .input('P', Items.POTATO)
            .pattern("SSS")
            .pattern("SPS")
            .pattern("SSS")
            .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.POISONOUS_POTATO));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PROSPECTOR, 1)
            .input('F', ModItems.FIRST_ITEM)
            .input('S', Items.STICK)
            .pattern("FF ")
            .pattern("FF ")
            .pattern("  S")
            .criterion(hasItem(ModItems.FIRST_ITEM), conditionsFromItem(ModItems.FIRST_ITEM))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ADVANCED_PROSPECTOR, 1)
            .input('I', ModItems.INSIGHT_ROD)
            .input('F', ModBlocks.FIRST_ITEM_BLOCK)
            .input('E', ModItems.ELECTRONIC_NUCLEUS_MATRIX)
            .input('L', CompressedBlocks.getBlock(Blocks.LAPIS_BLOCK, (short)1))
            .pattern("I I")
            .pattern("FEF")
            .pattern("LFL")
            .criterion(hasItem(ModItems.ELECTRONIC_NUCLEUS_MATRIX),
                conditionsFromItem(ModItems.ELECTRONIC_NUCLEUS_MATRIX))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AR_GLASSES, 1)
            .input('C', Items.COPPER_INGOT)
            .input('I', ModItems.INDUCTIVE_PREAMPLIFIER)
            .input('L', ModItems.CHROMOGENIC_LENS)
            .input('E', ModItems.ELECTRONIC_NUCLEUS_MATRIX)
            .pattern("C C")
            .pattern("CIC")
            .pattern("LEL")
            .criterion(hasItem(ModItems.ELECTRONIC_NUCLEUS_MATRIX),
                conditionsFromItem(ModItems.ELECTRONIC_NUCLEUS_MATRIX))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.THERAPEUTIC_TABLE, 1)
            .input('I', ModItems.INDUCTIVE_PREAMPLIFIER)
            .input('F', ModItems.FIRST_ITEM)
            .input('G', Items.GHAST_TEAR)
            .input('O', Items.OBSIDIAN)
            .pattern("IFI")
            .pattern("FGF")
            .pattern("OOO")
            .criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR))
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ELECTRONIC_ESSENCE, 1)
            .input('T', Items.TARGET)
            .input('S', Items.CALIBRATED_SCULK_SENSOR)
            .input('A', Items.REDSTONE_TORCH)
            .input('R', Items.REPEATER)
            .input('Q', Items.QUARTZ_BLOCK)
            .input('C', Items.COMPARATOR)
            .pattern("TST")
            .pattern("ARA")
            .pattern("QCQ")
            .criterion(hasItem(Items.CALIBRATED_SCULK_SENSOR),
                conditionsFromItem(Items.CALIBRATED_SCULK_SENSOR))
            .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ELECTRONIC_NUCLEUS_MATRIX, 1)
            .input('G', Items.GOLD_BLOCK)
            .input('A', Items.AMETHYST_SHARD)
            .input('P', Items.ENDER_PEARL)
            .input('E', ModItems.ELECTRONIC_ESSENCE)
            .input('I', Items.PACKED_ICE)
            .pattern("GAG")
            .pattern("PEP")
            .pattern("GIG")
            .criterion(hasItem(ModItems.ELECTRONIC_ESSENCE),
                conditionsFromItem(ModItems.ELECTRONIC_ESSENCE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INSIGHT_ROD, 1)
            .input('I', ModItems.INDUCTIVE_PREAMPLIFIER)
            .input('E', Items.ENDER_EYE)
            .input('C', Items.COPPER_INGOT)
            .pattern("IEI")
            .pattern(" C ")
            .pattern(" C ")
            .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHROMOGENIC_LENS, 1)
            .input('P', Items.GLASS_PANE)
            .input('R', Items.RED_DYE)
            .input('G', Items.GREEN_DYE)
            .input('B', Items.BLUE_DYE)
            .pattern("PPP")
            .pattern("RGB")
            .pattern("PPP")
            .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NIGHT_VISION_GOLDEN_CARROT, 3)
            .input('G', Items.GOLDEN_CARROT)
            .input('F', ModItems.FIRST_ITEM)
            .pattern("GGG")
            .pattern("GFG")
            .pattern("GGG")
            .criterion(hasItem(Items.GOLDEN_CARROT), conditionsFromItem(Items.GOLDEN_CARROT))
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

        CompressedBlocks.generateRecipeAll(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GHAST_TEAR, 1)
            .input('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short)1))
            .input('F', ModItems.FIRST_ITEM)
            .group(HelpfulFuncs.getSimpleIdStr(Items.GHAST_TEAR))
            .pattern("SSS")
            .pattern("SFS")
            .pattern("SSS")
            .criterion(hasItem(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short)1)),
                conditionsFromItem(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short)1)))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.GHAST_TEAR));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHER_STAR, 2)
            .input('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short)2))
            .input('F', ModBlocks.FIRST_ITEM_BLOCK)
            .input('N', Items.NETHER_STAR)
            .group(HelpfulFuncs.getSimpleIdStr(Items.NETHER_STAR))
            .pattern("FSF")
            .pattern("SNS")
            .pattern("FSF")
            .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
            .offerTo(exporter, HelpfulFuncs.getModNamespacedSimpleIdStr(Items.NETHER_STAR));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DISC_FRAGMENT_GENERAL, 9)
            .input(ItemTags.MUSIC_DISCS)
            .group(HelpfulFuncs.getSimpleIdStr(ModItems.DISC_FRAGMENT_GENERAL))
            .criterion("has_music_discs", conditionsFromTag(ItemTags.MUSIC_DISCS))
            .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DISC_FRAGMENT_5, 1)
            .input('S', Items.SCULK)
            .input('D', ModItems.DISC_FRAGMENT_GENERAL)
            .group(HelpfulFuncs.getSimpleIdStr(Items.DISC_FRAGMENT_5))
            .pattern(" S ")
            .pattern("SDS")
            .pattern(" S ")
            .criterion(hasItem(Items.SCULK), conditionsFromItem(Items.SCULK))
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
}
