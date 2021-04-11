package mvc.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class RetrieveEntity {
	Connection			con		= null;
	PreparedStatement	pstmt	= null;
	//'조회'건이므로 ResultSet을 사용한다.
	ResultSet			rs		= null;

	// ㅇ★★★★★오버로딩에 리턴타입은 영향이 없다!★★★★★
	// MN; 서로 다른 return 타입을 if문으로 처리할 수 없다. return 타입은 하나여야 하므로.
	/******************************************************************************************
	 * <상세조회 구현>
	 * 
	 * @param pavo - pavo.getId();
	 * @return AddressVO (or Map) 
	 * SELECT id, name, address, telephone, gender,
	 *         relationship, birthday, comments, registedate
	 *   FROM mkaddrtb
	 *  WHERE id = :x
	 ******************************************************************************************/
	public AddressVO select(AddressVO pavo) { // 무조건 한개의 로우만 가능함.
		System.out.println("RetrieveEntity select(pavo) 호출 성공");
		return null;
	}

	public Map<String, Object> selectMap(AddressVO vo) {
		System.out.println("RetrieveEntity select(vo) 호출 성공");
		return null;
	}

//	public AddressVO[] select() {	//이렇게 파라미터가 없으면 [조건 검색]을 할 수 없다는 단점이 있다.
//		return null;
//	}

	public AddressVO[] select() { // n개의 로우 가능함.
		System.out.println("RetrieveEntity select() 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, address, telephone, gender,            ");
		sql.append("        relationship, birthday, comments, registedate   ");
		sql.append("  FROM mkaddrtb                                         ");
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Vector<AddressVO> v = new Vector<>();
		AddressVO[] aVOS = null; //리턴할 배열 선언
		try {
			con = dbMgr.getConnection();
//			pstmt = con.prepareStatement(sql.toString());
			pstmt = con.prepareStatement(String.valueOf(sql));
			rs = pstmt.executeQuery();
			AddressVO aVO = null;
			while(rs.next()) {
				aVO = new AddressVO();
				aVO.setId(rs.getInt("id"));
				aVO.setName(rs.getString("name"));
				aVO.setAddress(rs.getString("address"));
				aVO.setTelephone(rs.getString("telephone"));
				aVO.setGender(rs.getString("gender"));
				aVO.setRelationship(rs.getString("relationship"));
				aVO.setBirthday(rs.getString("birthday"));
				aVO.setComments(rs.getString("comments"));
				aVO.setRegistedate(rs.getString("registedate"));
				v.add(aVO);
			}
			aVOS = new AddressVO[v.size()];
			v.copyInto(aVOS);
			//커넥션을 맺기 위한 [공통코드]를 찾아서 커넥션을 연결해보자.
		} catch (SQLException se) {
			
		} finally {	//사용한 자원을 반납하는 코드
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return aVOS;
	}

	/******************************************************************************************
	 * 
	 * @param vo
	 * @return
	 ******************************************************************************************/
	public List<AddressVO> selectList(AddressVO vo) { // n개의 로우 가능함.
		System.out.println("RetrieveEntity selectList() 호출 성공");
		return null;
	}

}
