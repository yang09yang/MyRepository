package com.jeff.sorm.bean;
/**
 * manage configure information
 * @author lcyan
 *
 */
public class Configuration {
	/**
	 * Driver
	 */
	private String driver;
	/**
	 * min size of pool
	 */
	private int poolMinSize;
	/**
	 * max size of pool
	 */
	private int poolMaxSize;
	/**
	 * url of jdbc
	 */
	private String url;
	/**
	 * user of database
	 */
	private String  user;
	/**
	 * password of database
	 */
	private String  pwd;
	/**
	 * using database
	 */
	private String UsingDB;
	/**
	 * path of source
	 */
	private	 String srcPath;
	/**
	 * class name for query using
	 */
	private String queryClass;

	/**
	 * package of class
	 */
	private String poPackage;
	
	
	public int getPoolMinSize() {
		return poolMinSize;
	}
	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}
	public int getPoolMaxSize() {
		return poolMaxSize;
	}
	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsingDB() {
		return UsingDB;
	}
	public void setUsingDB(String usingDB) {
		UsingDB = usingDB;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getPoPackage() {
		return poPackage;
	}
	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}
	public String getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}

	public Configuration(String driver, int poolMinSize, int poolMaxSize, String url, String user, String pwd,
			String usingDB, String srcPath, String queryClass, String poPackage) {
		super();
		this.driver = driver;
		this.poolMinSize = poolMinSize;
		this.poolMaxSize = poolMaxSize;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		UsingDB = usingDB;
		this.srcPath = srcPath;
		this.queryClass = queryClass;
		this.poPackage = poPackage;
	}
	public Configuration() {
		super();
	}
	
}
