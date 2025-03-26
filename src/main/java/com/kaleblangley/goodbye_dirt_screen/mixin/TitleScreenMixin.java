package com.kaleblangley.goodbye_dirt_screen.mixin;

import com.kaleblangley.goodbye_dirt_screen.util.PanoramaUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/PanoramaRenderer;render(FF)V"))
    public void deleteTitlePanorama(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        PanoramaUtil.panoramaUtil(this, partialTick, this.minecraft.level, guiGraphics, this.width, this.height);
    }
}
