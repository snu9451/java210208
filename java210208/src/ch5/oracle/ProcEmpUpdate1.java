package ch5.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class ProcEmpUpdate1 extends JFrame{
	Connection 		  con 		= null;
	CallableStatement cstmt 	= null;	//프로시저를 사용할 것이기 때문에 Prepared가 아닌 Callable
	DBConnectionMgr   dbMgr	    = null;
	/******************************************************************
	 * proc_emp_update1 프로시저 호출 테스트
	 * @param p_empno - 사원 번호를 입력받아서 단일 레코드를 처리하는 실습임.
	 * @result 프로시저 처리 결과를 int 값으로 확인한다.
	 ******************************************************************/
	public void salUpdate(int p_empno) {
		int result 	= 0;
		dbMgr 		= DBConnectionMgr.getInstance();
		try {
			con		= dbMgr.getConnection();
			//con1.setAutoCommit(false);	//SELECT는 commit의 대상이 아님--의미없는 문장ㅎㅎ
			cstmt	= con.prepareCall("{call proc_emp_update1(?,?)}");
			cstmt.setInt(1, p_empno);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			result 		= cstmt.executeUpdate();
			String msg 	= cstmt.getString(2);
			System.out.println("result: "+result);
			if(result == 1) {
				JOptionPane.showMessageDialog(this, "수정이 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "실패하였습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	/******************************************************************
	 * proc_emp_update2 프로시저 호출 테스트
	 * @param p_deptno - 부서 번호를 입력받아서 멀티 레코드를 처리하는 실습임.
	 * @result 프로시저 처리 결과를 int 값으로 확인한다.
	 ******************************************************************/
	public void salUpdate2(int p_deptno) {
		int result 	= 0;
		dbMgr 		= DBConnectionMgr.getInstance();
		try {
			con			= dbMgr.getConnection();
			//con1.setAutoCommit(false);	//SELECT는 commit의 대상이 아님--의미없는 문장ㅎㅎ
			cstmt		= con.prepareCall("{call proc_emp_update2(?)}");
			cstmt.setInt(1, p_deptno);
			result 		= cstmt.executeUpdate();
			System.out.println("result: "+result);
			if(result >= 1) {
				JOptionPane.showMessageDialog(this, "수정이 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "실패하였습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		ProcEmpUpdate1 neu1 		= new ProcEmpUpdate1();
		String 		   user_input 	= JOptionPane.showInputDialog("사원번호를 입력하세요.");
		int 		   empno    	= 0;
		if(user_input!=null || user_input.length()>1) {
			empno = Integer.parseInt(user_input);
		}
		neu1.salUpdate(empno);
		String 		   user_input2 	= JOptionPane.showInputDialog("부서번호를 입력하세요.");
		int 		   deptno    	= 0;
		if(user_input!=null || user_input2.length()>1) {
			deptno = Integer.parseInt(user_input2);
		}
	}

}
