package com.kaleblangley.goodbyedirtscreen.util.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;

public class VertexUtil {
    public static void blitPosTex(GuiGraphics guiGraphics, int colorTextureId, float width, float height){
        blitPosTex(guiGraphics.pose().last().pose(), colorTextureId, width, height);
    }

    public static void blitPosTex(Matrix4f matrix4f, int colorTextureId, float width, float height){
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        blitPosTex(bufferbuilder, colorTextureId, matrix4f, width, height);
    }

    public static void blitPosTex(BufferBuilder bufferbuilder, int colorTextureId, Matrix4f matrix4f, float width, float height){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, colorTextureId);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        blit(bufferbuilder, matrix4f, width, height);
    }

    public static void blit(BufferBuilder bufferbuilder, Matrix4f matrix4f, float width, float height) {
        blit(bufferbuilder, matrix4f, 0, 0, width, height, 0, 1, 1, 0);
    }

    public static void blit(BufferBuilder bufferbuilder, Matrix4f matrix4f, float x1, float y1, float x2, float y2, float u1, float v1, float u2, float v2) {
        bufferbuilder.vertex(matrix4f, x1, y1, 0).uv(u1, v1);
        bufferbuilder.vertex(matrix4f, x1, y2, 0).uv(u1, v2);
        bufferbuilder.vertex(matrix4f, x2, y2, 0).uv(u2, v2);
        bufferbuilder.vertex(matrix4f, x2, y1, 0).uv(u2, v1);
        BufferUploader.drawWithShader(bufferbuilder.end());
    }
}
