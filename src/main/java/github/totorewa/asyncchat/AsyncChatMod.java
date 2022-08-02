package github.totorewa.asyncchat;

import net.fabricmc.api.ClientModInitializer;

public class AsyncChatMod implements ClientModInitializer {
    private static ChatMessageDispatcher chatMessageDispatcher;

    @Override
    public void onInitializeClient() {
        chatMessageDispatcher = new ChatMessageDispatcher();
    }

    public static ChatMessageDispatcher getChatMessageDispatcher() {
        return chatMessageDispatcher;
    }

    public static void shutdown() {
        if (chatMessageDispatcher != null) {
            chatMessageDispatcher.dispose();
        }
    }
}
