package com.jeff.sorm.bean;
/**
 * the information of table
 * @author lcyan
 *
 */
public class ColumnInfo {
	/**
	 * column name
	 */
	private String name;
	/**
	 * data type
	 */
	private String dataType;
	/**
	 * key type
	 */
	private int keyType;
	public ColumnInfo(String name, String dataType, int keyType) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.keyType = keyType;
	}
	public ColumnInfo() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getKeyType() {
		return keyType;
	}
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}
	
}
