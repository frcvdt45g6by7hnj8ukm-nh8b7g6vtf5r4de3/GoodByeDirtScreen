package com.kaleblangley.goodbyedirtscreen.util.render;

import com.kaleblangley.goodbyedirtscreen.GoodByeDirtScreen;
import com.kaleblangley.goodbyedirtscreen.util.ResourceUtil;
import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;

import java.io.File;
import java.io.IOException;

import static com.kaleblangley.goodbyedirtscreen.GoodByeDirtScreen.*;
import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.*;
import static com.kaleblangley.goodbyedirtscreen.util.ResourceUtil.*;


public class BackGroundUtil {
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(new CubeMap(PANORAMA));
    private static float cacheFrame = -1f;
    private static Screen cacheScreen = null;

    public static void applyPanorama(Screen screen, float partialTick, ClientLevel level, GuiGraphics guiGraphics, int width, int height) {
        if (!allowScreen(screen) && (updateCache(screen) || excludeScreen(screen))) return;

//        FBOUtil.initBackgroundFBO(width, height);
//        FBOUtil.backgroundFBOWrite(false);
//        getMainRenderTarget().unbindWrite();

        if (level == null || allowScreen(screen)) {
            panoramaRenderer.render(partialTick, 1.0f);
            RenderSystem.enableBlend();
            guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, width, height, 0.0F, 0.0F, 16, 128, 16, 128);
            RenderSystem.disableBlend();
        }
        if (!(screen instanceof TitleScreen)) {
//            BackGroundUtil.renderBlurredBackground(getMinecraft().getPartialTick());
            BackGroundUtil.renderMenuBackground(guiGraphics, screen);
        }
    }

    private static boolean updateCache(Screen screen) {
        if (cacheFrame == getPartialTick() && cacheScreen == screen) {
            cacheFrame = getPartialTick();
            cacheScreen = screen;
            return true;
        }
        return false;
    }

    private static boolean allowScreen(Screen screen) {
        return EXCLUDE_SCREEN_LIST.get().contains(screen.getClass().getName());
    }

    private static boolean excludeScreen(Screen screen) {
        return EXCLUDE_SCREEN_LIST.get().contains(screen.getClass().getName());
    }

    //TODO 模糊的应用
    public static void renderBlurredBackground(float pPartialTick) {
        if (!GoodByeDirtScreen.ENABLE_BLUR_EFFECTS.get()) return;
        BlurUtil.loadBlurEffect();
        BlurUtil.processBlurEffect(pPartialTick);
    }

    public static void renderMenuBackground(GuiGraphics guiGraphics, Screen screen) {
        if (!GoodByeDirtScreen.ENABLE_DARKENING_EFFECTS.get()) return;
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceUtil.MENU_BACKGROUND, 0, 0, 0, 0, 0, screen.width, screen.height, 32, 32);
        RenderSystem.disableBlend();
    }

    public static PanoramaRenderer getPanoramaRenderer() {
        return panoramaRenderer;
    }

    //DEBUG
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
}
