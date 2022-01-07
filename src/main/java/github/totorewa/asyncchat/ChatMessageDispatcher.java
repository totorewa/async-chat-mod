package github.totorewa.asyncchat;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatMessageDispatcher {
    private final ExecutorService executor;

    public ChatMessageDispatcher() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void handleMessage(InGameHud hud, MessageType type, Text message, UUID sender) {
        // Not fully async because the thread will still block on the blocklist fetch request
        // but at least the thread being blocked isn't the main thread.
        executor.submit(() -> hud.addChatMessage(type, message, sender));
    }
}
