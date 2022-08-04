package github.totorewa.asyncchat.mixins;

import github.totorewa.asyncchat.AsyncChatMod;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Redirect(
            method = "handlePlayerChat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/chat/ChatListener;handleChatMessage(Lnet/minecraft/network/chat/PlayerChatMessage;Lnet/minecraft/network/chat/ChatType$Bound;)V"
            ))
    private void dispatchMessage(ChatListener self, PlayerChatMessage playerChatMessage, ChatType.Bound bound) {
        AsyncChatMod.getChatMessageDispatcher().handleMessage(self, playerChatMessage, bound);
    }
}
