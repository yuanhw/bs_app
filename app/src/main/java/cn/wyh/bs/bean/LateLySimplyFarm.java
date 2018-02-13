package cn.wyh.bs.bean;

import java.io.Serializable;

/**
 * Created by WYH on 2018/2/13.
 */
public class LateLySimplyFarm implements Serializable {
    private Integer id;
    private Integer fmId;
    private String fmTitle;
    private Integer consumerNum;
    private String distance;
    private String fmImg;
    private String spec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFmId() {
        return fmId;
    }

    public void setFmId(Integer fmId) {
        this.fmId = fmId;
    }

    public String getFmTitle() {
        return fmTitle;
    }

    public void setFmTitle(String fmTitle) {
        this.fmTitle = fmTitle;
    }

    public Integer getConsumerNum() {
        return consumerNum;
    }

    public void setConsumerNum(Integer consumerNum) {
        this.consumerNum = consumerNum;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFmImg() {
        return fmImg;
    }

    public void setFmImg(String fmImg) {
        this.fmImg = fmImg;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "LateLySimplyFarm{" +
                "id=" + id +
                ", fmId=" + fmId +
                ", fmTitle='" + fmTitle + '\'' +
                ", consumerNum=" + consumerNum +
                ", distance='" + distance + '\'' +
                ", fmImg='" + fmImg + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
