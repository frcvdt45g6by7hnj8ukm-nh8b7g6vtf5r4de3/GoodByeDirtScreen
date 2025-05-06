package com.kaleblangley.goodbyedirtscreen.util;

import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;

import java.util.Arrays;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.getMainRenderTarget;
import static com.kaleblangley.goodbyedirtscreen.util.ResourceUtil.PANORAMA;


public class BackGroundUtil {
    public static final Class<?>[] panoramaExcludeScreen = {};
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(new CubeMap(PANORAMA));

    public synchronized static void applyPanorama(Screen screen, float partialTick, ClientLevel level, GuiGraphics guiGraphics, int width, int height){
        if (Arrays.asList(BackGroundUtil.panoramaExcludeScreen).contains(screen.getClass())) return;
        if (level == null){
            panoramaRenderer.render(partialTick, 1.0f);
        }
        if (!(screen instanceof TitleScreen)){
            BackGroundUtil.renderMenuBackground(guiGraphics, screen);
//            BackGroundUtil.renderBlurredBackground(getMinecraft().getPartialTick());
        }
    }

    //TODO 模糊的应用
    public static void renderBlurredBackground(float pPartialTick) {
        BlurUtil.loadBlurEffect();
        BlurUtil.processBlurEffect(pPartialTick);
        getMainRenderTarget().bindWrite(false);
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
