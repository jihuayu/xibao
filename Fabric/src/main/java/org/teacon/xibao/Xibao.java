package org.teacon.xibao;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;


public class Xibao implements ModInitializer {

    @Override
    public void onInitialize() {

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight)->{
            CommonClass.onItemTooltip(screen, Screens.getButtons(screen)::add);
        });
    }
}
