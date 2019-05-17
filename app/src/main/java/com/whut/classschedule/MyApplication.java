package com.whut.classschedule;

import android.app.Activity;
import android.app.Application;

import org.litepal.LitePalApplication;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/17.
 */

public class MyApplication extends LitePalApplication {
    private static MyApplication mInstance;
    private static List<Activity> activityList = new LinkedList<Activity>();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public void exitAllActivity(){
        for(Activity activity:activityList) {
            activity.finish();
        }
    }
}
