package com.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;

public class BaseBallGameLogic {
	//JTextArea에 순번(횟수) 출력하기
	int cnt = 0;
	//컴퓨터가 난수발생으로 얻어낸 값 저장
	//채번하는 메소드가 필요함. 왜? 메소드 중심의 코딩을 전개하기 위해서(재사용성의 첫 단추) //재사용되는 시점? '새게임' 또는 '정답'을 눌렀을 때.
	int[] com = new int[3];	// 0 0 0
	//사용자가 입력한 값은 로컬에서 온다.
	int[] my = new int[3];	// 0 0 0
	public void nanSu(){
		com[0] = (int)(Math.random()*10);
		do{
			com[1] = (int)(Math.random()*10);
		}while(com[0]==com[1]);
		do{
			com[2] = (int)(Math.random()*10);
		}while((com[0]==com[2])||(com[1]==com[2]));
		System.out.println(com[0]+""+com[1]+""+com[2]);
	}
	//전광판에 출력될 메시지를 작성하는 메소드 선언
	/*****************************************************************
	 * @param	input jp_center속지 - 남쪽 - JTextField(숫자를 입력해도 문자로 인식함) - "256"
	 * @return	1S 1B, 2S 1B...
	 * 1회: (사용자 입력값)256 - (출력)1S 0B
	 * 2회: (사용자 입력값)356 - (출력)1S 1B ...
	 * 회차를 알려주는 cnt는 전역변수가 되어야 한다. (전역변수 - static 고려 대상이기는 함 / 그러나 지금은 '바뀌는' 값이므로 static X)
	 * strike, ball을 알려주는 변수는 지역변수가 되어야 한다. (지역변수 - static 고려 대상이 아님)
	 */
	public String call(String input){
		//스트라이크를 카운트할 변수
		int strike = 0;
		//볼을 카운트할 변수
		int ball = 0;		
		int temp = 0;
		//반드시 세자리 숫자 이어야 한다.
		if(input.length()!=3){
			return "세자리 숫자만 입력하세요!!!";
		}
		temp = Integer.parseInt(input);
		my[0] = temp/100;//백자리를 받는다.
		my[1] = (temp%100)/10;//십자리를 받는다.
		my[2] = temp%10;
		for(int i=0;i<com.length;i++){
			for(int j=0;j<my.length;j++){
				//같은 숫자가 존재하는 경우(볼확보)
				//컴퓨터가 채번한 숫자가 있는지 비교
				if(com[i] == my[j]){
					//자리수까지도 일치하는 경우(스트라이크확보)
					//그 숫자가 존재하는 배열의 인덱스값을 비교
					if(i==j){
						strike++;
					}else{
						ball++;
					}
				}//  end of if          ////////////////
			}//////  end of inner for   ////////////////
		}//////////  end of outter for  ////////////////
		if(strike == 3) return "정답입니다.";
		return strike+"스트라이크" +ball+"볼";
	}/////////////  end of call ////////////////////////	
	//화면처리하기
	public void initDisplay(){
		jm_game.add(jmi_new);
		jm_game.add(jmi_dap);
		jm_game.add(js_game);
		jm_game.add(jmi_exit);
		jm_about.add(jmi_info);
		jm_about.add(jmi_maker);
		jmb.add(jm_game);
		jmb.add(jm_about);
		//JFrame에 JMenuBar설정
		jf.setJMenuBar(jmb);
		jm_game.setMnemonic('G');
		jta_display.setEditable(false);
		jp_center.setLayout(new BorderLayout());
		jp_east.setLayout(new GridLayout(4,1,2,2));
		jp_center.setBackground(Color.green);
		jp_east.setBackground(Color.yellow);
		for(int i=0;i<4;i++){
			jbtns[i] = new JButton(jbtns_label[i]);
			System.out.println(jbtns[i]);//@abc1234
			jp_east.add(jbtns[i]);
		}
		//이벤트소스와 이벤트핸들러 클래스 매핑 코드
		jtf_input.addActionListener(this);
		jbtns[0].addActionListener(this);
		jbtns[1].addActionListener(this);
		jbtns[2].addActionListener(this);
		jbtns[3].addActionListener(this);
		Font myFont = new Font("Thoma",Font.BOLD,12);
		jbtns[0].setFont(myFont);
		//Color newColor = new Color(158,9,9);
//		jbtns[0].setBackground(newColor);
		jbtns[0].setBackground(new Color(158,9,9));
		jbtns[0].setForeground(new Color(212,212,212));
		jbtns[1].setBackground(new Color(7,84,170));
		jbtns[1].setForeground(new Color(212,212,212));
		jbtns[2].setBackground(new Color(19,99,57));
		jbtns[2].setForeground(new Color(212,212,212));
		jbtns[3].setBackground(new Color(54,54,54));
		jbtns[3].setForeground(new Color(212,212,212));
		jta_display.setBackground(new Color(255,255,200));
		jta_display.setForeground(new Color(57,109,165));
		int width = 400;
		int height = 300;
		boolean isVisible = true;
		jp_center.add("Center",jsp_display);
		jp_center.add("South",jtf_input);
		jf.add("Center",jp_center);
		jf.add("East",jp_east);
		//화면이 열리면서 JTextField쪽에 커서가 깜박이기.
		jtf_input.requestFocus();
		jf.setTitle("야구숫자게임");
		jf.setSize(width, height);
		jf.setVisible(isVisible);
	}	
}
