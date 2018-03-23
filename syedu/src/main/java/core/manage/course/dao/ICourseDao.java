package core.manage.course.dao;

import core.manage.course.domain.CourseInfo;
import core.manage.course.vo.CourseInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface ICourseDao {

    public void saveCourseInfo(CourseInfo courseInfo);

    public void deleteCourseInfo(String cid);
    public List<Map<String,Object>> getAllCourse();
    public void updateCourseInfo(CourseInfo courseInfo);
    public List<Map<String,Object>> getMajor(String school);
    public Map<String,Object> getCourse(String cid);
    public String getCourseDetail(String cid);
    public Float getPercentage(String uid,String cid);
    public void updateCourseImage(String id,String image);
}
