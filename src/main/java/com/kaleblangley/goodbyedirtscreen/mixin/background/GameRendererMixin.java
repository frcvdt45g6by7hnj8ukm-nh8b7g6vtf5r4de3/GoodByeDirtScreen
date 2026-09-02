package com.kaleblangley.goodbyedirtscreen.mixin.background;

import com.kaleblangley.goodbyedirtscreen.util.render.BackGroundUtil;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void beginBackgroundFrame(float partialTicks, long nanoTime, boolean renderLevel, CallbackInfo ci) {
        BackGroundUtil.beginFrame();
    }
}
