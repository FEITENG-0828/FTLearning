package com.feiteng.ftlearning;

import com.feiteng.ftlearning.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

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
    }
}
