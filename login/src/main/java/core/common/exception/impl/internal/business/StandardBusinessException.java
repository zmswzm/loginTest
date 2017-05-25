package core.common.exception.impl.internal.business;

import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.impl.BusinessException;

/**
 * 标准业务异常
 */
public class StandardBusinessException extends BusinessException{

    public StandardBusinessException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public String defShortName() {
        return "标准业务异常";
    }
}
