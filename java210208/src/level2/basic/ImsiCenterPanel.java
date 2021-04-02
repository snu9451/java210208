package level2.basic;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ImsiCenterPanel extends JPanel {
	
	JButton jbtn1 = new JButton("회원가입");
	JButton jbtn2 = new JButton("로그인");
	
//	public ImsiCenterPanel() {
//		initDisplay();
//	}

	public void initDisplay() {
//		JPanel jp = new ImsiCenterPanel();	//이렇게 쓰면 JPanel의 메소드, 변수만 누릴 수 있는데 왜????
		
//		this.setBackground(Color.green);
//		this.setSize(400, 300);
//		this.setVisible(true);
		//JPanel은 default Layout이 FlowLayout이다.
		//FlowLayout: 가운데서부터 양옆으로 펼쳐지면서 페이지가 그려짐.
		this.setBackground(Color.green);
		this.add(jbtn1);
		this.add(jbtn2);
//		setSize(400, 300);
	}
	
}
