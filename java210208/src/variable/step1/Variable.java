package variable.step1;

public class Variable {
	//선언부
	
	//메소드 선언
	void methodA(int x) {
		
	}
	double methodB(int x) {
		System.out.println(x);
		return 3.14;
	}
	//메인메소드 - 이 부분이 있어야 .exe 파일로 만들 수 있다.
	/************************************************************
	 * @return (리턴 타입은) void이다. 즉, 비어있다.(=돌려 받을 수 있는 값이 없다.)
	 * 								-> 이것 때문에 에러가 발생할 수 있음
	 * @param args
	 ************************************************************/
	public static void main(String[] args) {
		//System; 나의 컴퓨터 //out속성; 출력장치(하드웨어-모니터, 프린터...)
		//print(): 에러, print(int i), print(double pi) ...
		//우리는 API 또는 xxx.jar 안에 있는 것을 꺼내서 쓴다.
		//클래스 또는 객체 안에 아이디어, 생각, 사상, 철학, 감성을 넣어서 프로그래밍을 한다.
		/* 이론적으로는 클래스가 객체보다 '추상적'이라고 한다.
		 * 객체: 결정되지 않음.
		 * 클래스로 만들었다는 것은 우리가 Sonata.java를 갖게 되는 것이므로 
		 * (선생님 개인적으로는)클래스가 더 구체적이라고 생각함.
		 * recall) class Sonata{}
		 */
		/* SELECT 이름 FROM 회원 집합
		 *  WHERE 아이디 = 'test'
		 *    AND 비번 = '123'
		 */
		//메소드 안에 메소드를 선언하는 것은 금기(해봐야 소용X)
		//System.out.println(); //대입되는 값이 없으므로 에러임.
		Variable v = new Variable();
		System.out.println(v.methodB(43));
		//System.out.println(v.methodA(1)); //v.methodA(1)의 반환값이 없으니까 에러임.
		System.out.println("5"); //ln은 '줄바꿈'이 추가된 메소드임
		System.out.print(5); //줄바꿈 없이 출력됨
	}

}
