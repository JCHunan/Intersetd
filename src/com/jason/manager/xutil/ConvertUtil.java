package com.jason.manager.xutil;

import com.jason.manager.utils.DynamicStringBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


 
/**
 * 数据类型转换工具类
 * 
 * 修改记录
 * 
 */
public class ConvertUtil {
	/**
	 * int转换成boolean是采用严格类型转换，只有BOOLEAN_TRUE_INT和BOOLEAN_FALSE_INT两个值才可以完成转换，<br/>
	 * 如对其它整数值进行转换将产生异常<p/>
	 * 
	 * 此参数由配置文件中boolean.int.map.strict项指定
	 */
	public static boolean BOOLEAN_INT_MAP_STRICT = true;
	
	/**
	 * 表示TRUE的整数值，默认为1<p/>
	 * 
	 * 此参数由配置文件中boolean.true.int项指定
	 */
	public static int BOOLEAN_TRUE_INT = 1;
	
	/**
	 * 表示FALSE的整数值，默认为0<p/>
	 * 
	 * 此参数由配置文件中boolean.false.int项指定
	 */
	public static int BOOLEAN_FALSE_INT = 0;
	
	/**
	 * 日期的字符串显示格式，用于字符串、日期相互转换等<p/>
	 * 
	 * 此参数由配置文件中date.format项指定
	 */
	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间的字符串显示格式，用于字符串、时间相互转换<p/>
	 * 
	 * 此参数有配置文件中time.format项指定
	 */
	public static String TIME_FORMAT = "HH:mm:ss";
	
	/**
	 * 日期时间的字符串显示格式，用于字符串、日期时间相互转换<p/>
	 * 
	 * 此参数有配置文件中date.time.format项指定
	 */
	public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
//	static{
//		try{
//			InputStream is = ConvertUtil.class.getClassLoader().getResourceAsStream("x/util/config.properties");
//			Properties prop = new Properties();
//			prop.load(is);
//			
//			try{
//				String v = prop.getProperty("boolean.int.map.strict");
//				ConvertUtil.BOOLEAN_INT_MAP_STRICT = Boolean.parseBoolean(v);
//			}catch(Exception e){}
//			
//			try{
//				String v = prop.getProperty("boolean.true.int");
//				ConvertUtil.BOOLEAN_TRUE_INT = Integer.parseInt(v);				
//			}catch(Exception e){}
//			
//			try{
//				String v = prop.getProperty("boolean.false.int");
//				ConvertUtil.BOOLEAN_FALSE_INT = Integer.parseInt(v);
//			}catch(Exception e){}
//			
//			try{
//				ConvertUtil.DATE_FORMAT = prop.getProperty("date.format");				
//			}catch(Exception e){}
//			
//			try{
//				ConvertUtil.TIME_FORMAT = prop.getProperty("time.format");
//			}catch(Exception e){}
//			
//			try{
//				ConvertUtil.DATE_TIME_FORMAT = prop.getProperty("date.time.format");
//			}catch(Exception e){}
//			
//		}catch(Exception e){
//			System.out.println("加载x通用类库配置文件失败，请检查com/x/util/config.properties文件中的配置是否正确");
//		}
//	}
	
	/**
	 * BigDecimal转换成Double
	 * @param v
	 */
	public static Double bigDecimal2Double(BigDecimal v){
		if(v == null){
			return new Double(0);
		}
		
		return new Double(v.doubleValue());
	}
	
