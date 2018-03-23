package core.manage.course.service.impl;

import core.base.service.BaseService;
import core.common.CommonUtil.CommonUtils;
import core.common.CommonUtil.FileUtil;
import core.manage.course.dao.ICourseDao;
import core.manage.course.domain.CourseInfo;
import core.manage.course.service.ICourseService;
import core.manage.course.vo.CourseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("courseService")
public class CourseService extends BaseService implements ICourseService {

    @Autowired
    @Qualifier("courseDao")
    private ICourseDao courseDao;


    public void saveCourseInfo(HttpServletRequest request, HttpServletResponse response) {
        String uploadPath = FileUtil.getWebAppURL() + "syedu"+ File.separator+"image"; // 上传文件路径
        String fileName = FileUtil.uploadFile(request, uploadPath);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String title = null;
        String detail = null;
        String school = null;
        String cid = null;
        try {
            cid = multipartRequest.getParameter("cid");
            title = multipartRequest.getParameter("name");
            detail = multipartRequest.getParameter("detail");
            school = multipartRequest.getParameter("school");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setTitle(title);
        courseInfo.setImage(fileName);
        courseInfo.setDetail(detail);
        courseInfo.setSchool(school);
        courseInfo.setCid(cid);
        if(CommonUtils.TextUtil.isNotEmpty(cid)){
            courseDao.updateCourseInfo(courseInfo);
        }else{
            courseDao.saveCourseInfo(courseInfo);
        }
    }
    public void deleteCourseInfo(String cid){
        courseDao.deleteCourseInfo(cid);
    }

    public String saveDetailImage(HttpServletRequest request, HttpServletResponse response){
        String uploadPath = FileUtil.getWebAppURL() + "syedu"+ File.separator+"image"; // 上传文件路径
        String fileName = FileUtil.uploadFile(request, uploadPath);
        return fileName;
    }

    public List<Map<String,Object>> getAllCourse() {
        return courseDao.getAllCourse();
    }

    public String getCourseDetail(String cid) {
        return courseDao.getCourseDetail(cid);
    }
    public List<Map<String,Object>> getMajor(String school){
        return courseDao.getMajor(school);
    }
    public Map<String,Object> getCourse(String cid){
        return courseDao.getCourse(cid);
    }
    public boolean deleteFile(String key,String cid){
        if(CommonUtils.TextUtil.isNotEmpty(cid)){
            courseDao.updateCourseImage(cid,"");
        }
        return FileUtil.deleteFile(FileUtil.getWebAppURL() + "syedu"+ File.separator+"image" + File.separator + key);
    }
}
