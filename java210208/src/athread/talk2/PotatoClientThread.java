package athread.talk2;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;

import com.network4.Protocol;

/********************************************************************************
 * 이벤트 핸들러의 역할은 [말하기]이고, 클라이언트측 스레드의 역할은 [듣기]이다.
 * 스레드도 [스레드 풀링]이 가능하다.
 ********************************************************************************/
public class PotatoClientThread extends Thread{
	PotatoClientVer2 pc = null;
	MutableAttributeSet attr = new SimpleAttributeSet();
	public PotatoClientThread(PotatoClientVer2 pc) {
		System.out.println(123);
		System.out.println(Thread.currentThread().getName().toString());
		this.pc = pc;
		this.start();
	}
	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			try {
				String msg = "";// 100#apple
				msg = (String) pc.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0;// 100|200|201|202|300|500
				if (msg != null) {
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken());// 100
				}
				switch (protocol) {
				case Protocol.ROOM_IN: {// 100#apple
					String nickName = st.nextToken();
//					pc.jta_display.append(nickName + "님이 입장하였습니다.\n");
					//그리는 부분이라서 반드시 예외처리 해주어야 함.
					try {
						pc.sd_display.insertString(pc.sd_display.getLength(), nickName+"님이 입장하였습니다.\n", attr);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Vector<String> v = new Vector<>();
					v.add(nickName);
					pc.dtm.addRow(v);
				}
					break;
				case Protocol.MESSAGE: {
					String nickName = st.nextToken();
					String message = st.nextToken();
//					pc.jta_display.append("[" + nickName + "]" + message + "\n");
					try {
						pc.sd_display.insertString(pc.sd_display.getLength(), "[" + nickName + "]" + message + "\n", attr);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					pc.jta_display.setCaretPosition(pc.jta_display.getDocument().getLength());
					//새로 메세지가 들어오면 자동으로 [포커스 이동]을 처리하도록 하자. 자동 스크롤 기능 등...
					pc.jtp_display.setCaretPosition(pc.sd_display.getLength());
				}
					break;
				case 202: {
					
				}
				case Protocol.CHANGE: {	//대화명 변경
					///////////내가 짠 코드
//					String nickName = st.nextToken();
//					String afterName = st.nextToken();
//					int whichRowNum = Integer.parseInt(st.nextToken());
//					try {
//						pc.sd_display.insertString(pc.sd_display.getLength(), nickName + "님의 닉네임이 " + afterName + "으로 변경되었습니다.\n", null);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					pc.jtp_display.setCaretPosition(pc.sd_display.getLength());
//					pc.setTitle(pc.nickName);	//nickName은 클라이언트에서 관리되고 있고, 변경시 클라이언트에서 변경되므로, 바꾼 사람만 타이틀이 바뀌고 나머지 사람들은 (본인의 타이틀은) 그대로이다.
//					pc.dtm.setValueAt(afterName, whichRowNum, 0);
					
					String nickName = st.nextToken();
					String afterName = st.nextToken();
					for(int i=0; i<pc.dtm.getRowCount(); i++) {
						String currentName = (String)pc.dtm.getValueAt(i, 0);
						if(currentName.equals(nickName)) {
							//테이블의 DefaultTableModel 갱신 처리
							pc.dtm.setValueAt(afterName, i, 0);
							break;	//break를 쓰면 for문까지 탈출한다!
						}
					}
					System.out.println("이거 출력?");
					try {
						pc.sd_display.insertString(pc.sd_display.getLength(), nickName+"님의 닉네임이 "+afterName+"으로 변경되었습니다.", null);
					} catch (Exception e) {
					}
					pc.jtp_display.setCaretPosition(pc.sd_display.getLength());
					if(nickName.equals(pc.nickName)) {
						pc.setTitle(afterName+"님의 대화창");
						pc.nickName = afterName;	//동기화 작업 중요!
					}
					
				}
					break;
				case Protocol.ROOM_OUT: {
					String nickName = st.nextToken();
//					pc.jta_display.append(nickName + "님이 퇴장 하였습니다.\n");
					try {
						pc.sd_display.insertString(pc.sd_display.getLength(), nickName + "님이 퇴장 하였습니다.\n", attr);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					pc.jtp_display.setCaretPosition(pc.jta_display.getDocument().getLength());
					pc.jtp_display.setCaretPosition(pc.sd_display.getLength());
					for (int i = 0; i < pc.dtm.getRowCount(); i++) {	//동기화	//globalList로부터 값을 읽어 올 수도 있지만, 닉네임만 아는 상태에서 이렇게 모든 값을 비교한 후 같을 때만 처리하도록 해도 된다.
						String n = (String) pc.dtm.getValueAt(i, 0);
						if (n.equals(nickName)) {	//문자열 비교 시 equals 써야 합니다~~~ 내용비교
							pc.dtm.removeRow(i);
						}
						break;
					}
				}
					break;
				}//////////// end of switch
			} catch (Exception e) {
				// TODO: handle exception
			}
		} //////////////////// end of while
	}
	
	/* 추가학습: ①과 ②의 차이를 생각해보자.
	@Override							//- ①
	public synchronized void run() {
		
	}
	@Override							//- ②
	public synchronized void run() {
		synchronized (this) {
			
		}
	}
	*/
}
