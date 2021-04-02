package common.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.util.DBConnectionMgr;

public class MemberDao {	//DAO: Data Access Object
	Connection			con 	= null;
	CallableStatement	cstmt 	= null;
	DBConnectionMgr		dbMgr 	= null;
	public String login(String p_id, String p_pw) {
		String msg = "";
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();
			cstmt = con.prepareCall("{call proc_login80(?, ?, ?)}");
			cstmt.setString(1, p_id);
			cstmt.setString(2, p_pw);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			int result = cstmt.executeUpdate();
			if(result == 1) {
				msg = cstmt.getString(3);
			}
//			System.out.println("result: "+result);
//			System.out.println("cstmt.getString(3): "+cstmt.getString(3));
		} catch (Exception e) {
			
		} finally {
			dbMgr.freeConnection(con, cstmt);
		}
		return msg;
	}
	
	public static void main(String[] args) {
		MemberDao md = new MemberDao();
		System.out.println(md.login("test", "123"));
		System.out.println(md.login("test", "12311"));
		System.out.println(md.login("test11", "123"));
//		md.login("test", "123");
//		md.login("test", "12311");
//		md.login("test11", "123");
	}
}


