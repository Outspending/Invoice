package me.outspending.invoice.events;

import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.managers.ScoreboardManager;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.storage.StorageHandler;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    private ColorUtils COLOR_UTILS = new ColorUtils();
    private StorageHandler STORAGE = new Storage();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerData data = STORAGE.loadPlayerData(player);

        player.setResourcePack("https://download.mc-packs.net/pack/c4671ff5d8676a8fa19b1e024d81783a0a146c6f.zip", "c4671ff5d8676a8fa19b1e024d81783a0a146c6f");
        STORAGE.addPlayerData(data);

        ScoreboardManager.createScoreboard(player);
        if (!player.hasPlayedBefore()) {
            e.setJoinMessage(COLOR_UTILS.colorizeWithHex("&#ff5c33" + player.getName() + " &7&ohas joined for the first time!"));
        } else {
            e.setJoinMessage(COLOR_UTILS.colorize("&7&o" + player.getName() + " has joined"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerData data = STORAGE.getPlayerData(player);
        ScoreboardManager.removeScoreboard(player);

        STORAGE.savePlayerData(data);
        STORAGE.unloadPlayerData(data);
        e.setQuitMessage(COLOR_UTILS.colorize("&7&o" + player.getName() + " has left"));
    }
}
