package core.support.exception_handler.wrapper.impl;

import core.common.CommonUtil.CommonUtils;
import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.beans.impl.BusinessExceptionDesc;
import core.common.exception.beans.impl.ProtocolExceptionDesc;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.support.exception_handler.wrapper.IExceptionWrapper;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/5/24.
 */
public abstract class BaseExceptionWrapper implements IExceptionWrapper {

    protected Logger logger = Logger.getLogger(getClass());

    protected Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Object wrapUnexpectedException(BaseExceptionDes desc) {

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("success",false);
        data.put("message","未知的异常类型");
        return data;
    }

    public Object getWrappedResult(BaseExceptionDes des) {

        if(des instanceof SystemExceptionDesc){
            return wrapSystemException(des.getSerializedData());
        }
        if(des instanceof BusinessExceptionDesc){
            return wrapBusinessException(des.getSerializedData());
        }
        if (des instanceof ProtocolExceptionDesc){
            return wrapProtocolException(des.getSerializedData());
        }
        return wrapUnexpectedException(des);
    }

    public String getWrappedResultAsJson(BaseExceptionDes des) {
        return CommonUtils.JsonUtil.object2Json(getWrappedResult(des));
    }
}
