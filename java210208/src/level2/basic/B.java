package level2.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class B implements ActionListener{

	A a = null;
	C c = new C();
	
	public B(A a) {
		System.out.println("B(A a) 생성자 호출 성공");
		this.a = a;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {	//콜백메소드이다. - 자동호출이 일어남.
		System.out.println("actionPerformed 호출 성공");
		Object obj = ae.getSource();
		obj = ae.getSource().getClass();
		//JButton.class는 왜 안되는걸까? import하면 된다. 근데 차이는?
//		if(JButton.class == obj) {
		if(a.jbtn.getClass() == obj) {
			System.out.println("버튼 이벤트 발생했을 때 호출 됨");
//			String msg = a.c.methodA();
			String msg = c.methodA();
			System.out.println("메소드 처리 결과: "+msg);
		}
	}

}
