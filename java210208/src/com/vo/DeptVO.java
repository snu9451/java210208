package com.vo;
//오라클과 자바 만남
//이어야 됨 - 연결고리
//VO패턴 - 데이터의 영속성을 유지해주는 오라클 서버 제품과 연결하기
//Data관리 목적 - 모든 집합의 이름 뒤에 접미어로 VO 붙임.
//메인메소드는 필요없다. 단독으로 실행하지 않는다.
/*
 * Number(2) - 정수형 / 담을 수 있는 최대 크기는 99까지 임계값
 * 			 - int(4byte), long(8byte)
 * 
 * varchar2(4000) - String
 * VO패턴을 사용하면 한꺼번에 3가지 종류[부서번호, 부서명, 지역]의 값을 관리 가능
 */
public class DeptVO {
	private int		deptno	= 0; //원시형 타입 - 부르면 값이 나온다. 4byte 229540 229544 229548 ...
	private String	dname	= null; //레퍼런스타입 - <클래스 급>이다. - 값이 아니라 <주소번지>가 나온다. / 
	private String	loc		= null;
	//insert here
	private EmpVO	eVO		= null;
	/*
	//생성자 해보기
	*/
	public DeptVO() {}
	public DeptVO(int pdeptno) {
		this.deptno = pdeptno;
	}
	//getter메소드 - Read
	public int getDeptno() {
		return deptno;
	}
	//setter메소드 - Write, Save 의 느낌으로 접근해보기
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {//String dname: 지변
		this.dname = dname; // this.dname: 전변 / this는 안 써도 되는데 구분하려고 씀(구분자, 수정자) / this가 가리키는 건 나 자신 즉, DeptVO 클래스
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
