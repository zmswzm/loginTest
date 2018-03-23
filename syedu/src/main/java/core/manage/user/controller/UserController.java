package core.manage.user.controller;

import core.base.controller.BaseController;
import core.common.domain.GridParam;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.domain.CuserInfo;
import core.manage.user.domain.CuserPer;
import core.manage.user.service.IUserService;
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
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;

    @RequestMapping("login")
    public void login(AccessControlUser user, HttpServletRequest request, HttpServletResponse response) {
        String userPhone = user.getPhone().trim();
        String password = user.getPassword().trim();

        AccessControlUser loginUser = userService.getUserByPhoneAndPassword(userPhone,password);

        if (loginUser == null) {
            messager.jsonMessager().put("message","当前用户不存在").put("result", false).send(response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("uid", loginUser.getUid());
        session.setAttribute("uphone", loginUser.getPhone());
        session.setAttribute("utype", loginUser.getUserType());

        messager.jsonMessager().put("message","登陆成功")
                .put("uname", loginUser.getName())
                .put("uphone", loginUser.getPhone())
                .put("uid", loginUser.getUid())
                .put("utype", loginUser.getUserType()).send(response);
        return;
    }

    @RequestMapping("loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.invalidate();

        messager.jsonMessager().put("message","success")
                .send(response);
    }


    @RequestMapping("loadAll")
    public void loadAll(HttpServletRequest request, HttpServletResponse response) {

        List<AccessControlUser> users = userService.getAllUser();
        messager.jsonMessager().put("total", users.size()).put("rows",users).send(response);
    }

    @RequestMapping("loadByPhone")
    public void loadByPhone(String phone,HttpServletRequest request, HttpServletResponse response) {

        List<AccessControlUser> users = new ArrayList<AccessControlUser>();
        users.add(userService.getUserByPhone(phone));
        messager.jsonMessager().put("users", users).send(response);
    }

    @RequestMapping("loadByPrama")
    public void loadByPrama(GridParam gp, CuserInfo cui, HttpServletResponse response) {

        List<AccessControlUser> stuList =  userService.getUser(gp,cui);
        if(null == stuList){
            stuList = new ArrayList<AccessControlUser>();
        }
        messager.jsonMessager().put("total",userService.getCount(cui)).put("rows",stuList).send(response);
    }

    @RequestMapping("loadByPer")
    public void loadByPer(GridParam gp, CuserPer cup, HttpServletResponse response) {

        List<Map<String,Object>> stuList =  userService.getUserPer(gp, cup);
        if(null == stuList){
            stuList = new ArrayList<>();
        }
        messager.jsonMessager().put("total",userService.getPCount(cup)).put("rows",stuList).send(response);
    }

    @RequestMapping("delete")
    public void deleteUser(String uid,HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(uid);
        messager.jsonMessager().put("message","删除成功！").send(response);
        return;
    }

    @RequestMapping("save")
    public void saveUser(AccessControlUser user,HttpServletRequest request, HttpServletResponse response) {
        user.setReferee(request.getSession().getAttribute("uid").toString());
        if(11 == userService.saveUser(user)){
            messager.jsonMessager().put("message", "have").send(response);
        }else{
            messager.jsonMessager().put("message", "success").send(response);
        }
    }

    @RequestMapping("update")
    public void updateUser(AccessControlUser user,HttpServletRequest request, HttpServletResponse response) {

        userService.updateUser(user);
        messager.jsonMessager().put("message","修改成功！").send(response);
    }

    @RequestMapping("updatePer")
    public void updatePer(String uid,String cid,String per,HttpServletRequest request, HttpServletResponse response) {

        userService.updateUserPercent(uid,cid,per);
        messager.jsonMessager().put("message","设置成功！").send(response);
    }

    @RequestMapping("updatePW")
    public void updatePW(AccessControlUser user,HttpServletRequest request, HttpServletResponse response) {
        user.setPhone(request.getSession().getAttribute("uphone").toString());
        messager.jsonMessager().put("message", userService.updatePW(user)).send(response);
    }
}
