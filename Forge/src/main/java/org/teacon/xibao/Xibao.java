package org.teacon.xibao;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Mod("xibao")
public class Xibao {
    public Xibao() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "ANY", (a, b) -> b));
    }

    @Mod.EventBusSubscriber(modid = "xibao", value = Dist.CLIENT)
    public static final class XibaoImpl {
        @SubscribeEvent
        public static void on(ScreenEvent.Init.Post event) {
            CommonClass.onItemTooltip(event.getScreen(), event::addListener);
        }

        @SubscribeEvent
        public static void onRender(ScreenEvent.BackgroundRendered event) {
            CommonClass.onRender(event.getScreen());
        }
    }
}