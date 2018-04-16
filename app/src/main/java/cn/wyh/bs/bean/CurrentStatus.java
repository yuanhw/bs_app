package cn.wyh.bs.bean;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/15.
 */
public class CurrentStatus implements Serializable {
    private Integer plantId;
    private Integer tillageId;
    private String name;
    private String img;
    private String status;
    private Integer day;
    private String operate;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getTillageId() {
        return tillageId;
    }

    public void setTillageId(Integer tillageId) {
        this.tillageId = tillageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
