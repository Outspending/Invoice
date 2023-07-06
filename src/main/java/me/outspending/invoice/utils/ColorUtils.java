package me.outspending.invoice.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private final Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public List<String> colorize(List<String> strings) {
        for (int i = 0; i < strings.size(); i++)
            strings.set(i, colorize(strings.get(i)));

        return strings;
    }

    public String colorizeWithHex(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String color = string.substring((matcher.start() + 1), matcher.end());
            string = string.replace("&" + color, String.valueOf(ChatColor.of(color)));
            matcher = pattern.matcher(string);
        }
        return colorize(string);
    }

    public List<String> colorizeWithHex(List<String> strings) {
        for (int i = 0; i < strings.size(); i++)
            strings.set(i, colorizeWithHex(strings.get(i)));

        return strings;
    }
}