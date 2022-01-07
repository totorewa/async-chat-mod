package github.totorewa.asyncchat.mixins;

import github.totorewa.asyncchat.AsyncChatMod;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;


@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Redirect(
            method = "onGameMessage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;addChatMessage(Lnet/minecraft/network/MessageType;Lnet/minecraft/text/Text;Ljava/util/UUID;)V"
            ))
    private void dispatchMessage(InGameHud hud, MessageType type, Text message, UUID sender) {
        AsyncChatMod.getChatMessageDispatcher().handleMessage(hud, type, message, sender);
    }
}
