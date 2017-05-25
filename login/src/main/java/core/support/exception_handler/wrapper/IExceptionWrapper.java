package core.support.exception_handler.wrapper;

import core.common.exception.beans.BaseExceptionDes;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface IExceptionWrapper {

    /**
     * 包装系统异常
     * @param serializedData
     * @return
     */
    Object wrapSystemException(Map<String,Object> serializedData);

    /**
     * 包装业务异常
     * @param serializedData
     * @return
     */
    Object wrapBusinessException(Map<String,Object> serializedData);

    /**
     * 包装协议异常
     * @param serializedData
     * @return
     */
    Object wrapProtocolException(Map<String,Object> serializedData);

    /**
     * 包装未知异常
     * @param desc
     * @return
     */
    Object wrapUnexpectedException(BaseExceptionDes desc);

    /**
     * 获取包装结果
     * @param des
     * @return
     */
    Object getWrappedResult(BaseExceptionDes des);

    /**
     * 获取包装结果
     * @param des
     * @return
     */
    String getWrappedResultAsJson(BaseExceptionDes des);
}
