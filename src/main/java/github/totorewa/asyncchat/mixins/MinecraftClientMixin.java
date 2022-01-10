package github.totorewa.asyncchat.mixins;

import github.totorewa.asyncchat.AsyncChatMod;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "close", at = @At("HEAD"))
    private void disposeChatMessageDispatcher(CallbackInfo ci) {
        AsyncChatMod.shutdown();
    }
}
