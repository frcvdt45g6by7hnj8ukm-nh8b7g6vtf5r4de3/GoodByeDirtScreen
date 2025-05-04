package com.kaleblangley.goodbyedirtscreen.mixin;

import com.kaleblangley.goodbyedirtscreen.util.BackGroundUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.EditGameRulesScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Arrays;


@Mixin(Screen.class)
public class ScreenMixin {
    private static final Class<?>[] panoramaSpecialScreen = {SelectWorldScreen.class, CreateWorldScreen.class, EditGameRulesScreen.class};
    private final Screen currentScreen = (Screen) (Object) this;
    @Shadow
    @Nullable
    protected Minecraft minecraft;

    @Inject(method = "render", at = @At(value = "HEAD"))
    public void panoramaBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!Arrays.asList(panoramaSpecialScreen).contains(currentScreen.getClass())) return;
        BackGroundUtil.applyPanorama(currentScreen, this.minecraft.getPartialTick(), this.minecraft.level, guiGraphics, currentScreen.width, currentScreen.height);
    }

    @WrapOperation(method = "renderDirtBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V"))
    public void cancelDirt(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight, Operation<Void> original) {
        if (false) original.call(guiGraphics, atlasLocation, x, y, blitOffset, uOffset, vOffset, uWidth, vHeight, textureWidth, textureHeight);
    }

    @WrapOperation(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fillGradient(IIIIII)V"))
    public void cancelDirt(GuiGraphics instance, int x1, int y1, int x2, int y2, int colorFrom, int colorTo, Operation<Void> original) {
        if (false) original.call(instance, x1, y1, x2, y2, colorFrom, colorTo);
    }
}
