package book.ch6;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClassGubun extends JFrame {

	JButton jbtn_search = new JButton("조회");
	JButton jbtn_exit = new JButton("종료");
	
	
	public void start() {
		jbtn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("조회 버튼 호출 성공");
			}
		});
		jbtn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("나가기 버튼 호출 성공");
				System.exit(0);
			}
		});
	}
	
	public void initDisplay() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(jbtn_search);
		this.add(jbtn_exit);
		this.setSize(500, 300);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		ClassGubun cg = new ClassGubun();
		cg.start();
		cg.initDisplay();
	}

}
