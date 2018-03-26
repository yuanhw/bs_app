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

    private String unitPrice;

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", fmId=" + fmId +
                ", fmTitle='" + fmTitle + '\'' +
                ", size=" + size +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", posLng=" + posLng +
                ", posLat=" + posLat +
                ", grade=" + grade +
                ", consumerNum=" + consumerNum +
                ", keyVegetable='" + keyVegetable + '\'' +
                ", fmImg='" + fmImg + '\'' +
                ", fmVideo='" + fmVideo + '\'' +
                ", businessBegin='" + businessBegin + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", checkStatus=" + checkStatus +
                ", createTime=" + createTime +
                ", spec='" + spec + '\'' +
                ", fmIntroduce='" + fmIntroduce + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }

    public String getFullAddress() {
        return this.province + this.city + this.detailedAddress;
    }
}