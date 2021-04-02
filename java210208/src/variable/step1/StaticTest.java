package variable.step1;

public class StaticTest {
	//non-static 타입은 static 영역에서 접근이 불가하다.(= 호출할 수 없다. 사용할 수 없다.) 
	static void avg() { //사용자 정의 메소드입니다.
		//사용자 정의 메소드에도 지금처럼 <static>을 붙일 수 있다.
		System.out.println("avg메소드 호출 성공!");
	}
	public static void main(String[] args) {
		if(args[0]==null) { //같은 말: args.length == 0;
			//아래 문장은 조건에 따라 실행이 전혀 안 될 수 있다.
			System.out.println("배열을 입력하지 않았습니다.");
			return; //아래 문장을 실행하지 않기 위해 return;을 해줌.<main메소드 탈출>
		} else {
			System.out.println(Integer.parseInt(args[0])+10); //결과가 20이 되도록 코드를 출력하시오.
			//호출의 기본적인 형태 ===> int imsi = 주소번지.xxxx(args[0]);
			//메소드 이름 앞에 static이 있다면(가령 위의 avg()메소드 처럼), 주소번지 없이도 메소드를 호출할 수 있다.
			//main메소드는 JAVA가 제공하는 메소드이다? Yes or No
			//사용자가 메소드를 정의할 수도 있다; "사용자 정의 메소드"
			//=========================================================
			//내가 필요한 메소드는 '문자열을 집어 넣으면(문자열을 파라미터로 했을 때) int 타입으로 변환하여 반환해주는 메소드'이다.
			//정답: parseInt()
			//네이버나 구글링을 해서, 위 요구사항을 만족하는 코드를 작성하시오.
			avg(); //이렇게 <인스턴트화 없이> 호출할 수 있다. (why? static[정적인])
			//윗 줄을 StaticTest.avg();로 쓰는 것이 표준이다.
			
		}
	}

}
