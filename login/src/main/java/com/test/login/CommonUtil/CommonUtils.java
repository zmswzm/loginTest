package com.test.login.CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;
import com.test.login.exception.beans.impl.SystemExceptionDesc;
import com.test.login.exception.impl.internal.framework.FrameworkInternalException;

/**
* @author :suwei
* @date 锛�016骞�鏈�鏃�涓婂崍11:49:55
* 绫昏鏄�
*/
public class CommonUtils {

	private CommonUtils() {
	}
	
	public static class FileUtil{
		
		public static Properties loadProperties(String filePath) throws FrameworkInternalException{
			
			InputStream is = null;
			Properties properties = null;
			try {
				is = new FileInputStream(new File(filePath));
				properties = new Properties();
				
				properties.load(is);
				
			}catch (IOException e) {
				throw new FrameworkInternalException(new SystemExceptionDesc("系统异常",e));
			}finally {
				try {
					if(is != null){
						is.close();
					}
				} catch (Exception e2) {
					throw new FrameworkInternalException(new SystemExceptionDesc("系统异常",e2));
				}
			}
			
			return properties;
		}
	}
	
	public static class JsonUtil{
		
		private static Gson gson;
		
		public static String object2Json(Object object){
			return gson.toJson(object);
		}
		
	}
}
