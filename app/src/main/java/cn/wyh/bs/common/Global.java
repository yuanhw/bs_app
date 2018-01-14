package cn.wyh.bs.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WYH on 2018/1/2.
 */

public class Global {
    public static final String BASE_URL= "http://192.168.43.43:8080/demo";

    public static boolean isPhoneLegal(String phone) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
