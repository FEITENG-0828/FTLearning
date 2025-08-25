package com.feiteng.ftlearning.render;

import java.util.List;

import com.feiteng.ftlearning.item.custom.AdvancedProspectorItem;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class LookThroughBlockRenderer {
    public static boolean render(WorldRenderContext context, ClientPlayerEntity player) {
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
                    AdvancedProspectorItem.forEachOre(item_stack, (block, pos_list) -> {
                        pos_list.forEach(pos -> {
                            renderBlockOutline(
                                matrix_stack,
                                context.consumers().getBuffer(ModRenderLayer.LOOK_THROUGH_LINES),
                                pos,
                                block.getDefaultMapColor().color
                            );
                        });
                    });
                }
            }
        }

        matrix_stack.pop();
        return true;
    }

    private static void renderBlockOutline(
        MatrixStack matrix_stack, VertexConsumer consumer, BlockPos pos, int color
    ) {
        final float red = (color >> 16 & 0xFF) / 255F;
        final float green = (color >> 8 & 0xFF) / 255F;
        final float blue = (color & 0xFF) / 255F;
        final float opacity = 0.75F;

        WorldRenderer.drawBox(matrix_stack, consumer, new Box(pos), red, green, blue, opacity);
    }
}
