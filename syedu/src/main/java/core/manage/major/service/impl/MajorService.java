package core.manage.major.service.impl;

import core.base.service.BaseService;
import core.common.domain.GridParam;
import core.manage.major.dao.IMajorDao;
import core.manage.major.domain.MajorInfo;
import core.manage.major.service.IMajorService;
import core.manage.school.dao.ISchoolDao;
import core.manage.school.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("majorService")
public class MajorService extends BaseService implements IMajorService {

    @Autowired
    @Qualifier("majorDao")
    private IMajorDao majorDao;

    public void saveMajorInfo(MajorInfo sh) {
        majorDao.saveMajorInfo(sh);
    }

    @Override
    public List<Map<String,Object>> getAllMajor(GridParam gp, MajorInfo sci) {
        return majorDao.getAllMajor(gp,sci);
    }

    @Override
    public Integer getCount(MajorInfo sci) {
        return majorDao.getCount(sci);
    }

    @Override
    public void updateMajorInfo(MajorInfo schoolInfo) {
        majorDao.updateMajorInfo(schoolInfo);
    }

    @Override
    public void deleteMajorInfo(Integer id) {
        majorDao.deleteMajorInfo(id);
    }

    public List<Map<String,Object>> getMajor(String school,String level){
        return majorDao.getMajor(school,level);
    }
}
