package com.jeff.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * JDBC UTILS
 * @author lcyan
 *
 */
public class JDBCUtils {
	/**
	 * 	set up sql parameter
	 * @param ps
	 * @param params
	 */
	public static void handleParams(PreparedStatement ps,Object[] params) {
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				try {
					ps.setObject(1+i, params[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
