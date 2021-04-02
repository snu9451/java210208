package variable.step1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LoginView extends Object implements ActionListener{//모든 클래스의 상위: Object (생략할 수 있다.)
	//ActionListener 인터페이스이다. 결합도를 낮추는 코드를 작성할 때 필요함. 클래스 설계 시 필요함. 요즘은 웹 기반으로 처리하므로 이 부분을 복습할 필요는 X
	//java.lang에 있는 package가 아니므로 별도로 import해야한다. Ctrl+Shift+O로 자동 import!
	JFrame jf = new JFrame();
	JButton jbtn_login = new JButton("로그인");
	//화면 그리기 구현
	public void initDisplay() {
		jbtn_login.addActionListener(this);//this: 이벤트 소스와 이벤트 처리를 담당하는 클래스를 연결함(매칭함).
		//"내 자신이 이벤트 처리를 담당하는 핸들러 클래스이다."라는 의미로 this를 사용하기도 한다.
		jf.add("North", jbtn_login);
		jf.setSize(500, 400);
		jf.setVisible(true);//true이면 화면에 출력해줌 / false이면 비활성화함(ex- 노래 듣다가 2:10에 전화오면 노래는 멈추고 전화가 끝나면 2:10부터 이어재생)
	}
	//내 안에 있는 메소드이지만, static 영역 안에서 non-static에 접근할 수 없다: 인스턴스화를 통해 이 문제를 해결할 수 있다.
	public static void main(String[] args) {
		LoginView view = new LoginView();
		view.initDisplay();
		/*
		System.out.println(view);
		System.out.println(view.toString()); //toString()은 내장되어 있는 메소드라 생략 가능.
		*/
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jbtn_login == e.getSource()) {//이벤트 감지는 컴퓨터가 하고, 버튼의 주번(주소번지)은 내가 정의함. 주소번지가 같는지를 비교하는 작업(즉, 로그인 버튼을 눌렀는지 확인하는 작업)
			System.out.println("이순신 님 환영합니다.");
		}
	}

}
