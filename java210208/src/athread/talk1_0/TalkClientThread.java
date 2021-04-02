package athread.talk1_0;

import java.util.StringTokenizer;
import java.util.Vector;

public class TalkClientThread extends Thread{
	TalkClient tc = null;
	TalkClientVer2 tc2 = null;
//	TalkClient tc = new TalkClient();	//'원본'을 써야하므로 게으른 인스턴스화 해야 한다.
	
	public TalkClientThread(TalkClient talkClient) {
		this.tc = talkClient;	//조립/연결하는 것이다.
	}
	public TalkClientThread(TalkClientVer2 talkClientVer2) {
		this.tc2 = talkClientVer2;	//조립/연결하는 것이다.
	}

	@Override
	public void run() {	//run메소드는 말하는 곳이다? 듣는 곳이다? <듣는 곳>이다.
		System.out.println("클라이언트 run 호출 성공");
		boolean		isStop = false;
		while(!isStop) {		//while문 쓰는 이유?-------------------------------------------------------------------------------------------
			try {
				String msg = "";
				msg = (String)tc.ois.readObject();	//(String): Casting연산자
				StringTokenizer st = null;			//"#"을 기준으로 문자열과 문자열을 '썰 수 있는' 클래스이다.
				int protocol = 0;					//100#..에서 100
				if(msg!=null) {	//100#재훈#오늘 스터디 할까?
					st 			= new StringTokenizer(msg, "#");
					protocol 	= Integer.parseInt(st.nextToken());	//100
				}
				switch(protocol) {
					case 100: {
						String nickName = st.nextToken();
//						Vector<String> imsi = new Vector<>();			//내가 작성한 부분....고민해보자---------------------------------------------
//						imsi.add(nickName);
//						tc2.dtm_nick.addRow(imsi);		//테이블에 값을 추가해서 화면에 띄우려면 dtm에 추가? 아니면 jtb에 추가?
						tc.jta_display.append(nickName+"님이 입장하셨습니다.\n");
					};
					case 200: {
						String nickName = st.nextToken();	//재훈
						String msg2 = st.nextToken();		//오늘 스터디 할까?
						tc.jta_display.append("["+nickName+"]"+msg2);
					};
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
