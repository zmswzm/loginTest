package core.support.exception_handler.wrapper.impl;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/24.
 */
public abstract class ExceptionWrapperAdapter extends BaseExceptionWrapper{

    public Object wrapSystemException(Map<String, Object> serializedData) {
        return serializedData;
    }

    public Object wrapBusinessException(Map<String, Object> serializedData) {
        return serializedData;
    }

    public Object wrapProtocolException(Map<String, Object> serializedData) {
        return serializedData;
    }
}
