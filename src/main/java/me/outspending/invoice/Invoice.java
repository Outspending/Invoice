package me.outspending.invoice;

import de.leonhard.storage.SimplixBuilder;
import de.leonhard.storage.internal.settings.ReloadSettings;
import lombok.Getter;
import me.outspending.invoice.chat.Chat;
import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.events.PlayerListeners;
import me.outspending.invoice.runnables.SaveRunnable;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Invoice extends JavaPlugin {

    @Getter public static final Map<UUID, PlayerData> PLAYER_DATA_MAP = new HashMap<>();
    @Getter public static LuckPerms luckPerms;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new SaveRunnable(), 3000, 3000);

        getLuckPermsProvider();
    }

    private LuckPerms getLuckPermsProvider() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null)
            luckPerms = provider.getProvider();
        return luckPerms;
    }

    public static User getUser(Player player) {
        return luckPerms.getPlayerAdapter(Player.class).getUser(player);
    }
}
