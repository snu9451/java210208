package di.step1;

public class HelloBeanImpl implements HelloBean {

	private String msg333 = null;	//싱글톤이라서 private으로!
	//msg = new String("안녕! 나...");
	//아래 코드는 객체생성을 내가 관리하겠다(해야한다)는 것을 의미함. 반면, (ㄱ)...
	String name = new String("SCOTT");

	//이것을 스프링에서는 [setter객체 주입법]이라고 함. - 자바 코드를 적용함
	//xml코드를 활용하는 생성자 객체 주입법(여기서 초보자들이 포기함 ㅠ)
	//...(ㄱ) setter메소드를 사용하는 것은 객체 생성 및 관리를 스프링에서 대신 해주겠다는 의미이다.
	public void setMsg333(String msg) {
		this.msg333 = msg;
	}
	
	@Override
	public String getGreeting(String msg) {
		return "Spring"+this.msg333;
	}
	
	//Bean이 초기화된 후에 호출되는 메소드
	public void initMethod() {
		System.out.println("initMethod 호출 성공");
	}

	//Bean이 소멸되기 전에 호출되는 메소드
	public void destroyMethod() {
		System.out.println("destroyMethod 호출 성공");
	}
	

}
