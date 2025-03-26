package com.kaleblangley.goodbye_dirt_screen.mixin;

import com.kaleblangley.goodbye_dirt_screen.util.PanoramaUtil;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "render", at = @At(value = "HEAD"))
    public void panoramaBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        Object screen  = this;
        if (!(screen instanceof TitleScreen || screen instanceof RealmsNotificationsScreen)){
            PanoramaUtil.panoramaUtil(partialTick);
        }
    }
}
