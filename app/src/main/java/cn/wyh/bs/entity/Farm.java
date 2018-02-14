package cn.wyh.bs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Farm implements Serializable {
    private Integer id;

    private Integer fmId;

    private String fmTitle;

    private Integer size;

    private String province;

    private String city;

    private String detailedAddress;

    private BigDecimal posLng;

    private BigDecimal posLat;

    private Integer grade;

    private Integer consumerNum;

    private String keyVegetable;

    private String fmImg;

    private String fmVideo;

    private String businessBegin;

    private String contactName;

    private String contactPhone;

    private Integer checkStatus;

    private Date createTime;

    private String spec;

    private String fmIntroduce;

    private static final long serialVersionUID = 1L;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public BigDecimal getPosLng() {
        return posLng;
    }

    public void setPosLng(BigDecimal posLng) {
        this.posLng = posLng;
    }

    public BigDecimal getPosLat() {
        return posLat;
    }

    public void setPosLat(BigDecimal posLat) {
        this.posLat = posLat;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getConsumerNum() {
        return consumerNum;
    }

    public void setConsumerNum(Integer consumerNum) {
        this.consumerNum = consumerNum;
    }

    public String getKeyVegetable() {
        return keyVegetable;
    }

    public void setKeyVegetable(String keyVegetable) {
        this.keyVegetable = keyVegetable;
    }

    public String getFmImg() {
        return fmImg;
    }

    public void setFmImg(String fmImg) {
        this.fmImg = fmImg;
    }

    public String getFmVideo() {
        return fmVideo;
    }

    public void setFmVideo(String fmVideo) {
        this.fmVideo = fmVideo;
    }

    public String getBusinessBegin() {
        return businessBegin;
    }

    public void setBusinessBegin(String businessBegin) {
        this.businessBegin = businessBegin;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getFmIntroduce() {
        return fmIntroduce;
    }

    public void setFmIntroduce(String fmIntroduce) {
        this.fmIntroduce = fmIntroduce;
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
        Farm other = (Farm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFmId() == null ? other.getFmId() == null : this.getFmId().equals(other.getFmId()))
            && (this.getFmTitle() == null ? other.getFmTitle() == null : this.getFmTitle().equals(other.getFmTitle()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getDetailedAddress() == null ? other.getDetailedAddress() == null : this.getDetailedAddress().equals(other.getDetailedAddress()))
            && (this.getPosLng() == null ? other.getPosLng() == null : this.getPosLng().equals(other.getPosLng()))
            && (this.getPosLat() == null ? other.getPosLat() == null : this.getPosLat().equals(other.getPosLat()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getConsumerNum() == null ? other.getConsumerNum() == null : this.getConsumerNum().equals(other.getConsumerNum()))
            && (this.getKeyVegetable() == null ? other.getKeyVegetable() == null : this.getKeyVegetable().equals(other.getKeyVegetable()))
            && (this.getFmImg() == null ? other.getFmImg() == null : this.getFmImg().equals(other.getFmImg()))
            && (this.getFmVideo() == null ? other.getFmVideo() == null : this.getFmVideo().equals(other.getFmVideo()))
            && (this.getBusinessBegin() == null ? other.getBusinessBegin() == null : this.getBusinessBegin().equals(other.getBusinessBegin()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getCheckStatus() == null ? other.getCheckStatus() == null : this.getCheckStatus().equals(other.getCheckStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSpec() == null ? other.getSpec() == null : this.getSpec().equals(other.getSpec()))
            && (this.getFmIntroduce() == null ? other.getFmIntroduce() == null : this.getFmIntroduce().equals(other.getFmIntroduce()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFmId() == null) ? 0 : getFmId().hashCode());
        result = prime * result + ((getFmTitle() == null) ? 0 : getFmTitle().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getDetailedAddress() == null) ? 0 : getDetailedAddress().hashCode());
        result = prime * result + ((getPosLng() == null) ? 0 : getPosLng().hashCode());
        result = prime * result + ((getPosLat() == null) ? 0 : getPosLat().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getConsumerNum() == null) ? 0 : getConsumerNum().hashCode());
        result = prime * result + ((getKeyVegetable() == null) ? 0 : getKeyVegetable().hashCode());
        result = prime * result + ((getFmImg() == null) ? 0 : getFmImg().hashCode());
        result = prime * result + ((getFmVideo() == null) ? 0 : getFmVideo().hashCode());
        result = prime * result + ((getBusinessBegin() == null) ? 0 : getBusinessBegin().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getCheckStatus() == null) ? 0 : getCheckStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSpec() == null) ? 0 : getSpec().hashCode());
        result = prime * result + ((getFmIntroduce() == null) ? 0 : getFmIntroduce().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fmId=").append(fmId);
        sb.append(", fmTitle=").append(fmTitle);
        sb.append(", size=").append(size);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", detailedAddress=").append(detailedAddress);
        sb.append(", posLng=").append(posLng);
        sb.append(", posLat=").append(posLat);
        sb.append(", grade=").append(grade);
        sb.append(", consumerNum=").append(consumerNum);
        sb.append(", keyVegetable=").append(keyVegetable);
        sb.append(", fmImg=").append(fmImg);
        sb.append(", fmVideo=").append(fmVideo);
        sb.append(", businessBegin=").append(businessBegin);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", spec=").append(spec);
        sb.append(", fmIntroduce=").append(fmIntroduce);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getFullAddress() {
        return this.province + this.city + this.detailedAddress;
    }
}