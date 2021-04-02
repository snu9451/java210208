package com.pattern;

import java.util.Vector;

import com.vo.DeptVO;
import com.vo.EmpVO;

public class SalaryMgrLogic {
	SalaryMgrView smv = null;
	public SalaryMgrLogic(SalaryMgrView smv) {
		this.smv = smv;
	}
	public void getEmpDetail(int pempno) {
		EmpVO eVO	= new EmpVO();
		eVO.setEname("이순신");
		DeptVO dVO	= new DeptVO();
		dVO.setDname("재무관리팀");
		eVO.setdVO(dVO);
		Vector oneRow = new Vector();
		oneRow.addElement(eVO.getEname());
		oneRow.addElement(eVO.getdVO().getDname());
		smv.dtm_sal.addRow(oneRow);
	}

	
	
	/*
	//MN선언부
	private String	sawonName	= "";
	private int		sawonNo		= 0;
	private String	deptName	= "";
	private int		deptNo		= 0;
	private double	salary		= 0.0;
	private double	incentive	= 0.0;
	//MN생성부
	public String getSawonName() {
		return sawonName;
	}
	public void setSawonName(String sawonName) {
		this.sawonName = sawonName;
	}
	public int getSawonNo() {
		return sawonNo;
	}
	public void setSawonNo(int sawonNo) {
		this.sawonNo = sawonNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getIncentive() {
		return incentive;
	}
	public void setIncentive(double incentive) {
		this.incentive = incentive;
	}
	*////////////////////////내 스스로 작성해보는 부분
	
	
}
