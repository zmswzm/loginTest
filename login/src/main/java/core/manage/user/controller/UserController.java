package core.manage.user.controller;

import core.base.controller.BaseController;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Controller
public class UserController extends BaseController{

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;

    @RequestMapping("login")
    public void login(AccessControlUser user, HttpServletRequest request, HttpServletResponse response) {
        String userPhone = user.getPhone().trim();
        String password = user.getPassword().trim();

        AccessControlUser loginUser = userService.getUserByPhone(userPhone);

        if (loginUser == null) {
            messager.jsonMessager().put("message","当前用户不存在").put("result", false).send(response);
            return;
        }

        HttpSession session = request.getSession();
        Map<String, Object> hosParam = new HashMap<String, Object>();
        Map<String, Object> userOperateAccess = new HashMap<String, Object>();
        session.setAttribute("user", loginUser);
        messager.jsonMessager().put("message","登陆成功").put("result", true).send(response);
        return;
    }
}
