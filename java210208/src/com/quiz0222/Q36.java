package com.quiz0222;
class Q36_1{
	void methodC() {
		//methodB();	//클래스 이름이 다르기 때문에 이렇게 호출할 수 없다. 소유주가 누구인지 알 수 없다.
		Q36.methodB();	//대신, <클래스.메소드();>로 호출이 가능하다.
		//methodA와 methodB 호출의 비교
		Q36 q36 = new Q36();
		q36.methodA();
	}
}
/*
 * non-static 영역에서 static 타입으로는 접근할 수 있다.
 * 
 * 그러나
 * static 영역에서 non-static으로의 접근은 불가능하다.
 * 
 */
public class Q36 {
	//static 블럭
	static {				//MN 이게 실행이 되네?
		System.out.println("static block");
	}
	void methodA() {		//non static
		System.out.println("methodA 호출");
		methodB();			//이것은 합법. 하지만 methodB()에서 methodA()를 이런식으로 호출하는 것은 불법.
	}
	static void methodB() {	//static
		System.out.println("static methodB 호출");
	}
	//main메소드도 static이다.
	//main thread라고 한다.(우선순위가 높음)
	//자바스크립트는 single thread이다.
	public static void main(String[] args) {
		System.out.println("static main");
		methodB();
	}

}
