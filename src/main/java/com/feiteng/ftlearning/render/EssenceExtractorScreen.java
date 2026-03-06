package com.feiteng.ftlearning.render;

import com.feiteng.ftlearning.FTLearning;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class EssenceExtractorScreen extends AbstractContainerScreen<EssenceExtractorMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(FTLearning.MOD_ID,
            "textures/gui/container/essence_extractor.png");
    private static final Identifier PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(FTLearning.MOD_ID,
            "container/essence_extractor/progress");

    public EssenceExtractorScreen(EssenceExtractorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    public void render(GuiGraphics graphics, int i, int j, float f) {
        super.render(graphics, i, j, f);
        renderTooltip(graphics, i, j);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float f, int i, int j) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
                leftPos, topPos, 0F, 0F, imageWidth, imageHeight, 256, 256);
        int draw_width = Mth.ceil(menu.getProgress() * 42);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_SPRITE, 42, 16, 0, 0,
                leftPos + 65, topPos + 34, draw_width, 16);
    }
}
