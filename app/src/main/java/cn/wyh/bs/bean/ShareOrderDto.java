package cn.wyh.bs.bean;

import java.io.Serializable;

/**
 * Created by WYH on 2018/4/6.
 */
public class ShareOrderDto implements Serializable {
    private String orderId;
    private String farmName;
    private String farmAddress;
    private int blockType;
    private String blockTypeValue;
    private String spec;
    private int maxNum;
    private int num;
    private double unitPrice;
    private int maxLease;
    private int leaseTime;
    private double amount;
    private String unitLease;
    private String batchNo;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getUnitLease() {
        return unitLease;
    }

    public void setUnitLease(String unitLease) {
        this.unitLease = unitLease;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public int getBlockType() {
        return blockType;
    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }

    public String getBlockTypeValue() {
        return blockTypeValue;
    }

    public void setBlockTypeValue(String blockTypeValue) {
        this.blockTypeValue = blockTypeValue;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(int leaseTime) {
        this.leaseTime = leaseTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMaxLease() {
        return maxLease;
    }

    public void setMaxLease(int maxLease) {
        this.maxLease = maxLease;
    }

    @Override
    public String toString() {
        return "ShareOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", farmName='" + farmName + '\'' +
                ", farmAddress='" + farmAddress + '\'' +
                ", blockType=" + blockType +
                ", blockTypeValue='" + blockTypeValue + '\'' +
                ", spec='" + spec + '\'' +
                ", maxNum=" + maxNum +
                ", num=" + num +
                ", unitPrice=" + unitPrice +
                ", maxLease=" + maxLease +
                ", leaseTime=" + leaseTime +
                ", amount=" + amount +
                ", unitLease='" + unitLease + '\'' +
                ", batchNo='" + batchNo + '\'' +
                '}';
    }
}
