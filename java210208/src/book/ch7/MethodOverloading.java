package book.ch7;

public class MethodOverloading {
	void go() {
		
	}
	//public void go(){} 서로 다른 접근제한자는 영향이 없다.
	public void go(int[] a) {}//메소드 오버로딩임(메소드 오버로딩 규칙을 만족함)
	//public void go(int[] b) {} 서로 다른 변수명은 영향이 없다.
	public void go(DeptVO dvo) {////메소드 오버로딩임(메소드 오버로딩 규칙을 만족함)
		
	}
	/*
	int go() {	리턴타입이 있고 없고는 영향이 없다.
		return 0;
	}
	*/
	public static void main(String[] args) {
		
	}

}
