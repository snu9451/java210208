package athread.talk1_0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TalkClientVer2 extends JFrame implements ActionListener{
	
	//////////////////속지 두 장 추가하자/////////////////
	JPanel				jp_first		= new JPanel();
	JPanel				jp_second		= new JPanel();
	JPanel				jp_second_south	= new JPanel();
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
	///////////////두 번째 화면에 들어갈 전변///////////////
	String cols[] = {"대화명"};
	String data[][] = new String[0][1];
	DefaultTableModel dtm_nick = new DefaultTableModel(data, cols);
	JTable jtb_nick = new JTable(dtm_nick); 
	JScrollPane jsp_nick = new JScrollPane(jtb_nick);	//해당 요소에 스크롤 추가
	JButton jbtn_one = new JButton("1:1대화");
	JButton jbtn_change = new JButton("대화명 변경");
	JButton jbtn_emoticon = new JButton("이모티콘");
	JButton jbtn_exit = new JButton("나가기");
	
	
	//소켓 관련 초기화
	public void init() {
		try {
			//소켓 생성하기 - ip, port# 필요 - 이것을 통해 서버 측의 ServerSocket에 연결된다.
			socket 	= new Socket("192.168.0.29", 3000);
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
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center",jsp_display);
		jp_first.add("South", jp_south);
		jp_first.setBackground(Color.green);
		jp_second.setLayout(new BorderLayout());
		jp_second.setBackground(Color.red);
		jp_second.add("Center", jsp_nick);
		jp_second_south.add(jbtn_one);			//GridLayout이 채워지는 순서: (1,1) - (1,2) - (2,1) - (2,2)
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_emoticon);
		jp_second_south.add(jbtn_exit);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second.add("South", jp_second_south);
		
		
		this.setLayout(new GridLayout(1,2));
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName+"님의 창");
		this.setSize(500, 400);
		this.setVisible(true);						//메모리에 활성화는 되어 있지만 보이지 않는 화면을 '보여주세요'.
	}
	
	//메인메소드
	public static void main(String[] args) {
		TalkClientVer2 tc = new TalkClientVer2();
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
							  + "#"+msg);
				jtf_msg.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
