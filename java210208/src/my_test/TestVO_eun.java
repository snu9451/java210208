package my_test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vo.DeptVO;
import com.vo.EmpVO;

public class TestVO_eun {

	public void testConnect(DeptVO dvo, EmpVO evo
						  , EmpVO[] evoArray, DeptVO[] dvoArray) {
		Connection	conn = null;	//자바-오라클 연결을 위한 선언
		Statement	stat = null;	//쿼리문 대기 및 설정 --쿼리문..?
		ResultSet	rset = null;	//결과값 받아오기
		
		try {
			String dbURL		= "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";	//127.0.0.1: 루프백, 로컬호스트
			String dbID			= "SCOTT";										//아이디
			String dbPassword	= "tiger";										//패스워드
			Class.forName("oracle.jdbc.driver.OracleDriver");					//jdbc 드라이버 파일
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			//System.out.println(conn);
			
			System.out.println("Database에 연결되었습니다.");
			
			String sql1 = "SELECT * FROM emp";									//쿼리문
			//String sql = "SELECT dvo.deptno, dvo.dname, evo.ename FROM dept dvo, emp evo"
			//				+ "WHERE evo.deptno(+) = dvo.deptno";
			
			stat = conn.createStatement();
			rset = stat.executeQuery(sql1);
			
			int i=0, j=0;
			
			/* [ ResultSet.next() ]
			 * Moves the cursor forward one row from its current position.
			 * A ResultSet cursor is initially positionedbefore the first row; 
			 * the first call to the method next makes the first row the current row; 
			 * the second call makes the second row the current row, and so on. 
			 * When a call to the next method returns false,
			 * the cursor is positioned after the last row.
			 */
			while(rset.next()) {	//evo 인스턴스의 값 초기화하는 작업
				evo.setEmpno(rset.getInt("EMPNO"));		//EMPNO 열(column)의 값을 int로 가져옴
				evo.setEname(rset.getString("ENAME"));
				evo.setJob(rset.getString("JOB"));
				evo.setMgr(rset.getInt("MGR"));
				evo.setHiredate(rset.getString("HIREDATE"));
				evo.setSal(rset.getInt("SAL"));
				evo.setComm(rset.getInt("COMM"));
				evo.setDeptno(rset.getInt("DEPTNO"));
				evoArray[i] = evo;	//객체배열의 방 하나에 사원 한 사람의 정보(또는 VO?)가 담긴다고 보면 되나?
									//행의 갯수만큼(사원수만큼) 반복해서 채움
				evo = new EmpVO();	//새 인스턴스(왜냐하면, 직원 한 사람마다 인스턴스 만들어야 하니까)
				if(i<evoArray.length-1) {i++;}
			}
			
			for(EmpVO e : evoArray) {						//EmpVO[]
				System.out.println(e.getEmpno() 	+ " "
								 + e.getEname()		+ " "
								 + e.getJob() 		+ " "
								 + e.getMgr()		+ " "
								 + e.getHiredate()  + " "
								 + e.getSal()		+ " "
								 + e.getComm()		+ " "
								 + e.getDeptno());
			}
			System.out.println();
			String sql2 = "SELECT * FROM dept";
			rset = stat.executeQuery(sql2);
			
			while(rset.next()) {
				dvo.setDeptno(rset.getInt("DEPTNO"));
				dvo.setDname(rset.getString("DNAME"));
				dvo.setLoc(rset.getString("LOC"));
				dvoArray[j] = dvo;
				dvo = new DeptVO();
				if(j < evoArray.length - 1) {j++;}
			}
			for (DeptVO d : dvoArray) {
				System.out.println(d.getDeptno() + " "
								 + d.getDname()	 + " "
								 + d.getLoc());
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stat.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EmpVO		evo			= new EmpVO();
		DeptVO		dvo 		= new DeptVO();
		TestVO_eun	tvo	 		= new TestVO_eun();
		EmpVO[]	    evoArray	= new EmpVO[14];
		DeptVO[]	dvoArray	= new DeptVO[4];
		tvo.testConnect(dvo, evo, evoArray, dvoArray);
	}

}
