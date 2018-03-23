package core.manage.major.service;

import core.common.domain.GridParam;
import core.manage.major.domain.MajorInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface IMajorService {

    public void saveMajorInfo(MajorInfo sh);
    public List<Map<String,Object>> getAllMajor(GridParam gp, MajorInfo sci);
    public Integer getCount(MajorInfo sci);
    void updateMajorInfo(MajorInfo schoolInfo);
    void deleteMajorInfo(Integer id);
    List<Map<String,Object>> getMajor(String school,String level);
}
