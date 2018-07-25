package com.hx.hxfeima.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 字符串操作工具
 * @author abeir
 *
 */
public class StringUtils {
	
	/**
	 * 判断两个字符串是否相等，对null安全处理
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
        if (str1 == str2) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
	
	/**
	 * 是否为空值或空字符串
	 * @param str
	 * @return 空值或空字符串返回true
	 */
	public static boolean isBlank(String str){
		return str==null || "".equals(str);
	}
	
	/**
	 * 是否为非空值或非空字符串
	 * @param str
	 * @return 非空值或非空字符串返回true
	 */
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}

	/**
	 * 去除结尾的斜杠
	 * 
	 * @param path
	 * @return
	 */
	public static String trimEndSlash(String path) {
		if ("/".equals(path))
			return path;
		if (path.endsWith("/") || path.endsWith("\\")) {
			return path.substring(0, path.length() - 1);
		}
		return path;
	}

	/**
	 * 去除字符串起始的斜杠"/"
	 * 
	 * @param str
	 * @return
	 */
	public static String trimStartSlash(String str) {
		if ("/".equals(str))
			return str;
		if (str.startsWith("/"))
			return str.substring(1);
		return str;
	}

	/**
	 * 当hql需要返回map时，拼接指定的查询属性为返回map中的key
	 * 
	 * @param properties
	 *            需要查询的属性名
	 * @param prefix
	 *            查询的表名的别名
	 * @return
	 */
	public static String hqlSelectMap(String[] properties, String prefix) {
		for (int i = 0; i < properties.length; i++) {
			properties[i] = prefix + "." + properties[i] + " as "
					+ properties[i];
		}
		return org.apache.commons.lang3.StringUtils.join(properties, ",");
	}

	/**
	 * 将对象输出为json格式的字符串
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String toJSON(Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	/**
	 * 安全路径，能够正常使用带空格的路径
	 * 
	 * @param path
	 * @return
	 * @throws URISyntaxException
	 */
	public static String safedPath(String path) throws URISyntaxException {
		return new URI(path).getPath();
	}

	/**
	 * 检查字符是否是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查字符串是否是中文<br>
	 * 
	 * @param s
	 * @return 检查的字符串完全是中文时返回true
	 */
	public static boolean isChinese(String s) {
		boolean f = false;
		for (char c : s.toCharArray()) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
					f = true;
				} else {
					f = false;
					break;
				}
			} else {
				f = false;
				break;
			}
		}
		return f;
	}
	
	/**
	 * 判断字符串是否是布尔类型
	 * @param str 待判断的字符串
	 * @param loose 是否使用宽松模式
	 * @return
	 */
	public static boolean isBoolean(String str, boolean loose){
		if(isBlank(str))
			return false;
		
		String regex;
		if(loose){
			regex = "true|t|false|f";
			str = str.toLowerCase();
		}else{
			regex = "true|false";
		}
		return Pattern.compile(regex).matcher(str).matches();
	}
	
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		if(isBlank(str))
			return false;
		return Pattern.compile("\\d+?").matcher(str).matches();
	}
	

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String toFirstUpperCase(String str){
		if(isBlank(str))
			return str;
		StringBuilder s = new StringBuilder(str);
		s.setCharAt(0, Character.toUpperCase(s.charAt(0)));
		return s.toString();
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String toFirstLowerCase(String str){
		if(isBlank(str))
			return str;
		StringBuilder s = new StringBuilder(str);
		s.setCharAt(0, Character.toLowerCase(s.charAt(0)));
		return s.toString();
	}
	
	
	/**
	 * 驼峰式
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String toHumpCase(String str, char regex){
		if(isBlank(str))
			return str;
		
		int len = str.length();  
		StringBuilder sb = new StringBuilder(len);  
		for (int i = 0; i < len; i++) {  
			char c = str.charAt(i);  
			if (c == regex){  
				if (++i < len){  
					sb.append(Character.toUpperCase(str.charAt(i)));  
				}  
			}else{  
				sb.append(c);  
			}  
		}  
		return sb.toString();
	}
	
	public static String humpToLower(String str){
		if(isBlank(str))
			return str;
		String regex = "[A-Z]";  
		Matcher matcher = Pattern.compile(regex).matcher(str);  
		StringBuffer s = new StringBuffer();  
		while (matcher.find()) {  
		    String g = matcher.group();  
		    matcher.appendReplacement(s, "_" + g.toLowerCase());  
		}  
		matcher.appendTail(s);  
		if (s.charAt(0) == '_') {  
		    s.delete(0, 1);  
		} 
		return s.toString();
	}
	
	
	public static String humpToUpper(String str){
		if(isBlank(str))
			return str;
		return humpToLower(str).toUpperCase();
	}
	
	
	public static String findMatcher(String str, String regex){
		Matcher matcher = Pattern.compile(regex).matcher(str); 
		if(matcher.find()){
			return matcher.group();
		}
		return "";
	}
	
	/**
	 * 搜索关键词分词
	 * 
	 * @param key
	 * @return
	 */
	public static String getChineseFen(String key) {
		key = key.replace(" ", ""); // 去空格
		String newkey = "";
		int Len = key.length();
		int i = 0;
		while (i < Len) {
			newkey += "%" + key.charAt(i);
			i++;
		}
		return newkey;
	}
	
	
}
