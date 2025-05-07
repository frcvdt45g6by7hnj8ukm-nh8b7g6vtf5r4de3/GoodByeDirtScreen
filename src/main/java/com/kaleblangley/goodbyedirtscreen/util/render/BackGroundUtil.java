package com.kaleblangley.goodbyedirtscreen.util.render;

import com.kaleblangley.goodbyedirtscreen.util.ResourceUtil;
import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.FileUtil;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.*;
import static com.kaleblangley.goodbyedirtscreen.util.ResourceUtil.PANORAMA;


public class BackGroundUtil {
    private static final Class<?>[] panoramaExcludeScreen = {};
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(new CubeMap(PANORAMA));
    private static float lastFrame = -1f;

    public static void applyPanorama(Screen screen, float partialTick, ClientLevel level, GuiGraphics guiGraphics, int width, int height) {
        if (lastFrame == getPartialTick()) return;
        lastFrame = getPartialTick();
        if (Arrays.asList(BackGroundUtil.panoramaExcludeScreen).contains(screen.getClass())) return;

//        FBOUtil.initBackgroundFBO(width, height);
//        FBOUtil.backgroundFBOWrite(false);
//        getMainRenderTarget().unbindWrite();

        if (level == null) {
            panoramaRenderer.render(partialTick, 1.0f);
        }
        if (!(screen instanceof TitleScreen)) {
//            BackGroundUtil.renderBlurredBackground(getMinecraft().getPartialTick());
            BackGroundUtil.renderMenuBackground(guiGraphics, screen);
        }
//        guiGraphics.flush();
//        FBOUtil.backgroundFBOUnWrite();
//        VertexUtil.blitPosTex(guiGraphics, FBOUtil.getColorTextureId(), width, height);

//        saveFile(FBOUtil.getBackgroundFBO());
    }

    public static void saveFile(RenderTarget framebuffer) {
        if (Util.getMillis() % 300 != 0) return;
        int i = framebuffer.width;
        int j = framebuffer.height;
        NativeImage nativeimage = new NativeImage(i, j, false);
        RenderSystem.bindTexture(FBOUtil.getColorTextureId());
        nativeimage.downloadTexture(0, true);
        nativeimage.flipY();
        String s = Util.getFilenameFormattedDateTime();
        Util.ioPool().execute(() -> {
            try {
                File file = new File(getMinecraft().gameDirectory, "screenshots");
                nativeimage.writeToFile(new File(file, s + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //TODO 模糊的应用
    public static void renderBlurredBackground(float pPartialTick) {
        BlurUtil.loadBlurEffect();
        BlurUtil.processBlurEffect(pPartialTick);
    }

    public static void renderMenuBackground(GuiGraphics guiGraphics, Screen screen) {
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceUtil.MENU_BACKGROUND, 0, 0, 0, 0, 0, screen.width, screen.height, 32, 32);
        RenderSystem.disableBlend();
    }

    public static PanoramaRenderer getPanoramaRenderer() {
        return panoramaRenderer;
    }
}
