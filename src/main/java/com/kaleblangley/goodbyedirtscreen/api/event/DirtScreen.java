package com.kaleblangley.goodbyedirtscreen.api.event;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.eventbus.api.Event;

public class DirtScreen extends Event {
    private final GuiGraphics guiGraphics;

    public DirtScreen(GuiGraphics guiGraphics) {
        this.guiGraphics = guiGraphics;
    }


    public GuiGraphics getGuiGraphics() {
        return guiGraphics;
    }

    public static class BackGroundEvent extends DirtScreen {
        private final Screen screen;

        public BackGroundEvent(Screen screen, GuiGraphics guiGraphics) {
            super(guiGraphics);
            this.screen = screen;
        }

        public Screen getScreen() {
            return screen;
        }
    }

    public static class LayoutEvent extends DirtScreen {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public LayoutEvent(GuiGraphics guiGraphics, int x, int y, int width, int height) {
            super(guiGraphics);
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public static class MenuList extends LayoutEvent {
            public MenuList(GuiGraphics guiGraphics, int x, int y, int width, int height) {
                super(guiGraphics, x, y, width, height);
            }
        }

        public static class Header extends LayoutEvent {
            public Header(GuiGraphics guiGraphics, int x, int y, int width) {
                super(guiGraphics, x, y, width, 2);
            }
        }

        public static class Footer extends LayoutEvent {
            public Footer(GuiGraphics guiGraphics, int x, int y, int width) {
                super(guiGraphics, x, y, width, 2);
            }
        }
    }
}
