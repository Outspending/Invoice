package me.outspending.invoice.runnables;

import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.storage.StorageHandler;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class SaveRunnable implements Runnable {

    private static final ColorUtils COLOR_UTILS = new ColorUtils();
    private static final StorageHandler STORAGE = new Storage();

    @Override
    public void run() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("ｇ");
        Bukkit.broadcastMessage(COLOR_UTILS.colorize(" &8▪ &fSaving all player data..."));
        Bukkit.broadcastMessage(COLOR_UTILS.colorize("   &fThis may cause lag!"));
        Bukkit.broadcastMessage("");

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2.5F);
            PlayerData data = STORAGE.getPlayerData(player);
            STORAGE.savePlayerData(data);
        });
    }
}
