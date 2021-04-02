package ch5.oracle;
//java는 auto commit이 기본적으로 true이다. 즉, 자바로 실행하면 자동으로 커밋까지 된다는 뜻. 그게 싫으면 별도로 명령어를 걸어줘야 한다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class NoProcEmpUpdate1 extends JFrame{
	Connection con1 		 = null;
	Connection con2 		 = null;
	Connection con3 		 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	ResultSet rs1 			 = null;
	ResultSet rs2 			 = null;
	String sql1				 = "";	//null로 해두면 +(이어쓰기) 연산이 안 먹혀서 안 됨
	String sql2				 = "";
	String sql3				 = "";
	DBConnectionMgr dbMgr	 = null;
	public void salUpdate(int p_empno) {
		String 	r_ename	  	 = null;			//SELECT ename을 받을 예정
		double 	r_sal	  	 = 0.0;			//SELECT sal을 받을 예정
		double 	r_avg_sal 	 = 0.0;
		double	v_rate		 = 0.0;
		int 	result	  	 = 0;	//0이면 수정 실패, 1이면 수정 성공
		sql1 += "SELECT ename, sal";	//문자열 연결: +연산자	//컬럼과 FROM예약어?는 띄어써야 한다?상관없나?
		sql1 += " FROM emp";
		sql1 += " WHERE empno=?";		//물음표로 치환하는 게 권장사항
//		sql1 += " WHERE empno="+p_empno;
		sql2 += "SELECT avg(sal) sal FROM emp";	//SELECT avg(sal) as sal FROM emp로 써서 sal을 allias명으로 지정 가능한데, as가 생략될 수 있다.
		sql2 += " WHERE deptno = (SELECT deptno FROM emp WHERE empno=?)";
//		sql2 += " WHERE deptno = (SELECT deptno FROM emp WHERE empno="+p_empno+")";
		sql3 += "UPDATE emp SET sal=? WHERE empno=?";
//		sql3 += "UPDATE emp SET sal=? WHERE empno="+p_empno;
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con1	= dbMgr.getConnection();
			//con1.setAutoCommit(false);	//SELECT는 commit의 대상이 아님--의미없는 문장ㅎㅎ
			pstmt1	= con1.prepareStatement(sql1);
			pstmt1.setInt(1, p_empno);		//물음표를 변수로 인식하도록 약속되어 있음(숫자 1을 변수의 index)
			rs1		= pstmt1.executeQuery();
			rs1.next();	//MN: 사원번호는 고유하기 때문에 한 개의 row만 toad창에 나올 것이므로 커서는 한번만 움직인다.
			r_ename	= rs1.getString("ename");
			r_sal	= rs1.getDouble("sal");
			System.out.println("입력받은 사원의 "+r_ename+", 급여 "+r_sal);
			dbMgr.freeConnection(con1, pstmt1, rs1);
			
			con2	= dbMgr.getConnection();
			//con2.setAutoCommit(false);	//SELECT는 commit의 대상이 아님--의미없는 문장ㅎㅎ
			pstmt2	= con2.prepareStatement(sql2);
			pstmt2.setInt(1, p_empno);
			rs2		= pstmt2.executeQuery();
			rs2.next();
			r_avg_sal = rs2.getDouble("sal");
			System.out.println("부서평균 급여는 "+r_avg_sal+"입니다.");
			
			if(r_sal>r_avg_sal) v_rate = 1.1;
			else v_rate = 1.2;

			con3	= dbMgr.getConnection();
			con3.setAutoCommit(false);
			pstmt3	= con3.prepareStatement(sql3);
			pstmt3.setDouble(1, r_sal*v_rate);
			pstmt3.setInt(2, p_empno);
			result = pstmt3.executeUpdate();
			System.out.printf("%s사원의 인상된 급여는 %5.2f입니다. %n", r_ename, r_sal*v_rate);
			System.out.println("result: "+result+" (1이면 성공)");
			if(result ==1) {
				JOptionPane.showMessageDialog(this, "수정이 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "실패하였습니다.");
			}
		} catch (SQLException se) {
			System.out.println("[[sql1]] "+sql1);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		NoProcEmpUpdate1 neu1 		= new NoProcEmpUpdate1();
		String 			 user_input = JOptionPane.showInputDialog("사원번호를 입력하세요.");
		int 			 empno    	= 0;
		if(user_input!=null || user_input.length()>1) {
			empno = Integer.parseInt(user_input);
		}
		neu1.salUpdate(empno);
	}

}
