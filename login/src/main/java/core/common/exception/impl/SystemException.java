package core.common.exception.impl;

import core.common.exception.BaseException;
import core.common.exception.ExceptionType;
import core.common.exception.beans.impl.SystemExceptionDesc;

/**
* @author :suwei
* @date ：2016年2月2日 下午5:05:23
* 类说明
*/
public abstract class SystemException extends BaseException {

	public SystemException(SystemExceptionDesc desc){
		super(desc);
	}
	
	public SystemExceptionDesc getDesc(){
		return (SystemExceptionDesc) super.getDesc();
	}

	@Override
	public String defShortName() {
		return "系统异常";
	}

	@Override
	public ExceptionType defExceptionType() {
		return ExceptionType.SYSTEM_EXCEPTION;
	}
}
