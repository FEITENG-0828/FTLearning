package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.item.custom.ARGlassesItem;
import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;
import com.feiteng.ftlearning.item.custom.ModArmorItem;
import com.feiteng.ftlearning.item.custom.ProspectorItem;
import com.feiteng.ftlearning.sound.ModSoundEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
//import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item FIRST_ITEM = register(
        "first_item",
        new Item(new FabricItemSettings())
    );

    public static final Item PROSPECTOR = register(
        "prospector",
        new ProspectorItem(new FabricItemSettings()
            .maxDamage(256))
    );

    public static final Item INDUCTIVE_PREAMPLIFIER = register(
        "inductive_preamplifier",
        new Item(new FabricItemSettings())
    );

    public static final Item NIGHT_VISION_GOLDEN_CARROT = register(
        "night_vision_golden_carrot",
        new Item(new FabricItemSettings()
            .food(ModFoodComponents.NIGHT_VISION_GOLDEN_CARROT))
    );

    public static final Item VOIDABYSS_STONE_SWORD = register(
        "voidabyss_stone_sword",
        new SwordItem(ModToolMaterials.VOIDABYSS_STONE,
            3, -2.0f, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_AXE = register(
        "voidabyss_stone_axe",
        new AxeItem(ModToolMaterials.VOIDABYSS_STONE,
            6, -2.7f, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_PICKAXE = register(
        "voidabyss_stone_pickaxe",
        new PickaxeItem(ModToolMaterials.VOIDABYSS_STONE,
            1, -2.4f, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_SHOVEL = register(
        "voidabyss_stone_shovel",
        new ShovelItem(ModToolMaterials.VOIDABYSS_STONE,
            1.5f, -2.6f, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_HOE = register(
        "voidabyss_stone_hoe",
        new HoeItem(ModToolMaterials.VOIDABYSS_STONE,
            -1, -0.6f, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_HELMET = register(
        "voidabyss_stone_helmet",
        new ModArmorItem(ModArmorMaterials.VOIDABYSS_STONE,
            ArmorItem.Type.HELMET, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_CHESTPLATE = register(
        "voidabyss_stone_chestplate",
        new ModArmorItem(ModArmorMaterials.VOIDABYSS_STONE,
            ArmorItem.Type.CHESTPLATE, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_LEGGINGS = register(
        "voidabyss_stone_leggings",
        new ModArmorItem(ModArmorMaterials.VOIDABYSS_STONE,
            ArmorItem.Type.LEGGINGS, new FabricItemSettings())
    );

    public static final Item VOIDABYSS_STONE_BOOTS = register(
        "voidabyss_stone_boots",
        new ModArmorItem(ModArmorMaterials.VOIDABYSS_STONE,
            ArmorItem.Type.BOOTS, new FabricItemSettings())
    );

    public static final Item SHUODEDAOLI = register(
        "shuodedaoli",
        new Item(new FabricItemSettings())
    );

    public static final Item DISC_FRAGMENT_GENERAL = register(
        "disc_fragment_general",
        new DiscFragmentItem(new FabricItemSettings())
    );

    public static final Item MUSIC_DISC_IGOTSMOKE = register(
        "music_disc_igotsmoke",
        new MusicDiscItem(1, ModSoundEvents.MUSIC_DISC_IGOTSMOKE,
            new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 246)
    );

    public static final Item ADVANCED_PROSPECTOR = register(
        "advanced_prospector",
        new AdvancedProspectorItem(new FabricItemSettings()
            .maxDamage(64)
            .recipeRemainder(DISC_FRAGMENT_GENERAL))
    );

    public static final Item AR_GLASSES = register(
        "ar_glasses",
        new ARGlassesItem(new FabricItemSettings()
            .maxDamage(1024)
            .equipmentSlot(stack -> EquipmentSlot.HEAD))
    );

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM,
            new Identifier(FTLearning.MOD_ID, name),
            item);
    }

//    private static void addItemsToItemGroup(FabricItemGroupEntries fabric_item_group_entries) {
//        fabric_item_group_entries.add(FIRST_ITEM);
//    }

    public static void registerModItems() {
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
//            .register(ModItems::addItemsToItemGroup);
    }
}
