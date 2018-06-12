package cn.wyh.bs.entity;

import java.io.Serializable;

/**
 * Created by WYH on 2018/1/21.
 */
public class City implements Serializable{
    private int code;
    private int preCode;
    private String value;
    private String label;

    public City() {
    }

    public City(int preCode, String value, String label) {
        this.preCode = preCode;
        this.value = value;
        this.label = label;
    }

    public City(int code, int preCode, String value, String label) {
        this.code = code;
        this.preCode = preCode;
        this.value = value;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPreCode() {
        return preCode;
    }

    public void setPreCode(int preCode) {
        this.preCode = preCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.label = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
