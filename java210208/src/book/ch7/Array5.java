package book.ch7;

public class Array5 {
	void methodB(boolean isOk[]) {
		System.out.println(isOk.length);
		//System.out.println(isOk[3]);
	}
	/*
	10-11(선언만 했음 즉, 초기화가 안 됨(방 크기가 결정되지 않았음))
	recall) isOk.length (배열의 크기(길이)를 구해줌)
	방 크기를 결정하지 않고 특정 방에 값을 대입하면 Error: NullPointerException 
	*/
	public static void main(String[] args) {
		//지역변수를 선언만 하는 방법: null로 초기화(초기화라고 부를 수 있는지 모르겠지만..)
		boolean isOk[] = null; //이것은 선언만 한 것. 크기 결정 X
		Array5 a5 = new Array5();
		a5.methodB(isOk);
	}
	/*
	변수는 한 번에 하나의 값만 담을 수 있다. (변수의 단점)
	배열은 한 번에 여러 개의 값을 담을 수 있지만, 서로 다른 타입을 담을 수가 없고 끼워넣기가 불가능하다. (배열의 단점) - 객체배열, ArrayList (단점의 해결)
	
	배열의 단점은 ArrayList로 극복가능함.
	
	자료구조(List계열, Map계열, Set계열) - 늘었다 줄었다 한다는 장점이 있다.(즉, 끼워넣기 가능 - 회원탈퇴, 신입사원 등록...)
	FIFO(First In First Out)
	LIFO
	LILO ...
	
	세션과 쿠키
	
	오라클 서버 - 영원함(영속성)
	*/

}
