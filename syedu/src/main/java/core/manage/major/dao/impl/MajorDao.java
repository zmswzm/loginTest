package core.manage.major.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.major.dao.IMajorDao;
import core.manage.major.domain.MajorInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Repository("majorDao")
public class MajorDao extends JdbcBaseDao implements IMajorDao{

    @Override
    public void saveMajorInfo(MajorInfo majorInfo) {
        String sql = "INSERT INTO MAJOR(major,school,level) VALUES(:major,:school,:level)";
        Map param = new HashMap<String,Object>();
        param.put("school",majorInfo.getSchoolName());
        param.put("major",majorInfo.getMajor());
        param.put("level",majorInfo.getLevel());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    @Override
    public void updateMajorInfo(MajorInfo majorInfo) {
        String sql = "UPDATE MAJOR SET school = :school,major = :major,level = :level WHERE id = :id";
        Map<String,Object> p = new HashMap<>();
        p.put("school",majorInfo.getSchoolName());
        p.put("major",majorInfo.getMajor());
        p.put("level",majorInfo.getLevel());
        p.put("id",majorInfo.getId());
        this.namedParameterJdbcTemplate.update(sql,p);
    }

    @Override
    public void deleteMajorInfo(Integer id) {
        String sql = "DELETE FROM MAJOR WHERE id = :id";
        Map<String,Object> p = new HashMap<>();
        p.put("id",id);
        this.namedParameterJdbcTemplate.update(sql,p);
    }

    public Integer getCount(MajorInfo majorInfo){
        String sql = "SELECT COUNT(s.id) FROM MAJOR s WHERE 1 = 1";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(majorInfo.getSchoolName())){
            sql = sql + " AND s.school = :school";
            p.put("school",majorInfo.getSchoolName());
        }
        if(CommonUtils.TextUtil.isNotEmpty(majorInfo.getLevel())){
            sql = sql + " AND s.level = :level";
            p.put("level",majorInfo.getLevel());
        }
        return this.namedParameterJdbcTemplate.queryForObject(sql, p, Integer.class);
    }

    public List<Map<String,Object>> getAllMajor(GridParam gp, MajorInfo majorInfo){
        String sql = "SELECT s.id,sc.schoolName,s.major,s.level FROM MAJOR s " +
                " LEFT JOIN SCHOOL sc ON s.school = sc.id WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(majorInfo.getSchoolName())){
            sql = sql + " AND s.school = :school";
            p.put("school",majorInfo.getSchoolName());
        }
        if(CommonUtils.TextUtil.isNotEmpty(majorInfo.getLevel())){
            sql = sql + " AND s.level = :level";
            p.put("level",majorInfo.getLevel());
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }
        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

    public List<Map<String,Object>> getMajor(String school,String level){
        String sql = "SELECT major FROM MAJOR WHERE school = :school AND level = :level";
        Map<String,Object> p = new HashMap<>();
        p.put("school",school);
        p.put("level",level);
        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

}
