package me.outspending.invoice.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class PlayerData {

    @Getter private final Player player;
    @Getter @Setter private double balance = 0;
    @Getter @Setter private double tokens = 0;
    @Getter @Setter private int level = 0;
    @Getter @Setter private double xp = 0;
    @Getter @Setter private double xpMax = 250;
    @Getter @Setter private double multiplier = 1;
    @Getter @Setter private String chatColor = "&7";
    @Getter @Setter private String tag = "";

    public PlayerData(Player player) {
        this.player = player;
    }

    public PlayerData(Player player, double balance, double tokens, int level, double xp, double xpMax, double multiplier, String chatColor, String tag) {
        this.player = player;
        this.balance = balance;
        this.tokens = tokens;
        this.level = level;
        this.xp = xp;
        this.xpMax = xpMax;
        this.multiplier = multiplier;
        this.chatColor = chatColor;
        this.tag = tag;
    }

    public double addBalance(double amount) {
        return balance += amount;
    }

    public double removeBalance(double amount) {
        return balance -= amount;
    }

    public double addTokens(double amount) {
        return tokens += amount;
    }

    public double removeTokens(double amount) {
        return tokens -= amount;
    }

    public int addLevel(int amount) {
        return level += amount;
    }

    public int removeLevel(int amount) {
        return level -= amount;
    }

    public double addXP(double amount) {
        return xp += amount;
    }

    public double removeXP(double amount) {
        return xp -= amount;
    }

    public double addXPMax(double amount) {
        return xpMax += amount;
    }

    public double removeXPMax(double amount) {
        return xpMax -= amount;
    }

    public boolean canLevelup() {
        return xp >= xpMax;
    }

    public double addMultiplier(double amount) {
        return multiplier += amount;
    }

    public double removeMultiplier(double amount) {
        return multiplier -= amount;
    }
}
