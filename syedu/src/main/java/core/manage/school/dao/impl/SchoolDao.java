package core.manage.school.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.school.dao.ISchoolDao;
import core.manage.school.domain.SchoolInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Repository("schoolDao")
public class SchoolDao extends JdbcBaseDao implements ISchoolDao{

    @Override
    public void saveSchoolInfo(SchoolInfo schoolInfo) {
        String sql = "INSERT INTO SCHOOL (schoolName,schoolNo) VALUES(:sname,:sno)";
        Map param = new HashMap<String,Object>();
        param.put("sname",schoolInfo.getSchoolName());
        param.put("sno",schoolInfo.getSchoolNo());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    @Override
    public void updateSchoolInfo(SchoolInfo schoolInfo) {
        String sql = "UPDATE SCHOOL SET schoolName = :sName,schoolNo = :sNo WHERE id = :id AND schoolName <> 'currentNo'";
        Map<String,Object> p = new HashMap<>();
        p.put("sName",schoolInfo.getSchoolName());
        p.put("sNo",schoolInfo.getSchoolNo());
        p.put("id",schoolInfo.getId());
        this.namedParameterJdbcTemplate.update(sql,p);
    }

    @Override
    public void updateYearNo(String yearNo){
        String sql = "UPDATE dict d SET d.year_no = :yearNo WHERE d.type = '2'";
        Map<String,Object> p = new HashMap<>();
        p.put("yearNo",yearNo);
        this.namedParameterJdbcTemplate.update(sql,p);
    }

    @Override
    public void deleteSchoolInfo(Integer id) {
        String sql = "DELETE FROM SCHOOL WHERE id = :id";
        Map<String,Object> p = new HashMap<>();
        p.put("id",id);
        this.namedParameterJdbcTemplate.update(sql,p);
    }

    public Integer getCount(SchoolInfo schoolInfo){
        String sql = "SELECT COUNT(s.id) FROM SCHOOL s WHERE 1=1 ";
        if(CommonUtils.TextUtil.isNotEmpty(schoolInfo.getSchoolName())){
            sql = sql + " AND s.schoolName LIKE '%"+schoolInfo.getSchoolName()+"%'";
        }
        return this.namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    public String getSchoolNo(Integer id){
        String sql = "SELECT schoolNo FROM SCHOOL WHERE id=:id ";
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        return this.namedParameterJdbcTemplate.queryForObject(sql, param, String.class);
    }

    public List<SchoolInfo> getAllSchool(GridParam gp, SchoolInfo schoolInfo){
        String sql = "SELECT s.id,s.schoolName,s.schoolNo FROM SCHOOL s WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(schoolInfo.getSchoolName())){
            sql = sql + " AND s.schoolName LIKE '%"+schoolInfo.getSchoolName()+"%'";
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }
        List<SchoolInfo> stuList  = this.namedParameterJdbcTemplate.query(sql,p,new SchMapper());
        return stuList;
    }

    public List<Map<String,Object>> getYearNo(String name, String type){
        String sql = "SELECT year_no AS yearNo FROM dict WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(name)){
            p.put("name",name);
            sql = sql + " AND yearNo LIKE '%"+name+"%'";
        }
        if(CommonUtils.TextUtil.isNotEmpty(type)){
            p.put("type",type);
            sql = sql + " AND type = :type ";
        }
        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

    public List<Map<String,Object>> getMajor(String school){
        String sql = "SELECT s.id,s.schoolName,s.schoolNo FROM SCHOOL s WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

    class SchMapper implements RowMapper<SchoolInfo> {

        public SchoolInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            SchoolInfo sInfro = new SchoolInfo();
            sInfro.setId(rs.getInt("id"));
            sInfro.setSchoolName(rs.getString("schoolName"));
            sInfro.setSchoolNo(rs.getString("schoolNo"));
            return sInfro;
        }
    }
}
