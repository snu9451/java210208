package com.pattern;
//랜덤게임을 이용한 화면구성을 통해 ★클래스 쪼개기★에 대해 학습하자!
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RandomGameView {
	//선언부
	JTextArea jta_display	= new JTextArea(7,20);	//java.lang에 있지 않으므로 import 필요
	//JTextArea만으로는 스크롤 기능이 없어서 JScrollPane을 통해 스크롤을 추가
	JScrollPane jsp_display	= new JScrollPane(jta_display
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//vertical, horizontal 순서 바뀌면 arguments 오류 남

	JPanel jp_south	 = new JPanel();	//속지 생성
	JPanel jp_east	 = new JPanel();	//cf- 안드로이드에서는 JPanel이 Fragment임
	JButton jbtns[]	 = new JButton[10];	//버튼 10칸 생성	//아직은 '방(room)'만 있음
	JButton jbtn	 = null;			//초기화는 null로 한다.
	String nums_label[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	JButton jbtn_new	= new JButton("새게임");
	JButton jbtn_clear	= new JButton("지우기");
	JButton jbtn_dap	= new JButton("정답");
	JButton jbtn_exit	= new JButton("나가기");
	//생성자
	
	//화면처리부
	public void initDisplay() {
		//JFrame 인스턴스화
		JFrame jf = new JFrame();
		
		//레이아웃 설정
		jp_south.setLayout(new GridLayout(1,10,5,5));
		jp_east.setLayout(new GridLayout(4,1,5,5));	//뒷부분의 2,2: 타일줄눈처럼 사이 공간을 주기 위해 작성
		
		//버튼 추가(add)
		for(int i=0;i<jbtns.length;i++) {		//jbtns.length 대신에 nums_label.length 해도 똑같음
			jbtn = new JButton(nums_label[i]);	//★★★여기 생각해보기!
			jp_south.add(jbtn);
		}
		jp_east.add(jbtn_new);
		jp_east.add(jbtn_clear);
		jp_east.add(jbtn_dap);
		jp_east.add(jbtn_exit);
		
		//Panel의 배경색상 설정
		jp_east.setBackground(Color.magenta);
		jp_south.setBackground(Color.yellow);
		
		//
		jf.setLayout(new BorderLayout(2,2));	//2,2는 <간격>임
		
		//생성한 Panel들을 프레임의 원하는 위치에 박기
		jf.add("East", jp_east);
		jf.add("Center", jsp_display);	//jta_display가 jsp_display에 들어갔으므로 jsp_display로 바꿔써줌
		jf.add("South", jp_south);
		
		jf.setSize(500,400);
		jf.setVisible(true);
	}
	//main메소드
	public static void main(String[] args) {
		//내 안에 있다고 하더라도 메인 안에서 호출하려면 인스턴스화를 해야 한다.
		RandomGameView rgView = new RandomGameView();
		rgView.initDisplay();
		
	}

}
