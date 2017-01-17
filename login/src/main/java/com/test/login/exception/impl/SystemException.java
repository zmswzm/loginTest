package com.test.login.exception.impl;

import com.test.login.exception.BaseException;
import com.test.login.exception.beans.impl.SystemExceptionDesc;

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
}
