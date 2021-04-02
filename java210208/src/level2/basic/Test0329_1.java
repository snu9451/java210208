package level2.basic;

import javax.swing.JFrame;

public class Test0329_1 extends JFrame{
	//선언부 - 가능한 것: 전역변수, 정적(static)변수, 클래스급, 인스턴스화, 배열 선언...
	//선언부에 올 수 없는 것: 선언과 초기화를 분리하는 문장(선언한 후 초기화 하는 것), 제어문 사용 불가, 메소드 호출 불가
	JFrame jf = new Test0329_1();	//[선언부]에는 [상위 클래스], [생성부]에는 [구현체 클래스]가 온 케이스
	
	//생성부
	//화면처리부
	
	//메인메소드 - 디폴트 스레드
	public static void main(String[] args) {
		
	}

}
