package com.feiteng.ftlearning.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput data_output,
            CompletableFuture<HolderLookup.Provider> lookup) {
        super(data_output, lookup);
    }

    @Override
    public String getName() {
        return "Mod Recipes";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookup, RecipeOutput recipe_output) {
        return new RecipeProvider(lookup, recipe_output) {
            @Override
            public void buildRecipes() {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.ROTTEN_FLESH),
                        RecipeCategory.MISC, Items.LEATHER, 0.2F, 200)
                        .group(getItemName(Items.LEATHER))
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

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.FIRST_ITEM,
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

                // shaped(RecipeCategory.TOOLS, ModItems.ADVANCED_PROSPECTOR, 1)
                // .define('I', ModItems.INSIGHT_ROD)
                // .define('F', ModBlocks.FIRST_ITEM_BLOCK)
                // .define('E', ModItems.ELECTRONIC_NUCLEUS_MATRIX)
                // .define('L', CompressedBlocks.getBlock(Blocks.LAPIS_BLOCK, (short) 1))
                // .pattern("I I")
                // .pattern("FEF")
                // .pattern("LFL")
                // .unlockedBy(getHasName(ModItems.ELECTRONIC_NUCLEUS_MATRIX),
                // has(ModItems.ELECTRONIC_NUCLEUS_MATRIX))
                // .save(output);

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

                stairBuilder(ModBlocks.VOIDABYSS_STONE_STAIRS, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_STAIRS, ModBlocks.VOIDABYSS_STONE, 1);

                slab(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_SLAB, ModBlocks.VOIDABYSS_STONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_SLAB, ModBlocks.VOIDABYSS_STONE, 2);

                wall(RecipeCategory.DECORATIONS,
                        ModBlocks.VOIDABYSS_STONE_WALL, ModBlocks.VOIDABYSS_STONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.VOIDABYSS_STONE_WALL, ModBlocks.VOIDABYSS_STONE, 1);

                fenceBuilder(ModBlocks.VOIDABYSS_STONE_FENCE, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                fenceGateBuilder(ModBlocks.VOIDABYSS_STONE_FENCE_GATE,
                        Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                buttonBuilder(ModBlocks.VOIDABYSS_STONE_BUTTON,
                        Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                pressurePlate(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE, ModBlocks.VOIDABYSS_STONE);

                doorBuilder(ModBlocks.VOIDABYSS_STONE_DOOR, Ingredient.of(ModBlocks.VOIDABYSS_STONE))
                        .unlockedBy(getHasName(ModBlocks.VOIDABYSS_STONE), has(ModBlocks.VOIDABYSS_STONE))
                        .save(output);

                twoByTwoPacker(RecipeCategory.REDSTONE,
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

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SADDLE),
                        RecipeCategory.MISC, Items.LEATHER, 25.0F, 300)
                        .group(getItemName(Items.LEATHER))
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

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.SHUODEDAOLI,
                        RecipeCategory.MISC, ModBlocks.SHUODEDAOLI_BLOCK,
                        getModRecipeName(ModBlocks.SHUODEDAOLI_BLOCK, "from",
                                ModItems.SHUODEDAOLI, null),
                        getItemName(ModBlocks.SHUODEDAOLI_BLOCK),
                        getModRecipeName(ModItems.SHUODEDAOLI, "from",
                                ModBlocks.SHUODEDAOLI_BLOCK, null),
                        getItemName(ModItems.SHUODEDAOLI));

                // CompressedBlocks.generateRecipeAll(output);

                // shaped(RecipeCategory.MISC, Items.GHAST_TEAR, 1)
                //         .define('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1))
                //         .define('F', ModItems.FIRST_ITEM)
                //         .group(getItemName(Items.GHAST_TEAR))
                //         .pattern("SSS")
                //         .pattern("SFS")
                //         .pattern("SSS")
                //         .unlockedBy(getHasName(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1)),
                //                 has(CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 1)))
                //         .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.GHAST_TEAR));

                // shaped(RecipeCategory.MISC, Items.NETHER_STAR, 2)
                //         .define('S', CompressedBlocks.getBlock(Blocks.SOUL_SAND, (short) 2))
                //         .define('F', ModBlocks.FIRST_ITEM_BLOCK)
                //         .define('N', Items.NETHER_STAR)
                //         .group(getItemName(Items.NETHER_STAR))
                //         .pattern("FSF")
                //         .pattern("SNS")
                //         .pattern("FSF")
                //         .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                //         .save(output, HelpfulFuncs.getModNamespacedIdStr(Items.NETHER_STAR));

                // TODO
                // shapeless(RecipeCategory.MISC, ModItems.DISC_FRAGMENT_GENERAL, 9)
                //         .requires(ItemTags.MUSIC_DISCS)
                //         .group(getItemName(ModItems.DISC_FRAGMENT_GENERAL))
                //         .unlockedBy("has_music_discs", conditionsFromTag(ItemTags.MUSIC_DISCS))
                //         .save(output);

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
