package core.manage.major.controller;

import core.base.controller.BaseController;
import core.common.domain.GridParam;
import core.manage.major.domain.MajorInfo;
import core.manage.major.service.IMajorService;
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
@RequestMapping("major")
public class MajorController extends BaseController{

    @Autowired
    @Qualifier("majorService")
    private IMajorService majorService;

    @RequestMapping("save")
    public void saveMajorInfo(MajorInfo sh, HttpServletResponse response) {
        majorService.saveMajorInfo(sh);
        messager.jsonMessager().put("message","提交成功！").send(response);
    }

    @RequestMapping("update")
    public void updateMajorInfo(MajorInfo sh, HttpServletResponse response) {
        majorService.updateMajorInfo(sh);
        messager.jsonMessager().put("message","修改成功！").send(response);
    }

    @RequestMapping("delete")
    public void deleteSchoolInfo(Integer id, HttpServletResponse response) {
        majorService.deleteMajorInfo(id);
        messager.jsonMessager().put("message","删除成功！").send(response);
    }

    @RequestMapping("loadByParams")
    public void loadByParams(GridParam gp, MajorInfo sh, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> schoolList =  majorService.getAllMajor(gp,sh);
        if(null == schoolList){
            schoolList = new ArrayList<Map<String,Object>>();
        }
        messager.jsonMessager().put("total",majorService.getCount(sh)).put("rows",schoolList).send(response);
    }

    @RequestMapping("getMajor")
    public void getMajor(String school,String level,HttpServletResponse response) {
        List<Map<String,Object>> list =  majorService.getMajor(school,level);
        if(null == list){
            list = new ArrayList<>();
        }
        messager.jsonMessager().put("rows",list).send(response);
    }

}
