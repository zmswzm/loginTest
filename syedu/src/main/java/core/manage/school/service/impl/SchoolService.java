package core.manage.school.service.impl;

import core.base.service.BaseService;
import core.common.domain.GridParam;
import core.manage.school.dao.ISchoolDao;
import core.manage.school.domain.SchoolInfo;
import core.manage.school.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("schoolService")
public class SchoolService extends BaseService implements ISchoolService {

    @Autowired
    @Qualifier("schoolDao")
    private ISchoolDao schoolDao;

    public void saveSchoolInfo(SchoolInfo sh) {
        schoolDao.saveSchoolInfo(sh);
    }

    @Override
    public List<SchoolInfo> getAllSchool(GridParam gp, SchoolInfo sci) {
        return schoolDao.getAllSchool(gp,sci);
    }

    public List<Map<String,Object>> getYearNo(String name, String type){
        return schoolDao.getYearNo(name, type);
    }
    public void updateYearNo(String yearNo){
        schoolDao.updateYearNo(yearNo);
    }
    @Override
    public Integer getCount(SchoolInfo sci) {
        return schoolDao.getCount(sci);
    }

    @Override
    public void updateSchoolInfo(SchoolInfo schoolInfo) {
        schoolDao.updateSchoolInfo(schoolInfo);
    }

    @Override
    public void deleteSchoolInfo(Integer id) {
        schoolDao.deleteSchoolInfo(id);
    }
}
