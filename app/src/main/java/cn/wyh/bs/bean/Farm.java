package cn.wyh.bs.bean;

import java.io.Serializable;

/**
 * Created by WYH on 2017/12/24.
 */

public class Farm implements Serializable{

    private int id; //农场编号
    private String name; //农场名称
    private String spec; //农场规格
    private String consumers; //当前认养人数
    private String distance; //距离
    private String farmImg; // 缩略图路径

    public Farm() {
    }

    public Farm(int id, String name, String spec, String consumers, String distance, String farmImg) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.consumers = consumers;
        this.distance = distance;
        this.farmImg = farmImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getConsumers() {
        return consumers;
    }

    public void setConsumers(String consumers) {
        this.consumers = consumers;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmImg() {
        return farmImg;
    }

    public void setFarmImg(String farmImg) {
        this.farmImg = farmImg;
    }
}
