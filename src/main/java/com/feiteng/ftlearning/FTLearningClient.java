package com.feiteng.ftlearning;

import com.feiteng.ftlearning.block.ModBlocks;
// import com.feiteng.ftlearning.block.compressed.CompressedBlocks;
// import com.feiteng.ftlearning.item.custom.ArGlassesItem;
// import com.feiteng.ftlearning.render.LookThroughBlockRenderer;
// import com.mojang.authlib.minecraft.client.MinecraftClient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
// import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
// import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
// import net.minecraft.client.renderer.entity.layers.RenderLayer;
// import net.minecraft.world.entity.EquipmentSlot;

public class FTLearningClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.VOIDABYSS_STONE_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.VOIDABYSS_STONE_TRAPDOOR, ChunkSectionLayer.CUTOUT);

        // CompressedBlocks.mapRenderLayerAll();

        // WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, hit_result) -> {
        //     ClientPlayerEntity player = MinecraftClient.getInstance().player;
        //     if (player != null &&
        //             player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof ArGlassesItem) {
        //         LookThroughBlockRenderer.render(context, player);
        //     }
        //     return true;
        // });
    }
}
