package org.teacon.xibao.mixin;

import org.teacon.xibao.CommonClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class XibaoMixin {

    @Shadow
    protected Minecraft minecraft;


    @Inject(at = @At("TAIL"), method = "renderBackground(Lcom/mojang/blaze3d/vertex/PoseStack;I)V")
    private void init2(CallbackInfo info) {
        Screen screen = (Screen)(Object)this;
        CommonClass.onRender(screen);
    }
}