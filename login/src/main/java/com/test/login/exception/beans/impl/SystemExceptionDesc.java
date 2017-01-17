package com.test.login.exception.beans.impl;

import com.test.login.exception.beans.ExceptionDes;
import com.test.login.exception.impl.SystemException;
import com.test.login.exception.impl.internal.framework.FrameworkInternalException;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:47:45
* 类说明
*/
public class SystemExceptionDesc extends ExceptionDes{

	private Throwable throwable;
	
	public SystemExceptionDesc(Throwable throwable){
		setMessage("NONE");
		this.throwable = throwable;
		setExceptionType(EXCEPTION_TYPE.SYSTEM);
		setCode("NONE");
	}
	
	public SystemExceptionDesc(Throwable throwable, String code){
		setMessage("NONE");
		this.throwable = throwable;
		setExceptionType(EXCEPTION_TYPE.SYSTEM);
		setCode(code);
	}
	
	public SystemExceptionDesc(String message){
		setMessage(message);
		setExceptionType(EXCEPTION_TYPE.SYSTEM);
		setCode("NONE");
	}
	
	public SystemExceptionDesc(String message, Throwable throwable){
		setMessage(message);
		this.throwable = throwable;
		setExceptionType(EXCEPTION_TYPE.SYSTEM);
		setCode("NONE");
	}
	
	public SystemExceptionDesc(String message, String code, Throwable throwable){
		setMessage(message);
		this.throwable = throwable;
		setExceptionType(EXCEPTION_TYPE.SYSTEM);
		setCode(code);
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
	public static SystemException convertFromNativeException(Exception e){
		return new FrameworkInternalException(new SystemExceptionDesc(e));
	}
}
