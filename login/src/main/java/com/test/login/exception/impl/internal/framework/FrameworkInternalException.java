package com.test.login.exception.impl.internal.framework;

import com.test.login.exception.beans.impl.SystemExceptionDesc;
import com.test.login.exception.impl.SystemException;

/**
* @author :suwei
* @date ：2016年2月2日 下午5:30:57
* 类说明
*/
public class FrameworkInternalException extends SystemException{

	public FrameworkInternalException(SystemExceptionDesc desc){
		super(desc);
	}
}
