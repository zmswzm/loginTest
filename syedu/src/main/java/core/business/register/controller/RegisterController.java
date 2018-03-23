package core.business.register.controller;

import core.base.controller.BaseController;
import core.business.register.domain.BalanceInfo;
import core.business.register.domain.CstudentInfo;
import core.business.register.domain.RegisterInfo;
import core.business.register.service.IRegisterService;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Controller
@RequestMapping("register")
public class RegisterController extends BaseController{

    @Autowired
    @Qualifier("registerService")
    private IRegisterService registerService;

    @RequestMapping("attach")
    public void saveCourseInfo(HttpServletRequest request, HttpServletResponse response) {
        messager.jsonMessager().put("message",registerService.updateAttach(request,response)).send(response);
    }

    @RequestMapping("submit")
    public void saveRegisterInfo(RegisterInfo registerInfo, HttpServletRequest request, HttpServletResponse response) {
        if(CommonUtils.TextUtil.isEmpty(registerInfo.getUid())){
            registerInfo.setUid(request.getSession().getAttribute("uid").toString());
        }
        registerService.saveRegisterInfo(registerInfo);
        messager.jsonMessager().put("message","success").send(response);
    }

    @RequestMapping("update")
    public void updateRegisterInfo(RegisterInfo registerInfo, HttpServletRequest request, HttpServletResponse response) {
        registerService.updateRegisterInfo(registerInfo);
        messager.jsonMessager().put("message","修改成功！").send(response);
    }

    @RequestMapping("updateBalance")
    public void updateRegisterInfo(BalanceInfo bi, HttpServletRequest request, HttpServletResponse response) {
        registerService.updateBalanceStatus(bi);
        messager.jsonMessager().put("message","操作成功！").send(response);
    }

    @RequestMapping("deleteById")
    public void saveRegisterInfo(CstudentInfo sci, HttpServletResponse response) {
        registerService.deleteRegisterInfo(sci);
        messager.jsonMessager().put("message","删除成功！").send(response);
    }

    @RequestMapping("updateStatus")
    public void updateRegisterStatus(RegisterInfo sci, HttpServletResponse response) {
        registerService.updateRegisterStatus(sci);
        messager.jsonMessager().put("message","状态修改成功！").send(response);
        return;
    }

    @RequestMapping("loadByParams")
    public void loadByParams(GridParam gp, CstudentInfo sci, HttpServletRequest request, HttpServletResponse response) {
        List<RegisterInfo> stuList =  registerService.getAllStu(gp,sci);
        if(null == stuList){
            stuList = new ArrayList<RegisterInfo>();
        }
        messager.jsonMessager().put("total",registerService.getCount(sci)).put("rows",stuList).send(response);
    }

    @RequestMapping("loadStudentNo")
    public void loadStudentNo(String uid, HttpServletRequest request, HttpServletResponse response) {
       Map<String,Object> stu =  registerService.loadStudentNo(uid);
        if(null == stu){
            stu = new HashMap<>();
        }
        messager.jsonMessager().put("currentNo",stu.get("currentNo")).put("stuNum",stu.get("stuNum")).put("percentage",stu.get("percentage")).send(response);
    }

    @RequestMapping("loadAttach")
    public void loadAttach(String cid, HttpServletRequest request, HttpServletResponse response) {
        List<String> att =  registerService.loadAttach(cid);
        if(null == att){
            att = new ArrayList<>();
        }
        messager.jsonMessager().put("att",att).send(response);
    }

    @RequestMapping("deleteAtt")
    public void deleteAtt(String key,String cid,HttpServletRequest request, HttpServletResponse response) {
        messager.jsonMessager().put("message",registerService.execDeleteAtt(key,cid)).send(response);
    }

    @RequestMapping("loadAdminStudentNo")
    public void loadAdminStudentNo(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> stu =  registerService.loadAdminStudentNo();
        if(null == stu){
            stu = new HashMap<>();
        }
        messager.jsonMessager().put("currentNo",stu.get("currentNo")).put("stuNum",stu.get("stuNum")).put("percentage",stu.get("percentage")).send(response);
    }

    @RequestMapping("loadBalance")
    public void loadBalance(GridParam gp, BalanceInfo sci, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> stuList =  registerService.loadBalance(gp,sci);
        if(null == stuList){
            stuList = new ArrayList<>();
        }
        messager.jsonMessager().put("total",registerService.getBalanceCount(sci)).put("rows",stuList).send(response);
    }

    @RequestMapping("execBalance")
    public void execBalance(HttpServletResponse response){
        registerService.execBalance();
        messager.jsonMessager().put("message","结算完成").send(response);
    }
}
