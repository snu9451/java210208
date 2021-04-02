package book.ch5;

import java.util.StringTokenizer;

public abstract class Dog {

	public static void main(String[] args) {
		String imsi1 = "코스모";
		int imsi2 = 80;
		String imsi3 = "파이팅!";
		String total = imsi1 + "$" + imsi2 + "$" + imsi3;
		StringTokenizer st = new StringTokenizer(total, "$");
		String token = st.nextToken();
		st.nextToken();
		st.nextToken();
		System.out.println(token);
		System.out.println(total);
	}
}
