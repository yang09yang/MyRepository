package com.jeff.sorm.bean;
/**
 * 
 * @author lcyan
 *
 */

public class JavaFieldGetSet {
	/**
	 * source information of property
	 */
	private String FieldInfo;
	/**
	 * source information of get method
	 */
	private String getInfo;
	/**
	 * source information of set method
	 */
	private String setInfo;
	public String getFieldInfo() {
		return FieldInfo;
	}
	public void setFieldInfo(String fieldInfo) {
		FieldInfo = fieldInfo;
	}
	public String getGetInfo() {
		return getInfo;
	}
	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}
	public String getSetInfo() {
		return setInfo;
	}
	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}
	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		FieldInfo = fieldInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	}
	public JavaFieldGetSet() {
		super();
	}
	
}

