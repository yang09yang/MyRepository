package com.jeff.sorm.utils;

import java.lang.reflect.Method;

/**
 * Reflect Utils
 * @author lcyan
 *
 */
public class ReflectUtils {
	/**
	 * invoke the get method of object corresponding fieldname property
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj) {
		try {
			Class c=obj.getClass();
			Method m=c.getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName),null);
			return m.invoke(obj, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void invokeSet(Object obj, String columnName,Object columnValue) {
		try {
			Class c=obj.getClass();
			if(columnValue!=null) {
				Method m=obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),
						columnValue.getClass());
				m.invoke(obj, columnValue);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
