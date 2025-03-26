package com.kaleblangley.goodbye_dirt_screen.mixin;

import com.kaleblangley.goodbye_dirt_screen.util.PanoramaUtil;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;


@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow @Nullable protected Minecraft minecraft;

    @Shadow public int width;

    @Shadow public int height;

    @Inject(method = "render", at = @At(value = "HEAD"))
    public void panoramaBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        Screen screen = (Screen) (Object) this;
        PanoramaUtil.panoramaUtil(screen, partialTick, this.minecraft.level, guiGraphics, this.width, this.height);

    }
}
