package book.ch14;


/* API를 보는 습관을 들이자.
  * *********★★★★★★★★[[ 1일 1커밋! ]]★★★★★★★★********* 습관화 하자.
 * 이번 프로젝트에서 [[ 사용자 예외처리 ]]를 해보자.
 * 팀이 다르더라도 서로 코드리뷰 해보는 시간을 꼭 가져라.
 */


public class UserExceptionTest {

	public void test(String[] a) throws UserException {
		System.out.println("test 호출 성공");
		if (a.length < 1) // 배열에 아무것도 없니?
			throw new UserException("아무것도 없어요..ㅠ_ㅠ"); // if문 안의 실행문이 한 줄인 경우, 중괄호 생략할 수 있다.
		else {
			throw new UserException("최종 예선 .", 7000);
		}
	}

	public static void main(String[] args) {
		UserExceptionTest uet = new UserExceptionTest();
		try {
			// #1 (#2 주석)
//			String[] arg = {"안녕"};
//			uet.test(arg);
			
			// #2 (#1 주석)
			uet.test(args);
		} catch (UserException ue) {
//			System.out.println(ue.printStackTrace()); // error
//			ue.printStackTrace();
			
			//라인번호도, 클래스명도 아무것도 안 보여줌. - 후지다. 따라서 sysoout이 아니라 printStackTrace를 써라! 아님 log4j나 등등...
			System.out.println("[[[[[[[[[[[[[[[[[[[[[UserException]]]]]]]]]]]]]]]]]]]]] "+ue.toString());
			//자바 가상머신과의 연결고리를 끊음. -  finally를 타지 않는다.
			System.exit(0);
		} catch (Exception e) {
			e.toString();
			e.getMessage();
			e.printStackTrace();
		} finally {
			System.out.println("finally - 무조건 실행됩니다.");
		}
	}

}
