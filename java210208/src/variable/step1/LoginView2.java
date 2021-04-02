package variable.step1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LoginView2 extends JFrame implements ActionListener{
	JButton jbtn_login = new JButton("로그인");
	public void initDisplay() {
		jbtn_login.addActionListener(this);
		this.setTitle("네이버 로그인");
		this.add("North", jbtn_login);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		LoginView2 view = new LoginView2();
		view.initDisplay();
	}
	//@Override를 <어노테이션>이라고 한다.
	//메소드 오버라이드
	//@Override를 없애면 actionPerformed를 일반 '사용자 정의 메소드'로 인식함.
	//@Override가 있으면, actionPerformed의 이름을 예를 들어 actionPmd와 같이 변경할 수 없음(Error됨)
	//메소드 오버로딩(변수의 개수 또는 타입이 달라야 함) / 메소드 오버라이딩(메소드 이름이 같고 서로 상속관계인 경우에 해당)
	@Override
	public void actionPerformed(ActionEvent e) { //콜백메소드라고 함. 내가 호출하는 메소드가 아니라, 이벤트 감지가 일어나면 자동으로 호출됨.
		if(jbtn_login == e.getSource()) {//이벤트 감지는 컴퓨터가 하고, 버튼의 주번(주소번지)는 내가 정의함.
			System.out.println("이순신 님 환영합니다.");
		}
	}

}
