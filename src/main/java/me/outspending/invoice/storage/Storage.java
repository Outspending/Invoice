package me.outspending.invoice.storage;

import de.leonhard.storage.Json;
import de.leonhard.storage.SimplixBuilder;
import de.leonhard.storage.internal.FileData;
import de.leonhard.storage.internal.settings.ReloadSettings;
import me.outspending.invoice.Invoice;
import me.outspending.invoice.data.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class Storage {

    private static final Json storage = SimplixBuilder
            .fromFile(new File(Invoice.getPlugin(Invoice.class).getDataFolder(), "player-data.json"))
            .setReloadSettings(ReloadSettings.INTELLIGENT)
            .createJson();
    private static final FileData FILE_DATA = storage.getFileData();

    public static void savePlayerData(PlayerData data) {
        final String key = "data." + data.getPlayer().getUniqueId() + ".";

        FILE_DATA.insert(key + "balance", data.getBalance());
        FILE_DATA.insert(key + "tokens", data.getTokens());
        FILE_DATA.insert(key + "level", data.getLevel());
        FILE_DATA.insert(key + "xp", data.getXp());
        FILE_DATA.insert(key + "xpMax", data.getXpMax());
        FILE_DATA.insert(key + "multiplier", data.getMultiplier());
        FILE_DATA.insert(key + "chatColor", data.getChatColor());
        storage.set(key + "tag", data.getTag());

    }

    public static PlayerData loadPlayerData(Player player) {
        final String key = "data." + player.getUniqueId() + ".";

        if (storage.contains("data." + player.getUniqueId())) {
            return new PlayerData(player,
                    storage.getDouble(key + "balance"),
                    storage.getDouble(key + "tokens"),
                    storage.getInt(key + "level"),
                    storage.getDouble(key + "xp"),
                    storage.getDouble(key + "xpMax"),
                    storage.getDouble(key + "multiplier"),
                    storage.getString(key + "chatColor"),
                    storage.getString(key + "tag"));
        }
        return new PlayerData(player);
    }

    public static void addPlayerData(PlayerData data) {
        Invoice.getPLAYER_DATA_MAP().put(data.getPlayer().getUniqueId(), data);
    }

    public static void resetPlayerData(Player player) {
        final String key = "data." + player.getUniqueId() + ".";

        storage.removeAll(key + "balance", key + "tokens", key + "level", key + "xp", key + "xpMax", key + "multiplier", key + "chatColor", key + "tag");
    }

    public static void unloadPlayerData(PlayerData data) {
        Invoice.getPLAYER_DATA_MAP().remove(data.getPlayer().getUniqueId());
    }

    public static void unloadPlayerData(Player player) {
        Invoice.getPLAYER_DATA_MAP().remove(player.getUniqueId());
    }

    public static @Nullable PlayerData getPlayerData(Player player) {
        return Invoice.getPLAYER_DATA_MAP().get(player.getUniqueId());
    }
}
