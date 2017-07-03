package core.common.CommonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.common.base.GsonExclusionStrategy;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.internal.framework.FrameworkInternalException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
* @author :suwei
* @date 锛�016骞�鏈�鏃�涓婂崍11:49:55
* 绫昏鏄�
*/
public class CommonUtils {

	private CommonUtils() {
	}

	/**
	 * 文本工具类
	 */
	public static class TextUtil{


		public static boolean isNotEmpty(String text){
			return !isEmpty(text);
		}
		/**
		 * 判断字符串是否为空
		 */
		public static boolean isEmpty(String text){
			if(text == null){
				return true;
			}

			text = text.trim().toLowerCase().replace("'|\"", "");

			return "".equals(text) || "null".equals(text);
		}
	}

	public static class FileUtil{
		
		public static Properties loadProperties(String filePath) throws FrameworkInternalException {
			
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

		static{
			GsonBuilder builder = new GsonBuilder();
			builder.setDateFormat(DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm.toString());
			builder.setExclusionStrategies(new GsonExclusionStrategy());
			gson = builder.serializeNulls().create();

		}

		/**
		 * 获取 gson 对象
		 *
		 * @return
		 */
		public static Gson getGson(){

			return gson;
		}
		
		public static String object2Json(Object object){
			return gson.toJson(object);
		}
		
	}

	public static class DateUtil{

		public enum DATE_PATTERN{
			/**
			 * yyyyMMdd
			 */
			yyyyMMdd("yyyyMMdd", "^\\d{2,4}\\d{1,2}\\d{1,2}$"),

			/**
			 * yyyy/MM
			 */
			yyyy_MM("yyyy/MM", "^\\d{2,4}/\\d{1,2}$"),

			/**
			 * yyyy-MM
			 */
			yyyy_MM2("yyyy-MM", "^\\d{2,4}-\\d{1,2}$"),

			/**
			 * yyyy/MM/dd
			 */
			yyyy_MM_dd("yyyy/MM/dd", "^\\d{2,4}/\\d{1,2}/\\d{1,2}$"),

			/**
			 * yyyy-MM-dd
			 */
			yyyy_MM_dd2("yyyy-MM-dd", "^\\d{2,4}-\\d{1,2}-\\d{1,2}$"),

			/**
			 * yyyy/MM/dd HH:mm
			 */
			yyyy_MM_dd_HH_mm("yyyy/MM/dd HH:mm", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}$"),

			/**
			 * yyyy-MM-dd HH:mm
			 */
			yyyy_MM_dd_HH_mm2("yyyy-MM-dd HH:mm", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}$"),

			/**
			 * yyyy/MM/dd HH:mm:ss
			 */
			yyyy_MM_dd_HH_mm_ss("yyyy/MM/dd HH:mm:ss", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),

			/**
			 * yyyy-MM-dd HH:mm:ss
			 */
			yyyy_MM_dd_HH_mm_ss2("yyyy-MM-dd HH:mm:ss", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),

			/**
			 * yyyy/MM/dd HH:mm:ss.S
			 */
			yyyy_MM_dd_HH_mm_ss_S("yyyy/MM/dd HH:mm:ss.S", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,}$"),

			/**
			 * yyyy-MM-dd HH:mm:ss.S
			 */
			yyyy_MM_dd_HH_mm_ss_S2("yyyy-MM-dd HH:mm:ss.S", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,}$");

			private String value;

			private String pattern;

			private DATE_PATTERN(String value,String pattern){
				this.value = value;
				this.pattern = pattern;
			}

			@Override
			public String toString() {
				return value;
			}

			public static DATE_PATTERN getPatternBySample(String date){

				if(null != date){
					date = date.trim();

					for (DATE_PATTERN value : DATE_PATTERN.values()){
						if(date.matches(value.pattern)){
							return value;
						}
					}
				}
				throw new FrameworkInternalException(new SystemExceptionDesc("日期为空或是不支持的样本格式：" + date));
			}
		}

		public static String formatData(Date date,DATE_PATTERN datePattern){

			DateFormat df = new SimpleDateFormat(datePattern.toString());
			return df.format(date);
		}

		public static  Date parseDate(String date){
			if(null == date){
				return null;
			}

			date = date.trim();
			if (-1 != date.indexOf("中国标准时间") || -1 != date.indexOf("CST")){
				return new Date(Date.parse(date));
			}
			return parseDate(date,getPatternBySample(date));
		}

		public static Date parseDate(String date,DATE_PATTERN pattern){

			try{
				DateFormat df = new SimpleDateFormat(pattern.toString());
				return df.parse(date);
			}catch (Exception e){
				throw new FrameworkInternalException(new SystemExceptionDesc(e));
			}
		}

		public static DATE_PATTERN getPatternBySample(String date){

			if(null != date){

				date = date.trim();

				for (DATE_PATTERN value : DATE_PATTERN.values()){

					if (date.matches(value.pattern)){
						return value;
					}
				}
			}
			throw new FrameworkInternalException(new SystemExceptionDesc("日期为空或是不支持的样本格式：" + date));
		}

	}
}
