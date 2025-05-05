package com.kaleblangley.goodbyedirtscreen.api.event;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Event;

public class DirtScreen extends Event {
    private final Screen screen;

    public DirtScreen(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public static class BackGroundEvent extends DirtScreen{
        public BackGroundEvent(Screen screen) {
            super(screen);
        }
    }
}
