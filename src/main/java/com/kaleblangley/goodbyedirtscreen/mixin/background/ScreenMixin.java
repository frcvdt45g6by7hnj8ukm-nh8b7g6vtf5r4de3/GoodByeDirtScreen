package com.kaleblangley.goodbyedirtscreen.mixin.background;

import com.kaleblangley.goodbyedirtscreen.util.EventUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow @Nullable protected Minecraft minecraft;

    @Inject(method = {"renderDirtBackground", "renderBackground"}, at = @At(value = "TAIL"))
    public void listenerBackground(GuiGraphics guiGraphics, CallbackInfo ci){
        EventUtil.postBackground(this, guiGraphics);
    }

    @WrapOperation(method = "renderDirtBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V"))
    public void cancelDirt(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight, Operation<Void> original) {
        if (false) original.call(guiGraphics, atlasLocation, x, y, blitOffset, uOffset, vOffset, uWidth, vHeight, textureWidth, textureHeight);
    }

    @WrapOperation(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fillGradient(IIIIII)V"))
    public void cancelDirt(GuiGraphics instance, int x1, int y1, int x2, int y2, int colorFrom, int colorTo, Operation<Void> original) {
        if (minecraft.level == null) original.call(instance, x1, y1, x2, y2, colorFrom, colorTo);
    }
}
