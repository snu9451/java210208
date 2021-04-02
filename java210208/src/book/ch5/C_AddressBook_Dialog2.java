package book.ch5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.util.DBConnectionMgr;
import com.vo.DeptVO;

import oracle.jdbc.proxy.annotation.Pre;

public class C_AddressBook_Dialog2 extends JDialog implements ActionListener {
	static DBConnectionMgr 		dbMgr 	= null;
	Connection			con		= null;
	PreparedStatement 	i_pstmt	= null;
	PreparedStatement	u_pstmt = null;
	
	/* INSERT INTO dept(deptno, dname, loc)
	 * VALUES(53, '개발팀', '포항')
	 */
	StringBuffer	sql_ins2	= new StringBuffer();	//멀티스레드에 안전. 속도 느림.
	StringBuilder	sql_ins		= new StringBuilder();	//싱글스레드에 안전. 속도 빠름.
	/* UPDATE dept
	 *    SET loc = '인천'
	 *  WHERE deptno = 51
	 */
	StringBuilder 	sql_upd 	= new StringBuilder();
	JScrollPane jsp 			= null;			//기다려. (jp_center 속지가)아직 결정이 안 되었기 때문에 나중에 초기화 할 거다.
	JPanel		jp_south		= new JPanel();
	JPanel		jp_center		= new JPanel();
	//화면을 처리할 때, 해당 옵션을 두 가지로 설정할 수 있다. - getter/setter형식으로 코딩해보자.
	JLabel		jlb_deptno		= new JLabel("부서번호");
	JTextField	jtf_deptno		= new JTextField();
	JLabel		jlb_dname		= new JLabel("부서이름");
	JTextField	jtf_dname		= new JTextField();
	JLabel		jlb_loc			= new JLabel("지역");
	JTextField	jtf_loc			= new JTextField();
	JButton 	jbtn_account	= new JButton("처리");
	JButton 	jbtn_close		= new JButton("닫기");
	DeptVO 		dVO				= null;
	static P_AddressBook2 aBook	= null;						//게으른 인스턴스화 했음; NullPointerException 위험
	private	static C_AddressBook_Dialog2 instance_dial	= new C_AddressBook_Dialog2();
	public static C_AddressBook_Dialog2 getInstanceDial() {
		if(instance_dial == null) {
			instance_dial = new C_AddressBook_Dialog2();
		}
		return instance_dial;
	}
	private C_AddressBook_Dialog2() {
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();
			//자동 커밋 모드를 켜두거나 꺼둘 수 있다. - default: true(즉, autocommit on)
			//con.setAutoCommit(false);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		initDisplay();
	}
	//화면처리부
	public void initDisplay() {
		
		jbtn_account.addActionListener(this);
		jbtn_close.addActionListener(this);
		
		jp_south.add(jbtn_account);
		jp_south.add(jbtn_close);
		
		jp_center.setLayout(null);					//JPanel이 default로 갖고 있는 FlowLayout을 뭉갠다(안쓰겠다, null로 만들겠다).
		
		jlb_deptno.setBounds(20, 20, 100, 20);		//(x,y)를 왼쪽 윗 꼭짓점(시작점)으로 사각형을 그린다.
		jtf_deptno.setBounds(130, 20, 150, 20);
		jp_center.add(jlb_deptno);
		jp_center.add(jtf_deptno);
		jlb_dname.setBounds(20, 45, 100, 20);
		jtf_dname.setBounds(130, 45, 150, 20);
		jp_center.add(jlb_dname);
		jp_center.add(jtf_dname);
		jlb_loc.setBounds(20, 70, 100, 20);
		jtf_loc.setBounds(130, 70, 150, 20);
		jp_center.add(jlb_loc);
		jp_center.add(jtf_loc);
		jsp = new JScrollPane(jp_center);
		
		this.add("Center", jsp);
		this.add("South", jp_south);
		this.add("Center", jp_center);
		this.setTitle("입력");						//전변으로 빼야하는 <근거>를 갖고 빼라.
		this.setSize(400, 500);
		this.setVisible(false);
	}
	/*****************************************************************************
	 * @param string 사용자가 선택한 요청에 대한 '제목'을 지정함.
	 * @param aBook 부모창에서 화면처리에 대한 '원본 주소번지'가 필요함.
	 * [입력]일 때는 새 창을 열어서 입력 받아야 한다. - writable
	 * 그러나 [수정]일 때는 오라클을 경유한 후 그 결과를 화면에 출력해 놓고, <수정할 정보만> 새로 입력을 받아야 한다.
	 * [(상세)조회]일 때도 오라클을 경유하여 한 건만 출력해야 함. - readonly
	 * [수정]과 [상세조회]를 하나로 묶고, [입력]을 다른 하나로 묶어서 처리하자.
	 * 오라클을 경유했을 때 VO를 유지해야 한다.
	 * 그 값을 가진 주소번지를 부모창으로부터 받아서 자식창의 이벤트 처리 메소드인 actionPerformed()에서 재사용해야 하므로,
	 * (dVO를)전역변수로 선언한 뒤 지변(파라미터로 선언되어 있는 DeptVO dVO)을 이용해서 초기화하여 사용하기로 하자.
	 *****************************************************************************/
	public void set(String title, DeptVO dVO, P_AddressBook2 aBook, boolean isFlag) {
		this.setTitle(title);		//전변으로 굳이 안 빼도 괜찮아.
		this.dVO	= dVO;			//전변으로 빼야함
		C_AddressBook_Dialog2.aBook 	= aBook;		//전변으로 빼야함			//파라미터를 이용한 인스턴스 초기화(?)
		//입력모드|수정모드|상세조회모드
		this.setEnabled(isFlag);	//입력받는 JTextField 클래스에 대한 상태값을 조정하기
									//메소드 중심의 코딩으로 일괄처리 해본다.
		this.setValue(this.dVO);	//이 경우, 클래스 전체에서 공유되는 값이 되는 것이다.
	}
	private void setValue(DeptVO dVO) {
		//[입력]을 위한 Dialog(다이얼로그)창 설정 - 모든 값을 null로 셋팅한다.
		//aDia.set("입력", null. aBook, true);
		if(dVO == null) {		//[입력]일땐 dVO가 null임. 그래서 JTextField에 아무것도 입력 안 된 상태가 됨.
			setJtf_deptno("");
			setJtf_dname("");
			setJtf_loc("");		
		}
		//[상세조회] 또는 [수정] 시, 오라클에서 조회된 값으로 초기화 해야 한다.
		else {
			setJtf_deptno(String.valueOf(dVO.getDeptno()));
			setJtf_dname(dVO.getDname());
			setJtf_loc(dVO.getLoc());
		}
	}
	//set메소드를 통해서 넘어온 4번째 값(T/F)에 따라서, 화면을 처리하는 콤퍼넌트 클래스의 수정 모드에 관한 설정을 바꾸어 주려한다.
	//T(rue)이면, setEnabled(true)로 설정하고, F(alse)이면 setEnabled(false)로 설정하여 수정할 수 없도록 하자.
	public void setEnabled(boolean isFlag) {
		jtf_deptno.setEditable(isFlag);		//"부서번호 텍스트입력창(TextField) 활성화 할꺼니?"
		jtf_dname.setEditable(isFlag);
		jtf_loc.setEditable(isFlag);
	}
	//getter/setter 넣어보자
	/////////////////////////////////[[  화면 처리에 대한 getter와 setter 구현 시작   ]]////////////////////////////////
	public String getJtf_deptno() { return jtf_deptno.getText(); }
	public void setJtf_deptno(String deptno) {//**************************************String deptno?
		jtf_deptno.setText(deptno);
	}
	public String getJtf_dname() { return jtf_dname.getText(); }
	public void setJtf_dname(String dname) {
		jtf_dname.setText(dname);
	}
	public String getJtf_loc() { return jtf_loc.getText(); }
	public void setJtf_loc(String loc) {
		jtf_loc.setText(loc);
	}
	//////////////////////////////////[[  화면 처리에 대한 getter와 setter 구현 끝   ]]/////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		//////////////////////////////////////////////////////////////////////////
		//[처리]버튼을 눌렀을 때,
		//입력을 처리, 수정을 처리하도록 하자.
		//목적지가 다시 부모창의 목록페이지로 바뀌어, 그쪽에서 새로고침이 일어남
		//부모창의 주소번지(주번)가 있어야 새로고침을 처리하는 메소드를 호출할 수가 있음
		String command = e.getActionCommand();
		if("처리".equals(command)) {
			//입력 혹은 수정처리 시
			//[입력]일 때
			if(dVO == null) {					//if문과 else문에 코드가 반복되고 있음 > 나중에는 이 부분도 개선되어야 할 과제임.
				DeptVO pdVO = new DeptVO();
				pdVO.setDeptno(Integer.parseInt(getJtf_deptno()));	//문제점: NumberFormatException이 일어날 수 있다.("안녕"을 2로 바꿀 순 없음!)
				pdVO.setDname(getJtf_dname());
				pdVO.setLoc(getJtf_loc());
				sql_ins.append("INSERT INTO dept(deptno, dname, loc)" );
				sql_ins.append(" VALUES(?, ?, ?)"                     );
				try {
					i_pstmt = con.prepareStatement(sql_ins.toString());
					int i = 0;
					i_pstmt.setInt(++i, pdVO.getDeptno());
					i_pstmt.setString(++i, pdVO.getDname());
					i_pstmt.setString(++i, pdVO.getLoc());
					int i_result = i_pstmt.executeUpdate();
					System.out.println(i_result);
					if(i_result == 1) {
						JOptionPane.showMessageDialog(aBook.jf, "입력되었습니다.");
					}
					//사용한 자원 반납하기
//					dbMgr.freeConnection(con, i_pstmt);
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(aBook.jf, "Exception"+e2.toString());
					e2.printStackTrace();
				}
//				sql_upd.setLength(0);
				sql_ins = new StringBuilder();
			}
			//[수정]일 때
			else {
				DeptVO pdVO = new DeptVO();
				pdVO.setDeptno(Integer.parseInt(getJtf_deptno()));	//문제점: NumberFormatException이 일어날 수 있다.("안녕"을 2로 바꿀 순 없음!)
				pdVO.setDname(getJtf_dname());
				pdVO.setLoc(getJtf_loc());
				sql_upd.append("UPDATE dept"       );
				sql_upd.append("   SET loc = ?"    );
				sql_upd.append(" WHERE deptno = ?" );
				try {
					u_pstmt = con.prepareStatement(sql_upd.toString());
					int i = 0;
					u_pstmt.setString(++i, pdVO.getLoc());
					u_pstmt.setInt(++i, pdVO.getDeptno());
					int u_result = u_pstmt.executeUpdate();
					if(u_result == 1) {
						JOptionPane.showMessageDialog(aBook.jf, "수정되었습니다.");
					}
					//사용한 자원 반납하기
					dbMgr.freeConnection(con, u_pstmt);
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(aBook.jf, "Exception"+e2.toString());
				}
			}
			aBook.refresh();
			setVisible(false);	//Dialog를 보여줄지 말지 결정
			aBook.aDia = null;
		}//////////////////////////////////////end of 처리
		else if("닫기".equals(command)) {
			//닫기 버튼을 누르면 JVM과의 연결고리를 끊어서 강제로 종료시킨다.
			this.dispose();
//			System.exit(0);		//이거 하면 Run이 아예 종료됨. 그래서 dispose로!
		}
//		aBook.refresh();	//여기서 aBook을 사용하기 위해 선언부에서 static P_AddressBook2 aBook=null;을 하였다.
		//////////////////////////////////////////////////////////////////////////
		//[닫기]버튼을 눌렀을 때,
	}
	
/**/
//	public static void main(String[] args) {
//		C_AddressBook_Dialog2 ad2 = new C_AddressBook_Dialog2();	//인스턴스화 해서 써야 함
//		ad2.set("상세", ad2.dVO, aBook, false);
//		ad2.initDisplay();		//아래 ad2.setVisible(true); 코드가 ad2.initDisplay();보다 위에 있으면 창이 바로 사라짐(하지만 메모리엔 올라간다)
//								//왜냐, initDisplay에서 false가 됨
//		ad2.setVisible(true);	//static 안에서는 this 사용불가(불법, 문법에러)
//		ad2.setTitle("수정");
	}

	
