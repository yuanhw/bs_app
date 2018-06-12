package cn.wyh.bs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/13.
 */

public class SelfBDetail implements Serializable {
    private int id;
    private String spec;
    private Date noValid;
    private String orderId;
    private int type;
    private String status;
    private String address;

    public SelfBDetail(int id, String spec, Date noValid, String orderId, int type) {
        this.id = id;
        this.spec = spec;
        this.noValid = noValid;
        this.orderId = orderId;
        this.type = type;
    }

    public SelfBDetail(int id, String spec, Date noValid, String orderId, int type, String status) {
        this.id = id;
        this.spec = spec;
        this.noValid = noValid;
        this.orderId = orderId;
        this.type = type;
        this.status = status;
    }

    public SelfBDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Date getNoValid() {
        return noValid;
    }

    public void setNoValid(Date noValid) {
        this.noValid = noValid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
