package cn.wyh.bs.entity;

/**
 * Created by WYH on 2017/12/24.
 */

public class Farm {
    private String name;
    private String spec;
    private String consumers;
    private String distance;
    private int imgId;

    public Farm(String name, String spec, String consumers, String distance, int imgId) {
        this.name = name;
        this.spec = spec;
        this.consumers = consumers;
        this.distance = distance;
        this.imgId = imgId;
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

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
