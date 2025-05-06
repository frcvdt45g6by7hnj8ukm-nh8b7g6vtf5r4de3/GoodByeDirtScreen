package com.kaleblangley.goodbyedirtscreen.mixin.layout;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreeMixin {
    @Shadow @Final public static ResourceLocation FOOTER_SEPERATOR;

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"))
    public void cancelDirt(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        if (false) original.call(guiGraphics, atlasLocation, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
        Screen currentScreen = (Screen) (Object) this;
        RenderSystem.enableBlend();
        guiGraphics.blit(FOOTER_SEPERATOR, 0, Mth.roundToward(currentScreen.height - 36 - 2, 2), 0.0F, 0.0F, currentScreen.width, 2, 32, 2);
        RenderSystem.disableBlend();
    }
}
