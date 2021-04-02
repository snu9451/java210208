package book.ch5;

public class ParameterTest {
	String g_rdname = null;
	static int methodA() {
		return 100;
	}
	static int methodA(int p_empno) {
		return 100;
	}
	void methodA(int p_empno, String rdname) {
		g_rdname = rdname;
	}
	public static void main(String[] args) {
//		int x = ParameterTest.methodA();
		int x = ParameterTest.methodA(7566);
		int x2 = methodA(7566);
		System.out.println(x);
		String rdname = "개발2팀";
		new ParameterTest().methodA(7566, rdname);
//		System.out.println(g_rdname);
		ParameterTest pt = new ParameterTest();	//(1)
		pt.methodA(7566, rdname);				//(2)
		System.out.println(new ParameterTest().g_rdname);	//MN) 인스턴스를 새로 만드므로 그 쪽의 g_rdname은 null 상태이다?
//		SamNote) 위 코드에서 new ParameterTest().g_rdname은 void methodA()를 경유하지 않았으므로 null이 찍힌다.
		System.out.println(pt.g_rdname);	//(1), (2)과 연결
	}

}
