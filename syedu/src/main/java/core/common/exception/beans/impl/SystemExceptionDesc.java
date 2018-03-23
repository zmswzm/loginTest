package core.common.exception.beans.impl;

import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.impl.SystemException;
import core.common.exception.impl.internal.framework.FrameworkInternalException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */
public class SystemExceptionDesc extends BaseExceptionDes{

    private final String DEFAULT_MESSAGE = "系统异常";

    public  SystemExceptionDesc(String code,Throwable throwable){
        super();
        super.setThrowable(throwable);
        this.setMessage(DEFAULT_MESSAGE);
        this.setMessage(code);
    }

    public  SystemExceptionDesc(String code,String message,Throwable throwable){
        super();
        super.setThrowable(throwable);
        this.setCode(code);
        this.setMessage(message);
    }

    public SystemExceptionDesc(String message){
        super();
        this.setMessage(message);
    }

    public SystemExceptionDesc(Throwable throwable){
        super();
        this.setMessage(DEFAULT_MESSAGE);
        super.setThrowable(throwable);
    }

    @Override
    public Map<String, Object> getSerializedData() {

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("exceptionType","SYSTEM");
        data.putAll(getBaseSerializedData());

        return data;
    }

    public static SystemException convertFromNativeException(Exception e) {

        return new FrameworkInternalException(new SystemExceptionDesc(e));
    }
}
