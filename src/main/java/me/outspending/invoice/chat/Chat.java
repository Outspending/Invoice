package me.outspending.invoice.chat;

import me.outspending.invoice.Invoice;
import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.managers.ChatManager;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
//        PersistentDataContainer container = player.getPersistentDataContainer();
//        if (!CHAT_MANAGER.canChat(player, container)) {
//            Timespan timespan = new Timespan(CHAT_MANAGER.getChatCooldownLeft(player, container));
//            player.sendMessage(COLOR_UTILS.colorizeWithHex(String.format("ｆ &8→ &fYou must wait &#ffad99%s&fbefore chatting again!", timespan.getFormattedTime())));
//            e.setCancelled(true);
//            return;
//        }

        String message = e.getMessage();
        String prefix = Invoice.getUser(player).getCachedData().getMetaData().getPrefix();
        PlayerData data = Storage.getPlayerData(player);

        message = ChatManager.replacePings(message, data.getChatColor());
        e.setFormat(ColorUtils.colorizeWithHex(String.format(" &8[&#ffad99Lvl. %s&8] &f%s%s%s &8» %s%s", data.getLevel(), prefix, player.getDisplayName(), data.getTag(), data.getChatColor(), message)));
        //CHAT_MANAGER.setChatCooldown(player, container);
    }
}
