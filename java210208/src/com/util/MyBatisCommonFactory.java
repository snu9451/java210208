package com.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisCommonFactory {
	private static SqlSessionFactory sqlSessionFactory = null;
//	private SqlSession sqlSession = null;
	//실제 서버에서 사용하는 싱글톤 패턴임 - 싱글톤 패턴의 끝판왕 - 멀티스레드에서도 안전한 방식 - 다만, 속도가 느리다. Vector도 ArrayList보다 속도가 느리다. 동기화 때문.
	public static SqlSessionFactory getInstance() {
		if(sqlSessionFactory == null) {
			synchronized (SqlSessionFactory.class) {
				//동기화 블럭에 들어온 후에도 다시 한 번 Null 체크
				if(sqlSessionFactory == null) {
					try {
						String resource = "mybatis/step1/MapperConfig.xml";
						Reader reader = Resources.getResourceAsReader(resource);
						sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}////////동기화 처리
		}////////////end of
		return sqlSessionFactory;
	}////////////////end of getInstance()
}
