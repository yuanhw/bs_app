package cn.wyh.bs.entity;


import java.io.Serializable;

/**
 * Created by WYH on 2018/3/29.
 */
public class BlockRuleShowList extends BlockRule implements Serializable {
   private String hasLease;

    public String getHasLease() {
        return hasLease;
    }

    public void setHasLease(String hasLease) {
        this.hasLease = hasLease;
    }
}
