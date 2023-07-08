package me.outspending.invoice.runnables;

import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class SaveRunnable implements Runnable {

    @Override
    public void run() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("ｇ");
        Bukkit.broadcastMessage(ColorUtils.colorize(" &8▪ &fSaving all player data..."));
        Bukkit.broadcastMessage(ColorUtils.colorize("   &fThis may cause lag!"));
        Bukkit.broadcastMessage("");

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2.5F);
            PlayerData data = Storage.getPlayerData(player);
            Storage.savePlayerData(data);
        });
    }
}
