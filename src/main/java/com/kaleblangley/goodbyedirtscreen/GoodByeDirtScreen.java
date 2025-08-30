package com.kaleblangley.goodbyedirtscreen;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodByeDirtScreen implements ClientModInitializer {
    public static final String MODID = "goodbye_dirt_screen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        Config.load();
    }
}
