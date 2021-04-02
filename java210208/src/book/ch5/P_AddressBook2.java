package book.ch5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;
import com.vo.DeptVO;

import network.step1.TimeClient;
import network.step1.TimeServer;

public class P_AddressBook2 implements ActionListener{ //JFrame을 상속하지 않고 이렇게 이용하면 <창이 두개가 뜸!!?!>
	//선언부
	JFrame 					jf 		 = null;				//JFrame 타입의 참조변수 선언
	JMenuBar 				jbm 	 = new JMenuBar();
	JMenu					jm_file	 = new JMenu("FILE");
	JMenu					jm_orcl	 = new JMenu("DB");
	
	//현재시각 출력하는 라벨////////////////////////////////////////////////////////////////////////////////
//	Thread 					th		 = new TimeClient(jlb_time);	//(1) jlb_time의 선언이 먼저 와야한다!!!! 순서가 중요하군.
	JLabel					jlb_time = null;
//	Thread 					th		 = new TimeClient(jlb_time);	//(2) initDisplay로 넣자
//	th.start();
	
	JMenu					jm_exit  = new JMenu("EXIT");
	JMenuItem				jmi_naga = new JMenuItem("나가기");
			
	JMenuItem				jmi_sel  = new JMenuItem("상세조회");	//상세조회는 한 건 조회, 전체조회랑은 다름
	JMenuItem				jmi_seA  = new JMenuItem("전체조회");	//상세조회는 한 건 조회, 전체조회랑은 다름
	JMenuItem				jmi_ins	 = new JMenuItem("입력");
	JMenuItem				jmi_upd	 = new JMenuItem("수정");
	JMenuItem				jmi_del	 = new JMenuItem("삭제");
	JMenuItem				jmi_dbT  = new JMenuItem("오라클 연결");
	C_AddressBook_Dialog2 	aDia 	 = null;		//////C_Dia(자식 창) 호출
	//AddressBook 관련 VO를 만들어도 좋지만, DB연동이 목적이 아니므로 일단 dVO로 진행하려 한다.
	DeptVO					dVO		 = null;
	static P_AddressBook2 	aBook 	 = null;	//일단 오늘은 싱글톤X, 그래서 private 뺐음.
	String					cols[]	 = {"부서번호","부서명","지역"};
	String					data[][] = new String[0][3];
	//데이터셋을 담을 수 있는 클래스 생성하기
	//첫번째 param: data에 대한 정보, 두번째 param: 컬럼에 대한 정보
	DefaultTableModel 		dtm_dept = new DefaultTableModel(data,cols);
	JTable					jtb_dept = new JTable(dtm_dept);
	JScrollPane				jsp_dept = new JScrollPane(jtb_dept);
	//생성자
	public P_AddressBook2() {
//		initDisplay();
	}
	//주소 목록 조회 - 새로고침 처리
	public void refresh() {
		System.out.println("refresh 호출 성공!");
////////////////////////////////////////[[전체 조회하기 소스 추가]]////////////////////////////////////
	    DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
////////////////////////////[조회결과 처리]////////////////////////////////
//		DeptVO[] dVOS = null;
	    String sql = "SELECT * FROM dept";
	    try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
//			DeptVO dVO = null;
			Vector<DeptVO> vecDVO = new Vector<DeptVO>();
		
			while(rs.next()) {
				dVO = new DeptVO();
				dVO.setDeptno(rs.getInt("DEPTNO"));
				dVO.setDname(rs.getString("DNAME"));
				dVO.setLoc(rs.getString("LOC"));
				vecDVO.add(dVO);
			}
//			dVOS = new DeptVO[vecDVO.size()];
//			vecDVO.copyInto(dVOS);
			while(dtm_dept.getRowCount() > 0) {
				dtm_dept.removeRow(0);
			}//////////////////////////이미 출력된 레코드 클리어

			for (int i = 0; i < vecDVO.size(); i++) {
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(vecDVO.get(i).getDeptno());
				oneRow.add(vecDVO.get(i).getDname());
				oneRow.add(vecDVO.get(i).getLoc());
				dtm_dept.addRow(oneRow);

//			for (int i = 0; i < dVOS.length; i++) {
//				Vector oneRow = new Vector();
//				oneRow.add(dVOS[i].getDeptno());
//				oneRow.add(dVOS[i].getDname());
//				oneRow.add(dVOS[i].getLoc());
//				dtm_dept.addRow(oneRow);
				
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			}
	    } 
	    catch (SQLException se) {
	    	System.out.println(se.getMessage());
	    }
	}
	
	
