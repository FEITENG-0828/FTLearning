package com.feiteng.ftlearning;

import com.feiteng.ftlearning.block.ModBlocks;
import com.feiteng.ftlearning.item.custom.ARGlassesItem;
import com.feiteng.ftlearning.render.TestRenderOutlines;
import com.feiteng.ftlearning.render.LookThroughBlockRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
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

        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.COBBLESTONE_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.DIRT_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.SAND_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.GRAVEL_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.COBBLED_DEEPSLATE_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.REDSTONE_BLOCK_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.LAPIS_BLOCK_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.AMETHYST_BLOCK_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.NETHERRACK_COMPRESSED_BLOCKS);
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(), ModBlocks.SOUL_SAND_COMPRESSED_BLOCKS);

        // ServerLivingEntityEvents.ALLOW_DAMAGE.register(null); // TODO
        // WorldRenderEvents.LAST.register(TestRenderOutlines::render);
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, hit_result) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null &&
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof ARGlassesItem) {
                return LookThroughBlockRenderer.render(context, player);
            } else {
                return true;
            }
        });
    }
}
