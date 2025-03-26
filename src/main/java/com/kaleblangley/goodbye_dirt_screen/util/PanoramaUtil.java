package com.kaleblangley.goodbye_dirt_screen.util;

import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.PanoramaRenderer;

public class PanoramaUtil {
    private static final PanoramaRenderer panoramaRenderer = new PanoramaRenderer(TitleScreen.CUBE_MAP);
    public static void panoramaUtil(Screen screen, float partialTick, ClientLevel level, GuiGraphics guiGraphics, int width, int height){
        if (!(screen instanceof RealmsNotificationsScreen)) {
            if (level == null){
                panoramaRenderer.render(partialTick, 1.0f);
            }
            if (!(screen instanceof TitleScreen)){
                guiGraphics.fillGradient(0, 0, width, height, -1072689136, -804253680);
            }
        }
    }

    public static PanoramaRenderer getPanoramaRenderer() {
        return panoramaRenderer;
    }
}
