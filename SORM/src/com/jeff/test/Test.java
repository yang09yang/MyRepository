package com.jeff.test;

import java.util.List;

import com.jeff.po.Emp;
import com.jeff.sorm.core.MysqlQuery;
import com.jeff.sorm.core.Query;
import com.jeff.sorm.core.QueryFactory;
import com.jeff.vo.EmpVO;

public class Test {
	public static void test01() {
		Query q=QueryFactory.createQuery();
		String sql2="select e.id,e.empname,salary 'wage', age,d.dname 'deptName',d.address 'deptAddr'"+ 
	   			"from emp e join dept d on e.deptId=d.id";
		@SuppressWarnings("unchecked")
		List<EmpVO> list2 =q.queryRows(sql2,EmpVO.class,null);
		for (EmpVO e : list2) {
		System.out.println(e.getEmpname()+"-"+e.getDeptAddr()+"-"+e.getWage());
		}
	}
	public static void test02() {

		List<Emp> list = new MysqlQuery().queryRows("select id,empname,age from emp where age>? and salary<? ",
				Emp.class,new Object[] {10,5000});
		System.out.println(list);
		for (Emp e : list) {
			System.out.println(e.getEmpname());
		}
	
	}
	public static void testadd() {
		Emp e=new Emp();
//		e.setId(8);
		e.setAge(19);
		e.setEmpname("aili");
		e.setSalary(2000.2);
		Query q=QueryFactory.createQuery();
		q.insert(e);
	}
	public static void testdelete() {
		Emp e=new Emp();
		e.setId(8);

		Query q=QueryFactory.createQuery();
		q.delete(e);
	}
	public static void update() {
		Emp e=new Emp();
		e.setId(9);
		e.setAge(22);
		e.setEmpname("22");
		e.setSalary(2330.2);
		Query q=QueryFactory.createQuery();
		q.update(e, new String[] {"age","salary"});
	}
	public static void select01() {
		Query q=QueryFactory.createQuery();
		Number n= q.queryNumber("select count(*) from emp where salary>?", new Object[] {100});
		System.out.println(n);
	}
	public static void select02() {
		Query q=QueryFactory.createQuery();
		Emp e=(Emp) q.queryUniqueRow("select * from emp where id=?", Emp.class, new Object[] {3});
		System.out.println(e.getEmpname());
	}
	public static void select03() {
		Query q=QueryFactory.createQuery();
		List<Emp> list=(List) q.queryRows("select * from emp where id>?", Emp.class, new Object[] {2});
		for (Emp emp : list) {
			System.out.println(emp.getEmpname());
			
		}
	}
	public static void select04() {
		Query q=QueryFactory.createQuery();
		String sql="select e.id,e.empname,e.age,d.dname 'deptName',d.address 'deptAddr'  from emp e join dept d on e.deptId=d.id ";
		List<EmpVO> list=(List) q.queryRows(sql, EmpVO.class, new Object[] {});
		for (EmpVO emp : list) {
			System.out.println(emp.getEmpname()+"-"+emp.getDeptAddr()+"-"+emp.getDeptName());
			
		}
	}
	public static void test05() {
		Query q= QueryFactory.createQuery();
		Emp e= (Emp)q.queryById(Emp.class, 2);
		System.out.println(e.getEmpname());
	}
	public static void main(String[] args) {
//		test02();
//		int i;
//		long a = System.currentTimeMillis();
//		for (i = 0; i < 3000; i++) {
//			test01();
//			System.out.println(i);
//		}
//		long b =System.currentTimeMillis();
//		System.out.println(b-a);
//		testadd();
//		select01();
//		testdelete();
//		update();
//		select04();
		test05();
	}
}
