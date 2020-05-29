package com.jeff.sorm.core;

/**
 * conversion of data types between classes and databases 
 * @author lcyan
 *
 */
public interface TypeConvertor {
	/**
	 * convert database type to java type
	 * @param columnType
	 * @return
	 */
	public String database2JavaType(String columnType);
	/**
	 * convert java type to database type
	 * @param columnType
	 * @return
	 */
	public String javaType2dattabase(String columnType);
	
}
