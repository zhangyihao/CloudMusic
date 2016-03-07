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

    //偏好文件名
    public static final String SHAREDPREFERENCES_NAME = "my_pref";
    //引导界面KEY
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAMESPACE, Context.MODE_PRIVATE);
    }

    public static void setFtuShown(Context context, boolean shown) {
        getPreferences(context).edit().putBoolean(FTU_SHOWN, shown).apply();
    }

    public static boolean isFtuShown(Context context) {
        return getPreferences(context).getBoolean(FTU_SHOWN, false);
    }

    public static boolean activityIsGuid(Context context, String className) {
        if (context == null || className == null || "".equals(className)) {
            return false;
        }
        String[] classNames = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(KEY_GUIDE_ACTIVITY, "").split("\\|");
        for(String cn : classNames) {
            if(className.equals(cn)) {
                return true;
            }
        }
        return false;
    }

    public static void setIsGuid(Context context, String className) {
        if (context == null || className == null || "".equals(className)) {
            return;
        }
        String classNames = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(KEY_GUIDE_ACTIVITY, "");
        StringBuilder sb = new StringBuilder(classNames).append("|").append(className);//添加值
        context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)//保存修改后的值
                .edit()
                .putString(KEY_GUIDE_ACTIVITY, sb.toString())
                .commit();

    }
}