package com.sample.androidsampleprjct.util;

import android.app.Application;

import com.sample.androidsampleprjct.util.db.DaoMaster;
import com.sample.androidsampleprjct.util.db.DaoSession;

/**
 * Created by samsung on 2016/3/23.
 */
public class BaseApplication extends Application {

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(),"DB_SAMPLE", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
