package com.feiteng.ftlearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItemGroups;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.item.custom.ArGlassesItem;
import com.feiteng.ftlearning.sound.ModSoundEvents;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;

public class FTLearning implements ModInitializer {
    public static final String MOD_ID = "ftlearning";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // LOGGER.info("Hello Fabric world!");

        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModItemGroups.bootstrap();
        ModSoundEvents.bootstrap();

        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.FIRST_ITEM, 160 * 20);
        });
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.FIRST_ITEM_BLOCK, 1600 * 20);
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (entity instanceof ServerPlayerEntity player)
            {
                ItemStack stack = player.getEquippedStack(EquipmentSlot.HEAD);
                if (stack.getItem() instanceof ArGlassesItem) {
                    RegistryEntry<DamageType> damage_type = source.getTypeRegistryEntry();
                    if (damage_type != null && (damage_type.isIn(DamageTypeTags.DAMAGES_HELMET) ||
                            damage_type.isIn(DamageTypeTags.IS_EXPLOSION) ||
                            damage_type.isIn(DamageTypeTags.IS_LIGHTNING))
                        ) {
                        stack.damage(3, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
                    } else {
                        stack.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
                    }
                }
            }
            return true;
        });
    }
}