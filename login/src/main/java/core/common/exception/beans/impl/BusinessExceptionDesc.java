package core.common.exception.beans.impl;

import core.common.exception.beans.BaseExceptionDes;

import java.util.HashMap;
import java.util.Map;

/**
* @author :suwei
* @date ：2016年2月2日 下午4:47:45
* 类说明
*/
public class BusinessExceptionDesc extends BaseExceptionDes{

	public enum SHOW_TYPE{

		/**
		 * 不显示
		 */
		NONE("none"),

		/**
		 * 信息提示
		 */
		INFO("info"),

		/**
		 * 警告提示
		 */
		WARN("warn"),

		/**
		 * 错误提示
		 */
		ERROR("error");

		private String value;

		private SHOW_TYPE(String value){
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	private SHOW_TYPE showType;

	public BusinessExceptionDesc(String code,String message,SHOW_TYPE showType){
		this.setCode(code);
		this.setMessage(message);
		this.showType = showType;
	}

	@Override
	public Map<String, Object> getSerializedData() {

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("showType",showType);
		data.put("exceptionType","BUSINESS");
		data.putAll(getBaseSerializedData());

		return data;
	}
}
