package me.outspending.invoice.utils;

import java.text.DecimalFormat;

public class NumberUtils {

    private String[] SUFFIXES = {"", "K", "M", "B", "T", "Qa", "Qi", "Sx"};

    public String format(double number, int decimalPlaces) {
        if (number < 1000)
            return String.valueOf(number);

        double result = number;
        String suffix = "";
        int suffixIndex = 0;

        while (number >= 1000 && suffixIndex < SUFFIXES.length - 1) {
            result = result / 1000.0;
            suffix = SUFFIXES[suffixIndex];
            suffixIndex++;
        }
        return String.format("%." + decimalPlaces + "f%s", result, suffix);
    }

    public String regex(double number) {
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

    public String fix(double d) {
        return new DecimalFormat("0.##").format(d);
    }
}
