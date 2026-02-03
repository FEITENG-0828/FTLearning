package com.feiteng.ftlearning.item.custom;

import org.jspecify.annotations.Nullable;

import com.feiteng.ftlearning.item.ModEquipmentAssets;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

public class ModArmorItem extends Item {
    public ModArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity,
            @Nullable EquipmentSlot equipment_slot) {
        super.inventoryTick(stack, level, entity, equipment_slot);

        if (equipment_slot == null || equipment_slot.getType() != EquipmentSlot.Type.HUMANOID_ARMOR) {
            return;
        }
        if (entity instanceof LivingEntity living_entity && isWearingFullSuits(living_entity)) {
            living_entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 2, 0));
        }
    }

    private boolean isWearingFullSuits(LivingEntity entity) {
        return hasEquipmentAssetInSlot(entity, EquipmentSlot.HEAD, ModEquipmentAssets.VOIDABYSS_STONE) &&
                hasEquipmentAssetInSlot(entity, EquipmentSlot.CHEST, ModEquipmentAssets.VOIDABYSS_STONE) &&
                hasEquipmentAssetInSlot(entity, EquipmentSlot.LEGS, ModEquipmentAssets.VOIDABYSS_STONE) &&
                hasEquipmentAssetInSlot(entity, EquipmentSlot.FEET, ModEquipmentAssets.VOIDABYSS_STONE);
    }

    private boolean hasEquipmentAssetInSlot(LivingEntity entity, EquipmentSlot slot,
            ResourceKey<EquipmentAsset> asset) {
        ItemStack stack = entity.getItemBySlot(slot);
        if (stack.isEmpty()) {
            return false;
        }
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        return equippable != null &&
                equippable.assetId().map(key -> key.equals(asset)).orElse(false);
    }
}
