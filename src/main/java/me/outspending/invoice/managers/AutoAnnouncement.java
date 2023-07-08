package me.outspending.invoice.managers;

import lombok.Getter;
import me.outspending.invoice.utils.ColorUtils;
import org.bukkit.Bukkit;

public class AutoAnnouncement {

    @Getter private final String firstLine;
    @Getter private final String secondLine;

    public AutoAnnouncement(String firstLine, String secondLine) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
    }

    public void broadcast() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("ｇ");
        Bukkit.broadcastMessage(ColorUtils.colorizeWithHex(" &8▪ &f" + firstLine));
        Bukkit.broadcastMessage(ColorUtils.colorizeWithHex("   &f" + secondLine));
        Bukkit.broadcastMessage("");
    }
}
