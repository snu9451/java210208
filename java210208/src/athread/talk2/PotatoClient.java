package athread.talk2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
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

public class PotatoClient extends JFrame implements ActionListener {
	//////////////// 통신과 관련한 전역변수 추가 시작//////////////
	Socket socket = null;
	ObjectOutputStream oos = null;// 말 하고 싶을 때
	ObjectInputStream ois = null;// 듣기 할 때
	String nickName = null;// 닉네임 등록
	//////////////// 통신과 관련한 전역변수 추가 끝 //////////////
	JPanel jp_second = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_one = new JButton("1:1");
	JButton jbtn_change = new JButton("대화명변경");
	JButton jbtn_font = new JButton("글자색");
	JButton jbtn_exit = new JButton("나가기");
	String cols[] = { "대화명" };
	String data[][] = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data, cols);
	JTable jtb = new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jtb);
	JPanel jp_first = new JPanel();
	JPanel jp_first_south = new JPanel();
	JTextField jtf_msg = new JTextField(20);// south속지 center
	JButton jbtn_send = new JButton("전송");// south속지 east
	JTextArea jta_display = null;
	JScrollPane jsp_display = null;
	// 배경 이미지에 사용될 객체 선언-JTextArea에 페인팅
	Image back = null;

	public PotatoClient() {
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
	}

	public void initDisplay() {
		// 사용자의 닉네임 받기
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		this.setLayout(new GridLayout(1, 2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center", jsp);
		jp_second_south.setLayout(new GridLayout(2, 2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South", jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center", jtf_msg);
		jp_first_south.add("East", jbtn_send);
		back = getToolkit().getImage("src\\athread\\talk2\\back.jpg");
		jta_display = new JTextArea() {
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);
		jta_display.setEditable(false);
		Font font = new Font("돋움", Font.BOLD, 25);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display);
		jp_first.add("Center", jsp_display);
		jp_first.add("South", jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		// swing에 대한 skin을 사용하기 위한 설정이다.
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 메인 스레드가 우선권을 가짐.
		PotatoClient pc = new PotatoClient();
		// 화면 호출.
		pc.initDisplay();
		/*
		 * 소켓 생성 - PotatoServer쪽에서 ServerSocket을 감지해서 (일반)Socket에 전달하게 됨. Server 측의
		 * run() 안에서 PotatoServerThread가 생성됨(생성자 호출로 연결) - "생성자(this)"를 통해 PotatoServer와
		 * PotatoServerThread가 연결되게 되고 따라서 [듣기]가 가능해지게 됨. 듣기가 가능할 전제조건: 생성자 안에서 OOS(왜냐,
		 * 홀수부터 처리하므로, write는 홀수), OIS를 인스턴스화 해야 함. 이것은 소켓 객체가 있어야 가능한 일이다.
		 */
		pc.init();
	}

	// 소켓 관련 초기화
	public void init() {
		try {
			// 서버측의 ip주소 작성하기
			socket = new Socket("localhost", 3002);
//			socket = new Socket("192.168.0.37", 3002);	//쌤꺼
//			socket = new Socket("192.168.0.32", 7375);	//희태꺼
			// PotatoServer의 ServerSocket에서 감지가 일어남. + client = server.accept();로 클라이언트 소켓에
			// 대한 정보를 갖게 됨.
			// 홀수 소켓에 대한 처리(항상 홀수 소켓을 먼저해라. "oos")
			oos = new ObjectOutputStream(socket.getOutputStream());
			// 짝수 소켓에 대한 처리
			ois = new ObjectInputStream(socket.getInputStream());
			// initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
			// 서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(100 + "#" + nickName); // 말한다.
			// PotatoServerThread의 생성자가 듣기를 하게 되는 순간이 바로 이 지점이다.
			// 서버에 말을 한 후 들을 준비를 한다. - 여기서 대기를 탄다. 언제까지? 누군가 말을 할때까지. 누군가 말을 하면 듣기를 한다.
			// 듣기 시, [프로토콜]을 비교해야 한다. - 설계자는 이 부분(프로토콜 설정)을 신경써야 한다.
			// 프로젝트의 설계자는 [프로토콜을 설계]하고 [ERD를 그려]라. 그린 ERD로 데이터 클래스를 설계하고 거기에 List, Map 등을 갈아
			// 넣어야 한다. + 단위테스트도 해줘야 함.
			PotatoClientThread pct = new PotatoClientThread(this);
			pct.start();
		} catch (Exception e) {
			// 예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();
		if (jbtn_one == obj) {

		} else if (jtf_msg == obj) {
			try {
				oos.writeObject(201 + "#" + nickName + "#" + msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (jbtn_exit == obj) {
			try {
				oos.writeObject(500 + "#" + this.nickName);
				// 자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
			if (afterName == null || afterName.trim().length() < 1) {
				JOptionPane.showMessageDialog(this, "변경할 대화명을 입력하세요", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				oos.writeObject(202 + "#" + nickName + "#" + afterName + "#" + nickName + "의 대화명이 " + afterName
						+ "으로 변경되었습니다.");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}////////////////////// end of actionPerformed
}
