package di.step1;

public interface HelloBean {
	public String getGreeting(String msg);	//인터페이스이므로 선언만 가능(실행문(좌중괄호, 우중괄호)이 올 수 없고 세미콜론으로 끝남)
	//인터페이스는 일반메소드를 가질 수 없기 때문에 abstract를 생략하더라도 추상메소드이다.
}
