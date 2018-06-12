package cn.wyh.bs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WYH on 2018/1/2.
 */
public class User implements Serializable {
    private Integer id;
    private String userPhone;
    private String password;
    private String userName;
    private String gender;
    private Date regTime;
    private Date loginTime;
    private String payPassword;
    private double account;
    private String touImgPath;

    public User() {
    }

    public User(String userPhone, String password, Date regTime) {
        this.userPhone = userPhone;
        this.password = password;
        this.regTime = regTime;
    }

    public User(String userPhone, String password, String userName, String gender,
                Date regTime, String payPassword, double account, String touImgPath) {
        this.userPhone = userPhone;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.regTime = regTime;
        this.payPassword = payPassword;
        this.account = account;
        this.touImgPath = touImgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getTouImgPath() {
        return touImgPath;
    }

    public void setTouImgPath(String touImgPath) {
        this.touImgPath = touImgPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userPhone='" + userPhone + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", regTime=" + regTime +
                ", loginTime=" + loginTime +
                ", payPassword='" + payPassword + '\'' +
                ", account=" + account +
                ", touImgPath='" + touImgPath + '\'' +
                '}';
    }
}
