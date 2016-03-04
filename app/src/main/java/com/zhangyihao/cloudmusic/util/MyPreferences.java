package com.zhangyihao.cloudmusic.util;

import android.content.Context;

/**
 * Created by zhangyihao on 16/1/2.
 */
public class MyPreferences {
    //偏好文件名
    public static final String SHAREDPREFERENCES_NAME = "my_pref";
    //引导界面KEY
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";


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
