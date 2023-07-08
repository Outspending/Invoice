package me.outspending.invoice.managers;

import me.outspending.invoice.Invoice;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public final class ChatManager implements InvoiceManager {

    public static long getChatCooldown(Player player, PersistentDataContainer container) {
        NamespacedKey key = new NamespacedKey(Invoice.getPlugin(Invoice.class), "chatCooldown");
        if (!container.has(key)) {

            return 0;
        }
        return container.get(key, PersistentDataType.LONG);
    }

    public static void setChatCooldown(Player player, PersistentDataContainer container) {
        NamespacedKey key = new NamespacedKey(Invoice.getPlugin(Invoice.class), "chatCooldown");
        container.set(key, PersistentDataType.LONG, System.currentTimeMillis() + 3000L);
    }

    public static boolean canChat(Player player, PersistentDataContainer container) {
        return getChatCooldown(player, container) <= System.currentTimeMillis();
    }

    public static long getChatCooldownLeft(Player player, PersistentDataContainer container) {
        final long cooldown = getChatCooldown(player, container) - System.currentTimeMillis();
        return +cooldown;
    }

    public static List<Player> getPingedPlayers(String msg) {
        List<Player> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (msg.contains(player.getName()))
                players.add(player);
        });
        return players;
    }

    public static String replacePings(String msg, String chatColor) {
        for (Player player : getPingedPlayers(msg)) {
            msg = msg.replaceAll(player.getName(), "&#ffd9b3&n@" + player.getName() + chatColor);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1, 2.5F);
        }
        return msg;
    }
}
