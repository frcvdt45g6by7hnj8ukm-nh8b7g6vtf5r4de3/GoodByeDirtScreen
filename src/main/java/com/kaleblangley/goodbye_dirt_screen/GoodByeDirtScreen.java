package com.kaleblangley.goodbye_dirt_screen;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Mod(GoodByeDirtScreen.MODID)
public class GoodByeDirtScreen {
    public static final String MODID = "goodbye_dirt_screen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public GoodByeDirtScreen(){
        IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
