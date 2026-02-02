package com.feiteng.ftlearning.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.feiteng.ftlearning.FTLearning;
import com.feiteng.ftlearning.item.ModEquipmentAssets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider path_provider;

    public ModEquipmentAssetProvider(FabricDataOutput output) {
        path_provider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    @Override
    public String getName() {
        return "Equipment Asset Definitions";
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(ModEquipmentAssets.VOIDABYSS_STONE, onlyHumanoid("voidabyss_stone"));
    }

    private static EquipmentClientInfo onlyHumanoid(String str) {
        return EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(FTLearning.MOD_ID, str)).build();
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cached_output) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        bootstrap((resource_key, info) -> {
            if (map.putIfAbsent(resource_key, info) != null) {
                throw new IllegalStateException(
                        "Tried to register mod equipment asset twice for id: " + resource_key);
            }
        });
        return DataProvider.saveAll(cached_output, EquipmentClientInfo.CODEC, path_provider::json, map);
    }
}
