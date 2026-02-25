package com.feiteng.ftlearning.render;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class EssenceExtractorScreen extends AbstractContainerScreen<EssenceExtractorMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(FTLearning.MOD_ID,
            "textures/gui/container/essence_extractor.png");

    public EssenceExtractorScreen(EssenceExtractorMenu abstractContainerMenu, Inventory inventory,
            Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
                leftPos, topPos, 0F, 0F, imageWidth, imageHeight, 256, 256);
        // TODO: animation
    }
}
