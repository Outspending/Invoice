package me.outspending.invoice.managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatPingManager {

    public List<Player> getPingedPlayers(String msg) {
        List<Player> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (msg.contains(player.getName()))
                players.add(player);
        });
        return players;
    }

    public String replacePings(String msg, String chatColor) {
        for (Player player : getPingedPlayers(msg)) {
            msg = msg.replaceAll(player.getName(), "&#ffd9b3&n@" + player.getName() + chatColor);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1, 2.5F);
        }
        return msg;
    }


}
