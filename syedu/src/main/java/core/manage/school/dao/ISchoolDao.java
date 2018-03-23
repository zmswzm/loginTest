package core.manage.school.dao;

import core.common.domain.GridParam;
import core.manage.school.domain.SchoolInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface ISchoolDao {

    void saveSchoolInfo(SchoolInfo schoolInfo);

    void updateSchoolInfo(SchoolInfo schoolInfo);
    void updateYearNo(String yearNo);
    void deleteSchoolInfo(Integer id);
    String getSchoolNo(Integer id);
    Integer getCount(SchoolInfo schoolInfo);
    public List<Map<String,Object>> getYearNo(String name, String type);
    List<SchoolInfo> getAllSchool(GridParam gp, SchoolInfo schoolInfo);
}
