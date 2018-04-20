package cn.wyh.bs.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by WYH on 2018/3/25.
 */

public class TableParam {
    private Map<String, String> map = new HashMap<>();

    public void add(String key, String value) {
        map.put(key, value);
    }

    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> tmp : set) {
            sb.append(tmp.getKey() + "=" + tmp.getValue() + "&");
        }
        return sb.toString();
    }

    public String get(String key) {
        return this.map.get(key);
    }

    public String toString(Object obj) throws IllegalAccessException {
        Class<? extends Object> classType = obj.getClass();
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(obj);
            if (value != null) {
                map.put(key, String.valueOf(value));
            }
        }
        return toString();
    }
}
