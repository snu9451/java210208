package book.ch7;

public class TwoArray6 {
	void init() {
		int is[][] = {{1,2},{3,4},{5,6}};
		int x = 0;
		while(x<is.length) {
			int y = 0;
			while(y<is[x].length) {
				System.out.println("is["+x+"]["+y+"]="+is[x][y]);
				y = y + 1;
			}//////////////////end of inner while문
			x = x + 1;
		}//////////////////////end of outer while문
	}
	public static void main(String[] args) {
		TwoArray6 ta6 = new TwoArray6();
		ta6.init();
		System.out.println("====================================");
		int is[][] = new int[2][3]; //2중 for문과도 관련되어 있음
		//실무에서 3차배열까지는 쓰지 않기 때문에 3차배열은 하지 않고 자료구조로 넘어갈 예정
		//2는 Row를 말함
		//3은 Column을 말함
		for(int i=0;i<is.length;i++) {
			for(int j=0;j<is[i].length;j++) {
				System.out.println("is["+i+"]["+j+"]="+is[i][j]);
			}////////////////end of inner for문
		}////////////////////end of outer for문
	}

}
