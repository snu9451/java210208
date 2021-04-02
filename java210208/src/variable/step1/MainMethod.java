package variable.step1;

public class MainMethod {
/************************************************
 * @param args - 파라미터의 타입은 '배열'입니다.
 * 원래 배열은 String args[] = new String[3];이지만
 * main메소드는 좀 특별한 경우라서 따로 설명하려 함.
 * 학습목표: main메소드에 대해 설명할 수 있다.
 ************************************************/
	public static void main(String[] args) {
		//args[0]의 타입이 뭔지 확인해보자.
		//만일 숫자라면 20을 출력할거야.
		//만일 문자라면 1010을 출력할거야.
		//int + int = int
		//int + String = 붙여쓰기
		System.out.println(args[0]+10*2);
		if(args[0] instanceof String) {
			System.out.println("나는 문자열이야");
		}else {
			System.out.println("나는 문자열 아닌데...");
		}
		if(true) {	//이 if문에서 else부분이 출력될 일은 죽어도 없다.
					//왜냐하면 <상수>이기 때문에
			System.out.println("반드시 출력");
		}else {
			System.out.println("절대 출력 안 됨");
		}
		for (int i=0;i<100;i=i+1) { //코드로 증명
			if(true) {
				//왜냐하면 <상수>이기 때문에
				System.out.println("반드시 출력");
			}
		}
	}

}
