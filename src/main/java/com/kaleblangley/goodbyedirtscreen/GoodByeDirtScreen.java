package com.kaleblangley.goodbyedirtscreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import com.kaleblangley.goodbyedirtscreen.util.shader.BlurUtil;
import com.kaleblangley.goodbyedirtscreen.util.render.FBOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodByeDirtScreen implements ClientModInitializer {
    public static final String MODID = "goodbye_dirt_screen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        Config.load();
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override public ResourceLocation getFabricId() {
                return new ResourceLocation(MODID, "background_effects");
            }
            @Override public void onResourceManagerReload(ResourceManager manager) {
                BlurUtil.closeBlur();
                FBOUtil.close();
            }
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            BlurUtil.closeBlur();
            FBOUtil.close();
        });
    }
}
