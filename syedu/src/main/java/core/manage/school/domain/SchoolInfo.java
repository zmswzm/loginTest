package core.manage.school.domain;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class SchoolInfo extends BaseDataModel{

    private Integer id;
    private String schoolName;
    private String schoolNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }
}
