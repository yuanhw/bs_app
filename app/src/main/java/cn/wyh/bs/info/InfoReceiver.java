package cn.wyh.bs.info;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.tencent.tac.messaging.TACMessagingReceiver;
import com.tencent.tac.messaging.TACMessagingText;
import com.tencent.tac.messaging.TACMessagingToken;
import com.tencent.tac.messaging.TACNotification;

import cn.wyh.bs.activity.plant.PlantActivity;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/21.
 */
public class InfoReceiver extends TACMessagingReceiver {

    // 启动 Messaging 服务后，会自动向 Messaging 后台注册，注册完成后会回调此接口。
    @Override
    public void onRegisterResult(Context context, int errorCode, TACMessagingToken token) {
        final int id = KeyValueTable.getObject("user", User.class).getId();
        final String deviceToken = token.getTokenString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Global.httpPost3("/info/app/updateUserToken.do", "userId=" + id + "&token=" + deviceToken);
            }
        }).start();
    }

    // 反注册后回调此接口。
    @Override
    public void onUnregisterResult(Context context, int code) {
        Log.i("messaging", "MyReceiver::onUnregisterResult : code is " + code);
    }

    // 收到通知栏消息后回调此接口。
    @Override
    public void onNotificationShowed(Context context, TACNotification notification, int notificationId) {
        Log.i("messaging", "MyReceiver::OnNotificationShowed : notification is " + notification + " notification id is " + notificationId);
    }

    // 用户处理通知栏消息后回调此接口，如用户点击或滑动取消通知。
    @Override
    public void onNotificationClicked(Context context, TACNotification notification, long actionType) {
        Log.i("messaging", "MyReceiver::onNotificationClicked : notification is " + notification + " actionType is " + actionType);
    }

    // 收到应用内消息后回调此接口。
    @Override
    public void onTextMessage(Context context, TACMessagingText message) {
        Log.i("messaging", "MyReceiver::OnTextMessage : message is " + message);
    }

}
