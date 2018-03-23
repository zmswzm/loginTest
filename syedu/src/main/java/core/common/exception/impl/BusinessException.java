package core.common.exception.impl;

import core.common.exception.BaseException;
import core.common.exception.ExceptionType;
import core.common.exception.beans.BaseExceptionDes;
import core.common.exception.beans.impl.BusinessExceptionDesc;

/**
 * Created by Administrator on 2017/5/23.
 */
public class BusinessException extends BaseException{

    public BusinessException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public BusinessExceptionDesc getDesc() {
        return (BusinessExceptionDesc) super.getDesc();
    }

    @Override
    public String defShortName() {
        return "业务异常";
    }

    @Override
    public ExceptionType defExceptionType() {
        return ExceptionType.BUSINESS_EXCEPTION;
    }
}
