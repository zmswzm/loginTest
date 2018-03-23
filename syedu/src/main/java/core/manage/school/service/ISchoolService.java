package core.manage.school.service;

import core.common.domain.GridParam;
import core.manage.school.domain.SchoolInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface ISchoolService {

    public void saveSchoolInfo(SchoolInfo sh);
    public List<SchoolInfo> getAllSchool(GridParam gp, SchoolInfo sci);
    public List<Map<String,Object>> getYearNo(String name, String type);
    public Integer getCount(SchoolInfo sci);
    void updateSchoolInfo(SchoolInfo schoolInfo);
    void deleteSchoolInfo(Integer id);
    void updateYearNo(String yearNo);
}
