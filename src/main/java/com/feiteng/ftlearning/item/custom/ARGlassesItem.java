package com.feiteng.ftlearning.item.custom;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ArGlassesItem extends Item implements Equipment {
    public ArGlassesItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public static Integer getBlendedArgb(ItemStack stack, Integer origin) {
        float new_opacity = 0.75F * (1 - (float)stack.getDamage() / stack.getMaxDamage());
        return ((int)(new_opacity * 255) & 0xFF) << 24 | origin;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return equipAndSwap(this, world, user, hand);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.GLASS_PANE) || super.canRepair(stack, ingredient);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() &&
            entity instanceof PlayerEntity player &&
            player.getEquippedStack(EquipmentSlot.HEAD).isOf(this)
        ) {
            stepTimer(stack);
            if (needDamageItem(stack)) {
                stack.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
            }
        }
    }

    private void stepTimer(ItemStack stack) {
        int timer = stack.getOrCreateNbt().getInt("UseTimer");
        stack.getNbt().putInt("UseTimer", timer + 1);
    }

    private boolean needDamageItem(ItemStack stack) {
        if (stack.getNbt().getInt("UseTimer") > 600) {
            stack.getNbt().putInt("UseTimer", 0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_SPYGLASS_USE;
    }

    //TODO: switch, gui layer, head position

}
