package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
	private final static String 	_DRIVER	="oracle.jdbc.driver.OracleDriver";
	private final static String 	_URL	="jdbc:oracle:thin:@localhost:1521:orcl11";
	private final static String 	_USER	="eunyk";
	private final static String 	_PW		="cheetah";
	private static DBConnectionMgr dbMgr	= null;
	Connection	con	= null;	//java.sql 패키지임
	private DBConnectionMgr() {}
	//게으른 인스턴스화 - 선언과 생성이 따로 쓰여지는 경우	cf-이른 인스턴스화: private static DBConnectionMgr dbMgr2 = new DBConnectionMgr();
	public static DBConnectionMgr getInstance() {	//싱글톤 디자인패턴
		if(dbMgr == null) {
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	public Connection getConnection() {
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
			/* [트랜잭션(transaction) 처리]
			con.setAutoCommit(true);	//Autocommit [on]: 디폴트임
			con.setAutoCommit(false);
			con.commit();
			con.rollback();
			*/
		}catch(ClassNotFoundException ce) {	//예외 발생 시 없는 것과 똑같고, 발생 시 catch에서 잡아준다.
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");	//"해당하는 클래스를 찾을 수 없어요 Exception"
		}catch(Exception e) {
			System.out.println("오라클 서버와의 연결에 실패했습니다.");
		}
		return con;
	}
	//사용한 자원 반납하기
	//자원을 반납할 때는 생성된 객체의 역순으로 반납한다.★★★★
	//이것을 하지 않으면 오라클 서버에 접속할 때, <세션 수의 제한> 때문에 오라클서버를 재가동해야 하는 경우가 발생할 수 있음
	
//	세개짜리는 SELECT일 때 사용
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch(Exception e) {
			
		}
	}

//	두개짜리는 INSERT|UPDATE|DELETE일 대 사용
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch(Exception e) {
			
		}
	}
	public void freeConnection(Connection con, CallableStatement cstmt) {
		try {
			if(cstmt != null) cstmt.close();
			if(con != null) con.close();
		} catch(Exception e) {
			
		}
	}
	/*
	주의: 사용한 자원은 반드시 명시적으로 반납할 것!
	오라클 서버에 의해 세션을 종료당할 수 있다. 세션의 수는 누가 결정? DBA들이 결정.
	*/
	public void freeConnection(Connection con, CallableStatement cstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(cstmt != null) cstmt.close();
			if(con != null) con.close();
		} catch(Exception e) {
			
		}
	}
}
