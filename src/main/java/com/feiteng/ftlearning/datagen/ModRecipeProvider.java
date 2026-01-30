package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookup, RecipeOutput output) {
        return new RecipeProvider(lookup, output) {
            @Override
            public void buildRecipes() {
                SimpleCookingRecipeBuilder.generic(Ingredient.of(Items.ROTTEN_FLESH),
                        RecipeCategory.MISC, Items.LEATHER, // TODO: check cooking book tab
                        0.2F, 200,
                        RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                        .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                        .save(output, getModRecipeName(
                                Items.LEATHER, "from_smelting",
                                Items.ROTTEN_FLESH, null));

                shaped(RecipeCategory.MISC, ModItems.FIRST_ITEM, 1)
                        .define('N', Items.GOLD_NUGGET)
                        .define('I', Items.GOLD_INGOT)
                        .define('L', Items.LAPIS_LAZULI)
                        .group(getItemName(ModItems.FIRST_ITEM))
                        .pattern("NIN")
                        .pattern("ILI")
                        .pattern("NIN")
                        .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                        .save(output);

                offerReversibleCompactingRecipes(output,
                        RecipeCategory.MISC, ModItems.FIRST_ITEM,
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.FIRST_ITEM_BLOCK,
                        getModRecipeName(ModBlocks.FIRST_ITEM_BLOCK, "from",
                                ModItems.FIRST_ITEM, null),
                        getItemName(ModBlocks.FIRST_ITEM_BLOCK),
                        getModRecipeName(ModItems.FIRST_ITEM, "from",
                                ModBlocks.FIRST_ITEM_BLOCK, null),
                        getItemName(ModItems.FIRST_ITEM));

                shaped(RecipeCategory.MISC, Items.POISONOUS_POTATO, 1)
                        .define('S', Items.SPIDER_EYE)
                        .define('P', Items.POTATO)
                        .pattern("SSS")
                        .pattern("SPS")
                        .pattern("SSS")
                        .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                        .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.POISONOUS_POTATO));

                shaped(RecipeCategory.TOOLS, ModItems.PROSPECTOR, 1)
                        .define('F', ModItems.FIRST_ITEM)
                        .define('S', Items.STICK)
                        .pattern("FF ")
                        .pattern("FF ")
                        .pattern("  S")
                        .unlockedBy(getHasName(ModItems.FIRST_ITEM), has(ModItems.FIRST_ITEM))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.ADVANCED_PROSPECTOR, 1)
                        .define('I', ModItems.INSIGHT_ROD)
                        .define('F', ModBlocks.FIRST_ITEM_BLOCK)
                        .define('E', ModItems.ELECTRONIC_NUCLEUS_MATRIX)
                        .define('L', CompressedBlocks.getBlock(Blocks.LAPIS_BLOCK, (short) 1))
                        .pattern("I I")
                        .pattern("FEF")
                        .pattern("LFL")
                        .unlockedBy(getHasName(ModItems.ELECTRONIC_NUCLEUS_MATRIX),
                                has(ModItems.ELECTRONIC_NUCLEUS_MATRIX))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.AR_GLASSES, 1)
                        .define('C', Items.COPPER_INGOT)
                        .define('I', ModItems.INDUCTIVE_PREAMPLIFIER)
                        .define('L', ModItems.CHROMOGENIC_LENS)
                        .define('E', ModItems.ELECTRONIC_NUCLEUS_MATRIX)
                        .pattern("C C")
                        .pattern("CIC")
                        .pattern("LEL")
                        .unlockedBy(getHasName(ModItems.ELECTRONIC_NUCLEUS_MATRIX),
                                has(ModItems.ELECTRONIC_NUCLEUS_MATRIX))
                        .save(output);

                shaped(RecipeCategory.DECORATIONS, ModBlocks.THERAPEUTIC_TABLE, 1)
                        .define('I', ModItems.INDUCTIVE_PREAMPLIFIER)
                        .define('F', ModItems.FIRST_ITEM)
                        .define('G', Items.GHAST_TEAR)
                        .define('O', Items.OBSIDIAN)
                        .pattern("IFI")
                        .pattern("FGF")
                        .pattern("OOO")
                        .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                        .save(output);

                shaped(RecipeCategory.REDSTONE, ModItems.INDUCTIVE_PREAMPLIFIER, 1)
                        .define('C', Items.COPPER_INGOT)
                        .define('R', Items.REDSTONE)
                        .define('S', Items.STICK)
                        .pattern("CRC")
                        .pattern("CSC")
                        .pattern("CRC")
                        .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.ELECTRONIC_ESSENCE, 1)
                        .define('T', Items.TARGET)
                        .define('S', Items.CALIBRATED_SCULK_SENSOR)
                        .define('A', Items.REDSTONE_TORCH)
                        .define('R', Items.REPEATER)
                        .define('Q', Items.QUARTZ_BLOCK)
                        .define('C', Items.COMPARATOR)
                        .pattern("TST")
                        .pattern("ARA")
                        .pattern("QCQ")
                        .unlockedBy(getHasName(Items.CALIBRATED_SCULK_SENSOR),
                                has(Items.CALIBRATED_SCULK_SENSOR))
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.ELECTRONIC_NUCLEUS_MATRIX, 1)
                        .define('G', Items.GOLD_BLOCK)
                        .define('A', Items.AMETHYST_SHARD)
                        .define('P', Items.ENDER_PEARL)
                        .define('E', ModItems.ELECTRONIC_ESSENCE)
                        .define('I', Items.PACKED_ICE)
                        .pattern("GAG")
                        .pattern("PEP")
                        .pattern("GIG")
                        .unlockedBy(getHasName(ModItems.ELECTRONIC_ESSENCE),
                                has(ModItems.ELECTRONIC_ESSENCE))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.INSIGHT_ROD, 1)
                        .define('I', ModItems.INDUCTIVE_PREAMPLIFIER)
                        .define('E', Items.ENDER_EYE)
                        .define('C', Items.COPPER_INGOT)
                        .pattern("IEI")
                        .pattern(" C ")
                        .pattern(" C ")
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.CHROMOGENIC_LENS, 1)
                        .define('P', Items.GLASS_PANE)
                        .define('R', Items.RED_DYE)
                        .define('G', Items.GREEN_DYE)
                        .define('B', Items.BLUE_DYE)
                        .pattern("PPP")
                        .pattern("RGB")
                        .pattern("PPP")
                        .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.NIGHT_VISION_GOLDEN_CARROT, 3)
                        .define('G', Items.GOLDEN_CARROT)
                        .define('F', ModItems.FIRST_ITEM)
                        .pattern("GGG")
                        .pattern("GFG")
                        .pattern("GGG")
                        .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                        .save(output);

                createStairsRecipe(ModBlocks.VOIDABYSS_STONE_STAIRS, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                SingleItemRecipeJsonBuilder
                        .createStonecutting(Ingredient.of(ModBlocks.VOIDABYSS_STONE),
                                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_STAIRS, 1)
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output, getModRecipeName(
                                ModBlocks.VOIDABYSS_STONE_STAIRS, "from",
                                ModBlocks.VOIDABYSS_STONE, "stonecutting"));

                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_SLAB, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                SingleItemRecipeJsonBuilder
                        .createStonecutting(Ingredient.of(ModBlocks.VOIDABYSS_STONE),
                                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_SLAB, 2)
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output, getModRecipeName(
                                ModBlocks.VOIDABYSS_STONE_SLAB, "from",
                                ModBlocks.VOIDABYSS_STONE, "stonecutting"));

                getWallRecipe(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_WALL, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                SingleItemRecipeJsonBuilder
                        .createStonecutting(Ingredient.of(ModBlocks.VOIDABYSS_STONE),
                                RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOIDABYSS_STONE_WALL, 1)
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output, getModRecipeName(
                                ModBlocks.VOIDABYSS_STONE_WALL, "from",
                                ModBlocks.VOIDABYSS_STONE, "stonecutting"));

                createFenceRecipe(ModBlocks.VOIDABYSS_STONE_FENCE, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                createFenceGateRecipe(ModBlocks.VOIDABYSS_STONE_FENCE_GATE,
                        Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                createTransmutationRecipe(ModBlocks.VOIDABYSS_STONE_BUTTON,
                        Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                createPressurePlateRecipe(RecipeCategory.REDSTONE,
                        ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                createDoorRecipe(ModBlocks.VOIDABYSS_STONE_DOOR, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                offer2x2CompactingRecipe(output, RecipeCategory.REDSTONE,
                        ModBlocks.VOIDABYSS_STONE_TRAPDOOR, ModBlocks.VOIDABYSS_STONE);

                shaped(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_SWORD)
                        .define('S', Items.STICK)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("V")
                        .pattern("V")
                        .pattern("S")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_AXE)
                        .define('S', Items.STICK)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("VV")
                        .pattern("VS")
                        .pattern(" S")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_PICKAXE)
                        .define('S', Items.STICK)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("VVV")
                        .pattern(" S ")
                        .pattern(" S ")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_SHOVEL)
                        .define('S', Items.STICK)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("V")
                        .pattern("S")
                        .pattern("S")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.VOIDABYSS_STONE_HOE)
                        .define('S', Items.STICK)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("VV")
                        .pattern(" S")
                        .pattern(" S")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                SimpleCookingRecipeBuilder.create(Ingredient.of(Items.SADDLE),
                        RecipeCategory.MISC, Items.LEATHER,
                        25.0f, 300,
                        RecipeSerializer.SMELTING, SmeltingRecipe::new)
                        .unlockedBy(getHasName(Items.SADDLE), has(Items.SADDLE))
                        .save(output, getModRecipeName(
                                Items.LEATHER, "from_smelting",
                                Items.SADDLE, null));

                shaped(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_HELMET)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("VVV")
                        .pattern("V V")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_CHESTPLATE)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("V V")
                        .pattern("VVV")
                        .pattern("VVV")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_LEGGINGS)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("VVV")
                        .pattern("V V")
                        .pattern("V V")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.VOIDABYSS_STONE_BOOTS)
                        .define('V', ModBlocks.VOIDABYSS_STONE)
                        .pattern("V V")
                        .pattern("V V")
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                offerReversibleCompactingRecipes(output,
                        RecipeCategory.MISC, ModItems.SHUODEDAOLI,
                        RecipeCategory.MISC, ModBlocks.SHUODEDAOLI_BLOCK,
                        getModRecipeName(ModBlocks.SHUODEDAOLI_BLOCK, "from",
                                ModItems.SHUODEDAOLI, null),
                        getItemName(ModBlocks.SHUODEDAOLI_BLOCK),
                        getModRecipeName(ModItems.SHUODEDAOLI, "from",
                                ModBlocks.SHUODEDAOLI_BLOCK, null),
                        getItemName(ModItems.SHUODEDAOLI));

                CompressedBlocks.generateRecipeAll(output);

                shaped(RecipeCategory.MISC, Items.GHAST_TEAR, 1)
                        .define('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1))
                        .define('F', ModItems.FIRST_ITEM)
                        .group(getItemName(Items.GHAST_TEAR))
                        .pattern("SSS")
                        .pattern("SFS")
                        .pattern("SSS")
                        .unlockedBy(getHasName(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1)),
                                has(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1)))
                        .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.GHAST_TEAR));

                shaped(RecipeCategory.MISC, Items.NETHER_STAR, 2)
                        .define('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 2))
                        .define('F', ModBlocks.FIRST_ITEM_BLOCK)
                        .define('N', Items.NETHER_STAR)
                        .group(getItemName(Items.NETHER_STAR))
                        .pattern("FSF")
                        .pattern("SNS")
                        .pattern("FSF")
                        .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                        .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.NETHER_STAR));

                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DISC_FRAGMENT_GENERAL, 9)
                        .define(ItemTags.MUSIC_DISCS)
                        .group(getItemName(ModItems.DISC_FRAGMENT_GENERAL))
                        .unlockedBy("has_music_discs", conditionsFromTag(ItemTags.MUSIC_DISCS))
                        .save(output);

                shaped(RecipeCategory.MISC, Items.DISC_FRAGMENT_5, 1)
                        .define('S', Items.SCULK)
                        .define('D', ModItems.DISC_FRAGMENT_GENERAL)
                        .group(getItemName(Items.DISC_FRAGMENT_5))
                        .pattern(" S ")
                        .pattern("SDS")
                        .pattern(" S ")
                        .unlockedBy(getHasName(Items.SCULK), has(Items.SCULK))
                        .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.DISC_FRAGMENT_5));

            }
        };
    }

    public static String getModRecipeName(
            ItemLike output_item,
            String insert_str_1,
            ItemLike input_item,
            @Nullable String insert_str_2) {
        return FTLearning.MOD_ID + ':' + RecipeProvider.getItemName(output_item)
                + "_" + insert_str_1 + "_"
                + RecipeProvider.getItemName(input_item)
                + (insert_str_2 != null && !insert_str_2.isEmpty() ? ("_" + insert_str_2) : "");
    }
}
