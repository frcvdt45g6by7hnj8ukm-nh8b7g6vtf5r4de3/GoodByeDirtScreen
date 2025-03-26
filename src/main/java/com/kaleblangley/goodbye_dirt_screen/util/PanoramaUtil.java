package com.kaleblangley.goodbye_dirt_screen.util;

import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.PanoramaRenderer;

public class PanoramaUtil {
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(TitleScreen.CUBE_MAP);
    public static void panoramaUtil(float partialTick){
        panoramaRenderer.render(partialTick, 1.0f);
    }

    public static PanoramaRenderer getPanoramaRenderer() {
        return panoramaRenderer;
    }
}
