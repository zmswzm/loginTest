package core.business.register.service.impl;

import core.base.service.BaseService;
import core.business.register.dao.IRegisterDao;
import core.business.register.domain.BalanceInfo;
import core.business.register.domain.CstudentInfo;
import core.business.register.domain.PerInfo;
import core.business.register.domain.RegisterInfo;
import core.business.register.service.IRegisterService;
import core.common.CommonUtil.CommonUtils;
import core.common.CommonUtil.FileUtil;
import core.common.domain.GridParam;
import core.manage.course.domain.CourseInfo;
import core.manage.course.service.ICourseService;
import core.manage.school.dao.ISchoolDao;
import core.manage.user.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("registerService")
public class RegisterService extends BaseService implements IRegisterService {

    @Autowired
    @Qualifier("registerDao")
    private IRegisterDao registerDao;

    @Autowired
    @Qualifier("schoolDao")
    private ISchoolDao schoolDao;

    @Autowired
    @Qualifier("UserDao")
    private IUserDao userDao;

    public boolean execDeleteAtt(String key,String cid){
        List<String> att = loadAttach(cid);
        String r = "";
        String s = "";
        for(String a : att){
            if(!key.equals(a)){
                if(CommonUtils.TextUtil.isNotEmpty(r)){
                    s = ";";
                }else{
                    s = "";
                }
                r = r +s+ a;
            }
        }
        registerDao.updateAttach(cid,r);
        return FileUtil.deleteFile(FileUtil.getWebAppURL() + "syedu"+ File.separator+"image" + File.separator + key);
    }

