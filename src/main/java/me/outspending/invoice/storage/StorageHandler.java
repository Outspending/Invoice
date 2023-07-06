package me.outspending.invoice.storage;

import me.outspending.invoice.data.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public interface StorageHandler {

    /**
     * Saves the player's data to the database.w
     *
     * @param data
     */
    @Contract("null -> fail")
    void savePlayerData(PlayerData data);

    /**
     * Loads the player data if it hasen't been loaded yet
     *
     * @param player
     * @return
     */
    @Contract("null -> null")
    @Nullable PlayerData loadPlayerData(Player player);

    /**
     * unloads the playerdata from the map
     *
     * @param data
     */
    @Contract("null -> fail")
    void unloadPlayerData(PlayerData data);

    /**
     * unloads the playerdata from the map
     *
     * @param player
     */
    @Contract("null -> fail")
    void unloadPlayerData(Player player);

    /**
     * Adds the playerdata to the map
     *
     * @param data
     */
    @Contract("null -> fail")
    void addPlayerData(PlayerData data);

    /**
     * Removes the player data from the database
     *
     * @param player
     */
    @Contract("null -> fail")
    void resetPlayerData(Player player);

    /**
     * Grabs the player data from the map if it contains it
     *
     * @param player
     * @return
     */
    @Contract("null -> null")
    @Nullable PlayerData getPlayerData(Player player);
}
