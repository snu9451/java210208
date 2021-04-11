package ui.hanbit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class WestPanel extends JPanel {

	JButton	jbtn_login	= null;
	JButton	jbtn_search	= null;
	JButton	jbtn_yeme	= null;
	JButton	jbtn_null	= null;

	public WestPanel() {
		initDisplay();
	}

	public void initDisplay() {
		// 생성
		jbtn_login = new JButton("로그인");
		jbtn_search = new JButton("영화검색");
		jbtn_yeme = new JButton("영화예매");
		// 중간에 비는 게 아니라면 GridLayout의 뒷부분은 비워도 오류나지 않음!
//		JButton[] jbtn_arr = {jbtn_login, jbtn_search, jbtn_yeme
//							, jbtn_null, jbtn_null, jbtn_null
//							, jbtn_null, jbtn_null, jbtn_null
//							, jbtn_null};
		JButton[] jbtn_arr = { jbtn_login, jbtn_search, jbtn_yeme };

		jbtn_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[로그인] 버튼 호출 성공");
			}
		});
		jbtn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[영화검색] 버튼 호출 성공");
			}
		});
		jbtn_yeme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[영화예매] 버튼 호출 성공");
			}
		});

		this.setLayout(new GridLayout(10, 1, 0, 3));
		this.setBackground(Color.white);
		for (JButton jbtn : jbtn_arr) {
//			jbtn.setHorizontalAlignment(JButton.LEFT);	//글자가 정렬됨
			jbtn.setBackground(Color.white);
			this.add(jbtn);
		}

	}

}

class CenterPanel extends JPanel {

	JScrollPane					jsp				= null;
	JTable						jtb				= null;
	DefaultTableCellRenderer	dcr				= null;
	String[]					cols			= { "순위", "영화명", "상영시간", "상영관", "잔여 좌석수" };
	Vector						oneMovie		= new Vector();												// 서로다른 타입을
																											// 담기 위해
																											// ArrayList로
																											// 선언

	Image						geumja_image	= getToolkit().getImage("src\\my\\practice\\geumja.jpg");
	JLabel						jlb_geumja		= new JLabel() {
													private static final long serialVersionUID = 1L;

													public void paint(Graphics g) {
//			g.drawImage(geumja_image, 0, 0, this);
														Point p = jsp.getViewport().getViewPosition();
														g.drawImage(geumja_image, p.x, p.y, null);
														paintComponent(g);
													}
												};
//	ImageIcon geumja_imageicon = new ImageIcon("src\\my\\practice\\geumja.jpg", "친절한 금자씨");

	public CenterPanel() {
		initDisplay();
		movieAdd();
	}

	public void initDisplay() {

		this.setLayout(new BorderLayout());

//		dtm = new DefaultTableModel(new Object[10][5], cols) {
//			public boolean isCellEditable(int row, int col) {
//				return false;
//			}
//		};
//		jtb = new JTable(dtm);
		
		dcr = new DefaultTableCellRenderer();
		jtb = new JTable(dcr);
		
		jtb.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(jtb.getValueAt(jtb.getSelectedRow(), jtb.getSelectedColumn()));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		jsp = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		jlb_geumja = new JLabel();

		jtb.setPreferredSize(new Dimension(500, 1000));
		jtb.setOpaque(true);
		jtb.setBackground(Color.white);
		jtb.getTableHeader().setReorderingAllowed(false);
		jtb.getTableHeader().setBackground(Color.orange);

		this.add("Center", jsp);
//		this.add("Center", geumja_image);

	}

	public void movieAdd() {
		while (dtm.getRowCount() > 0) {
			dtm.removeRow(0);
		}
		oneMovie.add("1");
		oneMovie.add(geumja_image);
		dtm.addRow(oneMovie);
	}

}

public class MovieManager extends JFrame {

	JPanel		jp_center	= null;
	WestPanel	wp			= null;
	CenterPanel	cp			= null;

	public MovieManager() {

		jp_center = new JPanel();
		wp = new WestPanel();
		cp = new CenterPanel();

		jp_center.setLayout(new BorderLayout());

		jp_center.add("West", wp);
		jp_center.add("Center", cp);
		this.add("Center", jp_center);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("영화검색기");
		this.setSize(800, 600);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MovieManager();
	}

}
