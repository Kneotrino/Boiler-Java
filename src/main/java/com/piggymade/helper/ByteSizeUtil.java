package com.piggymade.helper;

public class ByteSizeUtil {
    private ByteSizeUtil() {
    }

    public static String readableSize(long size) {
        if (size < 1024) {
            return size + " B";
        }

        final String[] units = {"KB", "MB", "GB", "TB", "PB"};
        double readable = size;
        int unitIndex = -1;

        do {
            readable /= 1024;
            unitIndex++;
        } while (readable >= 1024 && unitIndex < units.length - 1);

        return String.format("%.2f %s", readable, units[unitIndex]);
    }

}
