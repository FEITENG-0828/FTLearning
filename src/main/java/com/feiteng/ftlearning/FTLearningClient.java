package com.feiteng.ftlearning;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
import com.feiteng.ftlearning.item.custom.ArGlassesItem;
import com.feiteng.ftlearning.render.LookThroughBlockRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;

public class FTLearningClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(
            ModBlocks.VOIDABYSS_STONE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(
            ModBlocks.VOIDABYSS_STONE_TRAPDOOR, RenderLayer.getCutout());

        CompressedBlocks.mapRenderLayerAll();

        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, hit_result) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null &&
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof ArGlassesItem) {
                LookThroughBlockRenderer.render(context, player);
            }
            return true;
        });
    }
}
