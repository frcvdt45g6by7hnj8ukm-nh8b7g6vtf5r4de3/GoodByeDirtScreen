package com.kaleblangley.goodbyedirtscreen.api.event;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.eventbus.api.Event;

public class DirtScreen extends Event {
    private final Screen screen;

    public DirtScreen(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public static class BackGroundEvent extends DirtScreen {
        private final GuiGraphics guiGraphics;

        public BackGroundEvent(Screen screen, GuiGraphics guiGraphics) {
            super(screen);
            this.guiGraphics = guiGraphics;
        }

        public GuiGraphics getGuiGraphics() {
            return guiGraphics;
        }
    }
}
