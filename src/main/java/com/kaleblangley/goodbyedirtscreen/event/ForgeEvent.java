package com.kaleblangley.goodbyedirtscreen.event;

import com.kaleblangley.goodbyedirtscreen.GoodByeDirtScreen;
import com.kaleblangley.goodbyedirtscreen.api.event.DirtScreen;
import com.kaleblangley.goodbyedirtscreen.util.render.BackGroundUtil;
import com.kaleblangley.goodbyedirtscreen.util.ResourceUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.getMinecraft;
import static com.kaleblangley.goodbyedirtscreen.util.MinecraftUtil.getPartialTick;

@Mod.EventBusSubscriber(modid = GoodByeDirtScreen.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvent {
    @SubscribeEvent
    public static void backgroundRender(DirtScreen.BackGroundEvent event) {
        Screen currentScreen = event.getScreen();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        BackGroundUtil.applyPanorama(currentScreen, getPartialTick(), guiGraphics, currentScreen.width, currentScreen.height);
    }

    @SubscribeEvent
    public static void footerRender(DirtScreen.LayoutEvent.Footer event){
        GuiGraphics guiGraphics = event.getGuiGraphics();
        int x = event.getX();
        int y = event.getY();
        int width = event.getWidth();
        int height = event.getHeight();
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceUtil.FOOTER_SEPERATOR, x, y, 0, 0, width, height, 32,2);
        RenderSystem.disableBlend();
    }

    @SubscribeEvent
    public static void headerRender(DirtScreen.LayoutEvent.Header event){
        GuiGraphics guiGraphics = event.getGuiGraphics();
        int x = event.getX();
        int y = event.getY();
        int width = event.getWidth();
        int height = event.getHeight();
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceUtil.HEADER_SEPERATOR, x, y, 0, 0, width, height, 32,2);
        RenderSystem.disableBlend();
    }

    @SubscribeEvent
    public static void menuListRender(DirtScreen.LayoutEvent.MenuList event){
        GuiGraphics guiGraphics = event.getGuiGraphics();
        int x = event.getX();
        int y = event.getY();
        int width = event.getWidth();
        int height = event.getHeight();
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceUtil.MENU_LIST_BACKGROUND, x, y, 0, 0, width, height, 32,32);
        RenderSystem.disableBlend();
    }
}
