package ch5.singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler2 implements ActionListener {
	ViewApp2_1 viewApp2_1 = null;
	public EventHandler2(ViewApp2_1 viewApp2_1) {
		this.viewApp2_1 = viewApp2_1;
	}

	//이벤트 처리부
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewApp2_1.jbtn) {			//NullPointerException
			System.out.println("전송 버튼 호출 성공했다~~");
			
		}
	}

}
