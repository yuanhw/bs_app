package cn.wyh.bs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/20.
 */

public class CaiOneDto implements Serializable {
    private Integer caiId;
    private Integer plantId;
    private String name;
    private Date createTime;
    private String caiImg;
    private String operate;
    private Date updateTIme;

    public Integer getCaiId() {
        return caiId;
    }

    public void setCaiId(Integer caiId) {
        this.caiId = caiId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCaiImg() {
        return caiImg;
    }

    public void setCaiImg(String caiImg) {
        this.caiImg = caiImg;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Date getUpdateTIme() {
        return updateTIme;
    }

    public void setUpdateTIme(Date updateTIme) {
        this.updateTIme = updateTIme;
    }
}
