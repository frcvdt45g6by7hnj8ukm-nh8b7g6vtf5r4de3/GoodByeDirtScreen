package com.kaleblangley.goodbyedirtscreen.util;

import com.kaleblangley.goodbyedirtscreen.api.event.DirtScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.MinecraftForge;

public class EventUtil {
    public static void postBackground(Object screen, GuiGraphics guiGraphics) {
        postBackground((Screen) screen, guiGraphics);
    }

    public static void postBackground(Screen screen, GuiGraphics guiGraphics) {
        DirtScreen.BackGroundEvent backGroundEvent = new DirtScreen.BackGroundEvent(screen, guiGraphics);
        MinecraftForge.EVENT_BUS.post(backGroundEvent);
    }

    public static void postFooter(GuiGraphics guiGraphics, int x, int y, int width) {
        DirtScreen.LayoutEvent.Footer backGroundEvent = new DirtScreen.LayoutEvent.Footer(guiGraphics, x, y, width);
        MinecraftForge.EVENT_BUS.post(backGroundEvent);
    }

    public static void postHeader(GuiGraphics guiGraphics, int x, int y, int width) {
        DirtScreen.LayoutEvent.Header backGroundEvent = new DirtScreen.LayoutEvent.Header(guiGraphics, x, y, width);
        MinecraftForge.EVENT_BUS.post(backGroundEvent);
    }

    public static void postMenuList(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        DirtScreen.LayoutEvent.MenuList backGroundEvent = new DirtScreen.LayoutEvent.MenuList(guiGraphics, x, y, width, height);
        MinecraftForge.EVENT_BUS.post(backGroundEvent);
    }
}
