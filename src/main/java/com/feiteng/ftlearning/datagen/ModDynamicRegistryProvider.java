package com.feiteng.ftlearning.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.registries.Registries;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.JUKEBOX_SONG));
    }

    @Override
    public String getName() {
        return "Features";
    }
}
