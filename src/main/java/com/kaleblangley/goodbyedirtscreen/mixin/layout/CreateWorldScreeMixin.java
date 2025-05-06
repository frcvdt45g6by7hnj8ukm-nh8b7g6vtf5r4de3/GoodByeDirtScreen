package com.kaleblangley.goodbyedirtscreen.mixin.layout;

import com.kaleblangley.goodbyedirtscreen.util.EventUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreeMixin {
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"))
    public void cancelDirt(GuiGraphics guiGraphics, ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        if (false)
            original.call(guiGraphics, atlasLocation, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
        Screen currentScreen = (Screen) (Object) this;
        EventUtil.postFooter(guiGraphics, 0, Mth.roundToward(currentScreen.height - 36 - 2, 2), currentScreen.width);
        EventUtil.postMenuList(guiGraphics, 0, 24, currentScreen.width, Mth.roundToward(currentScreen.height - 36 - 2, 2) - 24);
    }
}
