package com.hx.hxfeima.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UtilTool {

	/**
	 * 判断字符串是否存在
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isExist(String str) {
		return (str != null && !"".equals(str.trim())) ? true : false;
	}

	/**
	 * 对象转换成String
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		return ((obj != null) ? obj.toString() : "");
	}

	/**
	 * 判断对象为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
		} else if (obj instanceof String && obj.equals("")) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() == -1) {
			return true;
		} else if (obj instanceof Long && ((Long) obj).longValue() == -1) {
			return true;
		} else if (obj instanceof Integer && ((Integer) obj).intValue() == -1) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof List && ((List) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 数组转换成字符，以逗号相连
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStrArr(Object[] obj) {
		String s = "";
		if (empty(obj)) {
			return s;
		}
		for (int i = 0; i < obj.length; i++) {
			s += obj[i] + ",";
		}
		if (s.lastIndexOf(",") != -1) {
			s = s.substring(0, s.lastIndexOf(","));
		}
		return s;
	}

	/**
	 * 判断List是否存在
	 * 
	 * @param list
	 * @return
	 */

	public static boolean isExistList(List list) {
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将List转换成需要的编号字符串
	 * 
	 * @param list
	 * @param key
	 * @return
	 */
	public static String listToStr(List<HashMap> list, String key,
			boolean stringType) {
		String tempStr = "";
		if (!isExistList(list) || !isExist(key)) {
			return null;
		}
		for (HashMap tempMap : list) {
			if (stringType) {
				tempStr = tempStr + "'" + tempMap.get(key) + "',";
			} else {
				tempStr = tempStr + tempMap.get(key) + ",";
			}
		}
		if (isExist(tempStr)) {
			tempStr = tempStr.substring(0, tempStr.length() - 1);
		}
		return tempStr;
	}

	/**
	 * 返回区域交易统计字段名
	 * 
	 * @param areaLevel
	 * @return
	 */
	public static String getAreaTradePro(int areaLevel) {
		String tempStr = null;
		if (areaLevel == 1) {
			tempStr = "prov_code";
		} else if (areaLevel == 2) {
			tempStr = "city_code";
		} else if (areaLevel == 3) {
			tempStr = "count_code";
		} else if (areaLevel == 4) {
			tempStr = "town_code";
		} else if (areaLevel == 5) {
			tempStr = "village_code";
		}
		return tempStr;
	}

	/**
	 * 返回机构交易统计字段名
	 * 
	 * @param areaLevel
	 * @return
	 */
	public static String getOrganTradePro(int areaLevel) {
		String tempStr = null;
		if (areaLevel == 2) {
			tempStr = "prov_code";
		} else if (areaLevel == 3) {
			tempStr = "brabch_code";
		} else if (areaLevel == 4) {
			tempStr = "city_code";
		} else if (areaLevel == 5) {
			tempStr = "site_code";
		}
		return tempStr;
	}


	/**
	 * 字符串替换
	 * 
	 * @param str
	 * @return
	 */
	public static String strReplaceSpeci(String str) {
		return str;
	}	
	
	/**
     * 
     * 对content的内容进行转换后，在作为oracle查询的条件字段值。使用/作为oracle的转义字符,比较合适。<br>
     * 既能达到效果,而且java代码相对容易理解，建议这种使用方式<br>
     * "%'" + content + "'%  ESCAPE '/' "这种拼接sql看起来也容易理解<br>
     * 
     * @param content
     * @return
     */
    public static String decodeSpecialChars(String content)
    {
        // 单引号是oracle字符串的边界,oralce中用2个单引号代表1个单引号
        String afterDecode = content.replaceAll("'", "''");
        // 由于使用了/作为ESCAPE的转义特殊字符,所以需要对该字符进行转义
        // 这里的作用是将"a/a"转成"a//a"
        afterDecode = afterDecode.replaceAll("/", "//");
        // 使用转义字符 /,对oracle特殊字符% 进行转义,只作为普通查询字符，不是模糊匹配
        afterDecode = afterDecode.replaceAll("%", "/%");
        // 使用转义字符 /,对oracle特殊字符_ 进行转义,只作为普通查询字符，不是模糊匹配
        afterDecode = afterDecode.replaceAll("_", "/_");
        return afterDecode;
    }
}
