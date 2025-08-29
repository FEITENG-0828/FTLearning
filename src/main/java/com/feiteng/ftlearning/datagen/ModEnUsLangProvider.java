package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.item.ModItemGroups;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnUsLangProvider extends FabricLanguageProvider {
    public String language_code;

    public ModEnUsLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
        this.language_code = "en_us";
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.FIRST_ITEM, "First Item");
        builder.add(ModBlocks.FIRST_ITEM_BLOCK, "First Item Block");

        builder.add(ModItemGroups.FTL_GROUP_CORE, "FTL Group : Core");
        builder.add(ModItemGroups.FTL_GROUP_COMPRESSED, "FTL Group : Compressed");

        builder.add("item.ftlearning.tooltip_mask1", "Hold ");
        builder.add("item.ftlearning.tooltip_mask2", " for more information");

        builder.add(ModItems.PROSPECTOR, "Prospector");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".tooltip",
            "Right click on the block to detect precious ores"
            + " within the upper and lower 64 blocks");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".use.success",
            "%1$s was found at (%2$s, %3$s, %4$s)");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".use.failure",
            "No Ore Found");

        builder.add(ModItems.ADVANCED_PROSPECTOR, "Advanced Prospector");
        builder.add(ModItems.ADVANCED_PROSPECTOR.getTranslationKey() + ".tooltip",
            "Right click to scan precious ores within "
            + (1 + AdvancedProspectorItem.SCAN_CHUNK_RADIUS * 2)
            + '*' + (1 + AdvancedProspectorItem.SCAN_CHUNK_RADIUS * 2)
            + " chunks around you");
        builder.add(ModItems.ADVANCED_PROSPECTOR.getTranslationKey() + ".use.done",
            "Scan completed");

        builder.add("subtitles.ftlearning.item.prospector.use.success",
        "Ores detected");
        builder.add("subtitles.ftlearning.item.prospector.use.failure",
        "No ore detected");

        builder.add(ModItems.AR_GLASSES, "AR Glasses");
        builder.add(ModBlocks.THERAPEUTIC_TABLE, "Therapeutic Table");

        builder.add(ModItems.INDUCTIVE_PREAMPLIFIER, "Inductive Preamplifier");
        builder.add(ModItems.ELECTRONIC_ESSENCE, "Electronic Essence");
        builder.add(ModItems.ELECTRONIC_NUCLEUS_MATRIX, "Electronic Nucleus Matrix");
        builder.add(ModItems.INSIGHT_ROD, "Insight Rod");
        builder.add(ModItems.CHROMOGENIC_LENS, "Chromogenic Lens");

        builder.add(ModItems.NIGHT_VISION_GOLDEN_CARROT, "Night Vision Golden Carrot");

        builder.add(ModBlocks.VOIDABYSS_STONE, "Voidabyss Stone");
        builder.add(ModBlocks.VOIDABYSS_STONE_STAIRS, "Voidabyss Stone Stairs");
        builder.add(ModBlocks.VOIDABYSS_STONE_SLAB, "Voidabyss Stone Slab");
        builder.add(ModBlocks.VOIDABYSS_STONE_WALL, "Voidabyss Stone Wall");
        builder.add(ModBlocks.VOIDABYSS_STONE_FENCE, "Voidabyss Stone Fence");
        builder.add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE, "Voidabyss Stone Fence Gate");
        builder.add(ModBlocks.VOIDABYSS_STONE_BUTTON, "Voidabyss Stone Button");
        builder.add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE, "Voidabyss Stone Pressure Plate");
        builder.add(ModBlocks.VOIDABYSS_STONE_DOOR, "Voidabyss Stone Door");
        builder.add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR, "Voidabyss Stone Trapdoor");

        builder.add(ModItems.VOIDABYSS_STONE_SWORD, "Voidabyss Stone Sword");
        builder.add(ModItems.VOIDABYSS_STONE_AXE, "Voidabyss Stone Axe");
        builder.add(ModItems.VOIDABYSS_STONE_PICKAXE, "Voidabyss Stone Pickaxe");
        builder.add(ModItems.VOIDABYSS_STONE_SHOVEL, "Voidabyss Stone Shovel");
        builder.add(ModItems.VOIDABYSS_STONE_HOE, "Voidabyss Stone Hoe");

        builder.add(ModItems.VOIDABYSS_STONE_HELMET, "Voidabyss Stone Helmet");
        builder.add(ModItems.VOIDABYSS_STONE_CHESTPLATE, "Voidabyss Stone Chestplate");
        builder.add(ModItems.VOIDABYSS_STONE_LEGGINGS, "Voidabyss Stone Leggings");
        builder.add(ModItems.VOIDABYSS_STONE_BOOTS, "Voidabyss Stone Boots");

        builder.add(ModItems.SHUODEDAOLI, "ShuoDeDaoLi");
        builder.add(ModBlocks.SHUODEDAOLI_BLOCK, "ShuoDeDaoLi Block");

        builder.add(ModItems.DISC_FRAGMENT_GENERAL, "Disc Fragment");
        builder.add(ModItems.DISC_FRAGMENT_GENERAL.getTranslationKey() + ".desc",
            "Song unstored");
        builder.add(ModItems.MUSIC_DISC_IGOTSMOKE, "Music Disc");
        builder.add(ModItems.MUSIC_DISC_IGOTSMOKE.getTranslationKey() + ".desc",
            "Venoflame - I Got Smoke (Explicit Ver.)");

        CompressedBlocks.translateAll(builder, this.language_code);
    }
}