	/**
	 * 
	 * @param v
	 * @param defaultNull
	 * @return
	 */
	public static Double bigDecimal2Double(BigDecimal v, boolean defaultNull){
		if(v == null){
			return defaultNull ? null : new Double(0);
		}
		
		return new Double(v.doubleValue());
	}
		
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static BigDecimal str2BigDecimal(String str){
		try {			
			return new BigDecimal(str);
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @param defaultNull
	 * @return
	 */
	public static BigDecimal str2BigDecimal(String str, boolean defaultNull){
		try {			
			return new BigDecimal(str);
		} catch (Exception e) {
			return defaultNull ? null : new BigDecimal(0);
		}
	}
	 
    /**
     * 对象转换成字符串，此方法只是将str强制转换成String类型，如果str为null，则返回空字符串。
     * 
     * @param str Object引用的字符串变量
     * @return String类型变量
     */
    public static String obj2str(Object str){
    	if(str == null)
    		return "";
    	return str.toString();
    }
    
    public static String obj2str(Object str, boolean defaultNull){
    	if(str == null){
    		return defaultNull ? null : "";
    	}
    	return str.toString();
    }
    
    /**
	 * 字符串转换成布尔型
	 * 
	 * @param str 字符串格式的布尔值
	 * @return 布尔类型值，如果转换失败则返回false
	 */
	public static boolean str2bool(String str){
		try{
			return Boolean.parseBoolean(str);			
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 字符串转换成布尔型变量，如果str==null或转换时发生错误都会返回false
	 * 
	 * @param str Object引用的字符串格式的布尔值，首先将str强制转换成String类型，然后再执行转换
	 * @return 布尔类型值，如果转换失败则返回false
	 */
	public static boolean str2bool(Object str){
		if(str == null){
			return false;
		}
		
		return str2bool((String)str);
	}
	
	/**
	 * 字符串转换成布尔型
	 * 
	 * @param str 字符串格式的布尔值
	 * @return 布尔类型值，如果转换失败则返回值为false的java包装类型Boolean对象
	 */
	public static Boolean str2Boolean(String str){
		return str2Boolean(str, false);
	}
	
	/**
	 * 字符串转换成Boolean类型,如果str==null或转换时发生错误都返回false
	 * 
	 * @param str Object应用的字符串格式的布尔值，首先将str强制转换成String类型，然后再执行转换
	 * @return Boolean类型值，如果转换失败则返回false的java包装类型Boolean对象
	 */
	public static Boolean str2Boolean(Object str){
		if(str == null){
			return new Boolean(false);
		}
		return str2Boolean((String)str, false);
	}
	
	/**
	 * 字符串转换成Boolean类型
	 * 
	 * @param str 字符串格式的布尔值
	 * @param defaultNull 转换失败时，是否返回null
	 * @return Boolean类型值，转换失败后返回的值defaultNull有关，如果defaultNull == true则返回null, 否则返回0
	 */
	public static Boolean str2Boolean(String str, Boolean defaultNull){
		try{			
			return Boolean.valueOf(str);
		}catch(Exception e){
			return defaultNull ? null : new Boolean(false);
		}
	}
	
	/**
	 * 字符串转换成Boolean类型
	 * 
	 * @param str 字符串格式的布尔值,首先将str强制转换成String类型，然后再执行转换
	 * @param defaultNull 转换失败时，是否返回null
	 * @return Boolean类型值，转换失败后返回的值defaultNull有关，如果defaultNull == true则返回null, 否则返回0
	 */
	public static Boolean str2Boolean(Object str, Boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Boolean(false);
		}
		return str2Boolean(str.toString(), defaultNull);
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值
	 * @return byte类型值，转换失败返回0
	 */
	public static byte str2byte(String str){
		try {
			return Byte.parseByte(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值
	 * @return byte类型值，转换失败返回0
	 */
	public static byte str2byte(Object str){
		if(str == null){
			return 0;
		}
		
		return str2byte(str.toString());
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值
	 * @return byte类型值，转换失败返回0
	 */
	public static Byte str2Byte(String str){
		try{
			return Byte.valueOf(str);
		}catch(Exception e){
			return new Byte((byte)0);
		}		
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值
	 * @return byte类型值，转换失败返回0
	 */
	public static Byte str2Byte(Object str){
		if(str == null){
			return null;
		}
		
		return str2Byte(str.toString());
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值
	 * @return byte类型值，转换失败时返回的值域defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Byte str2Byte(String str, boolean defaultNull){
		try{
			return new Byte(str);
		}catch(Exception e){
			return defaultNull ? null : new Byte((byte)0);
		}
	}
	
	/**
	 * 字符串转换成比特型
	 * 
	 * @param str 字符串格式的byte值，首先将str强制转换成String类型，然后再执行转换
	 * @return byte类型值，转换失败时返回的值域defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Byte str2Byte(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Byte((byte)0);
		}
		
		return str2Byte(str.toString(), defaultNull);
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值
	 * @return byte类型值，转换失败返回0
	 */
	public static short str2short(String str){
		try{
			return Short.parseShort(str);
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值，首先将str强制转换成String类型，然后再执行转换
	 * @return byte类型值，转换失败返回0
	 */
	public static short str2short(Object str){
		if(str == null){
			return 0;
		}
		
		return str2short(str.toString());
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值
	 * @return byte类型值，转换失败返回0
	 */
	public static Short str2Short(String str){
		try{
			return Short.valueOf(str);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值，首先将str强制转换成String类型，然后再执行转换
	 * @return byte类型值，转换失败返回0
	 */
	public static Short str2Short(Object str){
		if(str == null){
			return null;
		}
		return str2Short(str.toString());
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值
	 * @return byte类型值，转换失败时返回的值域defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Short str2Short(String str, boolean defaultNull){
		try{
			return Short.valueOf(str);
		}catch(Exception e){
			return defaultNull ? null : new Short("0");
		}		
	}
	
	/**
	 * 字符串转换成短整型
	 * 
	 * @param str 字符串格式的short值，首先将str强制转换成String类型，然后再执行转换
	 * @return byte类型值，转换失败时返回的值域defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Short str2Short(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Short("0"); 
		}
		
		return str2Short(str.toString(), defaultNull);
	}
	
    /**
	 * 字符串转换成整型
	 * 
	 * @param str 字符串格式的整数值
	 * @return int类型值，如果转换失败，则返回0
	 */
	public static int str2int(String str){
		try {
			return Integer.parseInt(str.trim());
		} catch (Exception e) {
			return 0;
		}		
	}
	
	/**
	 * 字符串转换成整型
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换
	 * @return int类型值，如果转换失败，则返回0
	 */
	public static int str2int(Object str){
		if(str == null){
			return 0;
		}
		
		return str2int(str.toString());
	}
	
	/**
	 * 字符串转换成Integer 
	 * 
	 * @param str 字符串格式的整数值
	 * @return Integer类型值，如果转换失败，则返回0的Integer值
	 */
	public static Integer str2Integer(String str){
		if(str == null)
			return null;
		
		return str2Integer(str, null);
	}
	
	/**
	 * 字符串转换成Integer 
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换
	 * @return Integer类型值，如果转换失败，则返回0的Integer值
	 */
	public static Integer str2Integer(Object str){
		if(str == null)
			return null;
		
		return str2Integer(str.toString(), null);
	}
    
	/**
	 * 字符串转换成Integer
	 * 
	 * @param str 字符串格式的整数值
	 * @param defaultNull true表示转换失败时返回null, 否则返回0的Integer值
	 * @return Integer类型值
	 */
//	public static Integer str2Integer(String str, boolean defaultNull){
//		try{
//			return Integer.valueOf(str);
//		}catch(Exception e){
//			return defaultNull ? null : new Integer(0);
//		}
//	}
	
	public static Integer str2Integer(String str, Integer defaultValue){
		try{
			return Integer.valueOf(str);
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	public static Integer str2Integer(Object str, Integer defaultValue){
		if(str == null)
			return defaultValue;
		
		return str2Integer(str.toString(), defaultValue);
	}
	
	/**
	 * 字符串转换成Integer
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换
	 * @param defaultNull true表示转换失败时返回null, 否则返回0的Integer值
	 * @return Integer类型值
	 */
//	public static Integer str2Integer(Object str, boolean defaultNull){
//		if(str == null){
//			return defaultNull ? null : new Integer(0);
//		}
//		
//		return str2Integer(str.toString(), defaultNull);
//	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值	
	 * @return 长整型值,转换失败则返回0
	 */
	public static long str2long(String str){
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换
	 * @return 长整型值，转换失败则返回0
	 */
	public static long str2long(Object str){
		if(str == null){
			return 0L;
		}
		
		return str2long(str.toString());
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值	
	 * @return 长整型值，转换失败则返回0
	 */
	public static Long str2Long(String str){
		try{
			return Long.valueOf(str);
		}catch(Exception e){
			return new Long(0);
		}
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换	
	 * @return 长整型值，转换失败则返回0
	 */
	public static Long str2Long(Object str){
		if(str == null){
			return new Long(0);
		}
		
		return str2Long(str);
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值	
	 * @return 长整型值，转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Long str2Long(String str, boolean defaultNull){
		try{
			return Long.valueOf(str);
		}catch(Exception e){
			return defaultNull ? null : new Long(0);
		}
	}
	
	public static Long str2Long(String str, Long defaultValue){
		try{
			return Long.valueOf(str);
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 字符串转换成长整型
	 * 
	 * @param str 字符串格式的整数值，首先将str强制转换成String类型，然后再执行转换
	 * @return 长整型值，转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Long str2Long(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Long(0);
		}
		
		return str2Long(str.toString(), defaultNull);
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数
	 * @return 单精度浮点数,如果转换失败则返回0
	 */
	public static float str2float(String str){
		try {
			return Float.parseFloat(str.toString());
		} catch (Exception e) {
			return 0F;
		}
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 单精度浮点数,如果转换失败则返回0
	 */
	public static float str2float(Object str){
		if(str == null){
			return 0F;
		}
		
		return str2float(str.toString());
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数
	 * @return 单精度浮点数,如果转换失败则返回0
	 */
	public static Float str2Float(String str){
		try{
			return Float.valueOf(str);
		}catch(Exception e){
			return new Float(0);
		}
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 单精度浮点数,如果转换失败则返回0
	 */
	public static Float str2Float(Object str){
		if(str == null){
			return new Float(0);
		}
		
		return str2Float(str.toString());
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数
	 * @return 单精度浮点数,转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Float str2Float(String str, boolean defaultNull){
		try{
			return Float.valueOf(str);
		}catch(Exception e){
			return defaultNull ? null : new Float(0);
		}		
	}
	
	/**
	 * 字符串转换成浮点数
	 * 
	 * @param str 字符串格式的浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 单精度浮点数,转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Float str2Float(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Float(0);
		}
		
		return str2Float(str.toString(), defaultNull);
	}
		
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数
	 * @return 转换后得到的双精度浮点数，如果转换失败则返回0
	 */
	public static double str2double(String str){
		try {
			String nStr = str.replaceAll(",", "");
			
			return Double.parseDouble(nStr);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 转换后得到的双精度浮点数，如果转换失败则返回0
	 */
	public static double str2double(Object str){
		if(str == null){
			return 0;
		}
		
		return str2double(str.toString());		
	}
	
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数
	 * @return 转换后得到的双精度浮点数，如果转换失败则返回0
	 */
	public static Double str2Double(String str){
		try{
			return Double.valueOf(str);
		}catch(Exception e){
			return new Double(0);
		}
	}
	
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 转换后得到的双精度浮点数，如果转换失败则返回0
	 */
	public static Double str2Double(Object str){
		if(str == null){
			return new Double(0);
		}
		
		return str2Double(str.toString());
	}
	
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数
	 * @return 转换后得到的双精度浮点数，转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Double str2Double(String str, boolean defaultNull){
		try{
			return Double.valueOf(str);
		}catch(Exception e){
			return defaultNull ? null : new Double(0);
		}
	}
	
	public static Double str2Double(String str, Double defaultValue){
		try{
			return Double.valueOf(str);
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 字符串转换成双精度浮点型
	 * 
	 * @param str 字符串格式的双精度浮点数，首先将str强制转换成String类型，然后再执行转换
	 * @return 转换后得到的双精度浮点数，转换失败时返回的值类型与defaultNull有关，如果defaultNull==null则返回null，否则返回0
	 */
	public static Double str2Double(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Double(0);
		}
		
		return str2Double(str.toString());
	}
    
    /**
     * 异常对象转换程字符串
     * 
     * @param e 异常对新能够
     * @return  异常对象的异常消息
     */
    public static String throwable2str(Throwable e){
    	StringWriter sw = new StringWriter();
    	PrintWriter pwriter = new PrintWriter(sw);
    	e.printStackTrace(pwriter);
    	return sw.toString();
    }
     
    /**
     * 日期转换成字符串，日期格式由常量DATE_FORMAT指定
     * 
     * @param value 需要转换的日期对象
     * @return 日期的字符串，转换失败时返回空字符串("")
     */
    public static String date2str(Date date){
    	try{
    		SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_FORMAT);
    		return format.format(date);
    	}catch(Exception e){
    		return "";
    	}    	
    }
    
    /**
     * 日期转换成字符串，日期格式由常量DATE_FORMAT指定<br/>
     * 
     * @param date 需要转换的日期对象，由Object类型引用，转换时会强制将date转换成Date类型然后再转换，
     *             如果date的原始类型不是Date，在进行强制转换时将抛出异常
     * @return 日期的字符串表示，转换失败时返回空字符串("")
     */
    public static String date2str(Object date){
    	if(date == null){
    		return "";
    	}
    	
    	return date2str((Date)date);
    }
    
    /**
     * 时间转换成字符串，时间格式由常量TIME_FORMAT指定
     * 
     * @param value 需要转换的日期值
     * @return 时间的字符串
     */
    public static String time2str(Date time){
    	try {
    		SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.TIME_FORMAT);
    		return format.format(time);
		} catch (Exception e) {
			return "";
		}
    }
    
    /**
     * 时间转换成字符串，时间格式由常量TIME_FORMAT指定
     * 
     * @param time 需要转换的日期对象，由Object类型引用，转换时会强制将date转换成Date类型然后再转换，
     *             如果date的原始类型不是Date，在进行强制转换时将抛出异常
     * @return 日期的字符串表示，转换失败时返回空字符串("")
     */
    public static String time2str(Object time){
    	if(time == null){
    		return "";
    	}
    	
    	return time2str((Date)time);
    }
    
    /**
     * 日期时间转换成字符串，日期格式由常量DATE_TIME_FORMAT指定
     * 
     * @param value 需要转换的日期值
     * @return 日期时间的字符串，转换失败时返回空字符串("")
     */
    public static String datetime2str(Date datetime){
    	try {
    		SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
    		return format.format(datetime);
		} catch (Exception e) {
			return "";
		}
    }
    
    /**
     * 日期时间转换成字符串，日期格式由常量DATE_TIME_FORMAT指定
     * 
     * @param value 需要转换的日期值
     * @return 日期时间的字符串，转换失败时返回空字符串("")
     */
    public static String datetime2str(Object datetime){
    	if(datetime == null){
    		return "";
    	}
    	
    	return datetime2str((Date)datetime);
    }
    
    /**
     * 按照指定的日期格式格式化日期为字符串
     * 
     * @param date 需要转换的日期
     * @param format 日期格式字符串
     * @return 转换后的日期字符串
     */
    public static String date2str(Date date, String format){
    	try{
    		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    		return dateFormat.format(date);
    	}catch(Exception e){
    		return "";
    	}
    }
    
    /**
	 * 字符串转换成日期型
	 * 
	 * @param str 字符串格式的日期
	 * @return
	 */
	public static Date str2date(String str){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			return new Date(0);
		}
	}
	
	/**
	 * 字符串转换成日期型
	 * 
	 * @param str 字符串格式的日期
	 * @return
	 */
	public static Date str2date(Object str){
		if(str == null){
			return new Date(0);
		}
		
		return str2date((String)str);
	}
	
	/**
	 * 字符串转换成日期型
	 * 
	 * @param str 字符串格式的日期
	 * @param defaultNull 转换失败时是否返回null
	 * @return
	 */
	public static Date str2date(String str, boolean defaultNull){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			return defaultNull ? null : new Date(0);			
		}
	}
	
	public static Date str2date(String str, Date defaultValue){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			return defaultValue;	
		}
	}
	
	/**
	 * 字符串转换成日期型
	 * 
	 * @param str 字符串格式的日期
	 * @param defaultNull 转换失败时是否返回null
	 * @return
	 */
	public static Date str2date(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Date(0);
		}
		
		return str2date((String)str, defaultNull);
	}
	
	public static Date str2date(Object str, Date defaultValue){
		return str2date((String)str, defaultValue);
	}
    
	/**
	 * 字符串转换成时间型
	 * @param str
	 * @return
	 */
	public static Date str2time(String str){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.TIME_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			return new Date(0);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2time(Object str){
		if(str == null){
			return new Date(0);
		}		
		
		return str2time((String)str);
	}
	
	/**
	 * 
	 * @param str
	 * @param defaultNull
	 * @return
	 */
	public static Date str2time(String str, boolean defaultNull){		
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.TIME_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			return defaultNull ? null : new Date(0);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @param defaultNull
	 * @return
	 */
	public static Date str2time(Object str, boolean defaultNull){
		if(str == null){
			return defaultNull ? null : new Date(0);
		}
		return str2time((String)str, defaultNull);
	}
	
	/**
	 * 字符串转换成日期时间型
	 * @param str
	 * @return
	 */
	public static Date str2datetime(String str, boolean... throwErr){
		try {
			if(str == null){
				return null;
			}
			
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
			return format.parse(str.toString());
		} catch (Exception e) {
			if(throwErr.length > 0)
				throw new UtilException(e);
			else
				return new Date(0);
		}
	}	
	
	/**
	 * 
	 * @param str
	 * @return
	 */
//	public static Date str2datetime(Object str){
//		if(str == null){
//			return new Date(0);
//		}
//		return str2datetime((String)str);
//	}
	
	/**
	 * 
	 * @param str
	 * @param defaultNull
	 * @return
	 */
//	public static Date str2datetime(String str, boolean defaultNull){
//		try {
//			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
//			return format.parse(str.toString());
//		} catch (Exception e) {
//			return defaultNull ? null : new Date(0);
//		}
//	}
//	
//	public static Date str2datetime(String str, Date defaultValue){
//		try {
//			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
//			return format.parse(str.toString());
//		} catch (Exception e) {
//			return defaultValue;
//		}
//	}
	
	public static Timestamp str2timestamp(String str, boolean defaultNull){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
			Date date = format.parse(str.toString());
			Timestamp stamp = new Timestamp(date.getTime());
			return stamp;
		} catch (Exception e) {
			return defaultNull ? null : new Timestamp(0);
		}
	}
	
	public static Timestamp str2timestamp(String str, Timestamp defaultValue){
		try {
			SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_TIME_FORMAT);
			Date date = format.parse(str.toString());
			Timestamp stamp = new Timestamp(date.getTime());
			return stamp;
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
//	public static byte[] int2bytes(int v){
//		byte[] ret = new byte[4];
//		
//		ret[0] = v
//		
//		return ret;
//	}
	
	/**
	 * 
	 * @param str
	 * @param defaultNull
	 * @return
	 */
//	public static Date str2datetime(Object str, boolean defaultNull){
//		if(str == null){
//			return defaultNull ? null : new Date(0);
//		}
//		
//		return str2datetime((String)str, defaultNull);
//	}
	
	/**
	 * 毫秒转换成字符串
	 * 
	 * @param millis
	 * @return
	 */
	private static final int DAY_MOD = 1000 * 60 * 60 * 24;
	private static final int HOUR_MOD = 1000 * 60 * 60;
	private static final int MINUTE_MOD = 1000 * 60;
	private static final int SECOND_MOD = 1000;
	public static String millis2str(long millis){
		
		long day = millis / DAY_MOD;
		millis -= day * DAY_MOD;
		
		long hour = millis / HOUR_MOD;
		millis -= hour * HOUR_MOD;
		
		long minute = millis / MINUTE_MOD;
		millis -= minute * MINUTE_MOD;
		
		long second = millis / SECOND_MOD;
		millis -= second * SECOND_MOD;
		
		DynamicStringBuilder builder = new DynamicStringBuilder();
		builder.append(day, "天", 
				hour, "小时",
				minute, "分",
				second, "秒",
				millis, "毫秒");
		
		
		return builder.toString();
	}
	
    /**
     * 布尔型转换成整数型
     * 
     * @param value 布尔类型值
     * @return 对应的整数值，布尔与整数值的对应关系可以在在config.properties文件中配置
     */
    public static int bool2int(boolean value){
    	return value ? ConvertUtil.BOOLEAN_TRUE_INT : ConvertUtil.BOOLEAN_FALSE_INT;
    }
    
    /**
     * 整数型转换成布尔型,当启用严格对应模式时，整数型参数必须是常量BOOLEAN_TRUE_INT或BOOLEAN_FALSE_INT,
     * 如果不是将抛出异常
     * 
     * @param value 整数类型值
     * @return 对应的布尔值，布尔与整数值的对应关系可以在在config.properties文件中配置
     */
    public static boolean int2bool(int value){   
    	if(ConvertUtil.BOOLEAN_INT_MAP_STRICT){
    		if(value != ConvertUtil.BOOLEAN_TRUE_INT && value != ConvertUtil.BOOLEAN_FALSE_INT){
    			throw new UtilException("在严格模式下，整数值【" + value + "】无法映射成boolean类型");
    		}
    	}
    	return value == ConvertUtil.BOOLEAN_FALSE_INT ? false : true;
    }     
    
    /**
     * 将颜色RGB字符串转换成10进制int值
     * 
     * @param colorStr RGB颜色值，如：#ff0000, 开头的#有或没有都可以
     * @return 颜色的int值，默认返回0
     */
    public static int color2int(String colorStr, int... defaultValue){
    	//返回默认值
    	if(colorStr == null || colorStr.length() == 0){
    		return defaultValue.length > 0 ? defaultValue[0] : 0;
    	}
    	
    	String str;
    	if(colorStr.startsWith("#")){
    		if(colorStr.length() > 1){
    			str = colorStr.substring(1);
    		}else{
    			str = "";
    		}
    	}else{
    		str = colorStr;
    	}
    	
    	try{
    		int v = Integer.valueOf(str, 16);
    		return v;
    		
    	}catch(NumberFormatException e){
    		if(defaultValue.length > 0){
    			return defaultValue[0];
    		}else{
    			return 0;
    		}
    	}
    }

    public static void main(String[] args)throws Exception{
    	String str = "2011-13-12";
		SimpleDateFormat format = new SimpleDateFormat(ConvertUtil.DATE_FORMAT);
		Date d = format.parse(str.toString());
		System.out.println(d);
		
    }
}
