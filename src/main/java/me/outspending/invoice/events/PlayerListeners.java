package me.outspending.invoice.events;

import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.managers.ScoreboardManager;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerData data = Storage.loadPlayerData(player);
        Storage.addPlayerData(data);

        ScoreboardManager.createScoreboard(player);
        if (!player.hasPlayedBefore()) {
            e.setJoinMessage(ColorUtils.colorizeWithHex("&#ff5c33" + player.getName() + " &7&ohas joined for the first time!"));
        } else {
            e.setJoinMessage(ColorUtils.colorize("&7&o" + player.getName() + " has joined"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerData data = Storage.getPlayerData(player);
        ScoreboardManager.removeScoreboard(player);

        Storage.savePlayerData(data);
        Storage.unloadPlayerData(data);
        e.setQuitMessage(ColorUtils.colorize("&7&o" + player.getName() + " has left"));
    }
}
