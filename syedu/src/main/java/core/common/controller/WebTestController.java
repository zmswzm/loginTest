package core.common.controller;

import core.common.CommonUtil.CommonUtils;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.internal.framework.FrameworkInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@Controller
public class WebTestController {
	
	@Autowired
	@Qualifier("webViewConfig")
	private WebViewConfig webViewConfig;

	@PostConstruct
	private void init() {
		
		String fileName = webViewConfig.getMappingFileName() + ".properties";
		ClassPathResource config = new ClassPathResource(fileName);
		
		try {
			Properties file = CommonUtils.FileUtil.loadProperties(config.getFile().getAbsolutePath());
			webViewConfig.setUrlMappingTable(file);
		} catch (IOException e) {
			throw new FrameworkInternalException(new SystemExceptionDesc(e));
		}
		
	}

	/**
	 * 鍓嶅彴璁块棶鍒嗗彂鍏ュ彛
	 */
	@RequestMapping(value={"*", "*/*", "*/*/*", "*/*/*", "*/*/*/*", "*/*/*/*/*", "*/*/*/*/*/*"}, method= RequestMethod.GET)
	public ModelAndView bootView(HttpServletRequest request, HttpServletResponse response, HttpSession session){

		ModelAndView modeAndView = new ModelAndView();

		String uri = request.getRequestURI().replaceFirst(webViewConfig.getRequestUrlBasePath(), "").replaceAll("\\.do$", "");
		String returnView = webViewConfig.getMappingBasePath()  + webViewConfig.getUrlMappingTable().getProperty(uri);

		if(returnView == null || returnView == ""){
			throw new FrameworkInternalException(new SystemExceptionDesc("异常"+returnView));
		}

		modeAndView.setViewName(returnView);

		return modeAndView;
	}
}
