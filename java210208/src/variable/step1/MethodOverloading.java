package variable.step1;

import com.vo.DeptVO;

public class MethodOverloading {
	/*********************************************************************************
	 * @param pdvo - 조건절에서 사용될 상수값을 담는다. - where에 들어갈 조건 값으로 사용
	 * @return rdvo - 오라클에서 조회된 결과를 담는다. - 화면 출력용으로 사용
	 *********************************************************************************/
	DeptVO go(DeptVO newPdvo) { //parameter라는 의미에서 dvo 앞에 p를 붙여줬음
		System.out.println(newPdvo.getDeptno()+", "+newPdvo.getDname()+", "+newPdvo.getLoc());
		DeptVO rdvo = null; //return type이라는 의미에서 dvo 앞에 r을 붙여줬음
		//오라클을 경유한 다음에 받아온 조회결과를 내용으로 담는 클래스이다.
		return rdvo;
	}
	//메소드 이름은 같지만, 서로 다른 메소드로 인식된다.
	void go() {
		System.out.println("파라미터가 없는 go()가 호출되었습니다.");
	}
	void go(int i) {
		System.out.println("파라미터가 int인 go(int i)가 호출되었습니다.");		
	}
	void go(String name) {
		System.out.println("파라미터가 String인 go(String name)가 호출되었습니다.");		
	} //go는 메소드 오버로딩! (오버라이딩이 아님)
	//메소드 오버로딩은 자바스크립트에서는 지원되지 않음.
	public static void main(String[] args) {
		MethodOverloading mol = new MethodOverloading();
		mol.go();
		mol.go(10);
		mol.go("Smith"); //참조형 타입이므로 '참조에 의한 호출'이다.
		//다만, String은 예외적으로 호출할 때 주소번지가 아니라 값이 출력된다.
		//Tip; Ctrl 누르면서 마우스 갖다 대면 클릭할 수 있음
		DeptVO pdvo = new DeptVO(); //인스턴스화
		pdvo.setDeptno(50); //초기화
		pdvo.setDname("개발1팀"); //초기화
		pdvo.setLoc("제주도"); //초기화
		mol.go(pdvo); //주소번지를 넘겼다. 참조에 의한 호출(<-> 값에 의한 호출)
	}

}
