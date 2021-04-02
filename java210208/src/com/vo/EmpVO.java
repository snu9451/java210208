package com.vo;

public class EmpVO {
	//insert here - 급여 명세서에 부서이름을 출력하기 위한 SELECT문 이용
	private DeptVO dVO		= null;
	private int	   empno    = 0;    //ex) 7566
	private String ename    = "";   //사원명
	private String job      = "";   //
	private int    mgr      = 0;    //그룹코드
	private String hiredate = "";   //입사일자
	private double sal      = 0.0;  //급여
	private double comm     = 0.0;  //인센티브
	//부서번호(FK; Foreign Key, 외래키; 조인(join) 시 사용되는 컬럼(column).
	//부모로 부터 내려받은 속성(릴레이션, relation)이다.)
	private int    deptno   =0;
	//생성자의 경우 생략할 수 있고, 생략되었을 때 JVM이 대신 제공해준다. > 디폴트 생성자(default 생성자: 파라미터를 갖지 않음)
	//그러나 생성자가 한개라도 정의되어있다면 디폴트 생성자는 제공되지 않는다.
	public EmpVO() {}	//디폴트 생성자
	public EmpVO(int empno) {
		this.empno = empno; //대입연산자를 통해 값이 대입되어야 그 값(지변)이 유지된다. (<다른 클래스>에서 유지됨)
	}
	public EmpVO(int empno, String ename, String job, int mgr
			   , String hiredate, double sal, double comm
			   , int deptno, DeptVO dVO) {
		this.empno	  = empno; //대입연산자를 통해 값이 대입되어야 그 값(지변)이 유지된다. (<다른 클래스>에서 유지됨)
		this.ename	  = ename;
		this.job	  = job;
		this.mgr	  = mgr;
		this.hiredate = hiredate;
		this.sal	  = sal;
		this.comm	  = comm;
		this.deptno	  = deptno;
		this.dVO	  = dVO;
	}
	
	public DeptVO getdVO() {
		return dVO;
	}
	public void setdVO(DeptVO dVO) {
		this.dVO = dVO;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}
