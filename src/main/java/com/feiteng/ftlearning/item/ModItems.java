package com.feiteng.ftlearning.item;

import java.util.function.Function;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.item.custom.ProspectorItem;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiscFragmentItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSongs;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.equipment.ArmorType;

public class ModItems {
    public static final Item FIRST_ITEM = register(
            "first_item",
            Item::new, new Item.Properties());

    public static final Item PROSPECTOR = register(
            "prospector",
            ProspectorItem::new, new Item.Properties().durability(256));

    public static final Item INDUCTIVE_PREAMPLIFIER = register(
            "inductive_preamplifier",
            Item::new, new Item.Properties());

    public static final Item NIGHT_VISION_GOLDEN_CARROT = register(
            "night_vision_golden_carrot",
            Item::new, new Item.Properties().food(ModFoodProperties.NIGHT_VISION_GOLDEN_CARROT,
                    ModConsumables.NIGHT_VISION_GOLDEN_CARROT));

    public static final Item VOIDABYSS_STONE_SWORD = register(
            "voidabyss_stone_sword",
            Item::new, new Item.Properties().sword(ModToolMaterials.VOIDABYSS_STONE, 3.0F, -2.0F));

    public static final Item VOIDABYSS_STONE_AXE = register(
            "voidabyss_stone_axe",
            properties -> new AxeItem(ModToolMaterials.VOIDABYSS_STONE, 6.0F, -2.7F, properties),
            new Item.Properties());

    public static final Item VOIDABYSS_STONE_PICKAXE = register(
            "voidabyss_stone_pickaxe",
            Item::new, new Item.Properties().pickaxe(ModToolMaterials.VOIDABYSS_STONE, 1.0F, -2.4F));

    public static final Item VOIDABYSS_STONE_SHOVEL = register(
            "voidabyss_stone_shovel",
            properties -> new ShovelItem(ModToolMaterials.VOIDABYSS_STONE, 1.5F, -2.6F, properties),
            new Item.Properties());

    public static final Item VOIDABYSS_STONE_HOE = register(
            "voidabyss_stone_hoe",
            properties -> new HoeItem(ModToolMaterials.VOIDABYSS_STONE, -1.0F, -0.6F, properties),
            new Item.Properties());

    public static final Item VOIDABYSS_STONE_HELMET = register(
            "voidabyss_stone_helmet",
            Item::new, // TODO: ModArmorItem, full armor effects
            new Item.Properties().humanoidArmor(ModArmorMaterials.VOIDABYSS_STONE, ArmorType.HELMET));

    public static final Item VOIDABYSS_STONE_CHESTPLATE = register(
            "voidabyss_stone_chestplate",
            Item::new, // TODO: ModArmorItem, full armor effects
            new Item.Properties().humanoidArmor(ModArmorMaterials.VOIDABYSS_STONE, ArmorType.CHESTPLATE));

    public static final Item VOIDABYSS_STONE_LEGGINGS = register(
            "voidabyss_stone_leggings",
            Item::new, // TODO: ModArmorItem, full armor effects
            new Item.Properties().humanoidArmor(ModArmorMaterials.VOIDABYSS_STONE, ArmorType.LEGGINGS));

    public static final Item VOIDABYSS_STONE_BOOTS = register(
            "voidabyss_stone_boots",
            Item::new, // TODO: ModArmorItem, full armor effects
            new Item.Properties().humanoidArmor(ModArmorMaterials.VOIDABYSS_STONE, ArmorType.BOOTS));
    // TODO: 纹理, 装备模型定义
    // https://docs.fabricmc.net/develop/items/custom-armor#textures-and-models

    public static final Item SHUODEDAOLI = register(
            "shuodedaoli",
            Item::new, new Item.Properties());

    public static final Item DISC_FRAGMENT_GENERAL = register(
            "disc_fragment_general",
            DiscFragmentItem::new, new Item.Properties()); // TODO: .rarity(Rarity.UNCOMMON) ?

    public static final Item MUSIC_DISC_IGOTSMOKE = register(
            "music_disc_igotsmoke",
            Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
                    .jukeboxPlayable(JukeboxSongs.OTHERSIDE));
    // new MusicDiscItem(1, ModSoundEvents.MUSIC_DISC_IGOTSMOKE, TODO
    // new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 246));

    public static final Item ADVANCED_PROSPECTOR = register(
            "advanced_prospector",
            Item::new, new Item.Properties().durability(64)); // TODO: AdvancedProspectorItem

    public static final Item AR_GLASSES = register(
            "ar_glasses",
            Item::new, new Item.Properties().durability(64) // TODO: ArGlassesItem
                    .equipmentSlot((entity, stack) -> EquipmentSlot.HEAD));

    public static final Item ELECTRONIC_ESSENCE = register(
            "electronic_essence",
            Item::new, new Item.Properties());

    public static final Item ELECTRONIC_NUCLEUS_MATRIX = register(
            "electronic_nucleus_matrix",
            Item::new, new Item.Properties());

    public static final Item INSIGHT_ROD = register(
            "insight_rod",
            Item::new, new Item.Properties());

    public static final Item CHROMOGENIC_LENS = register(
            "chromogenic_lens",
            Item::new, new Item.Properties());

    public static Item register(String name,
            Function<Item.Properties, Item> func, Item.Properties properties) {
        ResourceKey<Item> item_key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, name));
        Item item = (Item) func.apply(properties.setId(item_key));
        Registry.register(BuiltInRegistries.ITEM, item_key, item);
        return item;
    }
    // TODO: 物品模型描述
    // https://docs.fabricmc.net/zh_cn/develop/items/first-item#creating-the-item-model-description
    // TODO: 合成配方路径
    // https://docs.fabricmc.net/zh_cn/develop/items/first-item#adding-a-basic-crafting-recipe

    public static void bootstrap() {
        // ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
        // .register(item_group -> item_group.accept(ModItems.FIRST_ITEM));
    }
}
