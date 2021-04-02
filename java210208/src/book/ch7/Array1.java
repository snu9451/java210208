package book.ch7;
//<헷갈릴 수 있으니 주의>
//배열의주번.length명사형: 원소의 개수이다.
//"hello".length()동사형: 문자열 길이이다.
public class Array1 {

	public void printArray(int empnos[]) {
		for(int i=0; i<empnos.length; i++) { //length: 배열의 길이를 기억해준다.
			System.out.println((i+1)+": "+empnos[i]);
		}
	}
	/* 19-20-22-24-9-10-11-12-11-12-11-12-... 순으로 논리를 따라가보는 연습을 하자 
	 */
	
	public static void main(String[] args) {
		int empnos[] = new int[10000]; //0이 14개 들어있게 된다. 왜냐, int는 초기값이 0임.
		Array1 a1 = new Array1();
		a1.printArray(empnos);
	}

}
