package core.business.register.domain;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class CstudentInfo extends BaseDataModel{

    private String id;
    private String cname;
    private String cuphone;
    private String cyearNo;
    private String cstatus;

    public String getCyearNo() {
        return cyearNo;
    }

    public void setCyearNo(String cyearNo) {
        this.cyearNo = cyearNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCuphone() {
        return cuphone;
    }

    public void setCuphone(String cuphone) {
        this.cuphone = cuphone;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
}
