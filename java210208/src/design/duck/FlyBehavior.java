package design.duck;
/*
생성자는 올 수 없다.
일반변수는 올 수 없다.
메소드 이름 앞에 abstract는 생략이 가능하다.
메소드 뒤에 세미콜론으로 끝맺는다. (중괄호가 없구나!) 
*/
public interface FlyBehavior {
//	static final int i=0;	//변수는 static final만 가능하다. 쓸 일은 X
//	public FlyBehavior() {}
	public abstract void fly();	//abstract는 생략이 가능. 왜냐, 어차피 인터페이스에는 추상메소드만 올 수 있음.
}
