package book.ch7;
//EmpVO는 1row만 담을 수가 있다. (단점)
public class EmpVO {
	//출력 타입이 3개이다. > Column일까? Row일까? - 홍길동의 사원번호, 홍길동의 이름, 홍길동의 월급 - 따라서 Row
	private int		empno = 0;
	private String	ename = null;
	private double	sal   = 0.0; //급여
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}

}
