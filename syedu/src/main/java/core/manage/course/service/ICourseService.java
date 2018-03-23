package core.manage.course.service;

import core.manage.course.vo.CourseInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface ICourseService {

    public void saveCourseInfo(HttpServletRequest request, HttpServletResponse response);
    public void deleteCourseInfo(String cid);
    public String saveDetailImage(HttpServletRequest request, HttpServletResponse response);
    public boolean deleteFile(String key,String cid);
    public List<Map<String,Object>> getAllCourse();
    public Map<String,Object> getCourse(String cid);
    public String getCourseDetail(String cid);
    public List<Map<String,Object>> getMajor(String school);

}
