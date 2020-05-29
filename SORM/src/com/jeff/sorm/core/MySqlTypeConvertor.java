package com.jeff.sorm.core;

/**
 * 
 * @author lcyan
 *
 */
public class MySqlTypeConvertor implements TypeConvertor{

	@Override
	public String database2JavaType(String columnType) {
		// TODO Auto-generated method stub
		if("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
			return "String ";
		}else if("int".equalsIgnoreCase(columnType)
					|| "tinyint".equalsIgnoreCase(columnType)
					|| "smallint".equalsIgnoreCase(columnType)
					|| "integer".equalsIgnoreCase(columnType)) {
			return "Integer ";
		}else if("bigint".equalsIgnoreCase(columnType)) {
			return "Long ";
		}else if("double".equalsIgnoreCase(columnType) || "float".equalsIgnoreCase(columnType)) {
		return "Double ";
		}else if("clob".equalsIgnoreCase(columnType)) {
			return "java.sql.CLob ";
		}else if("blob".equalsIgnoreCase(columnType)) {
			return "java.sql.BLob ";
		}else if("date".equalsIgnoreCase(columnType)) {
			return "java.sql.Date ";
		}else if("time".equalsIgnoreCase(columnType)) {
			return "java.sql.Time";
		}else if("timestamp".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp ";
		}
		return null;
	}

	@Override
	public String javaType2dattabase(String columnType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
