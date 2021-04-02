package ch5.oracle;
//원시적인 방법임 - 나중에 더 좋은 방법 배울 예정
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConnectionMgr;
import com.vo.EmpVO;

public class JdbcTest2 {
	//선언부
	static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String _URL	= "jdbc:oracle:thin:@localhost:1521:orcl11";
	String				_USER	= "scott";
	String				_PW		= "tiger";
	//물리적으로 떨어져 있는 서버에 연결통로를 만들기
	//Connection: SQL, 데이터베이스 쪽 연결해주는 클래스임
	Connection			con		= null;
	//24번 서버에 내가 작성한 select문을 전달해줄 객체 선언
	PreparedStatement	pstmt	= null;
	//오라클의 커서를 조작하는 객체 선언
	ResultSet			rs		= null;
	DBConnectionMgr		dbMgr	= null;
	//생성자
	public JdbcTest2() {
		String sql = "SELECT empno, ename, sal FROM emp";
		dbMgr	   = DBConnectionMgr.getInstance();
		try {
			//연결 통로 확보하기
			con 	  = dbMgr.getConnection();
			//오라클 서버의 SELECT문을 전달할 ★★★전령★★★ 객체 생성
			pstmt 	  = con.prepareStatement(sql);
			//<오라클에 살고 있는 커서(cursor)를 조작하기 위해서 java가 제공하는 객체> 생성
			rs 		  = pstmt.executeQuery();
			EmpVO eVO = null;
			while(rs.next()) {
				eVO = new EmpVO();
				//encapsulation(캡슐화)
				eVO.setEmpno(rs.getInt("empno"));			//(ㄱ)	//encapsulation 시, setter를 통해 값을 초기화한다.
				eVO.setEname(rs.getString("ename"));		//(ㄴ)
				eVO.setSal(rs.getDouble("sal"));			//(ㄷ)
				//int rempno = rs.getInt(1);				//db에서 가져온 것이므로 return이라는 의미에서 r을 붙였음
				//int		rempno = rs.getInt("empno");	//윗줄보다 이 줄이 더 직관적임(이렇게 써라! 윗줄은 노노)
				//String	rename = rs.getString("ename");	//이렇게 변수 선언과 초기화를 이용하지 않고 (ㄱ)~(ㄷ)의 방법을 이용하는 것이 encapsulation
				//double	rsal   = rs.getDouble("sal");
				System.out.printf("%4d %6s %5.0f %n", eVO.getEmpno(), eVO.getEname(), eVO.getSal());	//encapsulation 시, getter를 통해 값을 꺼낸다.
			}
//		} catch (ClassNotFoundException ce) {
//			System.out.println("드라이버 클래스 로딩 실패");
//			return;
		} catch (SQLException se) {
			//이 경우 toad에서 나타나는 메세지: "부적합한 식별자" 입니다.
			System.out.println("SQLException: "+se.getMessage()); //좀 더 구체적인 예외 처리 클래스 정보를 확인할 수 있다.
		}
		System.out.println("끝.");
	}
	//오라클 서버 접속
	public static void main(String[] args) {
		JdbcTest2 jt = new JdbcTest2();//생성자 호출도 동시에 일어남
	}

}