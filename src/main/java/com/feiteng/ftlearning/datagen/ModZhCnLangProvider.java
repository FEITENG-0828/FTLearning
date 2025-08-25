package com.feiteng.ftlearning.datagen;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItemGroups;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;
import com.feiteng.ftlearning.util.HelpfulFuncs;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;

public class ModZhCnLangProvider extends FabricLanguageProvider {
    public ModZhCnLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.FIRST_ITEM, "第一物品");
        builder.add(ModBlocks.FIRST_ITEM_BLOCK, "第一物块");

        builder.add(ModItemGroups.FTL_GROUP_CORE, "FTL物品组 : 核心");
        builder.add(ModItemGroups.FTL_GROUP_COMPRESSED, "FTL物品组 : 压缩");

        builder.add("item.ftlearning.tooltip_mask1", "按住");
        builder.add("item.ftlearning.tooltip_mask2", "以获得更多信息");

        builder.add(ModItems.PROSPECTOR, "探矿者");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".tooltip",
            "右键单击该方块以检测上下64格内的珍贵矿石");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".use.success",
            "在 (%2$s, %3$s, %4$s) 检测到 %1$s");
        builder.add(ModItems.PROSPECTOR.getTranslationKey() + ".use.failure",
            "未检测到矿石");

        builder.add(ModItems.ADVANCED_PROSPECTOR, "高级探矿者");
        builder.add(ModItems.ADVANCED_PROSPECTOR.getTranslationKey() + ".tooltip",
            "右键单击以扫描周围"
            + (1 + AdvancedProspectorItem.SCAN_CHUNK_RADIUS * 2)
            + '*' + (1 + AdvancedProspectorItem.SCAN_CHUNK_RADIUS * 2)
            + "个区块内的珍贵矿石");
        builder.add(ModItems.ADVANCED_PROSPECTOR.getTranslationKey() + ".use.done",
            "扫描完成");

        builder.add("subtitles.ftlearning.item.prospector.use.success",
        "检测到矿石");
        builder.add("subtitles.ftlearning.item.prospector.use.failure",
        "未检测到矿石");

        builder.add(ModItems.AR_GLASSES, "AR眼镜");
        builder.add(ModBlocks.THERAPEUTIC_TABLE, "治疗台");

        builder.add(ModItems.INDUCTIVE_PREAMPLIFIER, "感应预放器");
        builder.add(ModItems.ELECTRONIC_ESSENCE, "电子精粹");
        builder.add(ModItems.ELECTRONIC_NUCLEUS_MATRIX, "电子精核矩阵");
        builder.add(ModItems.INSIGHT_ROD, "灵视引");
        builder.add(ModItems.CHROMOGENIC_LENS, "显色镜片");

        builder.add(ModItems.NIGHT_VISION_GOLDEN_CARROT, "夜视金萝卜");

        builder.add(ModBlocks.VOIDABYSS_STONE, "渊虚石");
        builder.add(ModBlocks.VOIDABYSS_STONE_STAIRS, "渊虚石楼梯");
        builder.add(ModBlocks.VOIDABYSS_STONE_SLAB, "渊虚石台阶");
        builder.add(ModBlocks.VOIDABYSS_STONE_WALL, "渊虚石墙");
        builder.add(ModBlocks.VOIDABYSS_STONE_FENCE, "渊虚石栅栏");
        builder.add(ModBlocks.VOIDABYSS_STONE_FENCE_GATE, "渊虚石栅栏门");
        builder.add(ModBlocks.VOIDABYSS_STONE_BUTTON, "渊虚石按钮");
        builder.add(ModBlocks.VOIDABYSS_STONE_PRESSURE_PLATE, "渊虚石压力板");
        builder.add(ModBlocks.VOIDABYSS_STONE_DOOR, "渊虚石门");
        builder.add(ModBlocks.VOIDABYSS_STONE_TRAPDOOR, "渊虚石活板门");

        builder.add(ModItems.VOIDABYSS_STONE_SWORD, "渊虚石剑");
        builder.add(ModItems.VOIDABYSS_STONE_AXE, "渊虚石斧");
        builder.add(ModItems.VOIDABYSS_STONE_PICKAXE, "渊虚石镐");
        builder.add(ModItems.VOIDABYSS_STONE_SHOVEL, "渊虚石锹");
        builder.add(ModItems.VOIDABYSS_STONE_HOE, "渊虚石锄");

        builder.add(ModItems.VOIDABYSS_STONE_HELMET, "渊虚石头盔");
        builder.add(ModItems.VOIDABYSS_STONE_CHESTPLATE, "渊虚石胸甲");
        builder.add(ModItems.VOIDABYSS_STONE_LEGGINGS, "渊虚石护腿");
        builder.add(ModItems.VOIDABYSS_STONE_BOOTS, "渊虚石靴子");

        builder.add(ModItems.SHUODEDAOLI, "说的道理");
        builder.add(ModBlocks.SHUODEDAOLI_BLOCK, "说的道理块");

        builder.add(ModItems.DISC_FRAGMENT_GENERAL, "唱片残片");
        builder.add(ModItems.DISC_FRAGMENT_GENERAL.getTranslationKey() + ".desc",
            "未存储歌曲");
        builder.add(ModItems.MUSIC_DISC_IGOTSMOKE, "音乐唱片");
        builder.add(ModItems.MUSIC_DISC_IGOTSMOKE.getTranslationKey() + ".desc",
            "V在燃烧 - I Got Smoke (Explicit Ver.)");

        addCompressedBlockKind(
            builder, "圆石", ModBlocks.COBBLESTONE_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "泥土", ModBlocks.DIRT_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "沙子", ModBlocks.SAND_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "沙砾", ModBlocks.GRAVEL_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "深板岩圆石", ModBlocks.COBBLED_DEEPSLATE_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "红石块", ModBlocks.REDSTONE_BLOCK_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "青金石块", ModBlocks.LAPIS_BLOCK_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "紫水晶块", ModBlocks.AMETHYST_BLOCK_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "下界岩", ModBlocks.NETHERRACK_COMPRESSED_BLOCKS);
        addCompressedBlockKind(
            builder, "灵魂沙", ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS);
    }

    public static void addCompressedBlockKind(
        TranslationBuilder builder, String base_name, Block[] blocks
    ) {
        for (int i = 0; i < blocks.length; ++i) {
            builder.add(blocks[i],
                HelpfulFuncs.getCompressedBlockName(base_name, i + 1, "zh_cn"));
        }
    }
}
