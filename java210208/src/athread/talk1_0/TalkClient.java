package athread.talk1_0;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TalkClient extends JFrame implements ActionListener{

	///////////////통신과 관련된 전역변수 추가///////////////
	Socket				socket		= null;
	//장점: <직렬화>를 누릴 수 있다.
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;
	//닉네임을 담을 변수의 선언
	String				nickName	= null;
	///////////////화면과 관련된 전역변수 추가///////////////
	JTextArea			jta_display	= new JTextArea();
	JScrollPane			jsp_display	= new JScrollPane(jta_display);
	JPanel				jp_south	= new JPanel();			//오늘은 그냥 이른 인스턴스화 했음
	JTextField			jtf_msg		= new JTextField(20);	//글자 20자 담을 수 있음
	JButton				jbtn_send	= new JButton("전송");
	
	
	
	
	//소켓 관련 초기화
	public void init() {
		try {
			//소켓 생성하기 - ip, port# 필요 - 이것을 통해 서버 측의 ServerSocket에 연결된다.
			socket 	= new Socket("localhost", 3000);
			oos		= new ObjectOutputStream(socket.getOutputStream());
			ois		= new ObjectInputStream(socket.getInputStream());
			oos.writeObject(100+"#"+nickName);
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();	//run()호출
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//화면처리부
	public void initDisplay() {
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요");
		jtf_msg.addActionListener(this);
		jta_display.setEditable(false);
		jp_south.setLayout(new BorderLayout()); 	//Center, East, West... 잡기 위해서 넣은 코드
		jp_south.add("Center", jtf_msg);
		jp_south.add("East", jbtn_send);
		this.add("Center", jsp_display);
		this.add("South", jp_south);
		this.setTitle(nickName+"님의 창");
		this.setSize(500, 400);
		this.setVisible(true);						//메모리에 활성화는 되어 있지만 보이지 않는 화면을 '보여주세요'.
	}
	
	//메인메소드
	public static void main(String[] args) {
		TalkClient tc = new TalkClient();
		tc.initDisplay();	//(1) 화면을 먼저 띄우고
		tc.init(); 			//(2) 소켓처리
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		String msg = jtf_msg.getText();
		if(obj == jtf_msg) {
			try {
				oos.writeObject(200
							  + "#"+nickName
							  + "#"+msg+"\n");
				jtf_msg.setText("");	//엔터누르면 텍스트 필드는 비워지도록.
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
