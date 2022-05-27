package github.totorewa.asyncchat;

import github.totorewa.asyncchat.mixins.ChatMessageHandlerInvoker;
import net.minecraft.network.chat.ChatSender;
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

    public void handleMessage(ChatMessageHandlerInvoker handler, ChatType chatType,
                              PlayerChatMessage message, ChatSender sender) {
        if (disposed) return;
        // Not fully async because the thread will still block on the blocklist fetch request
        // but at least the thread being blocked isn't the main thread.
        executor.submit(() -> handler.invokeHandlePlayerChat(chatType, message, sender));
    }

    public void dispose() {
        executor.shutdown();
        disposed = true;
    }
}
