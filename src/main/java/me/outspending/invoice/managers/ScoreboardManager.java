package me.outspending.invoice.managers;

import fr.mrmicky.fastboard.FastBoard;
import lombok.Getter;
import me.outspending.invoice.Invoice;
import me.outspending.invoice.data.PlayerData;
import me.outspending.invoice.storage.Storage;
import me.outspending.invoice.utils.ColorUtils;
import me.outspending.invoice.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {

    @Getter protected static Map<UUID, FastBoard> boardMap = new HashMap<>();

    private static final ColorUtils COLOR_UTILS = new ColorUtils();
    private static final NumberUtils NUMBER_UTILS = new NumberUtils();

    static {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (FastBoard board : boardMap.values())
                    updateScoreboard(board);
            }
        }.runTaskTimerAsynchronously(Invoice.getPlugin(Invoice.class), 0, 20);
    }

    private static void updateScoreboard(FastBoard board) {
        Player player = board.getPlayer();
        PlayerData data = new Storage().getPlayerData(player);

        final double xp = data.getXp();
        final double xpMax = data.getXpMax();
        board.updateTitle(COLOR_UTILS.colorizeWithHex("&#ff5c33&lɪɴᴠᴏɪᴄᴇ &8● &f" + Bukkit.getOnlinePlayers().size()));
        board.updateLines(
                "",
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l" + player.getName()),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7ʙᴀʟᴀɴᴄᴇ: &2&l$&a" + NUMBER_UTILS.format(data.getBalance())),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7ᴛᴏᴋᴇɴꜱ: &fｐ&e" + NUMBER_UTILS.format(data.getTokens())),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7ɢᴇɴꜱ: &f0/25"),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7ᴍᴜʟᴛɪᴘʟɪᴇʀ: &fx" + NUMBER_UTILS.format(data.getMultiplier())),
                "",
                COLOR_UTILS.colorizeWithHex("&#ff5c33&lLEVELING"),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7xᴘ: &f" + NUMBER_UTILS.format(xp) + "/" + NUMBER_UTILS.format(xpMax) + " &7&o(" + NUMBER_UTILS.fix(xp / xpMax * 100) + "%)"),
                COLOR_UTILS.colorizeWithHex("&#ff5c33&l| &7ʟᴇᴠᴇʟ: &f" + data.getLevel()),
                "",
                COLOR_UTILS.colorizeWithHex("&#ff5c33&nInvoice&f.Minehut.gg")
        );
    }
    public static FastBoard createScoreboard(Player player) {
        FastBoard board = new FastBoard(player);
        updateScoreboard(board);
        boardMap.put(player.getUniqueId(), board);
        return board;
    }

    public static void removeScoreboard(Player player) {
        FastBoard board = boardMap.get(player.getUniqueId());
        boardMap.remove(player.getUniqueId());
        if (!board.isDeleted())
            board.delete();
    }
}
