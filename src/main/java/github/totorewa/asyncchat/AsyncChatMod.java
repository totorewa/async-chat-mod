package github.totorewa.asyncchat;

import net.fabricmc.api.ModInitializer;

public class AsyncChatMod implements ModInitializer {
    private static ChatMessageDispatcher chatMessageDispatcher;

    @Override
    public void onInitialize() {
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
