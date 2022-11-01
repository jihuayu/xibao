package org.teacon.xibao;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.teacon.xibao.platform.Services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Consumer;

public class CommonClass {


    public static boolean checkShowXibao(){
        var gameDir = Services.PLATFORM.getGameDir();
        return !Files.exists(gameDir.resolve(".xibao_stop"));
    }

    public static void onItemTooltip(Screen screen, Consumer<AbstractWidget> addButton) {

        if (checkShowXibao() && screen instanceof DisconnectedScreen s) {
            var disableXibao = new Button(s.width / 2 - 75, s.height - 30, 150, 20, Component.translatable("xibao.do_not_show_again"), btn -> {
                try {
                    Files.writeString(Services.PLATFORM.getGameDir().resolve(".xibao_stop"), "Remove this file to show Xibao again", StandardCharsets.UTF_8);
                } catch (IOException e) {
                    return;
                }
                btn.active = false;
            });
            addButton.accept(disableXibao);
        }
    }

    private static final ResourceLocation LOCATION = new ResourceLocation("xibao", "textures/xibao.png");

    public static void onRender(Screen screen) {
        if (checkShowXibao() && screen instanceof DisconnectedScreen s) {
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            RenderSystem.setShaderTexture(0, LOCATION);
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(0.0D, s.height, 0.0D).uv(0F, 1F).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(s.width, s.height, 0.0D).uv(1F, 1F).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(s.width, 0.0D, 0.0D).uv(1F, 0F).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0F, 0F).color(255, 255, 255, 255).endVertex();
            tesselator.end();
        }
    }
}