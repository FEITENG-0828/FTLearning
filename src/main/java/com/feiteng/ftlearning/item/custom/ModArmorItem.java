package com.feiteng.ftlearning.item.custom;

// import java.util.List;
// import java.util.Map;

// import com.feiteng.ftlearning.item.ModArmorMaterials;
// import com.google.common.collect.ImmutableList;
// import com.google.common.collect.ImmutableMap;

// import net.fabricmc.fabric.api.item.v1.FabricItem.Settings;
// import net.minecraft.entity.effect.StatusEffectInstance;
// import net.minecraft.entity.effect.StatusEffects;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.item.ArmorItem;
// import net.minecraft.world.World;
// import net.minecraft.world.entity.Entity;
// import net.minecraft.world.entity.EquipmentSlot;
// import net.minecraft.world.entity.EquipmentSlot.Type;
// import net.minecraft.world.item.Item;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.equipment.ArmorMaterial;

// public class ModArmorItem extends Item {
    // private static final Map<ArmorMaterial, List<StatusEffectInstance>> ARMOR_EFFECTS_MAP =
    //     (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
    //         .put(ModArmorMaterials.VOIDABYSS_STONE, ImmutableList.of(
    //                 new StatusEffectInstance(StatusEffects.RESISTANCE, 2, 0)
    //             ))
    //         .build();

    // public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
    //     super(material, type, settings);
    // }

    // @Override
    // public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    //     super.inventoryTick(stack, world, entity, slot, selected);
    //     if (!world.isClient()) {
    //         if (entity instanceof PlayerEntity player && isWearingFullSuits(player)) {
    //             applyArmorEffects(player);
    //         }
    //     }
    // }

    // private void applyArmorEffects(PlayerEntity player) {
    //     ArmorMaterial material =
    //         ((ArmorItem) player.getEquippedStack(EquipmentSlot.HEAD).getItem()).getMaterial();
    //     if (ARMOR_EFFECTS_MAP.containsKey(material)) {
    //         for (StatusEffectInstance effect : ARMOR_EFFECTS_MAP.get(material)) {
    //             player.addStatusEffect(effect);
    //         }
    //     }
    // }

    // private boolean isWearingFullSuits(PlayerEntity player) {
    //     ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
    //     ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
    //     ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
    //     ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

    //     if (!(head.getItem() instanceof ArmorItem helmet) ||
    //         !(chest.getItem() instanceof ArmorItem chestplate) ||
    //         !(legs.getItem() instanceof ArmorItem leggings) ||
    //         !(feet.getItem() instanceof ArmorItem boots)) {
    //         return false;
    //     }

    //     ArmorMaterial material = helmet.getMaterial();
    //     return material == chestplate.getMaterial() &&
    //         material == leggings.getMaterial() &&
    //         material == boots.getMaterial();
    // }
// }
