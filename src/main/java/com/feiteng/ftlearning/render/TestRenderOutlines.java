package com.feiteng.ftlearning.render;

import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.block.MapColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestRenderOutlines {
    private static VertexBuffer vertexBuffer;
    public static AtomicBoolean requestedRefresh = new AtomicBoolean(false);

    public static synchronized void render(WorldRenderContext context) {

        if (vertexBuffer == null || requestedRefresh.get()) {
            requestedRefresh.set(false);
            vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

            PlayerInventory inventory = MinecraftClient.getInstance().player.getInventory();
            for (List<ItemStack> list : ImmutableList.of(
                inventory.main, inventory.offHand, inventory.armor)) {
                for (ItemStack item_stack : list) {
                    if (item_stack.getItem() instanceof AdvancedProspectorItem) {
                        AdvancedProspectorItem.forEachOre(item_stack, (block, pos_list) -> {
                            pos_list.forEach(pos -> {
                                renderBlock(buffer, pos, block.getDefaultMapColor(), 1);
                            });
                        });
                    }
                }
            }

            vertexBuffer.bind();
            vertexBuffer.upload(buffer.end());
            VertexBuffer.unbind();
        }

        if (vertexBuffer != null) {
            Camera camera = context.camera();
            Vec3d cameraPos = camera.getPos();

            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            MatrixStack poseStack = RenderSystem.getModelViewStack();
            poseStack.push();

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.applyModelViewMatrix();
            RenderSystem.depthFunc(GL11.GL_ALWAYS);

            context.projectionMatrix().lookAt(cameraPos.toVector3f(), cameraPos.toVector3f().add(camera.getHorizontalPlane()), camera.getVerticalPlane());

            vertexBuffer.bind();
            vertexBuffer.draw(poseStack.peek().getPositionMatrix(), new Matrix4f(context.projectionMatrix()), RenderSystem.getShader());
            VertexBuffer.unbind();
            RenderSystem.depthFunc(GL11.GL_LEQUAL);

            poseStack.pop();
            RenderSystem.applyModelViewMatrix();
        }
    }

    private static void renderBlock(BufferBuilder buffer, BlockPos block_pos, MapColor color, float opacity) {
        final float size = 1.0f;
        final double x = block_pos.getX(), y = block_pos.getY(), z = block_pos.getZ();

        final float red = 0; // (color.color >> 16 & 0xFF) / 255f;
        final float green = 0; // (color.color >> 8 & 0xFF) / 255f;
        final float blue = 1; // (color.color & 0xFF) / 255f;

        buffer.vertex(x, y + size, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y + size, z).color(red, green, blue, opacity).next();

        // BOTTOM
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y, z).color(red, green, blue, opacity).next();
        buffer.vertex(x, y, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).next();

        // Edge 1
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).next();

        // Edge 2
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).next();
        buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).next();

        // Edge 3
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).next();
        buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).next();

        // Edge 4
        buffer.vertex(x, y, z).color(red, green, blue, opacity).next();
        buffer.vertex(x, y + size, z).color(red, green, blue, opacity).next();
    }
}
