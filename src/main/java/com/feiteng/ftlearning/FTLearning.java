package com.feiteng.ftlearning;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.ModItemGroups;
import com.feiteng.ftlearning.item.ModItems;
import com.feiteng.ftlearning.sound.ModSoundEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOGGER.info("Hello Fabric world!");

        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModItemGroups.registerModItemGroups();
        ModSoundEvents.registerModSoundEvents();

        FuelRegistry.INSTANCE.add(ModItems.FIRST_ITEM, 3200);
        FuelRegistry.INSTANCE.add(ModBlocks.FIRST_ITEM_BLOCK, 32000);
    }
}