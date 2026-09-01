package com.kaleblangley.goodbyedirtscreen.util.render;

import com.kaleblangley.goodbyedirtscreen.Config;
import com.kaleblangley.goodbyedirtscreen.util.ResourceUtil;
import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.*;
import static com.kaleblangley.goodbyedirtscreen.util.ResourceUtil.*;


public class BackGroundUtil {
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(new CubeMap(PANORAMA));
    private static final Set<Screen> renderedScreens = Collections.newSetFromMap(new IdentityHashMap<>());

    public static void beginFrame() {
        renderedScreens.clear();
    }

    public static boolean shouldReplaceBackground(Screen screen) {
        return !(screen instanceof TitleScreen) && !excludeScreen(screen);
    }

    public static void applyPanorama(Screen screen, float partialTick, GuiGraphics guiGraphics, int width, int height) {
        if (!shouldReplaceBackground(screen) || !renderedScreens.add(screen)) return;
        // Flush GUI batches before panorama or post-processing changes GL state.
        guiGraphics.flush();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (getMinecraft().level == null || allowScreen(screen)) {
            panoramaRenderer.render(partialTick, 1.0f);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, width, height, 0.0F, 0.0F, 16, 128, 16, 128);
            RenderSystem.disableBlend();
        }

        guiGraphics.flush();
        renderBlurredBackground(partialTick);
        renderMenuBackground(guiGraphics, screen);
    }

    public static boolean allowScreen(Screen screen) {
        return Config.getInstance().allowScreenList.contains(screen.getClass().getName());
    }

    public static boolean excludeScreen(Screen screen) {
        return Config.getInstance().excludeScreenList.contains(screen.getClass().getName());
    }

    public static void renderBlurredBackground(float pPartialTick) {
        if (!Config.getInstance().blur) return;
        BlurUtil.loadBlurEffect();
        BlurUtil.processBlurEffect(pPartialTick);
    }

    public static void renderMenuBackground(GuiGraphics guiGraphics, Screen screen) {
        if (!Config.getInstance().darkening) return;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
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
        RenderSystem.bindTexture(framebuffer.getColorTextureId());
        nativeimage.downloadTexture(0, true);
        nativeimage.flipY();
        String s = Util.getFilenameFormattedDateTime();
        Util.ioPool().execute(() -> {
            try (nativeimage) {
                File file = new File(getMinecraft().gameDirectory, "screenshots");
                if (!file.isDirectory() && !file.mkdirs()) throw new IOException("Cannot create screenshots directory");
                nativeimage.writeToFile(new File(file, s + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
