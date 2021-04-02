package level2.basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//화면 갱신 시 [패널 단위]로  표현하는 게 좋을 듯하다. ★★★★★

//JLabel은 ActionListener를 지원 받지 못하기 때문에, MouseListener를 사용하기로 한다.

class Quiz_sub extends JPanel {

	JButton jbtn = new JButton("버튼");

	public void initDisplay() {
		this.setLayout(new BorderLayout());
		this.add("Center", jbtn);
	}

}

public class Quiz1 extends JFrame implements MouseListener{

	JTextArea	jta	= new JTextArea();
	JLabel		jlb	= new JLabel("화면갱신");
	Cursor		csr = new Cursor(Cursor.HAND_CURSOR);

	public void initDisplay() {
		jlb.setCursor(csr);
		jlb.addMouseListener(this);
		jta.setBackground(Color.orange);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add("Center", jta);
		this.add("South", jlb);
		this.setSize(600, 450);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Quiz1 q1 = new Quiz1();
		q1.initDisplay();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("JLabel");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
