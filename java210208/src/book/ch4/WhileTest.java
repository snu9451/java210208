package book.ch4;

public class WhileTest {

	public static void main(String[] args) {
		//Parameter 자리에 true와 같은 <상수>를 사용하지 않기! 서버다운을 유발할 수 있음
		//(1)
		while(true) //while문은 항상 <무한루프 방지 코드>를 작성할(포함할) 것!
		{
			break;
		}
		//(2)
		boolean isStop = false;
		while(!isStop)
		{
			
		}
		//(3)
		while(true)
		{
			
		}
		//int i = 5; //무한루프때문에 실행되지 않는 코드임
		
		/***********************************************
		 *(2)번처럼 작성하는 것이 권장사항이다.
		 ***********************************************/
		
	}////////////////////end of main
}////////////////////////end of WhileTest
