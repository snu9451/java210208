package book.ch7;

public class B {
	int deptno	= 0;
	public B(int deptno) {	//생성자
		//생성자의 경우에는 파라미터 변수명과 {} 안의 변수명을 일치시키는 것이 좋다.(deptno)
		this.deptno = deptno;
	}
	int methodA() {
		int u_deptno = 0;
		return u_deptno;
	}
	void getDeptList(int u_deptno) {	//이런 경우엔 굳이 일치시키지 않아도 ㄱㅊ
		/*
		SELECT dname, loc FROM dept
		WHERE deptno = ?
		*/
		//u_deptno = methodA(); //u_deptno: Bad
		int r_deptno = methodA(); //r_deptno: Good
	}
	public static void main(String[] args) {

	}

}
