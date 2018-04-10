package cn.wyh.bs.common;

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
}
