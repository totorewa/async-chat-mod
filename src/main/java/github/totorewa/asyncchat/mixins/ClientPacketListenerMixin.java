package github.totorewa.asyncchat.mixins;

import github.totorewa.asyncchat.AsyncChatMod;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.ChatSender;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Redirect(
            method = "handlePlayerChat(Lnet/minecraft/network/protocol/game/ClientboundPlayerChatPacket;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;handlePlayerChat(Lnet/minecraft/network/chat/ChatType;Lnet/minecraft/network/chat/PlayerChatMessage;Lnet/minecraft/network/chat/ChatSender;)V"
            ))
    private void dispatchMessage(ClientPacketListener self, ChatType chatType,
                                 PlayerChatMessage message, ChatSender sender) {
        AsyncChatMod.getChatMessageDispatcher().handleMessage(
                (ChatMessageHandlerInvoker) self, chatType,
                message, sender);
    }
}
