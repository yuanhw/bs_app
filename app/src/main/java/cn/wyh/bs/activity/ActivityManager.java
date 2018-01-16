package cn.wyh.bs.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYH on 2018/1/16.
 */

public class ActivityManager {
    public static List<Activity> list = new ArrayList<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void finashAll() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
