package cn.wyh.bs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/3/8.
 */
public class BlockRule implements Serializable {
    private int id;
    private int farmId;
    private String batchNo;
    private String area;
    private String spec;
    private double unitPrice;
    private int number;
    private int type;
    private String description;
    private String leaseUnit;
    private String maxLease;
    private Date createTime;
    private Date validityBegin;
    private Date validityEnd;
    private Date updateTime;

    public BlockRule() {
    }

    public BlockRule(int farmId, String batchNo, String area, String spec,
                     double unitPrice, int number, int type, String leaseUnit,
                     String maxLease, Date createTime, Date validityBegin, Date validityEnd) {
        this.farmId = farmId;
        this.batchNo = batchNo;
        this.area = area;
        this.spec = spec;
        this.unitPrice = unitPrice;
        this.number = number;
        this.type = type;
        this.leaseUnit = leaseUnit;
        this.maxLease = maxLease;
        this.createTime = createTime;
        this.validityBegin = validityBegin;
        this.validityEnd = validityEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeaseUnit() {
        return leaseUnit;
    }

    public void setLeaseUnit(String leaseUnit) {
        this.leaseUnit = leaseUnit;
    }

    public String getMaxLease() {
        return maxLease;
    }

    public void setMaxLease(String maxLease) {
        this.maxLease = maxLease;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getValidityBegin() {
        return validityBegin;
    }

    public void setValidityBegin(Date validityBegin) {
        this.validityBegin = validityBegin;
    }

    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BlockRule{" +
                "id=" + id +
                ", farmId=" + farmId +
                ", batchNo='" + batchNo + '\'' +
                ", area='" + area + '\'' +
                ", spec='" + spec + '\'' +
                ", unitPrice=" + unitPrice +
                ", number=" + number +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", leaseUnit='" + leaseUnit + '\'' +
                ", maxLease='" + maxLease + '\'' +
                ", createTime=" + createTime +
                ", validityBegin=" + validityBegin +
                ", validityEnd=" + validityEnd +
                ", updateTime=" + updateTime +
                '}';
    }
}
