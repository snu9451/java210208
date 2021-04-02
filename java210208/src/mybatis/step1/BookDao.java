package mybatis.step1;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BookDao {
	String resource = "mybatis/step1/MapperConfig.xml";
	//SqlSessionFactory: MyBatis에서 제공하는, 커넥션을 맺기 위한 클래스
	//이것을 통하여 xml문서의 정보를 읽어서 커넥션을 얻어냄.
	SqlSessionFactory sqlMapper = null;
	public List<Map<String, Object>> getBookList(){
		List<Map<String, Object>> bookList = null;
		//selectOne, selectList, selectMap, insert, update, delete 메소드의 소유주
		//트랜잭션처리: 묶음배송, 일괄처리 -- commit이나 rollback을 할 수 있어야 함.
		//con.commit - oracle에서의 commit; 스냅샷(이미지)만 갖고 있는 것을 물리적인 테이블에 반영하는 작업
		//git에서의 commit - ??????????로컬 / 웹????????
		//Sqlsession(1)물리적으로 떨어진 오라클 서버의 인증을 받고, (2)커밋이나 롤백을 할 수 있게 함.
		//자바???????는 commit이 default true인 반면, insert, update, delete는 commit이 default false임. 따라서 commit해주어야 함
		SqlSession session = null;
		//setAutocommit대신 openSession을 씀
		//rmbr) boolean의 default 값은 false임.
		//IO패키지는 Exception이 발생할 수 있어서 반드시 예외처리 해주어야  한다.
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();
			String currentTime = session.selectOne("currentTime");
			System.out.println("currentTime => "+currentTime);
		} catch (Exception e) {
			e.printStackTrace();	//힌트를 많이 준다.
		}
		return bookList;
	}
	public static void main(String[] args) {
		BookDao bDao = new BookDao();
		List<Map<String, Object>> bookList = null;
		bookList = bDao.getBookList();
		System.out.println(bookList);
	}
}
