package core.support.filter;

import core.common.CommonUtil.CommonUtils;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.internal.framework.FrameworkInternalException;
import core.support.exception_handler.ExceptionHandler;
import core.support.message.Messager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseNativeFilter implements INativeFilter {

    private ExceptionHandler exceptionHandler;

    protected Messager messager;

    public void init(FilterConfig filterConfig) throws ServletException {

        ServletContext ctx = filterConfig.getServletContext();
        exceptionHandler = CommonUtils.SpringUtil.getBeanIfExist("exceptionHandler", ExceptionHandler.class, ctx);
        messager = CommonUtils.SpringUtil.getBeanIfExist("Messager", Messager.class, ctx);
    }

    public void prepare(ServletContext context) throws Exception{
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        //跳过静态资源
        String uri = req.getRequestURI();
        if(uri != null && isShouldSkip(uri)){
            chain.doFilter(req, resp);
            return;
        }

        Boolean beforeResult = false;
        try {
            beforeResult = before(req, resp, req.getSession());
        } catch (Exception e) {

            if(exceptionHandler == null){
                logger.error(e);
                throw new FrameworkInternalException(new SystemExceptionDesc("无法将异常抛给 spring，因为没有找到 exceptionHandler"));
            }
            exceptionHandler.resolveException(req, resp, this, e);
        }

        if(beforeResult){

            InterceptorResponseWrapper respWrapper = new InterceptorResponseWrapper(resp);
            chain.doFilter(req, respWrapper);

            InterceptorResponseWrapper.OutputChannel outputChannel = respWrapper.getOutputChannel();

            //仅能捕获以 WRITER 管道输出的响应文本
            String respContent = null;
            if(outputChannel == InterceptorResponseWrapper.OutputChannel.WRITER){

                //获取 response 内容
                respContent = respWrapper.getContent();
                response.getWriter().print(respContent);
            }

            try {
                after(respContent, outputChannel, req, resp, req.getSession());
            } catch (Exception e) {

                if(exceptionHandler == null){
                    logger.error("无法将异常抛给 spring，因为没有找到 exceptionHandler", e);
                    throw new FrameworkInternalException(new SystemExceptionDesc("无法将异常抛给 spring，因为没有找到 exceptionHandler"));
                }
                exceptionHandler.resolveException(req, resp, this, e);
            }
        }
    }

    public void destroy() {
    }

    /**
     * 是否跳过此次拦截
     * <br/>
     * 针对于静态资源
     *
     * @param url
     * @return
     */
    private boolean isShouldSkip(String url){

        //排除静态资源，为了效率，因此使用原始的 endsWith 依次判断
        url = url.toLowerCase();
        if(url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".html") ||url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".gif")){
            return true;
        }

        return false;
    }
}

