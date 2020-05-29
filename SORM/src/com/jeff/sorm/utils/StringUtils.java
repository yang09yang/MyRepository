package com.jeff.sorm.utils;
/**
 * String utils
 * @author lcyan
 *
 */
public class StringUtils {
	public static String firstChar2UpperCase(String str) {
		return str.toUpperCase().substring(0,1)+str.substring(1);
		
	}
}
