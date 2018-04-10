package cn.wyh.bs.bean;

import cn.wyh.bs.entity.Refund;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/10.
 */
public class RefundListDto extends Refund implements Serializable {
    private String farmName;

    private String farmImg;

    public String getFarmImg() {
        return farmImg;
    }

    public void setFarmImg(String farmImg) {
        this.farmImg = farmImg;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
