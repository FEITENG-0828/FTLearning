package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.block.ModBlocks;
// import com.feiteng.ftlearning.block.compressed.CompressedBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
// import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class ModCreativeModeTabs {
    public static final ResourceKey<CreativeModeTab> FTL_GROUP_CORE = createKey(
            "ftl_group_core");
    public static final ResourceKey<CreativeModeTab> FTL_GROUP_COMPRESSED = createKey(
            "ftl_group_compressed");

    private static ResourceKey<CreativeModeTab> createKey(String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, id));
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

                            display_parameters.holders().lookup(Registries.POTION)
                                    .ifPresent(registry_lookup -> {
                                        generateModPotionEffectTypes(output, registry_lookup,
                                                Items.POTION, display_parameters.enabledFeatures());
                                        generateModPotionEffectTypes(output, registry_lookup,
                                                Items.SPLASH_POTION, display_parameters.enabledFeatures());
                                        generateModPotionEffectTypes(output, registry_lookup,
                                                Items.LINGERING_POTION, display_parameters.enabledFeatures());
                                    });

                            output.accept(ModItems.ARMOR_STAND_SPAWN_EGG);

                            display_parameters.holders().lookup(Registries.ENCHANTMENT)
                                    .ifPresent(registry_lookup -> {
                                        generateModEnchantmentBookTypes(output, registry_lookup);
                                    });

                            output.accept(ModItems.SIMPLE_TUNER);

                            output.accept(ModBlocks.VOIDABYSS_STONE_PILLAR);

                            output.accept(ModBlocks.TUNABLE_EMITTER);

                            output.accept(ModBlocks.ESSENCE_EXTRACTOR);
                        })
                        .build());

        // FIXME
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                FTL_GROUP_COMPRESSED,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.ftlearning.ftl_group_compressed"))
                        .icon(() -> new ItemStack(ModItems.NIGHT_VISION_GOLDEN_CARROT))
                        // .icon(() -> new ItemStack(CompressedBlocks.getBlock(Blocks.COBBLESTONE,
                        // (short) 1)))
                        .displayItems((display_parameters, output) -> {
                            // CompressedBlocks.registerItemGroupAll(output);
                            output.accept(ModItems.SHUODEDAOLI);
                        })
                        .build());
    }

    private static void generateModPotionEffectTypes(CreativeModeTab.Output output,
            RegistryLookup<Potion> lookup, Item item, FeatureFlagSet feature_flag_set) {
        lookup.listElements()
                .filter(reference -> reference.key().identifier().getNamespace() == FTLearning.MOD_ID
                        && reference.value().isEnabled(feature_flag_set))
                .map(reference -> PotionContents.createItemStack(item, reference))
                .forEach(itemStack -> output.accept(itemStack,
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }

    private static void generateModEnchantmentBookTypes(CreativeModeTab.Output output,
            HolderLookup<Enchantment> holderLookup) {
        holderLookup.listElements()
                .filter(reference -> reference.key().identifier().getNamespace() == FTLearning.MOD_ID)
                .forEach(reference -> {
                    Enchantment enchantment = reference.value();
                    for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
                        output.accept(EnchantmentHelper.createBook(new EnchantmentInstance(reference, i)),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    }
                });
    }
}
