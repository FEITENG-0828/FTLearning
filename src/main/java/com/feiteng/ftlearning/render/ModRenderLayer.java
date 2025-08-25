package com.feiteng.ftlearning.render;

import java.util.OptionalDouble;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.VertexFormats;

public class ModRenderLayer extends RenderLayer {
    public ModRenderLayer(
        String name,
        VertexFormat vertex_format,
        DrawMode draw_mode,
        int expected_buffer_size,
        boolean has_crumbling,
        boolean translucent,
        Runnable start_action,
        Runnable end_action
    ) {
        super(
            name,
            vertex_format,
            draw_mode,
            expected_buffer_size,
            has_crumbling,
            translucent,
            start_action,
            end_action
        );
    }

    public static final RenderLayer LOOK_THROUGH_LINES = of(
        "look_through_lines",
        VertexFormats.LINES,
        VertexFormat.DrawMode.LINES,
        1536,
        false,
        true,
        RenderLayer.MultiPhaseParameters.builder()
            // .texture(NO_TEXTURE)
            .program(LINES_PROGRAM)
            .transparency(TRANSLUCENT_TRANSPARENCY)
            .depthTest(ALWAYS_DEPTH_TEST)
            .cull(DISABLE_CULLING)
            // .lightmap(DISABLE_LIGHTMAP)
            // .overlay(DISABLE_OVERLAY_COLOR)
            .layering(VIEW_OFFSET_Z_LAYERING)
            .target(OUTLINE_TARGET) // IMPORTANT!
            // .texturing(DEFAULT_TEXTURING)
            .writeMaskState(COLOR_MASK)
            .lineWidth(new RenderPhase.LineWidth(OptionalDouble.of(5.0D)))
            // .colorLogic(NO_COLOR_LOGIC)
            .build(false)
    );
}
