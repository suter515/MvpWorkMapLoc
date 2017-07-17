package com.automic.roomdemo.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.automic.roomdemo.baseparts.dao.DaoMaster;
import com.automic.roomdemo.baseparts.dao.DaoSession;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.BuildConfig;
import org.xutils.x;



/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class AppContext extends Application {
    private static AppContext mInstance;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        //
        mInstance=this;
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        setupDatabase();
    }

    public static AppContext getInstance() {
        return mInstance;
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "employees.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
