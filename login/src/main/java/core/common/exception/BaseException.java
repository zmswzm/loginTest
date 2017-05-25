package core.common.exception;

import core.common.exception.beans.BaseExceptionDes;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:12:20
* 异常基类
*/
public abstract class BaseException extends RuntimeException{

	private BaseExceptionDes desc;
	
	public BaseException(BaseExceptionDes desc){
		this.setDesc(desc);
	}

	public BaseExceptionDes getDesc() {
		return desc;
	}

	public void setDesc(BaseExceptionDes desc) {
		this.desc = desc;
	}

	/**
	 * 定义简称
	 * @return
	 */
	public abstract String defShortName();

	/**
	 * 定义异常类型
	 * @return
	 */
	public abstract ExceptionType defExceptionType();

	public String getMessage(){
		return this.desc.toJson();
	}
}
