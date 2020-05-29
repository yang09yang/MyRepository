package com.jeff.sorm.core;

/**
 * 
 * @author lcyan
 *
 */
public class QueryFactory {
	//public Query createQuery();
//	private static QueryFactory factory=new QueryFactory();
	private static Query prototypeObj;
	static {
		System.out.println(TableContext.class);
		try {
			Class c=Class.forName(DBManager.getConf().getQueryClass());
			prototypeObj =(Query) c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private QueryFactory() {
		
	}
	
	public static Query createQuery() {
//		return new MysqlQuery();
		try {
			return (Query)prototypeObj.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
