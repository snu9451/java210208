package variable.step1;

public class ASimulation {
	//recall) 전역변수의 경우 초기화를 생략할 수 있다. 즉, 0이라는 값을 굳이 주지 않아도 0이라는 값을 갖는다.
	//C언어는 초기화를 생략하면 쓰레기값을 출력한다.
	//하지만 JAVA언어에서는 각 타입이 갖는 초기값이 정해져 있다.
	//ex) int=0, long=0, float=0.0f, double=0.0(d)
	//범위; public > protected > friendly > private (작음)
	public static void main(String[] args) {
		A a = new A();
		System.out.println("영어: "+a.eng); //예상출력: 0
		System.out.println("수학: "+a.math); //예상출력: 0
		System.out.println("국어: "+a.kor); //예상출력: 0
		System.out.println("====================");
		System.out.println("영어: "+a.eng1); //예상출력: 0
		System.out.println("수학: "+a.math1); //예상출력: 0
		System.out.println("국어: "+a.kor1); //예상출력: 0
		System.out.println("====================");
		System.out.println("영어: "+a.eng2); //예상출력: 90
		System.out.println("수학: "+a.math2); //예상출력: 80
		System.out.println("국어: "+a.kor2); //예상출력: 70
		A a2 = new A();
		a2.eng2  = 95;
		a2.math2 = 85;
		a2.eng2  = 75;
		System.out.println("====================");
		System.out.println("영어: "+a2.eng2); //예상출력: 95
		System.out.println("수학: "+a2.math2); //예상출력: 85
		System.out.println("국어: "+a2.kor2); //예상출력: 75
		//같은 타입이지만 서로 다른 내용과 메소드의 처리 결과를 가질 수 있다.
		//인스턴스화를 할 때마다 복제본이 여러개 생긴다.

	}

}
