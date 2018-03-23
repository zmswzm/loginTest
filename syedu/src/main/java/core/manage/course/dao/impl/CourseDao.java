package core.manage.course.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.manage.course.dao.ICourseDao;
import core.manage.course.domain.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Repository("courseDao")
public class CourseDao extends JdbcBaseDao implements ICourseDao{

    public void saveCourseInfo(CourseInfo courseInfo) {
        String sql = "INSERT INTO course_info (TITLE,SCHOOLID,IMAGE,DETAIL) VALUES(:title,:school,:image,:detail)";
        Map param = new HashMap<String,Object>();
        param.put("title",courseInfo.getTitle());
        param.put("image",courseInfo.getImage());
        param.put("detail",courseInfo.getDetail());
        param.put("school",courseInfo.getSchool());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateCourseInfo(CourseInfo courseInfo) {
        String sql = "UPDATE course_info SET TITLE = :title," +
                "SCHOOLID = :school," +
                "IMAGE = :image," +
                "DETAIL = :detail " +
                "WHERE ID = :cid";
        Map param = new HashMap<String,Object>();
        param.put("title",courseInfo.getTitle());
        param.put("image",courseInfo.getImage());
        param.put("detail",courseInfo.getDetail());
        param.put("school",courseInfo.getSchool());
        param.put("cid",courseInfo.getCid());
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void updateCourseImage(String id,String image) {
        String sql = "UPDATE course_info SET IMAGE = :image WHERE ID = :cid";
        Map param = new HashMap<String,Object>();
        param.put("cid",id);
        param.put("image",image);
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public void deleteCourseInfo(String cid){
        String sql = "DELETE FROM course_info WHERE id = :cid";
        Map param = new HashMap<String,Object>();
        param.put("cid",cid);
        this.namedParameterJdbcTemplate.update(sql,param);
    }

    public List<Map<String,Object>> getAllCourse() {
        String sql = "SELECT ci.ID AS cid,ci.SCHOOLID AS school,s.schoolName,ci.IMAGE AS image,ci.TITLE AS title " +
                "FROM course_info ci LEFT JOIN school s ON ci.SCHOOLID = s.id";
        List<Map<String,Object>> courseList  = this.namedParameterJdbcTemplate.queryForList(sql,new HashMap());
        return courseList;
    }

    public List<Map<String,Object>> getMajor(String school){
        String sql = "SELECT ID AS id,MAJOR AS name FROM course_info WHERE SCHOOLID = :school";
        Map p = new HashMap<String,Object>();
        p.put("school",school);
        List<Map<String,Object>> majorList  = this.namedParameterJdbcTemplate.queryForList(sql,p);
        return majorList;
    }

    public Float getPercentage(String uid,String cid){
        String sql = "SELECT per FROM percentage WHERE uid = :uid AND cid = :cid";
        Map p = new HashMap<String,Object>();
        p.put("uid",uid);
        p.put("cid",cid);
        return this.namedParameterJdbcTemplate.queryForObject(sql,p,Float.class);
    }

    public String getCourseDetail(String cid) {
        String sql = "SELECT ci.DETAIL AS detail FROM course_info ci WHERE ci.ID = :cid";
        Map param = new HashMap<String,Object>();
        param.put("cid",cid);
        return this.namedParameterJdbcTemplate.queryForObject(sql,param,String.class);
    }

    public Map<String,Object> getCourse(String cid) {
        String sql = "SELECT ci.TITLE AS title,ci.SCHOOLID AS school,ci.IMAGE AS image," +
                "ci.DETAIL AS detail FROM course_info ci WHERE ci.ID = :cid";
        Map param = new HashMap<String,Object>();
        param.put("cid",cid);
        return this.namedParameterJdbcTemplate.queryForMap(sql,param);
    }
}
