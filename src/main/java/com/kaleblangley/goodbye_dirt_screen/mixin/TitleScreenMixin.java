package com.kaleblangley.goodbye_dirt_screen.mixin;

import com.kaleblangley.goodbye_dirt_screen.util.PanoramaUtil;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/PanoramaRenderer;render(FF)V"))
    public void deleteTitlePanorama(PanoramaRenderer instance, float deltaT, float alpha){
        PanoramaUtil.panoramaUtil(deltaT);
    }
}
