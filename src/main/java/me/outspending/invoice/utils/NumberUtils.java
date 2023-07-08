package me.outspending.invoice.utils;

import java.text.DecimalFormat;

public class NumberUtils {

    private static DecimalFormat FIX_FORMAT = new DecimalFormat("0.##");
    private static String[] suffix = {"", "K", "M", "B", "T", "Qa", "Qi", "Sx"};

    public static String format(double number) {
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while(r.length() > 4 || r.matches("[0-9]+\\.[a-z]")){
            r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
        }
        return r;
    }

    public static String regex(double number) {
        StringBuilder sb = new StringBuilder();
        String numberString = String.valueOf(number);
        int length = numberString.length();

        for (int i = length - 1, count = 0; i >= 0; i--, count++) {
            if (count != 0 && count % 3 == 0) {
                sb.insert(0, ',');
            }
            sb.insert(0, numberString.charAt(i));
        }

        return sb.toString();
    }

    public static String fix(double d) {
        return FIX_FORMAT.format(d);
    }
}
