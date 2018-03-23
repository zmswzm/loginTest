package core.support.exception_handler;

import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.beans.impl.BusinessExceptionDesc;
import core.common.exception.beans.impl.ProtocolExceptionDesc;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.BusinessException;
import core.common.exception.impl.ProtocolException;
import core.common.exception.impl.SystemException;
import core.support.exception_handler.wrapper.IExceptionWrapper;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/24.
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver,Ordered{

    protected Logger logger = Logger.getLogger(getClass());
    private IExceptionWrapper exceptionWrapper;

    @PostConstruct
    private void init(){

        String className = "core.support.exception_handler.wrapper.impl.internal.DefaultExceptionWrapper";
        try {
            exceptionWrapper = (IExceptionWrapper)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return processException(request,response,handler,ex);
    }

    private ModelAndView processException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

        BaseExceptionDes targetDesc = null;

        if (ex instanceof SystemException){
            SystemException exception = (SystemException)ex;
            SystemExceptionDesc desc = exception.getDesc();
            targetDesc = desc;
        }else if(ex instanceof BusinessException){

            //业务异常
            BusinessException exception = (BusinessException)ex;
            BusinessExceptionDesc desc = exception.getDesc();
            //对于非系统异常，由于不存在原始异常，因此直接设置 e
            desc.setThrowable(ex);
            targetDesc = desc;

        }else if(ex instanceof ProtocolException){

            //协议异常
            ProtocolException exception = (ProtocolException)ex;
            ProtocolExceptionDesc desc = exception.getDesc();
            //对于非系统异常，由于不存在原始异常，因此直接设置 e
            desc.setThrowable(ex);
            targetDesc = desc;

        }else{

            //原始异常
            SystemException exception = SystemExceptionDesc.convertFromNativeException(ex);
            SystemExceptionDesc desc = exception.getDesc();
            targetDesc = desc;
        }
        logger.error(targetDesc.getMessage(),targetDesc.getThrowable());
        try {
            response.setContentType("text/json");
            response.getOutputStream().write(exceptionWrapper.getWrappedResultAsJson(targetDesc).getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
