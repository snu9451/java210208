package com.design.zipcode;

import java.util.ArrayList;

//제네릭
public class A {

	public static void main(String[] args) {
		ArrayList f1 = new ArrayList();
		f1.add("수박");
		f1.add("딸기");
		f1.add("키위");
		//제네릭이 없는 경우이므로 타입을 확인할 수가 없다. Object인 것 까지만 확인이 가능하다.
//		for(String str:f1) {
//			System.out.println(str);
//		}
		for(Object str:f1) {
			System.out.println(str);
		}
		String my = (String)f1.get(0);
		Object obj = f1.get(2);
		System.out.println(my);
		System.out.println(obj);
		System.out.println("========================[아래]=======================");
		//제네릭은 내 안에 어떤 타입이 있는지 명시하는 것이다.
		ArrayList<String> f2 = new ArrayList<String>();
		f2.add("수박");
		f2.add("딸기");
		f2.add("키위");
		String my2 = f2.get(0);
		Object obj2 = f2.get(2);
		System.out.println(my2);
		System.out.println(obj2);
		ArrayList<ZipCodeVO> f3 = new ArrayList<>();
		//n개를 담고 싶으면 인스턴스화 및 초기화를 n번 하면 된다.
		ZipCodeVO zVO = new ZipCodeVO();
		zVO.setZipcode(21356);
		zVO.setAddress("서울시 마포구 공덕동 246번지");
		f3.add(zVO);
		zVO = new ZipCodeVO();
		zVO.setZipcode(21357);
		zVO.setAddress("서울시 영등포구 영등포동");
		f3.add(zVO);
		zVO = new ZipCodeVO();
		zVO.setZipcode(21358);
		zVO.setAddress("서울시 구로구 고척동");
		f3.add(zVO);
		for(ZipCodeVO rzVO:f3) {
			System.out.println(rzVO);
		}
		System.out.println("===============================================");
		System.out.println("주소번지를 두 번 접근한다는 게 무슨 말이지?");
		for(ZipCodeVO rzVO:f3) {
			String addr = rzVO.getAddress();
			int dong = rzVO.getZipcode();
			System.out.println(dong);
			System.out.println(addr);
		}
	}

}
