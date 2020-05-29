package com.jeff.sorm.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jeff.sorm.core.DBManager;


public class DBConnPool {
	/**
	 * connection pool
	 */
	private static List<Connection> pool;
	/**
	 * max connection number
	 */
	private static final int POOL_MAX_SIZE=DBManager.getConf().getPoolMaxSize();
	/**
	 * min connection number
	 */
	private static final int POOL_MIN_SIZE=DBManager.getConf().getPoolMinSize();
	/**
	 * init pool
	 */
	public void initPool() {
		if(pool==null) {
			pool = new ArrayList<Connection>();
		}
		while (pool.size()<POOL_MIN_SIZE) {
			pool.add(DBManager.createConn());
			//System.out.println("connection number of the pool" +pool.size());
		}
	}
	/**
	 * constructor
	 */
	public DBConnPool() {
		// TODO Auto-generated constructor stub
		initPool();
	}
	/**
	 * get connection from pool
	 * @return
	 */
	public synchronized Connection getConnection() {
		int last_index=pool.size()-1;
		Connection conn=pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	public synchronized void closeConnection(Connection conn) {
		if(pool.size()>=POOL_MAX_SIZE) {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			pool.add(conn);
		}
	}
}
