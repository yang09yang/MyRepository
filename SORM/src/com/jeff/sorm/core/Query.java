package com.jeff.sorm.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jeff.sorm.bean.ColumnInfo;
import com.jeff.sorm.bean.TableInfo;
import com.jeff.sorm.utils.JDBCUtils;
import com.jeff.sorm.utils.ReflectUtils;

/**
 * query data (the core class of external service delivery)
 * @author lcyan
 *
 */
@SuppressWarnings("all")
public abstract class Query implements Cloneable{
	/**
	 * query model
	 * @return
	 */
	public Object excuteQueryTemplate(String sql, Object[] params,Class clazz, CallBack back) {
		Connection conn=DBManager.getConn();
		List list =null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			//set up sql parameter
			JDBCUtils.handleParams(ps, params);
			//System.out.println(ps);
			rs=ps.executeQuery();
			return back.doExecute(conn,ps,rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			DBManager.close(ps,conn);
		}
		
	
	
	}
	/**
	 * execute a DML statement
	 * @param sql statement
	 * @param params
	 * @return the number of the result after execute DML statement
	 */
	public int  excuteDML(String sql,Object[] params){
		Connection conn=DBManager.getConn();
		int count=0 ;
		PreparedStatement ps =null;
		try {
			ps=conn.prepareStatement(sql);
			//set up sql parameter
			JDBCUtils.handleParams(ps, params);
			count=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(ps,conn);
		}
		return count;
	}

	/**
	 * store a object into database;
	 * @param obj
	 */
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		//insert into emp (age,salary) values (?,?)
		Class c=obj.getClass();
		List<Object> params = new ArrayList<Object>();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		StringBuilder sql= new StringBuilder("insert into "+tableInfo.getTname()+" (");
		int countNotNullField =0;
		Field[] fs = c.getDeclaredFields();
		for(Field f:fs) {
			String fieldName=f.getName();
			Object fieldValue= ReflectUtils.invokeGet(fieldName, obj);
			if(fieldValue!=null) {
				countNotNullField++;
				sql.append(fieldName+",");
				params.add(fieldValue);
			}
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values (");
		for (int i = 0; i < countNotNullField; i++) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		excuteDML(sql.toString(),params.toArray());
	}

	/**
	 * delete data from table through id
	 * @param clazz
	 * @param id
	 * @return
	 */
	public void delete(Class clazz, Object id) {
		// TODO Auto-generated method stub
		TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
		ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey(); 
		String sql="delete from "+ tableInfo.getTname()+" where "+onlyPriKey.getName()+"=?";
		excuteDML(sql, new Object[] {id});
	}

/**
 * 	  delete data from table through id
 * @param obj
 */
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		Class c=obj.getClass();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey=tableInfo.getOnlyPriKey();
		Object priKeyValue = ReflectUtils.invokeGet(onlyPriKey.getName(), obj);
		delete(c,priKeyValue);
	}

	/**
	 * update data through field name
	 * @param obj
	 * @param fieldNames
	 * @return
	 */
	public int update(Object obj, String[] fieldNames) {
		// TODO Auto-generated method stub
		Class c=obj.getClass();
		List<Object> params = new ArrayList<Object>();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		ColumnInfo priKey=tableInfo.getOnlyPriKey();
		StringBuilder sql= new StringBuilder("update "+tableInfo.getTname()+" set ");
		for (String fname : fieldNames) {
			Object fvalue=ReflectUtils.invokeGet(fname, obj);
			params.add(fvalue);
			sql.append(fname+"=?,");
		}
		sql.setCharAt(sql.length()-1, ' ');
		sql.append(" where ");
		sql.append(priKey.getName()+"=? ");
		params.add(ReflectUtils.invokeGet(priKey.getName(), obj));
//		System.err.println(sql.toString());
		return excuteDML(sql.toString(), params.toArray());
	}

	/**
	 * receive multi-row and package the result into object of clazz
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List queryRows(final String sql, final Class clazz, final Object[] params) {
		Connection conn=DBManager.getConn();
		return (List)excuteQueryTemplate(sql, params, clazz, new CallBack() {
			
			@Override
			public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
				List list =null;
				try {
					ResultSetMetaData metaData=rs.getMetaData();
					//row
					while (rs.next()) {
						if(list==null) {
							list=new ArrayList();
						}
						Object rowObj=clazz.newInstance();
						//columns
						for (int i = 0; i < metaData.getColumnCount(); i++) {
							String columnName = metaData.getColumnLabel(i+1);
							Object columnValue = rs.getObject(i+1);
							ReflectUtils.invokeSet(rowObj, columnName, columnValue);
						}
						list.add(rowObj);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBManager.close(ps,conn);
				}
				return list;
			}
		});
	
	}
	/**
	 * query data by id
	 * @param clazz
	 * @param obj
	 * @return
	 */
	public Object queryById(Class clazz,Object id) {
		//select * from emp where id=?
		TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
		ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey(); 
		String sql="select * from "+ tableInfo.getTname()+" where "+onlyPriKey.getName()+"=?";
		return queryUniqueRow(sql, clazz,new Object[] {id});
	}

	/**
	 * receive one row and package the result into object of clazz
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		List list =queryRows(sql, clazz, params);
		return (list==null&&list.size()>0)?null:list.get(0);
	}

	/**
	 * query one value and return
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object queryValue(String sql, Object[] params) {
		return excuteQueryTemplate(sql, params, null, new CallBack() {
			
			@Override
			public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs) {
				Object value=null;
					try {
						while (rs.next()) {
						value=rs.getObject(1);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				return value;
			}
		});
	
	}

	/**
	 * retrun a number of result
	 * @param sql
	 * @param params
	 * @return
	 */
	public Number queryNumber(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return (Number)queryValue(sql, params);
	}
	/**
	 * query pagenate
	 * @param pageNum
	 * @param size
	 * @return
	 */
	public abstract Object queryPagenate(int pageNum,int size);
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
