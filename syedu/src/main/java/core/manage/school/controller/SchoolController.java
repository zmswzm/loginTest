package core.manage.school.controller;

import core.base.controller.BaseController;
import core.common.domain.GridParam;
import core.manage.school.domain.SchoolInfo;
import core.manage.school.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Controller
@RequestMapping("school")
public class SchoolController extends BaseController{

    @Autowired
    @Qualifier("schoolService")
    private ISchoolService schoolService;

    @RequestMapping("save")
    public void saveSchoolInfo(SchoolInfo sh, HttpServletResponse response) {
        schoolService.saveSchoolInfo(sh);
        messager.jsonMessager().put("message","提交成功！").send(response);
    }

    @RequestMapping("update")
    public void updateSchoolInfo(SchoolInfo sh, HttpServletResponse response) {
        schoolService.updateSchoolInfo(sh);
        messager.jsonMessager().put("message","修改成功！").send(response);
    }

    @RequestMapping("delete")
    public void deleteSchoolInfo(Integer id, HttpServletResponse response) {
        schoolService.deleteSchoolInfo(id);
        messager.jsonMessager().put("message","删除成功！").send(response);
    }

    @RequestMapping("loadByParams")
    public void loadByParams(GridParam gp, SchoolInfo sh, HttpServletRequest request, HttpServletResponse response) {
        List<SchoolInfo> schoolList =  schoolService.getAllSchool(gp,sh);
        if(null == schoolList){
            schoolList = new ArrayList<SchoolInfo>();
        }
        messager.jsonMessager().put("total",schoolService.getCount(sh)).put("rows",schoolList).send(response);
    }

    @RequestMapping("load")
    public void load(HttpServletResponse response) {
        List<SchoolInfo> schoolList =  schoolService.getAllSchool(new GridParam(),new SchoolInfo());
        if(null == schoolList){
            schoolList = new ArrayList<>();
        }
        messager.jsonMessager().put("rows",schoolList).send(response);
    }

    @RequestMapping("loadYearNo")
    public void loadYearNo(String name,HttpServletResponse response) {
        List<Map<String,Object>> list =  schoolService.getYearNo(name,"1");
        if(null == list){
            list = new ArrayList<>();
        }
        messager.jsonMessager().put("rows",list).send(response);
    }

    @RequestMapping("loadCurrentNo")
    public void loadCurrentNo(HttpServletResponse response) {
        List<Map<String,Object>> list =  schoolService.getYearNo("","2");
        String currentNo = "";
        if(null != list && !list.isEmpty()){
            currentNo = list.get(0).get("yearNo").toString();
        }
        messager.jsonMessager().put("currentNo",currentNo).send(response);
    }

    @RequestMapping("updateYearNo")
    public void updateYearNo(String yearNo,HttpServletResponse response) {
        schoolService.updateYearNo(yearNo);
        messager.jsonMessager().put("success",true).send(response);
    }
}
