package com.kaleblangley.goodbyedirtscreen.util;

import com.kaleblangley.goodbyedirtscreen.api.event.DirtScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.MinecraftForge;

public class EventUtil {
    public static void postBackground(Object screen, GuiGraphics guiGraphics){
        postBackground((Screen) screen, guiGraphics);
    }

    public static void postBackground(Screen screen, GuiGraphics guiGraphics){
        DirtScreen.BackGroundEvent backGroundEvent = new DirtScreen.BackGroundEvent(screen, guiGraphics);
        MinecraftForge.EVENT_BUS.post(backGroundEvent);
    }
}
