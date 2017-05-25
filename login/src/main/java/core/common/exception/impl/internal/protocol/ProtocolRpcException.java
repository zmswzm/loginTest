package core.common.exception.impl.internal.protocol;

import core.common.exception.ExceptionType;
import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.impl.ProtocolException;

/**
 * Created by Administrator on 2017/5/23.
 */
public class ProtocolRpcException extends ProtocolException {

    public ProtocolRpcException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public String defShortName() {
        return "协议通信异常";
    }

    @Override
    public ExceptionType defExceptionType() {
        return ExceptionType.PROTOCOL_RPC_EXCEPTION;
    }
}
