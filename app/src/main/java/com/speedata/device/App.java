package com.speedata.device;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.speedata.device.gen.DaoMaster;
import com.speedata.device.gen.DaoSession;

/**
 * Created by echo on 2017/5/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }
    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }
    public static DaoSession daoSession;
    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
