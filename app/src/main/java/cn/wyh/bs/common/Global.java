package cn.wyh.bs.common;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WYH on 2018/1/2.
 *  全局变量和函数
 */

public class Global {

    //public static final String BASE_URL= "http://192.168.43.43:8080/demo"; //本地电脑
    public static final String BASE_URL= "http://106.14.5.10:8080/demo"; //云服务地址

    public static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
            .writeTimeout(20, TimeUnit.SECONDS) // 获取数据超时时间
            .readTimeout(20, TimeUnit.SECONDS); // 传递数据超时时间

    /* 手机格式验证 */
    public static boolean isPhoneLegal(String phone) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }


    /**
     *  post请求
     * @param url
     * @param jsonString
     * @return
     */
    public static JSONObject httpPost(String url, String jsonString) {
        if (jsonString == null) {
            JSONObject noObj = new JSONObject();
            noObj.put("token", "wyh");
            jsonString = noObj.toJSONString();
        }
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
            jsonObject.put("msg", "连接超时");
            Log.i("mms_http", e.getMessage() + " error");
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    /**
     * 表单传递参数，中文
     * @param url
     * @param tableString
     * @return
     */
    public static JSONObject httpPost2(String url, String tableString) {
        final JSONObject jsonObject = new JSONObject();
        try {
            OkHttpClient okHttpClient = builder.build();
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),tableString);
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
            jsonObject.put("msg", "连接超时");
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    /**
     *  简单表单数据传参，中文参数
     * @param url
     * @param tableString
     * @return
     */
    public static String httpPost3(String url, String tableString) {
        String str = null;
        try {
            OkHttpClient okHttpClient = builder.build();
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),tableString);
            Request request = new Request.Builder()
                    .url(BASE_URL + url)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            str = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }

    /**
     *  post请求 简单数据格式
     * @param url
     * @param jsonString
     * @return
     */
    public static JSONObject simpleHttpPost(String url, String jsonString) {
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
            return JSONObject.parseObject(dataStr, JSONObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /* 上传单张图片 */
    public static JSONObject uploadImg(String url, File imgFile, String phone) {
        final JSONObject jsonObject = new JSONObject();
        try {
            OkHttpClient okHttpClient = builder.build();
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), imgFile);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("phone", phone)
                    .addFormDataPart("imgFile", imgFile.getName(), fileBody)
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
            jsonObject.put("msg", "连接超时");
            Log.i("mms_Global_uploadImg", e.getMessage());
        } finally {
            return jsonObject;
        }
    }

    /* 上传单张图片 */
    public static InputStream upDownFile(String url) throws Exception {
        try {
            OkHttpClient okHttpClient = builder.build();
            Request request = new Request.Builder()
                    .url(BASE_URL + url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().byteStream();
        } catch (Exception e) {
           throw new Exception("上传异常");
        }
    }

    /**
     *  md5加密
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);
            md5 = bytesToHex(md5Byte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

    public static String convertDate(Date date, String str) {
        SimpleDateFormat format = new SimpleDateFormat(str);
        return format.format(date);
    }
}
