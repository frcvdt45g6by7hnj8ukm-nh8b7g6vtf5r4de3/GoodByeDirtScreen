package com.kaleblangley.goodbyedirtscreen.util;

import com.kaleblangley.goodbyedirtscreen.api.event.DirtScreen;
import com.kaleblangley.goodbyedirtscreen.event.EventImpl;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class EventUtil {
    public static void postBackground(Object screen, GuiGraphics guiGraphics) {
        postBackground((Screen) screen, guiGraphics);
    }

    public static void postBackground(Screen screen, GuiGraphics guiGraphics) {
        DirtScreen.BackGroundEvent backGroundEvent = new DirtScreen.BackGroundEvent(screen, guiGraphics);
        EventImpl.backgroundRender(backGroundEvent);
    }

    public static void postFooter(GuiGraphics guiGraphics, int x, int y, int width) {
        DirtScreen.LayoutEvent.Footer footer = new DirtScreen.LayoutEvent.Footer(guiGraphics, x, y, width);
        EventImpl.footerRender(footer);
    }

    public static void postHeader(GuiGraphics guiGraphics, int x, int y, int width) {
        DirtScreen.LayoutEvent.Header header = new DirtScreen.LayoutEvent.Header(guiGraphics, x, y, width);
        EventImpl.headerRender(header);
    }

    public static void postMenuList(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        DirtScreen.LayoutEvent.MenuList menuList = new DirtScreen.LayoutEvent.MenuList(guiGraphics, x, y, width, height);
        EventImpl.menuListRender(menuList);
    }
}
