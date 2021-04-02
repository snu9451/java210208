package com.design.zipcode;

import java.util.List;
import java.util.Vector;

/*
 * 자료구조의 종류 
 * 1) List계열
 * 1) List계열
 * 		ArrayList	- 싱글스레드에서 안전하다. 동기화 처리는 생략한다. 속도가 빠르다.
 * 		Vector		- 멀티스레드에서 안전하다. 동기화를 처리한다. 속도가 느린 편이다. '경합, 다중 접속자 처리, 순서, 동기화 처리' 등이 요구되는 상황에서 사용하면 좋다.
 * 
 * 2) Map계열(해시태그)
 * 
 * 3) Set계열(사용빈도 낮음) - 집합
 */
public class ZipcodeList {

	public static void main(String[] args) {
		
		//<>: 제네릭(Generic). 생성부에는 타입 없이 <>만 붙임.
		Vector<ZipcodeVO>	v		= new Vector<>();	//다형성을 누릴 수 없다.
		List<ZipcodeVO>		v2		= new Vector<>();	//다형성을 누릴 수 있다.
		ZipcodeVO			zcVO	= null;
		ZipcodeVO			zcVOs[]	= null;
		int					i		= 0;
		
		while(i<3) {
//			31번 다음 라인에서 VO에 전역변수인 address에 서울시 금천구 가산동을 초기화 하였다.
//		
//			44번에서 객체배열의 address변수를 출력하였을 때
//			-----------------------------------------------------------------
//			1. 3개가 몽땅 null일 것이다.
//		
//			2. 아니다 서울시 금천구 가산동일 것이다.
//		
//			zcVO.setAddress("서울시"); -nullpointexception
			zcVO = new ZipcodeVO();
			zcVO.setAddress("서울시 금천구 가산동");
			//VO안에 전역변수인 ADDRESS에 서울시 금천구 가산동을 초기화하였다.
			//41번에서 객체배열의 ADDRESS변수를 출력했을때 모두NULL일것이다? 서울시 금천구 가산동일 것이다.
			System.out.println("zcVO주소번지 바뀐다. ===> "+zcVO);
			v.add(zcVO);
			zcVO.setAddress("인천시");
			i++;
		}
		
		for(int x=0;x<v.size() ;x++) {
			ZipcodeVO	zVO = v.get(x);		//while 안의 첫번째 줄에서 생성된 VO와는 다른 것이다. 
			System.out.println(zVO);		//서로 다른 주소번지가 출력된다.
		}
		
		zcVOs = new ZipcodeVO[v.size()];//객체배열이 3개 만들어진다.
		System.out.println("배열의 크기는 " + zcVOs.length);//3이 출력됨
		v.copyInto(zcVOs);//벡터에 담긴것을 배열에 담아준다.
		
		for(int y = 0; y <zcVOs.length; y++) {
		   System.out.println(zcVOs[y] + "," + zcVOs[y].getAddress());//객체 배열의 주소번지가 3개 출력된다
		}
	}
}
