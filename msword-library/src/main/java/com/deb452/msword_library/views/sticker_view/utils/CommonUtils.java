package com.deb452.msword_library.views.sticker_view.utils;

public class CommonUtils {
    private static String TAG = CommonUtils.class.getSimpleName();
    private static long lastClickTime;
    private static int lastClickViewId;
    private static final int KEY_PREVENT_TS = -20000;

    public static long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }
}
