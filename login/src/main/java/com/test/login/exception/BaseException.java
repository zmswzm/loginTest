package com.test.login.exception;

import com.test.login.exception.beans.ExceptionDes;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:12:20
* 类说明
*/
public abstract class BaseException extends RuntimeException{

	private ExceptionDes desc;
	
	public BaseException(ExceptionDes desc){
		this.setDesc(desc);
	}

	public ExceptionDes getDesc() {
		return desc;
	}

	public void setDesc(ExceptionDes desc) {
		this.desc = desc;
	}
	
	public String getMessage(){
		return this.desc.toString();
	}
}
