package variable.step1;

public class Quiz {
	int x; //전역변수는 초기화를 생략할 수 있다. 초기화 하려면 한 줄에 해야 함.
	/* 메소드에는 2가지 종류가 있다.
	 * JVM에서 제공하는 메소드 || 사용자 정의 메소드
	 * 메소드 선언 순서는 [리턴타입] [메소드이름()]{ 실행문; }
	 * 학습목표: 메소드를 선언할 때, 리턴타입과 반환타입을 결정할 수 있다.
	 */
	//변수 타입은 실행할 때가 아니라 컴파일 할 때 검사함
	//이클립스에서 컴파일은 저장할 때 일어난다? YES
	//저장되는 순간에 컴파일도 같이 일어남
	public static void main(String[] args) {
		Quiz q = new Quiz();
		//System.out.println("methodA(int x) 호출 성공 ======> "+x); x때문에 안됨
		System.out.println("methodA(int x) 호출 성공 ======> "+q.x);
		q.methodA(1); //q 안의 methodA() 메소드를 가져온다는 뜻이라서 Variable 클래스의 것과는 무관
	}
	//methodA의 호출이 선언 앞에 오더라도(지금 같은 경우) Error가 아님.
	//왜냐하면 JAVA는 절차 지향의 프로그램이 아니기 때문.*************
	//JAVA는 객체 지향의 언어이므로 호출 순서와는 상관이 없다.**********
	/*JAVA에서는 동일한 이름의 메소드를 중복하여 선언할 수 있다.
	 *이것은 <<메소드 오버로딩>>이라는 규칙을 준수할 때 가능하다.
	 *메소드 오버로딩은 반드시 
	 *(1)파라미터의 타입이 다르거나 (2)파라미터의 개수가 다르거나
	 *(1), (2) 둘 중 하나 이상은 만족해야 한다.
	 */
	private void methodA() { //private으로 하면 내 안(내 클래스 안)에서만 사용가능
		// 외부 클래스에서는 접근 불가. 호출 불가. 재사용 불가.
		System.out.println("methodA 호출 성공");
	}
	private void methodA(int x) {
		System.out.println("methodA(int x) 호출 성공 ======> "+x);
	}
	private int methodA(float y) { //private으로 하면 내 안(내 클래스 안)에서만 사용가능
		System.out.println("methodA(float y) 호출 성공 ======> "+y);
		return 0; //return 타입을 int로 정했으므로 return을 줘야 함.
	}
}
