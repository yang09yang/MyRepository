package com.jeff.sorm.core;
/**
 * based on configuration information to manage the link of object
 * @author lcyan
 *
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jeff.sorm.bean.Configuration;
/**
 * 
 * @author lcyan
 *
 */
import com.jeff.sorm.pool.DBConnPool;
public class DBManager {
	/**
	 * configuration info
	 */
	private static Configuration conf;
	/**
	 * init pool
	 */
	private static DBConnPool pool;
	static {
		Properties pros= new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf =new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
//		System.out.println(TableContext.class);
	}
	public static Connection getOracleConn() {
		return null;
	}
	/**
	 * get connection
	 * @return
	 */
	public static Connection getConn() {
		if (pool==null) {
			pool=new DBConnPool();
		}
		return pool.getConnection();
	}
	/**
	 * create connection
	 * @return
	 */
	public static Connection createConn() {
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} 
	}
	public static Configuration getConf() {
		return conf;
	}
	/**
	 * close connection 
	 * @param conn
	 */
	public static void close(Connection conn) {
		pool.closeConnection(conn);
	}
	/**
	 * close statement and connection
	 * @param ps
	 * @param conn
	 */
	public static void close(Statement ps, Connection conn) {
		try {
			if(ps!=null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.closeConnection(conn);
	}
	/**
	 * close resultset and statement and connection
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement ps, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(ps!=null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			if(conn!=null) {
//				conn.close();
//			}
			pool.closeConnection(conn);
	}
}
