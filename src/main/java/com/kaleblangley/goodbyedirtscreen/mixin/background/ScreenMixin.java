package com.kaleblangley.goodbyedirtscreen.mixin.background;

import com.kaleblangley.goodbyedirtscreen.util.EventUtil;
import com.kaleblangley.goodbyedirtscreen.util.render.BackGroundUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = {"renderDirtBackground", "renderBackground"}, at = @At("HEAD"), cancellable = true)
    private void replaceBackground(GuiGraphics guiGraphics, CallbackInfo ci) {
        Screen screen = (Screen) (Object) this;
        if (BackGroundUtil.shouldReplaceBackground(screen)) {
            EventUtil.postBackground(screen, guiGraphics);
            ci.cancel();
        }
    }
}
