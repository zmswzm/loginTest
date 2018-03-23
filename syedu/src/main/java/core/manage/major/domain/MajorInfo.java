package core.manage.major.domain;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class MajorInfo extends BaseDataModel{

    private Integer id;
    private String schoolName;
    private String level;
    private String major;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
