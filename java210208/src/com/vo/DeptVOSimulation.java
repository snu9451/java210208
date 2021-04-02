package com.vo;

public class DeptVOSimulation {
	void methodA() {
	}
	DeptVO methodB() {
		return new DeptVO();
	}
	DeptVO[] methodC() {
		return new DeptVO[3];
	}
	public static void main(String[] args) {
		DeptVO dvo = new DeptVO();
		dvo = new DeptVO();
		dvo.setDeptno(30);
		System.out.println();
		int myDeptNo	= dvo.getDeptno(); //0출력
		//System.out.println(dvo.dptno);
		//insert here - 부서번호 30을 출력하도록 코드 추가하기
		String myDname	= dvo.getDname(); //null
		String myLoc = dvo.getLoc();
		//dvo = new DeptVO(); //MN: 디폴트 생성자 없어서 오류
		
	}

}
