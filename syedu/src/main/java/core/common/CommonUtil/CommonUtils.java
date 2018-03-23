package core.common.CommonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.common.base.GsonExclusionStrategy;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.internal.framework.FrameworkInternalException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
* @author :suwei
* @date 锛�016骞�鏈�鏃�涓婂崍11:49:55
* 绫昏鏄�
*/
public class CommonUtils {

	private CommonUtils() {
	}

	public static class SpringUtil{

		public static <T> T getBeanIfExist(String beanName, Class<T> beanType, ServletContext context){

			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			if(ctx != null && ctx.containsBean(beanName)){
				return ctx.getBean(beanName, beanType);
			}

			return null;
		}
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

		public static String getFileExtName(String fileName){

			if(fileName != null){

				int posi = fileName.lastIndexOf(".");

				return fileName.substring(posi + 1);
			}

			return null;
		}

		public static String generateUUID(){

			return UUID.randomUUID().toString().replaceAll("-", "");
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

	public static class SecurityUtil {

		private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

		/**
		 * MD5 加密类型
		 */
		public enum MD5_LENGTH {

			LENGTH_16(16),
			LENGTH_32(21);

			int length;

			MD5_LENGTH(int length) {

				this.length = length;
			}
		}

		/**
		 * 编码为 base64
		 *
		 * @param src
		 * @return
		 */
		public static String encodeForBase64(String src) {

			try {
				return Base64.encodeBase64String(src.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new FrameworkInternalException(new SystemExceptionDesc(e));
			}
		}

		/**
		 * 解码为 base64
		 *
		 * @param src
		 * @return
		 */
		public static String decodeForBase64(String src) {

			try {
				return new String(Base64.decodeBase64(src), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new FrameworkInternalException(new SystemExceptionDesc(e));
			}
		}

		/**
		 * MD5 摘要算法（16 位）
		 * <br/>
		 * 注意：包含汉字等特殊字符时，请使用 String MD5(String source, MD5_LENGTH length, Charset charset) 方法，并设置对应编码
		 *
		 * @return
		 */
		public static String MD5_16(String source) {

			return MD5(source, MD5_LENGTH.LENGTH_16, null);
		}

		/**
		 * MD5 摘要算法（32 位）
		 * <br/>
		 * 注意：包含汉字等特殊字符时，请使用 String MD5(String source, MD5_LENGTH length, Charset charset) 方法，并设置对应编码
		 *
		 * @return
		 */
		public static String MD5_32(String source) {

			return MD5(source, MD5_LENGTH.LENGTH_32, null);
		}

		/**
		 * MD5 摘要算法
		 * <br/>
		 * 注意：包含汉字等特殊字符时，请使用 String MD5(String source, MD5_LENGTH length, Charset charset) 方法，并设置对应编码
		 *
		 * @param source
		 * @param length
		 * @return
		 */
		public static String MD5(String source, MD5_LENGTH length) {

			return MD5(source, length, null);
		}

		/**
		 * MD5 摘要算法
		 *
		 * @param source
		 * @param length
		 * @param charset
		 * @return
		 */
		public static String MD5(String source, MD5_LENGTH length, Charset charset) {

			String result = "";
			try {

				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(charset == null ? source.getBytes() : source.getBytes(charset));
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				result = buf.toString();

				return length == MD5_LENGTH.LENGTH_32 ? result : buf.toString().substring(8, 24);
			} catch (NoSuchAlgorithmException e) {
				throw new FrameworkInternalException(new SystemExceptionDesc(e));
			}
		}
	}
}
