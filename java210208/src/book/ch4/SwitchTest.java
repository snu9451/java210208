package book.ch4;

import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SwitchTest extends JFrame {

	public static void main(String[] args) {
		int protocol = 300;
		/* [ protocol(통신규약) ]
		 * 100번이면 로그인, 200번이면 입장하기, 300번이면 다자간 대화,
		 * 301번이면 1:1대화 등으로 설계할 수 있다.
		 *////////////////////////////////////////////////////////
		SwitchTest swt 		= new SwitchTest();
		String nickName		= "tomato";
		String inputVal		= "오늘 스터디 할까?";
		String msg			= protocol+"#"+nickName+"#"+inputVal;
		StringTokenizer st 	= new StringTokenizer(msg,"#");		//#으로 문자열을 쪼갬(지금의 경우, 세 부분으로 쪼개짐)
		protocol			= Integer.parseInt(st.nextToken());	//st.nextToken = nickName = "tomato" (하나씩 꺼냄)
		String s_nickName	= st.nextToken();
		String s_inputVal	= st.nextToken();
		/* 제어문의 역할: 업무 순서에 대한 흐름을 바꾼다.
		 * 순서에 대한 정보를 바탕으로 의사를 결정하는 사람은 팀장, 차장, 부장 선택자
		 * 선택(비즈니스로직계층-model계층)에 따라
		 *////////////////////////////////////////////////////////
		
		/* [ break 와 continue ]
		 * break: for문과 while문에 쓰여서 '무한루프'를 방지함.
		 * 		  switch문을 빠져 나가기 위해 사용함.
		 * (단, if문에서는 break 대신에 return 사용 - 메소드 자체를 탈출)
		 *////////////////////////////////////////////////////////
		
		switch(protocol) { //원시형타입+String타입
			case 100:
				//실행문
				System.out.println("100입니다.");
				break;
			case 301:	//case 10>301: break가 없으면, 여기서부터 아래로 다 실행됨.
				//실행문
				System.out.println("301입니다.");
				break;
			case 300:
				//실행문
				System.out.println("["+s_nickName+"]"+s_inputVal);
				break;
			default:	//현재 protocol로 301이 대입되었으므로 default가 실행된다.
				JOptionPane.showMessageDialog(swt, "잘못된 메시지 입니다.");
				break;
		}////////////////////end of switch
		System.out.println("끝.");
	}////////////////////////end of main

}
