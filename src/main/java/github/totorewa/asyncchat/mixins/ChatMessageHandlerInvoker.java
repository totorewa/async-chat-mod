package github.totorewa.asyncchat.mixins;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.ChatSender;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ClientPacketListener.class)
public interface ChatMessageHandlerInvoker {
    @Invoker("handlePlayerChat")
    public void invokeHandlePlayerChat(ChatType chatType, PlayerChatMessage message, ChatSender sender);
}
