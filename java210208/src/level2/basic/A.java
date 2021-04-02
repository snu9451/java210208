package level2.basic;

import javax.swing.JButton;
import javax.swing.JFrame;

public class A extends JFrame {

	JButton jbtn = null;
//	JButton jbtn = new JButton("전송1");	//jbtn의 NullPointerException을 막는 첫번째 방법
	boolean isView = false;	//boolean은 초기값이 false이다.
	
//	B b = new B(this);	//둘 중 어느것을 할 것인가? 결정해야 한다.
	B b = null;			//타입만 결정된 상태이므로, [시점]에 따라 NullPointerException을 당할 수 있음에 유의해야 함.
//	C c = new C();		//A가 컴파일 될 때 같이 메모리에 상주하게 됨.
	
	public A(B b) {
		this.b = b;
	}
	
	
	public A() {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		jbtn = new JButton("전송2");		//main에서 호출되는 생성자가 이게 아니기 때문에 여기서 생성하는 것은 의미가 없다. Null...
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		initDisplay();
	}
	
	public A(boolean isView) {
		this.isView = isView;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		jbtn = new JButton("전송4");		//jbtn의 NullPointerException을 막는 두번째 방법
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		jbtn = new JButton();			//윗줄처럼 생성자를 '잘못' 골라서 또 메소드를 호출해야 되잖아. 그러니까 생성자를 '먼저' 그리고 '잘' 보는 것이 중요!!
//		jbtn.setText("전송5");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		jbtn = new JButton();
		initDisplay();
		jbtn.setText("전송6"); 			//이렇게 하면 '전송6'이 나온다! 오오호,,,, 이상해,,, 이름은 나중에 붙였는데도 괜찮다: [동기화 처리]가 되고 있음!
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		jbtn = new JButton("전송3");		//소 잃고 외양간 고치는... 시점의 문제로 Null 발생. [위치/시점]이 중요하다!!
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void initDisplay() {
		b = new B(this);
		jbtn.addActionListener(b);
//		jbtn.addActionListener(b);	//addActionListener 두 개 달면 actionPerformed가 두 번 호출됨.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add("Center", jbtn);
		this.setSize(500, 300);//상수로 처리하는 방법
		this.setVisible(isView);
	}
	
	public static void main(String[] args) {
//		new A();		//이렇게만 하면 창이 열리지 않는다.
		new A(true);	//창이 열린다.
	}

}
