package com.kaleblangley.goodbyedirtscreen.event;

import com.kaleblangley.goodbyedirtscreen.GoodByeDirtScreen;
import com.kaleblangley.goodbyedirtscreen.util.BackGroundUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.*;

@Mod.EventBusSubscriber(modid = GoodByeDirtScreen.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvent {
    @SubscribeEvent
    public static void screenEventBackgroundRendered(ScreenEvent.BackgroundRendered event) {
        Screen currentScreen = event.getScreen();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        BackGroundUtil.panoramaUtil(currentScreen, getPartialTick(), getMinecraft().level, guiGraphics, currentScreen.width, currentScreen.height);
    }
}
