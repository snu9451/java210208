package book.ch14;

import javax.swing.JFrame;

//★★★★★ 예외처리 관련 학습 - throw, throws, try-catch ...

public class CalendarPage extends JFrame {
	
//	public void print(int mm, int yy) Exception {
	/**************************************************************************************
	 * @param mm: 0~11 사이의 숫자를 받는다.
	 * @param yy: 출력하고자 하는 달력의 연도를 입력 받는다.
	 **************************************************************************************/
	public void print(int mm, int yy) throws Exception {
		System.out.println("print 호출 성공!");
		if(mm<0 || mm>11) {
			throw new IllegalArgumentException("must be 0~11");	//생성자의 파라미터로 변수(String)를 넘길 수 있다/합법이다.(생성자 오버로딩이 되어있다.)
		}
		System.out.println("여기");
	}
	
	public static void main(String[] args) {
		CalendarPage cp = new CalendarPage();
		if(args.length == 2) {
			try {	//print메소드는 throws가 붙어있기 때문에 try-catch문으로 반드시 예외처리를 해주어야 한다.
				cp.print(Integer.parseInt(args[0])-1, Integer.parseInt(args[1]));
			//catch문은 좁은 범위부터 넓은 범위로 쓴다. 
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("NumberFormatException: "+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception: "+e.getMessage());
			}
		} else {
			System.out.println("달과 연도를 입력하세요.");
			return;
		}
	}

}
