package book.ch4;
import java.util.Random;
/****************************************************************************
 * java.util에 있는 패키지들은 (제공되는 API를 통해)뭔가를 만들 때 유용한 기능들을 모아둔 패키지이다.
 * java.lang이 아닌 패키지는 모두 import 대상이다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 ****************************************************************************/
import java.util.Scanner;

public class PrintExam {

	public static void main(String[] args) {
		//한번에 인스턴스화	//Scanner는 디폴트 생성자를 지원하지 않으므로 괄호 안에 넣어줘야 오류가 안 뜸
		//System이란 현재 내가 사용하고 있는 장치(컴퓨터, device) 즉, 처리 주체이다.
		//'사용자나 업무 담당자가 키보드에 입력한 값'을 읽어오기 위해서는 '입력장치'를 나타내는 '속성(attribute)'이 필요하다.
		//local device(즉, 서버)가 아니므로 동기화가 불가능. 즉, 예전 데이터를 계속 갖고 있고 새로 등록된 (ex- 운동선수에 대한)정보가 없다.
		//동기화를 위해서는 '서버'를 이용해야 한다. 클라이언트가 아니라 서버가 바쁘게 일하게 되는 것!
		Scanner sc = new Scanner(System.in); //선언부(왼쪽) = 생성부(오른쪽)
		//나눠서 인스턴스화
		Scanner sc2 = null;
		sc2 = new Scanner(System.in);
		System.out.println("당신의 나이를 입력하세요.");
		System.out.println("=======================================");
		System.out.println(sc.nextInt());
		//디폴트 생성자를 제공해줌
		Random r = new Random(); //채번하기, 난수 발생 등에 사용됨.
		int count = 1;
		int cnt = 0;
		for(int i=0; i<1000; i=i+2) {
			int dap = r.nextInt(10);
			System.out.println(count++ +":"+dap); //cnt++하면 출력 후, cnt에 1을 더함
			System.out.println(++cnt +":"+dap);
		}
	}

}
