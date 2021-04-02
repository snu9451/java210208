package com.design.zipcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;

public class ZipcodeSearch2 extends JFrame implements MouseListener
												   	, ItemListener
												   	, FocusListener
												   	, ActionListener {
	// 선언부
	String zdo = null;
	// DBConnectionMgr 클래스 선언
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();	//어차피 싱글톤으로 관리되므로, 전역변수 선언부에서 인스턴스를 가져와도 좋다.
	// 물리적으로 떨어져 있는 db서버와 연결통로 만들기
	Connection con = null;
	// 위에서 연결되면 쿼리문을 전달할 전령의 역할을 하는 인터페이스 객체 생성하기
	PreparedStatement pstmt = null;
	// 조회된 결과를 화면에 처리해야 하므로 오라클에 커서를 조작하기 위해 ResultSet추가
	ResultSet rs = null;
	JPanel jp_north = new JPanel();
	// insert here
	String zdos[] = { "전체", "서울", "경기", "강원" };
	String zdos2[] = { "전체", "부산", "전남", "대구" };
	Vector<String> vzdos = new Vector<>();// vzdos.size()==>0
	JComboBox jcb_zdo = null;// West
	JComboBox jcb_zdo2 = null;// West
	JTextField jtf_search = new JTextField("동이름을 입력하세요.");// Center
	JButton jbtn_search = new JButton("조회");// East
	String cols[] = { "우편번호", "주소" };
	String data[][] = new String[0][2];
	DefaultTableModel dtm_zipcode = new DefaultTableModel(data, cols);
	JTable jtb_zipcode = new JTable(dtm_zipcode);
	JTableHeader jth = jtb_zipcode.getTableHeader();
	JScrollPane jsp_zipcode = new JScrollPane(jtb_zipcode
											, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
											, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String zdos3[] = null;
	MemberShip memberShip = null;


	// 생성자
	public ZipcodeSearch2() {
		zdos3 = getZDOList();
		jcb_zdo = new JComboBox(zdos3);
	}

	public ZipcodeSearch2(MemberShip memberShip) {
		this();
		this.memberShip = memberShip;
	}

	// 화면처리부
	public void initDisplay() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jth.setBackground(new Color(0, 200, 100));
		jth.setFont(new Font("궁서체", Font.BOLD, 15));
		jtb_zipcode.setGridColor(new Color(100,100,100));
		jtb_zipcode.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtb_zipcode.getColumnModel().getColumn(1).setPreferredWidth(300);
		jtb_zipcode.requestFocus();
		jtb_zipcode.addMouseListener(this);
		jbtn_search.addActionListener(this);
		jtf_search.addFocusListener(this);
		jtf_search.addActionListener(this);
		jp_north.setLayout(new BorderLayout());
		// vzdos.copyInto(zdos2);
		for (int x = 0; x < zdos2.length; x++) {
			vzdos.add(zdos2[x]);
		}
		for (String s : vzdos) {
			System.out.println("s===>" + s);
		}
		jcb_zdo2 = new JComboBox(zdos3);// West
		jcb_zdo2.addItemListener(this);
		jp_north.add("West", jcb_zdo2);
		jp_north.add("Center", jtf_search);
		jp_north.add("East", jbtn_search);
		this.add("North", jp_north);
		this.add("Center", jsp_zipcode);
		this.setTitle("우편번호 검색");
		this.setSize(430, 400);
		this.setVisible(true);
	}

	// 메인메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);	//MVC패턴을 반영한 최초의 API
		ZipcodeSearch2 zcs = new ZipcodeSearch2();
		zcs.initDisplay();
	}

	@Override
	public void focusGained(FocusEvent e) {
		System.out.println("focusGained 호출 성공");
		if (e.getSource() == jtf_search) {
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	//콤보 박스에 뿌려질 ZDO 컬럼의 정보를 오라클 서버에서 꺼내오기
	public String[] getZDOList() {
		String zdos[] = null;
		//오라클 서버에 보낼 select문 작성하기
		//String 자체는 원본이 바뀌지 않는 특성을 가진다.
		//StringBuilder는 단일 스레드에서 안전하고
		//StringBuffer는 다중 스레드에서 안전하다.
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT '전체' zdo FROM dual      	");
		sb.append(" UNION ALL                       ");
		sb.append("SELECT zdo                       ");
		sb.append("  FROM (    	           			");
		sb.append("        SELECT distinct(zdo) zdo ");
		sb.append("          FROM ZIPCODE_T         ");
		sb.append("         ORDER BY zdo asc        ");
		sb.append("       )                         ");
		try {
			con 	= dbMgr.getConnection();
			pstmt 	= con.prepareStatement(sb.toString());	//String 타입을 매개변수(파라미터)로 받기 때문에 toString으로 타입전환하였음.
			rs = pstmt.executeQuery();
			//배열로 받자	//'도'에 관한 데이터이므로 String
			Vector<String> v = new Vector<>();
			List<String> v2 = new Vector<>();
			while(rs.next()) {
				String zdo = rs.getString("zdo");
				v.add(zdo);
			}
			zdos = new String[v.size()];
			v.copyInto(zdos);
//			v2.copyInto(zdos);
		} catch (Exception e) {
			
		} finally {
			
		}
		return zdos;
	}

	public void refreshData(String zdo, String dong) {
		System.out.println("zdo:" + zdo + ", dong:" + dong);
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT zipcode, address" 				);
		sql.append("  FROM zipcode_t"		 				);
		sql.append(" WHERE 1=1"				 				);
		//둘 다 각각 처리되어야 하므로 if-esle가 아니라 if-if 사용
		if((zdo != null) && (zdo.length() > 0)) {
			sql.append(" AND zdo = ?");
		}
		if((dong != null) && (dong.length() > 0)) {
			sql.append(" AND dong LIKE '%'||?||'%'");
		}
		int i=1;
		try {
			con 	= dbMgr.getConnection();
			pstmt 	= con.prepareStatement(sql.toString());
			if((zdo != null) && (zdo.length() > 0)) {
				pstmt.setString(i++, zdo);
			}
			if((dong != null) && (dong.length() > 0)) {
				pstmt.setString(i, dong);
			}
			rs 		= pstmt.executeQuery();
			Vector<ZipcodeVO> v = new Vector<>();
			ZipcodeVO[] 	zVOs	= null;
			ZipcodeVO 		zVO 	= null;
			while(rs.next()) {
				zVO = new ZipcodeVO();
				zVO.setZipcode(rs.getInt("zipcode"));
				zVO.setAddress(rs.getString("address"));
				v.add(zVO);
			}
			zVOs = new ZipcodeVO[v.size()];
			v.copyInto(zVOs);
		} catch (Exception e) {
			
		} finally {
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == jtf_search) {
			jtf_search.setText("");
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if (obj == jcb_zdo2) {
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if (e.getClickCount() == 2) {
			System.out.println("더블 클릭 한거야");
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}