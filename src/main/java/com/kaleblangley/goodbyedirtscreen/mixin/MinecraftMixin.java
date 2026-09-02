package com.kaleblangley.goodbyedirtscreen.mixin;

import com.kaleblangley.goodbyedirtscreen.event.ClientModEvents;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "close", at = @At("HEAD"))
    private void closeRenderingResources(CallbackInfo ci) {
        ClientModEvents.closeRenderingResources();
    }
}
