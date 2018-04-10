package cn.wyh.bs.entity;

import java.io.Serializable;
import java.util.Date;

public class Refund implements Serializable {
    private Integer id;

    private String orderId;

    private Double orderAmt;

    private String refundYs;

    private Double refundAmt;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String refundSm;

    private Integer userId;

    private Integer farmManagerId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Double orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getRefundYs() {
        return refundYs;
    }

    public void setRefundYs(String refundYs) {
        this.refundYs = refundYs;
    }

    public Double getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(Double refundAmt) {
        this.refundAmt = refundAmt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRefundSm() {
        return refundSm;
    }

    public void setRefundSm(String refundSm) {
        this.refundSm = refundSm;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(Integer farmManagerId) {
        this.farmManagerId = farmManagerId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Refund other = (Refund) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderAmt() == null ? other.getOrderAmt() == null : this.getOrderAmt().equals(other.getOrderAmt()))
            && (this.getRefundYs() == null ? other.getRefundYs() == null : this.getRefundYs().equals(other.getRefundYs()))
            && (this.getRefundAmt() == null ? other.getRefundAmt() == null : this.getRefundAmt().equals(other.getRefundAmt()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRefundSm() == null ? other.getRefundSm() == null : this.getRefundSm().equals(other.getRefundSm()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFarmManagerId() == null ? other.getFarmManagerId() == null : this.getFarmManagerId().equals(other.getFarmManagerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderAmt() == null) ? 0 : getOrderAmt().hashCode());
        result = prime * result + ((getRefundYs() == null) ? 0 : getRefundYs().hashCode());
        result = prime * result + ((getRefundAmt() == null) ? 0 : getRefundAmt().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRefundSm() == null) ? 0 : getRefundSm().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFarmManagerId() == null) ? 0 : getFarmManagerId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderAmt=").append(orderAmt);
        sb.append(", refundYs=").append(refundYs);
        sb.append(", refundAmt=").append(refundAmt);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", refundSm=").append(refundSm);
        sb.append(", userId=").append(userId);
        sb.append(", farmManagerId=").append(farmManagerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}