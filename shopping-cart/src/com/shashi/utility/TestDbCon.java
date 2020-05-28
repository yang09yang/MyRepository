package com.shashi.utility;
import java.sql.Connection;
import java.sql.DriverManager;
class DbUtil1 {
	private String dbUrl="jdbc:mysql://localhost:3306/shopping";
	private String name="root";
	private String password="0925y3357";
	private String jdbcName="com.mysql.jdbc.Driver";
	public Connection getCon()throws Exception{
	Class.forName(jdbcName);
	Connection con = DriverManager.getConnection(dbUrl,name,password);
	return con;
	}
	public void closeCon(Connection con)throws Exception{
	if(con!=null)
	{
	con.close();
	}
	}
}
public class TestDbCon {
	public static void main(String[] args) {
		DbUtil1 db = new DbUtil1();
		try {
			db.getCon();
			System.out.println("connect successful");
		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("connect successful");
		}
		}

		}