    public boolean updateAttach(HttpServletRequest request, HttpServletResponse response) {

        String uploadPath = FileUtil.getWebAppURL() + "syedu"+ File.separator+"image"; // 上传文件路径
        List<String> attach = FileUtil.uploadFiles(request, uploadPath);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String cid = "";
        if(null != attach && !attach.isEmpty()){
            try {
                cid = multipartRequest.getParameter("cid");
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<String> attList = loadAttach(cid);
            attach.addAll(attList);
            String att = "";
            for(String a : attach){
                att = att + a + ";";
            }
            registerDao.updateAttach(cid,att);
            return true;
        }
        return false;
    }

    public List<String> loadAttach(String cid){
        String att = registerDao.loadAttach(cid);
        String satt[] = {} ;
        if(CommonUtils.TextUtil.isNotEmpty(att)){
            satt = att.split(";");
        }
        List<String> arrayList = Arrays.asList(satt);
        return arrayList;
    }

    public void saveRegisterInfo(RegisterInfo registerInfo) {
        registerInfo.setYearNo(schoolDao.getSchoolNo(Integer.valueOf(registerInfo.getSchool())));
        registerDao.saveRegisterInfo(registerInfo);
    }
    public List<RegisterInfo> getAllStu(GridParam gp, CstudentInfo sci){
        return registerDao.getAllStu(gp,sci);
    }
    public Integer getCount(CstudentInfo sci){
        return registerDao.getCount(sci);
    }

    @Override
    public void deleteRegisterInfo(CstudentInfo sci) {
        registerDao.deleteRegisterInfo(sci);
    }

    @Override
    public void updateRegisterInfo(RegisterInfo registerInfo) {
        registerDao.updateRegisterInfo(registerInfo);
    }

    @Override
    public void updateRegisterStatus(RegisterInfo registerInfo){
        registerDao.updateRegisterStatus(registerInfo);
    }

    @Override
    public void execBalance(){
        List<Map<String,Object>> user = userDao.getUserByType();
        if(null != user && !user.isEmpty()){
            String currNoString = schoolDao.getYearNo("","2").get(0).get("yearNo").toString();
            String sns[] = currNoString.split("#");
            for(Map<String,Object> u : user){
                List<PerInfo> r1 = new ArrayList<>();
                List<PerInfo> r2;
                for(int i = 0; i < sns.length; i++){
                    String s[] = sns[i].split("-");
                    List<Map<String,Object>> userList = userDao.getUserByUid(u.get("uid").toString());
                    String userS = "'" + u.get("uid").toString() + "'";
                    if(null != userList && !userList.isEmpty()){
                        for(Map<String,Object> m : userList){
                            userS = userS +","+ "'" +m.get("uid").toString()+ "'";
                        }
                    }
                    r2 = registerDao.loadStudentPer(userS,s[0],s[1]);
                    if(!r2.isEmpty()){
                        r1.addAll(r2);
                    }
                }
                BalanceInfo bi = new BalanceInfo();
                if(null != r1 && !r1.isEmpty()){
                    bi.setUid(u.get("uid").toString());
                    bi.setYearNo(currNoString);
                    bi.setStuNo(r1.size());
                    Float percentage1 = new Float(0.0);
                    for (PerInfo p : r1) {
                        percentage1 =  percentage1 + p.getPercentage();
                    }
                    bi.setFee(percentage1);
                    Integer bc = registerDao.loadBalanceCount(u.get("uid").toString(),currNoString);
                    if(bc >= 1){
                        registerDao.updateBalance(bi);
                    }else{
                        registerDao.saveBalance(bi);
                    }
                }
            }
        }
    }
    public List<Map<String,Object>> loadBalance(GridParam gp, BalanceInfo sci){
        return registerDao.loadBalance(gp, sci);
    }
    public Integer getBalanceCount(BalanceInfo sci){
        return registerDao.getBalanceCount(sci);
    }
    public void updateBalanceStatus(BalanceInfo balanceInfo) {
        registerDao.updateBalanceStatus(balanceInfo);
    }

    public Map<String,Object> loadAdminStudentNo(){
        String currNoString = schoolDao.getYearNo("","2").get(0).get("yearNo").toString();
        String sns[] = currNoString.split("#");
        List<PerInfo> r1 = new ArrayList<>();
        List<PerInfo> r2;
        for(int i = 0; i < sns.length; i++){
            String s[] = sns[i].split("-");
            r2 = registerDao.loadStudentPer("",s[0],s[1]);
            if(!r2.isEmpty()){
                r1.addAll(r2);
            }
        }

        Map<String,Object> re = new HashMap<>();
        Float percentage1 = new Float(0.0);
        if(null != r1 && !r1.isEmpty()){
            re.put("name",r1.get(0).getName());
            re.put("phone",r1.get(0).getPhone());
            re.put("currentNo",currNoString.replace("#","学年 ").concat("学年"));
            re.put("stuNum",r1.size());

            for (PerInfo p : r1) {
                percentage1 =  percentage1 + p.getPercentage();
            }
            re.put("percentage",percentage1);
        }
        if(!re.isEmpty()){
            return re;
        }else{
            re.put("name","-");
            re.put("phone","-");
            re.put("currentNo","-");
            re.put("stuNum","-");
            re.put("percentage","-");
            return re;
        }
    }

    @Override
    public Map<String,Object> loadStudentNo(String uid){
        String currNoString = schoolDao.getYearNo("","2").get(0).get("yearNo").toString();
        String sns[] = currNoString.split("#");
        List<PerInfo> r1 = new ArrayList<>();
        List<PerInfo> r2;
        List<Map<String,Object>> userList = userDao.getUserByUid(uid);
        String user = "'" + uid + "'";
        if(null != userList && !userList.isEmpty()){
            for(Map<String,Object> m : userList){
                user = user +","+ "'" +m.get("uid").toString()+ "'";
            }
        }
        for(int i = 0; i < sns.length; i++){
            String s[] = sns[i].split("-");
            r2 = registerDao.loadStudentPer(user,s[0],s[1]);
            if(!r2.isEmpty()){
                r1.addAll(r2);
            }
        }

        Map<String,Object> re = new HashMap<>();
        Float percentage1 = new Float(0.0);
        if(null != r1 && !r1.isEmpty()){
            re.put("name",r1.get(0).getName());
            re.put("phone",r1.get(0).getPhone());
            re.put("currentNo",currNoString.replace("#","学年 ").concat("学年"));
            re.put("stuNum",r1.size());

            for (PerInfo p : r1) {
                percentage1 =  percentage1 + p.getPercentage();
            }
            re.put("percentage",percentage1);
        }
        if(!re.isEmpty()){
            return re;
        }else{
            re.put("name","-");
            re.put("phone","-");
            re.put("currentNo","-");
            re.put("stuNum","-");
            re.put("percentage","-");
            return re;
        }
    }
}
