package com.design.zipcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class ZipcodeDAO {

	private DBConnectionMgr		dbMgr	= null;
	private Connection			con		= null;
	private PreparedStatement	pstmt	= null;
	private ResultSet			rs		= null;
	
	public ZipcodeDAO() {
		dbMgr	= DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();
		} catch (Exception e) {
			System.out.println("오라클 서버 연결 실패!!");
		}
	}
	//콤보박스에 담길 시/도 정보 조회하기 구현
	/*********************************************************************************************
	 * 쿼리문
	 * SELECT '전체' zdo FROM dual UNION ALL SELECT distinct(zdo) FROM zipcode_t ORDER BY zdo asc
	 * @return String[]
	 * 전체 경기 강원 경북 ...
	 *********************************************************************************************/
	public String[] getZdoList() {
		//원격에 있는 오라클 서버에 접속하기 위해서 DBConnectionMgr 객체 생성하기
		//콤보 박스에 '도'에 대한 정보 가져오기
		String zdos[] = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT '전체' zdo FROM dual"				);
		sql.append("UNION ALL"								);
		sql.append("SELECT distinct(zdo) FROM zipcode_t"	);
		sql.append("ORDER BY zdo asc"						);
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<>();
			while(rs.next()) {
				String zdo = rs.getString("zdo");
				v.add(zdo);
			}
			for(String zdo1:v) {
				System.out.println(zdo1);
			}
			zdos = new String[v.size()];
			v.copyInto(zdos);
		} catch (SQLException se) {
			System.out.println("[[query]]: "+sql.toString());
		} catch (Exception e) {
			System.out.println("Exception: "+e.toString());
		} finally {
			//사용한 자원 반납하기
			//freeConnection 상습적으로 안 하는 세션은 오라클에서 닫아버린다. -자바 튜닝팀의 권장사항임.
			//여는 순서의 역순으로 닫아준다.
			/* [여는 순서 recall]
			 * con
			 * pstmt = con.preparedStatement();
			 * rs = pstmt.executeQuery();
			 */
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return zdos;
	}
	
	//클래스 쪼개기에서 초급자가 항상 염두에 둘 사항은 '배달 사고'이다.
	public ArrayList<ZipcodeVO> getZipCodeList(String dong){
		System.out.println("getZipCodeList 호출 성공"+dong);
		ArrayList<ZipcodeVO> zipcodeList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT"										);
		sql.append(" zipcode, address"							);
		sql.append(" FROM zipcode_t"							);
		sql.append(" WHERE dong LIKE ?||\'%\'"					);
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			ZipcodeVO zcVO = null;
			while(rs.next()) {								//★
				zcVO = new ZipcodeVO();						//★
				zcVO.setZipcode(rs.getInt("zipcode"));		//★
				zcVO.setAddress(rs.getString("address"));	//★
				zipcodeList.add(zcVO);						//★
			}												//★
		} catch (SQLException se) {
			System.out.println("[[query]]"+sql.toString());
			System.out.println("[[SQLException]]"+se.toString());
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		}
		return zipcodeList;
	}
	
	public static void main(String[] args) {
		ZipcodeDAO zcd = new ZipcodeDAO();
		zcd.getZdoList();
		ArrayList<ZipcodeVO> zipcodeList = zcd.getZipCodeList("가산동");
		for(ZipcodeVO zcVO:zipcodeList) {
			System.out.println(zcVO.getAddress()+", "+zcVO.getZipcode());
		}
	}
}
