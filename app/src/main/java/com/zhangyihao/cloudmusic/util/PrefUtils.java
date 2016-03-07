package com.zhangyihao.cloudmusic.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Util for seeting and accessing {@link android.content.SharedPreferences} for the current Application
 * Created by zhangyihao on 2016-3-7.
 */
public class PrefUtils {
    private static final String PREF_NAMESPACE = "com.zhangyihao.cloudmusic.util.PREFS";
    // stands for first to use
    private static final String FTU_SHOWN = "ftu_shown";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAMESPACE, Context.MODE_PRIVATE);
    }

    public static void setFtuShown(Context context, boolean shown) {
        getPreferences(context).edit().putBoolean(FTU_SHOWN, shown).apply();
    }

    public static boolean isFtuShown(Context context) {
        return getPreferences(context).getBoolean(FTU_SHOWN, false);
    }
}