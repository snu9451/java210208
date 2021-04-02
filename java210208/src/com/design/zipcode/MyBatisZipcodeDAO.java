package com.design.zipcode;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.util.MyBatisCommonFactory;

public class MyBatisZipcodeDAO {

	SqlSessionFactory	sqlSessionFactory = null;
	
	public MyBatisZipcodeDAO() {
		sqlSessionFactory	= MyBatisCommonFactory.getInstance();
		System.out.println("sqlSessionFactory: "+sqlSessionFactory);
	}
	
	public List<ZipcodeVO> getZipCodeList(ZipcodeVO pzVO){	//다형성을 위해 ArrayList가 아닌, List 사용하자.
		System.out.println("getZipCodeList 호출 성공"+pzVO);
		List<ZipcodeVO> zipcodeList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			zipcodeList = sqlSession.selectList("refreshData", pzVO);	//id
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e.toString());
		} finally {
			//사용한 자원을 명시적으로 반납하자. 안 그러면 오라클 서버 터진다.
			sqlSession.close();
		}
		return zipcodeList;
	}
	
	public static void main(String[] args) {
		MyBatisZipcodeDAO zcd = new MyBatisZipcodeDAO();
		List<ZipcodeVO> zipcodeList = new ArrayList<>();
		ZipcodeVO pzVO = new ZipcodeVO();
		pzVO.setDong("가산동");
		zipcodeList = zcd.getZipCodeList(pzVO);
		for(ZipcodeVO zcl : zipcodeList) {
			System.out.println("zipcodeList: "+zcl.getAddress());
		}
	}
}
