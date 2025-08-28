package com.feiteng.ftlearning.render;

import java.util.List;

import com.feiteng.ftlearning.item.custom.ArGlassesItem;
import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;

public class LookThroughBlockRenderer {
    public static void render(WorldRenderContext context, ClientPlayerEntity player) {
        MatrixStack matrix_stack = context.matrixStack();
        matrix_stack.push();

        Vec3d camera_pos = context.camera().getPos();
        matrix_stack.translate(-camera_pos.getX(), -camera_pos.getY(), -camera_pos.getZ());

        PlayerInventory inventory = player.getInventory();
        for (List<ItemStack> list : ImmutableList.of(
            inventory.main, inventory.offHand, inventory.armor)) {
            for (ItemStack item_stack : list) {
                if (item_stack.getItem() instanceof AdvancedProspectorItem &&
                    AdvancedProspectorItem.isInScanDimension(item_stack, context.world())) {
                    AdvancedProspectorItem.forEachOreMatchingPredicate(item_stack,
                        pos -> player.getChunkPos().getChebyshevDistance(new ChunkPos(pos)) <=
                            MinecraftClient.getInstance().options.getClampedViewDistance(),
                        (block, pos) -> {
                            renderBlockOutline(
                                matrix_stack,
                                context.consumers().getBuffer(ModRenderLayer.LOOK_THROUGH_LINES),
                                pos,
                                ArGlassesItem.getBlendedArgb(
                                    player.getEquippedStack(EquipmentSlot.HEAD),
                                    AdvancedProspectorItem.getRenderRgb(block))
                            );
                        }
                    );
                }
            }
        }

        matrix_stack.pop();
    }

    private static void renderBlockOutline(
        MatrixStack matrix_stack, VertexConsumer consumer, BlockPos pos, int argb
    ) {
        final float opacity = (argb >> 24 & 0xFF) / 255F;
        final float red = (argb >> 16 & 0xFF) / 255F;
        final float green = (argb >> 8 & 0xFF) / 255F;
        final float blue = (argb & 0xFF) / 255F;

        WorldRenderer.drawBox(matrix_stack, consumer, new Box(pos), red, green, blue, opacity);
    }
}
