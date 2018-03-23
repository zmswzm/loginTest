package core.business.register.domain;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class BalanceInfo extends BaseDataModel{

    private String uid;
    private String name;
    private String phone;
    private String yearNo;
    private String status;
    private Integer stuNo;
    private Float fee;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getYearNo() {
        return yearNo;
    }

    public void setYearNo(String yearNo) {
        this.yearNo = yearNo;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }
}
