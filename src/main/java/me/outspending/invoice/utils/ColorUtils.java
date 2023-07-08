package me.outspending.invoice.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private static final Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");


    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> colorize(List<String> strings) {
        strings.replaceAll(ColorUtils::colorize);
        return strings;
    }

    public static String colorizeWithHex(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String color = string.substring((matcher.start() + 1), matcher.end());
            string = string.replace("&" + color, String.valueOf(ChatColor.of(color)));
            matcher = pattern.matcher(string);
        }
        return colorize(string);
    }

    public static List<String> colorizeWithHex(List<String> strings) {
        strings.replaceAll(ColorUtils::colorizeWithHex);
        return strings;
    }
}