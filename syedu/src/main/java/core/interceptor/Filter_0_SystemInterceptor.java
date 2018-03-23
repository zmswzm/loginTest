package core.interceptor;

import core.common.CommonUtil.CommonUtils;
import core.common.utils.SpringContextUtil;
import core.support.filter.spring_filter.ISpringFilter;
import core.support.message.Messager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter_0_SystemInterceptor implements ISpringFilter {


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String urlRegex = "(/syedu/user/save.do)|(/syedu/userAdd.do)|(/syedu/course.do)" +
				"|(/syedu/course/loadDetail.do)|(/syedu/register/submit.do)|(/syedu/school/load.do)" +
				"|(/syedu/major/getMajor.do)";
		Pattern p = Pattern.compile(urlRegex);
		Matcher m1 = p.matcher(request.getRequestURI());
		String uid = request.getParameter("uid");
		HttpSession session=request.getSession();
		if(CommonUtils.TextUtil.isNotEmpty(uid)){
			session.setAttribute("uid",uid);
			return true;
		}
		if(m1.matches()){
			return true;
		}
		if(session.getAttribute("uid")!=null){
			return true;
		}
		response.sendRedirect("/syedu/login.do");
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
