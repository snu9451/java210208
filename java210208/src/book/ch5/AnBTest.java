package book.ch5;

//A1과 B1은 관계가 없고 독립적으로 구성된 클래스임 > 따라서 누가 누구의 기준이냐 하는 것이 없음
//반면, EmpVO와 deptVO 의 경우, EmpVO가 기준이 된다고 볼 수 있다. 따라서 EmpVO에서 DeptVO를 인스턴스화 하는 것(주입 받는 것)이 맞다.

class C {
	public C() {	//주의; 생성자(constructor)는 클래스명과 이름이 같아야 함
		System.out.println("생성자호출성공");
	}
}

class A1{
	int ival = 0;
	void piPrint() {
		B1 b1 = new B1();
		double result = b1.pi;
		System.out.println("result pi: "+result);
	}
}
class B1{
	double pi = 0.0;
	void ivalPrint() {
		A1 a1 = new A1();
		double result = a1.ival;
		System.out.println("result ival: "+result);
	}
}

public class AnBTest {

	public static void main(String[] args) {
		
		System.out.println(new C());
		
		A1 a1 = new A1();
		a1.piPrint();
		B1 b1 = new B1();
		b1.ivalPrint();
	}

}
