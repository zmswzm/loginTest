package core.manage.major.dao;

import core.common.domain.GridParam;
import core.manage.major.domain.MajorInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IMajorDao {

    void saveMajorInfo(MajorInfo majorInfo);
    void updateMajorInfo(MajorInfo majorInfo);
    void deleteMajorInfo(Integer id);
    Integer getCount(MajorInfo majorInfo);
    List<Map<String,Object>> getMajor(String school,String level);
    List<Map<String,Object>> getAllMajor(GridParam gp, MajorInfo majorInfo);
}
