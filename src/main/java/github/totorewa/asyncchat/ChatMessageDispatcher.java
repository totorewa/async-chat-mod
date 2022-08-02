package github.totorewa.asyncchat;

import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatMessageDispatcher {
    private final ExecutorService executor;
    private boolean disposed;

    public ChatMessageDispatcher() {
        executor = Executors.newSingleThreadExecutor((r) -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void handleMessage(ChatListener handler, PlayerChatMessage playerChatMessage, ChatType.Bound bound) {
        if (disposed) return;
        // Not fully async because the thread will still block on the blocklist fetch request
        // but at least the thread being blocked isn't the main thread.
        executor.submit(() -> handler.handleChatMessage(playerChatMessage, bound));
    }

    public void dispose() {
        executor.shutdown();
        disposed = true;
    }
}
