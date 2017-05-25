package core.common.exception.beans;

import core.common.CommonUtil.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:46:50
* 异常描述基类
*/
public abstract class BaseExceptionDes {

	private String code;
	private String message;
	private Throwable throwable;

	public BaseExceptionDes(){}

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

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	protected Map<String,Object> getBaseSerializedData(){

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code",code);
		data.put("message",message);

		return data;
	}

	public abstract Map<String,Object> getSerializedData();

	public String toJson(){ return CommonUtils.JsonUtil.object2Json(getSerializedData());}
}
