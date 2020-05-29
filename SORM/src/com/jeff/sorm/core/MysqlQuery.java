package com.jeff.sorm.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jeff.po.Emp;
import com.jeff.sorm.bean.ColumnInfo;
import com.jeff.sorm.bean.TableInfo;
import com.jeff.sorm.utils.JDBCUtils;
import com.jeff.sorm.utils.ReflectUtils;
import com.jeff.sorm.utils.StringUtils;
import com.jeff.vo.EmpVO;
 
/**
 * query data from mysql
 * @author lcyan
 *
 */

public class MysqlQuery extends Query {
	public static void testQueryValue() {
		Object obj = new MysqlQuery().queryValue("select count(*) from emp where salary>?",new Object[] {1000});
		System.out.println(obj);
		Number obj1 = new MysqlQuery().queryNumber("select count(*) from emp where salary>?",new Object[] {1000});
		System.out.println(obj);
		System.out.println(obj1.doubleValue());
		
	}
	public static void testDML() {
		Emp e=new Emp();
		e.setId(1);
		e.setAge(30);
		e.setSalary(3000.0);
		e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		new MysqlQuery().update(e, new String[] {"empname","age","salary"});
	}
	public static void testQuery() {
		List<Emp> list = new MysqlQuery().queryRows("select id,empname,age from emp where age>? and salary<? ",
				Emp.class,new Object[] {10,5000});
		System.out.println(list);
		for (Emp e : list) {
			System.out.println(e.getEmpname());
		}
		String sql2="select e.id,e.empname,salary 'wage', age,d.dname 'deptName',d.address 'deptAddr'"+ 
				   			"from emp e join dept d on e.deptId=d.id";
		List<EmpVO> list2 = new MysqlQuery().queryRows(sql2,EmpVO.class,null);
		for (EmpVO e : list2) {
			System.out.println(e.getEmpname()+"-"+e.getDeptAddr()+"-"+e.getWage());
		}
	}
	@Override
	public Object queryPagenate(int pageNum, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
