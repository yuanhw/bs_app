package cn.wyh.bs.storage;

import java.util.HashMap;
import java.util.Map;

/**
 *  会话保存在内存中
 * Created by WYH on 2018/1/18.
 */

public class Session {
    private static final Map<String, Object> sessionMap = new HashMap<String, Object>();

    public static Object getObject(String key) {
        if (sessionMap.containsKey(key)) {
            return sessionMap.get(key);
        }
        return null;
    }

    public static void addObject(String key, Object value) {
        sessionMap.put(key, value);
    }

    public static boolean containsKey(String key) {
        return sessionMap.containsKey(key);
    }

    public static void removeObject(String key) {
        if (containsKey(key)) {
            sessionMap.remove(key);
        }
    }
}
