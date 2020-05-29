package com.jeff.vo;

/**
 * 
 * @author lcyan
 *
 */
public class EmpVO {
	//select e.id,e.empname,salary 'wage', age,d.dname 'deptName',d.address 'deptAddr' 
	//from emp e join dept d on e.deptId=d.id;
	private Integer id;
	private String empname;
	private Double wage;
	private Integer age;
	private String deptName;
	private String deptAddr;
	public EmpVO(Integer id, String empname, Double wage, Integer age, String deptName, String deptAddr) {
		super();
		this.id = id;
		this.empname = empname;
		this.wage = wage;
		this.age = age;
		this.deptName = deptName;
		this.deptAddr = deptAddr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Double getWage() {
		return wage;
	}
	public void setWage(Double wage) {
		this.wage = wage;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptAddr() {
		return deptAddr;
	}
	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}
	public EmpVO() {
		super();
	}
	
}
