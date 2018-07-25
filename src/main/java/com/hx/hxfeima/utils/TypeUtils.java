package com.hx.hxfeima.utils;

/**
 * 类型测试工具
 * @author abeir
 *
 */
public class TypeUtils {

	/**
	 * 基本类型测试工具
	 * @author abeir
	 *
	 */
	public static class Basic{
		
		public static boolean isShort(Object obj){
			return obj instanceof Short;
		}
		
		public static boolean isInteger(Object obj){
			return obj instanceof Integer;
		}
		
		public static boolean isLong(Object obj){
			return obj instanceof Long;
		}
		
		public static boolean isFloat(Object obj){
			return obj instanceof Float;
		}
		
		public static boolean isDouble(Object obj){
			return obj instanceof Double;
		}
		
		public static boolean isBoolean(Object obj){
			return obj instanceof Boolean;
		}
		
		public static boolean isCharacter(Object obj){
			return obj instanceof Character;
		}
		
		public static boolean isByte(Object obj){
			return obj instanceof Byte;
		}
		
	}
	
	/**
	 * 测试两个类的类型是否相同
	 * @param testClass
	 * @param testClass1
	 * @return
	 */
	public static boolean is(Class testClass, Class testClass1){
		return testClass.equals(testClass1);
	}
	
}


