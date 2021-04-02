package athread.talk1_0;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class TalkServerThread extends Thread {
	
	TalkServer			ts			= null;
	Socket 				client 		= null;
	ObjectOutputStream	oos 		= null;
	ObjectInputStream	ois			= null;
	//닉네임
	String				nickName	= null;
	/*
	 * 클라이언트 측에서 서버 소켓에 접속하고나면 생성자를 통해서 TalkServer의 주소번지를 받게 되고, 이것으로 소켓에 접속이 가능함.
	 * 이 소켓으로 ois와 oos를 생성하고 접속해 온 사용자의 정보를 청취함
	 * 청취한 내용을 TalkServer의 jta_display에 표시함
	 */
	public TalkServerThread(TalkServer talkServer) {
		this.ts = talkServer;
		this.client = ts.client;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject();
			ts.jta_log.append(msg + "\n");	//<100#닉네임>이 화면에 뜸
			StringTokenizer st = new StringTokenizer(msg, "#");
			st.nextToken();	//100	--어떤 변수에 저장하고 있지 않으므로 날라감. 필요한 건 두번째값(닉네임)부터여서 이짓 함.
			nickName = st.nextToken();
			ts.jta_log.append(nickName+"님이 입장하셨습니다.\n");
			/////////////////////////////////////////////
			for(TalkServerThread tst: ts.globalList) {					//★★★★
				//재훈이 입장하기 전에 있는 친구들의 정보를 받기							//★★★★
				this.send(100+"#"+tst.nickName);	//this는 현재 스레드		//★★★★
			}															//★★★★
			ts.globalList.add(this);				//현재 스레드는 재훈 스레드	//★★★★
			this.broadCasting(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	//globalList에는 '서버에 접속해 온 클라이언트'에 관한 '스레드 객체'가 담겨 있음
	public void broadCasting(String msg) {
		for(TalkServerThread tst:ts.globalList) {
			//그 스레드에 send메소드를 호출하여 메시지를 전송함.
			tst.send(msg);
		}
	}
	
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("서버 run 호출 성공");
		boolean		isStop = false;
		try {
			run_start:
				while(!isStop) {
					try {
						String msg = "";
//						System.out.println("무한루프 도니?");
						msg = (String)ois.readObject();
						ts.jta_log.append(msg);
						StringTokenizer st = null;
						int protocol = 0;
						if(msg!=null) {
							st 			= new StringTokenizer(msg, "#");
							protocol 	= Integer.parseInt(st.nextToken());
						}
						switch(protocol) {
							case 200: {
								String nickName = st.nextToken();
								String msg2 = st.nextToken();
								broadCasting(msg);
//								broadCasting(protocol+"#"+nickName+"#"+msg2);
							}
						}
					} catch (Exception e) {
						System.out.println("예외발생!");
					}
				}
		} catch (Exception e) {
			
		}
		
	}
}
