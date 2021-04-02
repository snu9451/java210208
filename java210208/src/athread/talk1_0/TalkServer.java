package athread.talk1_0;

import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * 클라이언트 측에서 접속 시도가 있을 때, 서버 측에서 클라이언트의 로그 정보를 볼 수 있도록 하는 것이 좋으므로
 * 화면에 전광판을 하나 추가하여 텍스트로 로그 정보를 확인할 수 있도록 할 것이다. 이를 위해 JFrame을 상속 받았다.
 * main메소드가 있는 클래스는 <디폴트 스레드>를 가진다.
 * 이 스레드에서 소켓 서버 정보를 관리하는 것에 경합이 발생할 수 있고, 그에 따라 충돌이나 이상이 발생할 수 있다.
 * 따라서 별도의 스레드를 구현하고, 그 run메소드 안에서 안전하게 소켓들이 생성되고 관리될 수 있도록 하자.
 */
public class TalkServer extends JFrame implements Runnable {
	///////////////통신과 관련된 전역변수 추가///////////////
	ServerSocket				server		= null;
	Socket						client		= null;	//클라이언트의 소켓	//서버만이 클라이언트(의 소켓)-클라이언트(의 소켓)를 연결할 수 있다.
	TalkServerThread 			tst 		= null;
	Vector<TalkServerThread>	globalList 	= null;	//★★★★★★★★★★★★★★★오늘의 핵심 Keyword; 해시태그
	JTextArea			jta_log	= new JTextArea();
	JScrollPane			jsp_log	= new JScrollPane(jta_log);
	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
		jta_log.setBackground(Color.orange);
		jta_log.setEditable(false);
		this.add("Center", jsp_log);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		Thread th = new Thread(ts);
		th.start();	//이 코드에 의해 run메소드가 호출된다.
	}
	@Override
	public void run() {
		System.out.println("run 호출 성공");
		globalList = new Vector<>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3000);
			jta_log.append("Server Ready...\n");
			while(!isStop) {
				//기다려....
				client = server.accept();
				jta_log.append("client info: "+client+"\n");
				TalkServerThread tst = new TalkServerThread(this);
				tst.start();
			}
		} catch (Exception e) {
			//예외 발생에 대한 메시지들을 stack 영역에 관리하는데 이것들을 출력해주는 메소드이다.
			//라인 번호와 호출 순서에 대한 내용까지도 포함하므로 더 많은 힌트를 얻을 수 있다.
			e.printStackTrace();
		}
	}

}
