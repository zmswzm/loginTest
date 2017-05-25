package core.common.exception.impl.internal.protocol;

import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.impl.ProtocolException;

/**
 * Created by Administrator on 2017/5/23.
 */
public class StandardprotocolException extends ProtocolException {

    public StandardprotocolException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public String defShortName() {
        return "标准协议异常";
    }
}
