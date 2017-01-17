package com.test.login.exception.beans;

import com.test.login.CommonUtil.CommonUtils;
import com.test.login.CommonUtil.CommonUtils.JsonUtil;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:46:50
* 类说明
*/
public abstract class ExceptionDes {
	
	private RESPONSE_TYPE responseType;
	private EXCEPTION_TYPE exceptionType;
	private String code;
	private String message;
	
	public ExceptionDes(){
		setResponseType(RESPONSE_TYPE.EXCEPTION);
	}
	
	public RESPONSE_TYPE getResponseType() {
		return responseType;
	}

	public void setResponseType(RESPONSE_TYPE responseType) {
		this.responseType = responseType;
	}

	public EXCEPTION_TYPE getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(EXCEPTION_TYPE exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return JsonUtil.object2Json(this);
	}

	protected static enum EXCEPTION_TYPE{
		SYSTEM, BUSINESS;
	}
	
	protected static enum RESPONSE_TYPE{
		RESULT, EXCEPTION;
	}

}
