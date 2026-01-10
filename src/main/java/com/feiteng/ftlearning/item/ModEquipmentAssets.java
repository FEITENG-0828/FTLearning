package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ModEquipmentAssets {
    public static final ResourceKey<EquipmentAsset> VOIDABYSS_STONE = ResourceKey.create(EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, "voidabyss_stone"));
}
