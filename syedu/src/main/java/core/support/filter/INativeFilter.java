package core.support.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface INativeFilter extends IFilter, Filter {

    /**
     * 逻辑处理前
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean before(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

    /**
     * 逻辑处理后
     *
     * @param respContent
     * @param outputChannel
     * @param request
     * @param response
     * @throws Exception
     */
    public void after(String respContent, InterceptorResponseWrapper.OutputChannel outputChannel, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
}
