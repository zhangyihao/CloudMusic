package com.zhangyihao.cloudmusic.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyihao on 16/1/18.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG =  "Exception";

//  系统默认的UncaughtExceptionHandler
    private Thread.UncaughtExceptionHandler mExceptionHandler;
    private static CrashHandler crashHandler = new CrashHandler();
    private Context mContext;
//    用来存储错误信息
    private Map<String, String> infos = new HashMap<String, String>();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init(Context context) {
        this.mContext = context;
        //获取系统默认的UncaughtException
        this.mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置当前实例为系统默认UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mExceptionHandler != null) {
            this.mExceptionHandler.uncaughtException(thread,ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(Throwable ex) {
        if(ex == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序出错了!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectionDeviceInfo(this.mContext);
        saveCrashInfo(ex);
        return true;
    }

    private void saveCrashInfo(Throwable ex) {

    }

    /**
     * 收集设备信息
     * @param context
     */
    private void collectionDeviceInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if(packageInfo!=null) {
                String versionNmae = packageInfo.versionName==null?"null":packageInfo.versionName;
                String versionCode = packageInfo.versionCode+"";
                infos.put("versionName",versionNmae);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
            }
        }
    }
}
