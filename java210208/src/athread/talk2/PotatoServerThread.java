package athread.talk2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;

import com.network4.Protocol;

import athread.talk1.TalkServer;
import athread.talk1.TalkServerThread;

public class PotatoServerThread extends Thread {
	
	public PotatoServer ps = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null;// 현재 서버에 입장한 클라이언트 스레드 닉네임 저장

	public PotatoServerThread(PotatoServer ps) {
		// 전역변수임을 구분할 수 있도록 "상징적으로" this를 붙임.
		this.ps = ps;
		this.client = ps.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());	//홀수소켓
			ois = new ObjectInputStream(client.getInputStream());	//짝수소켓
			//130#은영[청취: 듣기]
			String msg = (String) ois.readObject();					//듣기
			ps.jta_log.append(msg + "\n");							//서버에 출력
			StringTokenizer st = new StringTokenizer(msg, "#");		//자르기
			st.nextToken();											// 130
			chatName = st.nextToken();								//은영
			ps.jta_log.append(chatName + "님이 입장하였습니다람쥐.\n");
			for (PotatoServerThread pst : ps.globalList) {			//은영만 입장한 상태에서는 목록이 비어있으므로 for문이 돌지X
				// 이전에 입장해 있는 친구들 정보 받아내기
				// String currentName = tst.chatName;
				pst.send(100 + "#" + pst.chatName);
			}
			// 현재 서버에 입장한 클라이언트 스레드 추가하기
			ps.globalList.add(this);								//위쪽의 for문은 건너뛰고 (은영의)스레드가 추가된다.
			this.broadCasting(msg);									//방송 - 1명(은영)에게만 전송.
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}/////////////////////// [[end of TomatoServerThread]] /////////////

	// 현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		for (PotatoServerThread pst : ps.globalList) {				//현재 globalList.size()=1 (은영만 있음) -> 2(희태입장)
//			this.send(msg);											//이건 망하는 거야~ 은영한테만 쓰면 어째
			pst.send(msg);											//은영에게만 보내짐.
		}
	}

	// 클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (SocketException se) {
			se.toString();
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String msg = null;
		boolean isStop = false;
		try {
			// while(true) {//무한루프에 빠질 수 있다.
			run_start:
				while (!isStop) {
				msg = (String) ois.readObject();
				ps.jta_log.append(msg + "\n");
				ps.jta_log.setCaretPosition(ps.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				
				int protocol = 0;// 100|200|201|202|500
				if (msg != null) {
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken());// 100
				}
				switch (protocol) {
				case Protocol.MESSAGE: {
					String nickName = st.nextToken();
					String message = st.nextToken();
					broadCasting(Protocol.MESSAGE
							   + "#"
							   + nickName
							   + "#"
							   + message);
				}
					break;
				case 202: {

				}
					break;
				case Protocol.CHANGE: {	//대화명 변경	//300#하하#하늘소
					String nickName = st.nextToken();
					String afterName = st.nextToken();
					this.chatName = afterName;	//서버측 이름과 동기화 하는 작업임. 잊지 말자!
					ps.jta_log.append("닉네임 변경: " + nickName + " -> " + "afterName\n");
					broadCasting(Protocol.CHANGE 				//말하기.
							   + "#"
							   + nickName
							   + "#"
							   + afterName
							   + "#" +
							   ps.globalList.indexOf(this));	//마지막 숫자는 [대화명] 목록의 몇번째에 있는 클라이언트인지를 알려주기 위한 숫자이다.
					
				}
				case Protocol.ROOM_OUT: {
					String nickName = st.nextToken();
					ps.globalList.remove(this);
					broadCasting(Protocol.ROOM_OUT + "#" + nickName);
					System.out.println("종료되었습니다.");
				}
					break run_start;	//라벨 - 라벨로 이름이 붙은 블럭 전체를 빠져나가게 함.
				}///////////// end of switch
			} ///////////////// end of while
		} catch (Exception e) {
			
		}
	}///////////////////////// end of run
	
	
}
