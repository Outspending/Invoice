package me.outspending.invoice.chat;

import me.outspending.invoice.Invoice;
import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.managers.ChatPingManager;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements Listener {

    private static final ChatPingManager PING_MANAGER = new ChatPingManager();
    private static final ColorUtils COLOR_UTILS = new ColorUtils();

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String prefix = Invoice.getUser(player).getCachedData().getMetaData().getPrefix();
        PlayerData data = new Storage().getPlayerData(player);

        message = PING_MANAGER.replacePings(message, data.getChatColor());
        e.setFormat(COLOR_UTILS.colorizeWithHex(" &8▪ &f" + prefix + player.getDisplayName() + data.getTag() + " &8» " + data.getChatColor() + message));
    }
}
