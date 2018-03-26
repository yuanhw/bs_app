package cn.wyh.bs.common;

/**
 * Created by WYH on 2018/3/25.
 */

public class TableParam {
    private StringBuffer sb = new StringBuffer();
    public void add(String key, String value) {
        sb.append(key + "=" + value + "&");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
