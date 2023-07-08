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

public final class ScoreboardManager implements InvoiceManager {

    @Getter protected static Map<UUID, FastBoard> boardMap = new HashMap<>();

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
        PlayerData data = Storage.getPlayerData(player);

        final double xp = data.getXp();
        final double xpMax = data.getXpMax();
        board.updateTitle(ColorUtils.colorizeWithHex("&#ff5c33&lɪɴᴠᴏɪᴄᴇ &8● &f" + Bukkit.getOnlinePlayers().size()));
        board.updateLines(
                "",
                ColorUtils.colorizeWithHex("&#ff5c33&l" + player.getName()),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7ʙᴀʟᴀɴᴄᴇ: &2&l$&a" + NumberUtils.format(data.getBalance())),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7ᴛᴏᴋᴇɴꜱ: &fｐ&e" + NumberUtils.format(data.getTokens())),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7ɢᴇɴꜱ: &f0/25"),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7ᴍᴜʟᴛɪᴘʟɪᴇʀ: &fx" + NumberUtils.format(data.getMultiplier())),
                "",
                ColorUtils.colorizeWithHex("&#ff5c33&lLEVELING"),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7xᴘ: &f" + NumberUtils.format(xp) + "/" + NumberUtils.format(xpMax) + " &7&o(" + NumberUtils.fix(xp / xpMax * 100) + "%)"),
                ColorUtils.colorizeWithHex("&#ff5c33&l| &7ʟᴇᴠᴇʟ: &f" + data.getLevel()),
                "",
                ColorUtils.colorizeWithHex("&#ff5c33&nInvoice&f.Minehut.gg")
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
