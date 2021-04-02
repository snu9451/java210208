package com.pattern;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutView {

	public static void main(String[] args) {
		//일단 main에 진행
		JButton jbtn_north = new JButton("북쪽");
		JButton jbtn_south = new JButton("남쪽");
		JButton jbtn_center = new JButton("중앙");
		JButton jbtn_west = new JButton("서쪽");
		JButton jbtn_east = new JButton("동쪽");
		//JFrame: 윈도우에서 독립된 창을 띄울 때 사용함
		JFrame jf = new JFrame();
		//JFrame의 배치 레이아웃을 정함.(★창을 잡아늘리면 중앙의 크기가 크게 변함★)
		jf.setLayout(new BorderLayout());
		//@param1: 위치 정보, @param2: 주소번지
		//jf.add("North", jbtn_north);	//위치를 표시할 때 꼭 첫글자는 대문자로('N')
		jf.add("South", jbtn_south);
		jf.add("Center", jbtn_center);
		//jf.add("West", jbtn_west);
		//jf.add("East", jbtn_east);
		jf.setSize(500,300);
		jf.setVisible(true);
		
		//GridLayout과 BorderLayout을 동시에 줄 수 없어서 Panel을 생성할 것임
		
	}

}
