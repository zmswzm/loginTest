package core.manage.user.domain;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class CuserInfo extends BaseDataModel{

    private String id;
    private String cname;
    private String cphone;
    private String creferee;
    private String cmajor;

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

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCreferee() {
        return creferee;
    }

    public void setCreferee(String creferee) {
        this.creferee = creferee;
    }

    public String getCmajor() {
        return cmajor;
    }

    public void setCmajor(String cmajor) {
        this.cmajor = cmajor;
    }
}
