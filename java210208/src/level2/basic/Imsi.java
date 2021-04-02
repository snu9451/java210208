package level2.basic;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imsi extends JFrame {

	JPanel		jp_north	= new JPanel();
	JButton		jbtn_one	= new JButton("하나");
	JButton		jbtn_two	= new JButton("둘");
	JButton		jbtn_three	= new JButton("셋");
	JLabel		jlb			= new JLabel("현재 시간", JLabel.CENTER);	// 다양한 생성자 이용 가능
	Container	cont		= this.getContentPane();				// Container를 얻는 방법
	ImsiCenterPanel jp = new ImsiCenterPanel();
	
	public Imsi() {
		jp_north.setLayout(new GridLayout(1,2,3,3));
		jp_north.setBackground(Color.orange);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Action처리를 안드로이드식으로 처리해보자. 내부클래스 - 익명클래스 이용!
		jbtn_one.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("1번 버튼 호출 성공");
				viewUpdate(e);
			}
		});
		jbtn_two.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("2번 버튼 호출 성공");
				viewAllUpdate(e);
			}
		});
		jbtn_three.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("3번 버튼 호출 성공");
				System.out.println("초록패널이 빨간패널로 변경되었습니다.");
				jp.setBackground(Color.red);
				if(jp != null) {
					jp.remove(jp.jbtn1);
					jp.remove(jp.jbtn2);
				}
				cont.revalidate();
				cont.repaint();
			}
		});
		jp_north.add(jbtn_one);
		jp_north.add(jbtn_two);
		jp_north.add(jbtn_three);
		this.add("North", jp_north);
		this.add("South", jlb);
		this.setSize(500, 400);
		this.setVisible(true);
	}

	public void viewUpdate(ActionEvent e) { // 이렇게 함으로써 같은 이벤트(ActionEvent e)를 다른 메소드에서도 재사용 할 수가 있다.★★★★★★
		System.out.println("viewUpdate 메소드 호출 성공");
		cont.remove(jlb); // JFrame이나 ContentPane의 child만 remove 가능
//		cont.revalidate();
		cont.repaint();
//		this.remove(jbtn);
//		this.revalidate();
//		this.repaint();
	}

	public void viewAllUpdate(ActionEvent e) {
		System.out.println("viewAllUpdate 메소드 호출 성공");
//		JPanel jp = new ImsiCenterPanel();
		jp.initDisplay();
		this.add("Center", jp);
		this.revalidate();
//		this.repaint();
	}
	
	public static void main(String[] args) {
		// static 안에서는 this, super 쓸 수 없다.
		new Imsi();
	}

}
