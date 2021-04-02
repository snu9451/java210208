package ch5.oracle;
/***********************************************************************************
 * 대량의 정보를 조회 시, 오라클에서 제공하는 [REFCURSOR]를 활용할 수 있다.
 * 
 * CREATE OR REPLACE PROCEDURE eunyk.proc_temp_list(p_temp OUT sys_refcursor)
 * IS
 * BEGIN
 * 		OPEN p_temp
 * 		FOR SELECT emp_id, emp_name, lev FROM temp;
 * END;
 * 
 * JDBC연동 기술은 공통된 관심사이다.
 * 매번 동시 접속자 수가 많을 수 있고 & 다중처리를 해야 하므로, DB커넥션 풀링을 고려해야 한다. (keyword; 객체풀링)
 * 	Tomcat(WAS)에서 제공되는 풀링이 있다. - 시니어 개발자들에 의해 개발되고 검증되었음.
 * 	HikariCP - tomcat보다 빠르다고 함.
 **********************************************************************************/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBConnectionMgr;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

public class REFCursorTest {
	Connection 				con 	= null;
	CallableStatement 		cstmt 	= null;
	OracleCallableStatement ocstmt 	= null;
	ResultSet 				rs 		= null;
	DBConnectionMgr			dbMgr	= null;
	public void init() {
		dbMgr = DBConnectionMgr.getInstance();	//인스턴스화#3(싱글톤)
	}
	//join시에는 두개이상의 테이블이 같이 들어오므로 VO로는 안된다. 오늘은 Map에 대해서는 소개만.
	public List<Map<String, Object>> getTempList(){
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			con = dbMgr.getConnection();
			cstmt = con.prepareCall("{call proc_temp_list(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			rs = ocstmt.getCursor(1);
			Map<String, Object> rmap = null;
			while(rs.next()) {
				rmap = new HashMap<>();	//map은 키값으로 관리하므로 VO를 대체 가능
				rmap.put("emp_id", rs.getInt("emp_id"));
				rmap.put("emp_name", rs.getString("emp_name"));
				rmap.put("lev", rs.getString("lev"));
				list.add(rmap);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	public static void main(String[] args) {
		REFCursorTest rt = new REFCursorTest();
		rt.init();
		List<Map<String, Object>> list = rt.getTempList();
		for(Map<String, Object> pMap : list) {
			System.out.println(pMap);
		}
	}

}
