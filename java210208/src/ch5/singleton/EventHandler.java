package ch5.singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
	ViewApp1_1 viewApp1_1 = null;
	public EventHandler(ViewApp1_1 viewApp1_1) {
		this.viewApp1_1 = viewApp1_1;
	}

	//이벤트 처리부
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewApp1_1.jbtn) {			//NullPointerException
			System.out.println("전송 버튼 호출 성공했다~~");
			
		}
	}

}
