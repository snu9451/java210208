package ch5.singleton;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewApp2_1 {		//ViewApp implements ActionListener는 상속관계 아님
	
	//선언부
	//setSize를 하기 전에 먼저 인스턴스화를 하였다.
	JFrame jf	= new JFrame();
	JFrame jf2	= null;
	JButton jbtn = null;				//버튼을 선언만 하였다.
	//initDisplay메소드
	public void initDisplay() {
		jf2 = new JFrame();
		//디폴트 생성자는 제공해주지만, 파라미터를 갖는 생성자는 제공되지 않는다.
		EventHandler2 eh = new EventHandler2(null);
		//Run As 돌리자마자 터질까? 아니면 '전송'을 누를때 터지는가?
		//살펴야 할 내용1) 이 메소드(initDisplay)는 호출되나요? YES
		//살펴야 할 내용2) jbtn.addActionListener(eh)가 실행문인가요? YES 그러니까 바로 NullPointerException으로 터진다.
		//jbtn.addActionListener(eh) 라고 썼지만 jbtn이 null이라서 null.addActionListener(eh)라고 쓴 거나 다름 없음.
		jbtn = new JButton("전송");		//(2) 따라서 이렇게 초기화를 위에 해주어야 한다.
		jbtn.addActionListener(eh);		//(1) 현재로써는 jbtn이 null이라서 NullPointerException 발생 위험 
		jf2.add("North", jbtn);
		jf2.setSize(300,200);
		jf2.setVisible(true);
	}
	
	//main메소드
	public static void main(String[] args) {
		ViewApp2_1 va = new ViewApp2_1();
		va.initDisplay();							//내 안에 있어도, main에서 호출할 때에는 인스턴스화 필수.
	}
}
