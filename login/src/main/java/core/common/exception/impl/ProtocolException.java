package core.common.exception.impl;

import core.common.exception.BaseException;
import core.common.exception.ExceptionType;
import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.beans.impl.ProtocolExceptionDesc;

/**
 * Created by Administrator on 2017/5/23.
 */
public class ProtocolException extends BaseException {

    public ProtocolException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public ProtocolExceptionDesc getDesc() {
        return (ProtocolExceptionDesc) super.getDesc();
    }

    @Override
    public String defShortName() {
        return "协议异常";
    }

    @Override
    public ExceptionType defExceptionType() {
        return ExceptionType.PROTOCOL_EXCEPTION;
    }
}
