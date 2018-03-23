package core.manage.course.controller;

import core.base.controller.BaseController;
import core.manage.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Controller
@RequestMapping("course")
public class CourseController extends BaseController{

    @Autowired
    @Qualifier("courseService")
    private ICourseService courseService;

    @RequestMapping("save")
    public void saveCourseInfo(HttpServletRequest request, HttpServletResponse response) {
        courseService.saveCourseInfo(request,response);
        messager.jsonMessager().put("message","提交成功！").send(response);
    }

    @RequestMapping("deleteFile")
    public void deleteFile(String key,String cid,HttpServletRequest request, HttpServletResponse response) {
        messager.jsonMessager().put("message",courseService.deleteFile(key,cid)).send(response);
    }

    @RequestMapping("saveDetailImage")
    public void saveDetailInfo(HttpServletRequest request, HttpServletResponse response) {
        String fileName = courseService.saveDetailImage( request, response);
        messager.jsonMessager().put("message","提交成功！").put("fileName",fileName).send(response);
        return;
    }

    @RequestMapping("loadAll")
    public void loadAll(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> courseList;
        courseList = courseService.getAllCourse();
        if(null == courseList){
            courseList = new ArrayList<Map<String, Object>>();
        }
        HttpSession session = request.getSession();
        String uid = session.getAttribute("uid").toString();
        messager.jsonMessager().put("uid",uid).put("total", courseList.size()).put("rows",courseList).send(response);
    }

    @RequestMapping("load")
    public void load(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> courseList = courseService.getAllCourse();
        if(null == courseList){
            courseList = new ArrayList<>();
        }
        messager.jsonMessager().put("rows",courseList).send(response);
    }

    @RequestMapping("delete")
    public void deleCourse(String cid, HttpServletRequest request, HttpServletResponse response) {
        courseService.deleteCourseInfo(cid);
        messager.jsonMessager().put("success",true).send(response);
    }

    @RequestMapping("loadMajor")
    public void loadMajor(String school, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> majorList = courseService.getMajor(school);
        if(null == majorList){
            majorList = new ArrayList<>();
        }
        messager.jsonMessager().put("rows",majorList).send(response);
    }

    @RequestMapping("loadDetail")
    public void loadAll(String cid,HttpServletRequest request, HttpServletResponse response) {
        String detail = courseService.getCourseDetail(cid);
        messager.jsonMessager().put("detail",detail).send(response);
    }

    @RequestMapping("loadCourseInfo")
    public void loadCourseInfo(String cid,HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> detail = courseService.getCourse(cid);
        messager.jsonMessager().put("detail",detail.get("detail")).put("title",detail.get("title"))
                .put("school",detail.get("school")).put("image",detail.get("image")).send(response);
    }
}
