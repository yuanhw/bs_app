package cn.wyh.bs;

import android.app.Application;

import com.tencent.tac.TACApplication;
import com.tencent.tac.messaging.TACMessagingService;

/**
 * Created by WYH on 2018/4/21.
 */

public class InfoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TACApplication.configure(this);
    }
}
