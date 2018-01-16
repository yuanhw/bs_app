package cn.wyh.bs.common;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WYH on 2018/1/2.
 */

public class Global {

    public static final String BASE_URL= "http://192.168.43.43:8080/demo";

    public static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS) //连接超时时间
            .writeTimeout(20, TimeUnit.SECONDS) // 获取数据超时时间
            .readTimeout(20, TimeUnit.SECONDS); // 传递数据超时时间

    /* 手机格式验证 */
    public static boolean isPhoneLegal(String phone) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /* ajaxPost请求 */
    public static JSONObject httpPost(String url, String jsonString) {
        final JSONObject jsonObject = new JSONObject();
        try {
            OkHttpClient okHttpClient = builder.build();
            RequestBody requestBody = new FormBody.Builder()
                    .add("jsonStr", jsonString)
                    .build();
            Request request = new Request.Builder()
                    .url(BASE_URL + url)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String dataStr = response.body().string();
            jsonObject.put("code", 1);
            jsonObject.put("respStr", dataStr);
            jsonObject.put("msg", "success");
        } catch (Exception e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "服务器错误");
            Log.i("mms_Global_httpPost", e.getMessage());
        } finally {
            return jsonObject;
        }
    }
}
