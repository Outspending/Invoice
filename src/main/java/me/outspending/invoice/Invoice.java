package me.outspending.invoice;

import lombok.Getter;
import me.outspending.invoice.chat.Chat;
import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.events.PlayerListeners;
import me.outspending.invoice.managers.AnnouncementManager;
import me.outspending.invoice.managers.AutoAnnouncement;
import me.outspending.invoice.runnables.BroadcastRunnable;
import me.outspending.invoice.runnables.SaveRunnable;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class Invoice extends JavaPlugin {

    @Getter public static final Map<UUID, PlayerData> PLAYER_DATA_MAP = new HashMap<>();
    @Getter public static LuckPerms luckPerms;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);

        getLuckPermsProvider();

        AnnouncementManager.registerAll(() -> List.of(
                new AutoAnnouncement("&fDid you know this server is &#ff5c33Open Source&f!",
                        "&fCheck out our github &8(&#ffad99/github&8)"),
                new AutoAnnouncement("&fDo you enjoy this server? Help us improve it by donating!",
                        "&fDonating helps us develop this server! &8(&#ffad99/buy&8)"),
                new AutoAnnouncement("&fJoin our discord to receive more information about",
                        "&#ff5c33updates, giveaways, staff changes, and more&f! &8(&#ffad99/discord&8)")
        ));

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new SaveRunnable(), 3250, 3250);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new BroadcastRunnable(), 5000, 5000);
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
