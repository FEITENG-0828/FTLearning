package com.feiteng.ftlearning;

import com.feiteng.ftlearning.datagen.ModBlockLootTableProvider;
import com.feiteng.ftlearning.datagen.ModBlockTagProvider;
import com.feiteng.ftlearning.datagen.ModDynamicRegistryProvider;
import com.feiteng.ftlearning.datagen.ModEnUsLangProvider;
import com.feiteng.ftlearning.datagen.ModEquipmentAssetProvider;
import com.feiteng.ftlearning.datagen.ModItemTagProvider;
import com.feiteng.ftlearning.datagen.ModModelProvider;
import com.feiteng.ftlearning.datagen.ModRecipeProvider;
import com.feiteng.ftlearning.datagen.ModZhCnLangProvider;
import com.feiteng.ftlearning.sound.ModJukeboxSongs;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FTLearningDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabric_data_generator) {
        FabricDataGenerator.Pack pack = fabric_data_generator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModEquipmentAssetProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModEnUsLangProvider::new);
        pack.addProvider(ModZhCnLangProvider::new);
        pack.addProvider(ModDynamicRegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);
        // TODO: not add enchantments but still work ??? maybe need refactor
        // (see https://beishanair.github.io/2025/08/01/121/enchantment/index.html)
    }
}
