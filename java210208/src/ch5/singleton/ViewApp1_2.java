package ch5.singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewApp1_2 implements ActionListener{		//ViewApp implements ActionListener는 상속관계 아님
	
	//선언부
	//setSize를 하기 전에 먼저 인스턴스화를 하였다.
	JFrame	jf		= new JFrame();
	//인스턴스화를 분리해서 할 수도 있다.
	//이런 경우 무슨 차이가 있지?
	JFrame	jf2		= null;
	JButton	jbtn	= null;
	
	//initDisplay메소드
	public void initDisplay() {
		jf2 = new JFrame();
//		jbtn.setText("전송");
		jbtn = new JButton("전송");
		//이벤트 소스와 이벤트 처리를 담당하는 클래스를 연결해주어야 한다.
		//이벤트 처리를 담당하는 클래스를 <이벤트 핸들러 클래스> 라고 한다.
		//이벤트 처리를 담당하는 클래스는 반드시 actionPerformed라는 메소드를 오버라이딩(Overriding)해야한다.
		//[case 1]
		jbtn.addActionListener(this);
		//[case 2]						//<시점>에서 문제가 발생할 수 있다.
		//이벤트 처리 메소드가 외부에 있을 때는,,,
//		ViewAppEvent ve = new ViewAppEvent();
//		jbtn.addActionListener(ve);
		//생성된 버튼을 JFrame의 북쪽에 배치해보자.
		jf2.add("North", jbtn);
		jf2.setSize(300,200);
		jf2.setVisible(true);
	}
	
	//main메소드
	public static void main(String[] args) {
		ViewApp1_2 va = new ViewApp1_2();
		va.initDisplay();							//내 안에 있어도, main에서 호출할 때에는 인스턴스화 필수.
	}
	
	//이벤트 처리부
	@Override
	public void actionPerformed(ActionEvent ae) {	//추상 메소드 actionPerformed
		//전송버튼이 눌렸을 경우
		//ae.getSource()는 이벤트가 일어난 버튼의 주소번지를 반환해주는 메소드이다.
		if(ae.getSource()==jbtn) {
			System.out.println("전송버튼 호출 성공");
		}
	}

}
