package com.kaleblangley.goodbyedirtscreen.event;

import com.kaleblangley.goodbyedirtscreen.GoodByeDirtScreen;
import com.kaleblangley.goodbyedirtscreen.util.render.FBOUtil;
import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoodByeDirtScreen.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener((ResourceManagerReloadListener) resourceManager -> closeRenderingResources());
    }

    public static void closeRenderingResources() {
        BlurUtil.closeBlur();
        FBOUtil.close();
    }
}
