package com.kaleblangley.goodbyedirtscreen.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.telemetry.TelemetryInfoScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TelemetryInfoScreen.class)
public class TelemetryInfoScreenMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public void renderMenu(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
//        BackGroundUtil.renderMenuBackground(guiGraphics, (Screen) (Object) this);
    }
}
