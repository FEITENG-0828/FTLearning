package com.feiteng.ftlearning.item;

import com.feiteng.ftlearning.tag.ModItemTags;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;

public class ModArmorMaterials {
    public static final ArmorMaterial VOIDABYSS_STONE = new ArmorMaterial(
            20,
            ArmorMaterials.makeDefense(1, 4, 5, 2, 4),
            20,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0F,
            0.05F,
            ModItemTags.REPAIRS_VOIDABYSS_STONE_ARMOR,
            ModEquipmentAssets.VOIDABYSS_STONE);
}
