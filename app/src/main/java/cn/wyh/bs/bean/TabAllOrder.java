package cn.wyh.bs.bean;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/8.
 */

public class TabAllOrder implements Serializable {
    private String orderId;
    private String status;
    private String spec;
    private String time;
    private String num;
    private String price;
    private String farmName;
    private String createTime;
    private String farmImg;

    public TabAllOrder() {
    }

    public TabAllOrder(String orderId, String status, String spec, String time, String num, String price,
                       String farmName, String createTime, String farmImg) {
        this.orderId = orderId;
        this.status = status;
        this.spec = spec;
        this.time = time;
        this.num = num;
        this.price = price;
        this.farmName = farmName;
        this.createTime = createTime;
        this.farmImg = farmImg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFarmImg() {
        return farmImg;
    }

    public void setFarmImg(String farmImg) {
        this.farmImg = farmImg;
    }
}
