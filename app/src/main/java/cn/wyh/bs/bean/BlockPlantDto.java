package cn.wyh.bs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/4/16.
 */
public class BlockPlantDto implements Serializable {
    private Integer plantId;
    private Integer blockId;
    private String greenTitle;
    private String greenImg;
    private Date createTime;
    private Date updateTime;
    private String operator;
    private String phone;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getGreenTitle() {
        return greenTitle;
    }

    public void setGreenTitle(String greenTitle) {
        this.greenTitle = greenTitle;
    }

    public String getGreenImg() {
        return greenImg;
    }

    public void setGreenImg(String greenImg) {
        this.greenImg = greenImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