////////////////////////////////////////[[전체 조회하기 소스 추가]]////////////////////////////////////
	//화면처리부
	public void initDisplay() {
		jf = new JFrame();
		
		
		
		//이벤트 소스와 이벤트 핸들러 연결하기
		jmi_sel.addActionListener(this);
		jmi_seA.addActionListener(this);
		jmi_ins.addActionListener(this);
		jmi_upd.addActionListener(this);
		jmi_del.addActionListener(this);
		jmi_dbT.addActionListener(this);
		jmi_naga.addActionListener(this);
		jm_file.add(jmi_sel);
		jm_file.add(jmi_seA);
		jm_file.add(jmi_ins);
		jm_file.add(jmi_upd);
		jm_file.add(jmi_del);
		jm_orcl.add(jmi_dbT);
		jm_exit.add(jmi_naga);
		jbm.add(jm_file);
		jbm.add(jm_orcl);
		jbm.add(jm_exit);
		jf.setJMenuBar(jbm);
		jf.setTitle("주소록 ver1.0");
		jf.add("Center", jsp_dept);
		Thread th = new TimeClient(jlb_time);	//이른 인스턴스화
		jlb_time.setHorizontalAlignment(JLabel.CENTER);
		th.start();		//run메소드를 호출해줌
		jf.add("South", jlb_time);
		
		
		jf.setSize(500, 400);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
//		P_AddressBook2 aBook = new P_AddressBook2();	//이건 한계가 있다.
		aBook = new P_AddressBook2();
		aBook.initDisplay();
	}/////////////////////////////////////////////////////////////////////end of 메인메소드
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		aDia = C_AddressBook_Dialog2.getInstanceDial();
		Object obj = ae.getSource();
		/////////////////////////////////////////////////////////////////////////////////////////////////////[입력] 이벤트 처리
		if(obj == jmi_ins) {
			/* @param1은 AddressDialog에 출력되는 제목(창 제목)을 바꾸기 위해 필요
			 * @param2는 AddressBook에서 조회된 결과를 AddressDialog에서 재사용해야 하는 경우에 필요
			 * @param3은 AddressDialog에서 입력이 성공하거나 수정에 성공했을 때 부모창을 새로고침 해주어야 한다고 담당자가 요구했기 때문에 필요
			 * @param4는 AddressDialog 화면에서 사용자에게 입력받는 JTextField들에 대한 상태값을 반영해야 할 것이다.
			 */
			aDia.set("입력", null, aBook, true);	//입력일 때에는 오라클을 경유 안하기 때문에 null 넣었음
			aDia.setVisible(true);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////[상세조회] 이벤트 처리
		else if(obj == jmi_sel) {
			//"한 건을 먼저 선택하세요. - deptno"
			int index[] = jtb_dept.getSelectedRows();	//SelectedRows는 행의 index를 불러옴 (다중삭제 하기 위해 배열에 담음)
			//테이블의 데이터를 선택하지 않은 경우
			if(index.length==0) {
				JOptionPane.showMessageDialog(jf, "조회할 데이터를 선택하세요", "Error", JOptionPane.ERROR_MESSAGE);	//마지막에 있는 변수는 아이콘 모양을 결정
				return;
			}
			/*
			내가 쓴 코드
			if(index.length == 0 || index == null) {
				JOptionPane.showMessageDialog(jf, "데이터를 선택하지 않았습니다.");
				return;
			}
			*/
			//선택된 로우가 한 개가 넘는 경우(두 개 이상)
			else if(index.length > 1) {
				JOptionPane.showMessageDialog(jf, "데이터는 한 건만 선택하세요", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//그 나머지
			else {
				Integer deptno = Integer.parseInt(dtm_dept.getValueAt(index[0], 0).toString());
				
				DBConnectionMgr 	dbMgr	= DBConnectionMgr.getInstance();
				Connection			con		= null;
				PreparedStatement 	pstmt	= null;
				ResultSet			rs		= null;
				DeptVO				dVO		= null;
	/////////////////////////////////////////[[   조회결과를 처리     ]]/////////////////////////////////////
				String sql 	  = "SELECT deptno, dname, loc FROM dept";
					   sql	 += " WHERE deptno=?";
				try {
					con 	  = dbMgr.getConnection();
					pstmt 	  = con.prepareStatement(sql);
					pstmt.setInt(1, deptno);
					rs 		  = pstmt.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(jf, "조회결과가 있습니다.");
						dVO = new DeptVO();
						dVO.setDeptno(rs.getInt("deptno"));
						dVO.setDname(rs.getString("dname"));
						dVO.setLoc(rs.getString("loc"));
					}
					else {
						dVO = new DeptVO();		//이렇게 하면 테스트 과정에서 NullPointerException 만큼은 피할 수 있다.
					}
					//오라클 서버와 연동하여 사용자가 선택한 한 개의 로우(row)만 가져온다.
					aDia.set("상세조회", dVO, aBook, false);
					aDia.setTitle("상세조회");
					aDia.setVisible(true);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(jf, "Exception: "+e.toString());
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////[수정] 이벤트 처리
		else if(obj == jmi_upd) {
			//"한 건을 먼저 선택하세요. - deptno"
			int index[] = jtb_dept.getSelectedRows();	//SelectedRows는 행의 index를 불러옴 (다중삭제 하기 위해 배열에 담음)
			//테이블의 데이터를 선택하지 않은 경우
			if(index.length==0) {
				JOptionPane.showMessageDialog(jf, "수정할 데이터를 선택하세요", "Error", JOptionPane.ERROR_MESSAGE);	//마지막에 있는 변수는 아이콘 모양을 결정
				return;
			}
			//선택된 로우가 한 개가 넘는 경우(두 개 이상)
			else if(index.length > 1) {
				JOptionPane.showMessageDialog(jf, "수정은 한 번에 한 건에 대해서만 가능합니다.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//그 나머지([수정]이 이루어지는 조건)
			else {
				Integer deptno = Integer.parseInt(dtm_dept.getValueAt(index[0], 0).toString());	//getValueAt의 리턴이 Object라서 int가 아닌 Integer로 받았음
				DBConnectionMgr 	dbMgr	= DBConnectionMgr.getInstance();
				Connection			con		= null;
				PreparedStatement 	pstmt	= null;
				ResultSet			rs		= null;
				DeptVO				dVO		= null;
///////////////////////////////[[   수정결과를 처리     ]]////////////////////////////////////
				String sql 	  = "SELECT deptno, dname, loc FROM dept";
					   sql	 += " WHERE deptno=?";
				try {
					con 	  = dbMgr.getConnection();
					pstmt 	  = con.prepareStatement(sql);
					pstmt.setInt(1, deptno);
					rs 		  = pstmt.executeQuery();
					if(rs.next()) {
						dVO = new DeptVO();
						dVO.setDeptno(rs.getInt("deptno"));
						dVO.setDname(rs.getString("dname"));
						dVO.setLoc(rs.getString("loc"));
					}
					else {
						dVO = new DeptVO();
					}
					//오라클 서버와 연동하여 사용자가 선택한 한 개의 로우(row)만 가져온다.
					aDia.set("수정", dVO, aBook, true);
					aDia.setTitle("수정");
					aDia.setVisible(true);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(jf, "Exception: "+e.toString());
				}
			}
//			aDia.set("수정", dVO, aBook, true);
//			aDia.setTitle("수정");
//			aDia.setVisible(true);
		}
//////////////////////////////////<<[수정]에 대한 처리 끝>>///////////////////////////////////
		
//////////////////////////////////<<[삭제]에 대한 처리 시작>>///////////////////////////////////
		else if(obj == jmi_del) {			//삭제는(삭제 처리는) 화면 목록에서 직접 처리하도록 하자~
			int index[] = jtb_dept.getSelectedRows();	//사용자의 선택 순서와는 무관하게, 정렬해서 배열에 담는다.
//			Vector<Object> deptno = new Vector<Object>();
//			deptno.add(index.get);
//			deptno.add(vecDVO.get(i).getDname());
//			deptno.add(vecDVO.get(i).getLoc());
//			for(int i=0; i<index.length; i++) {
//				deptno[i] = Integer.parseInt(dtm_dept.getValueAt(index[i], 0).toString());
//			}
//			System.out.printf("%3d %3d %3d %n", index[0], index[1], index[2]);
			if(index.length==0) {
				JOptionPane.showMessageDialog(jf, "삭제할 데이터를 선택하세요", "Error", JOptionPane.ERROR_MESSAGE);	//마지막에 있는 변수는 아이콘 모양을 결정
				return;
			}
			else {
				int reSult = this.yesORno();
				if(reSult==0) {
		            Vector<Integer> deptVec = new Vector<Integer>();
		
		            for (int i = 0; i < index.length; i++) {
		                Integer deptno = Integer.parseInt(dtm_dept.getValueAt(index[i], 0).toString());
		                deptVec.add(deptno);
		            }
		            DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		            Connection con = null;
		            PreparedStatement d_pstmt = null;
					StringBuilder sql_del = new StringBuilder();
		            sql_del.append("DELETE FROM dept WHERE deptno IN (");
		            for (int row = 0; row < deptVec.size(); row++) {
		                sql_del.append(deptVec.get(row));
		                if (row < deptVec.size() - 1) sql_del.append(", "); //마지막 콤마는 찍지 않기 위해서 if
		            }
		            sql_del.append(")");
		
		            try {
		                con = dbMgr.getConnection();
		                d_pstmt = con.prepareStatement(sql_del.toString());
		                int dresult = d_pstmt.executeUpdate();
		                sql_del.setLength(0);								//계속 sql문이 append되지 않도록 길이를 0으로 초기화
		
		                if(dresult == 1) {
		                    JOptionPane.showMessageDialog(aBook.jf, "삭제되었습니다");
		                }
		                dbMgr.freeConnection(con, d_pstmt);
		
		            } catch (SQLException se){
		                System.out.println(se.getMessage());
		            }
				}
				this.refresh();	//즉시 새로고침을 통해 결과를 바로 확인할 수 있도록 함.
			}
//			else {
//				StringBuilder sql_del = new StringBuilder();
//				sql_del.append("DELETE FROM DEPT WHERE deptno IN(");
//				for(int row=0; row<index.length; row++) {
//					sql_del.append(index[row]);
//					sql_del.append(",");
//				}
//				sql_del.deleteCharAt(-1);
//				sql_del.append(")");
//				
//			}
//			aDia.setTitle("삭제");
//			aDia.setVisible(true);
		}
//////////////////////////////////<<[삭제]에 대한 처리 끝>>///////////////////////////////////

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////[DB 연동] 이벤트 처리
		else if(obj == jmi_dbT) {			//오라클 연동
			DBConnectionMgr dbMgr	= DBConnectionMgr.getInstance();
			Connection		con		= dbMgr.getConnection();
			if(con != null) {
				JOptionPane.showMessageDialog(jf, "오라클 서버 연결에 [성공]하였습니다.");
				try {						  																			//■■■■■■■ 내가 추가한 코드
					con.close();			  																			//■■■■■■■ 내가 추가한 코드
				} catch (SQLException e) {    																			//■■■■■■■ 내가 추가한 코드
					e.printStackTrace();      																			//■■■■■■■ 내가 추가한 코드
				}                             																			//■■■■■■■ 내가 추가한 코드
				return;
			}else {
				JOptionPane.showMessageDialog(jf, "--오라클 서버 연결 실패--");
				return;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////[전체조회] 이벤트 처리
		else if(obj == jmi_seA) {			//전체조회
			DBConnectionMgr 	dbMgr	= DBConnectionMgr.getInstance();
			Connection			con		= null;
			PreparedStatement 	pstmt	= null;
			ResultSet			rs		= null;
/////////////////////////////////////////[[   조회결과를 처리     ]]/////////////////////////////////////
			String sql 	  = "SELECT deptno, dname, loc FROM dept";
			dbMgr	      = DBConnectionMgr.getInstance();
			DeptVO dVOS[] = null;
			try {
				System.out.println("con before");
				con 	  = dbMgr.getConnection();
				System.out.println("con after");
				System.out.println("pstmt before");
				pstmt 	  = con.prepareStatement(sql);
				System.out.println("pstmt after");
				System.out.println("rs before");
				rs 		  = pstmt.executeQuery();
				System.out.println("rs after");
				dVO		  = null;
//				ArrayList<DeptVO>	al = new ArrayList<DeptVO>();		//Generic(제네릭)
				Vector<DeptVO>		al = new Vector<DeptVO>();			//ArrayList는 싱글 스레드에서 안전하고 Vector는 멀티 스레드에서 안전하다.	//Vector클래스, ArrayList클래스...
				while(rs.next()) {
					dVO = new DeptVO();
					dVO.setDeptno(rs.getInt("deptno"));
					dVO.setDname(rs.getString("dname"));
					dVO.setLoc(rs.getString("loc"));
					al.add(dVO);
					System.out.printf("%3d %10s %6s %n", dVO.getDeptno(), dVO.getDname(), dVO.getLoc());
				}
				dVOS = new DeptVO[al.size()];
				al.copyInto(dVOS);
				//앞에 조회된 결과가 (전체조회를 누를 때마다) 쌓이지 않도록 해보자.
				while(dtm_dept.getRowCount()>0) {
					dtm_dept.removeRow(0);
				}
				for(int i=0; i<dVOS.length; i++) {
					Vector oneRow = new Vector();
					oneRow.add(dVOS[i].getDeptno());
					oneRow.add(dVOS[i].getDname());
					oneRow.add(dVOS[i].getLoc());
					dtm_dept.addRow(oneRow);	//dtm: 데이터셋을 통째로 클래스 타입으로 만들어줌
				}
			} catch (SQLException se) {
				System.out.println("SQLException: "+se.getMessage());
			}
			System.out.println("끝.");
/////////////////////////////////////////[[   조회결과를 처리     ]]/////////////////////////////////////
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////[종료] 이벤트 처리
		else if(obj == jmi_naga) {
			System.exit(0);
		}
	}

	public int yesORno(){
		JFrame		jf			= new JFrame();
		int			reSult		= 0;
		Object[]	options 	= {"네", "아니오"};
		reSult = JOptionPane.showOptionDialog(jf
				, "정말로 삭제하시겠습니까?"
				, "삭제 확인창"
				, JOptionPane.YES_NO_OPTION
				, JOptionPane.QUESTION_MESSAGE
				, null
				, options
				, options[0]);
		System.out.println(reSult);	//네: 0을 반환, 아니오: 1을 반환
		jf.setVisible(false);
		return reSult;
	}
	public JLabel getJlb_time() {
		return jlb_time;
	}
	public void setJlb_time(JLabel jlb_time) {
		this.jlb_time = jlb_time;
	}
}





















