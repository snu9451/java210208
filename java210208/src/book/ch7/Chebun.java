package book.ch7;

import java.util.Random;

public class Chebun {
	int com[] = new int[3];
	void nansu() {
		Random r = new Random();
		com[0] = r.nextInt(10);
		do {
			com[1] = r.nextInt(10);
		}while(com[0] == com[1]);
		do {
			com[2] = r.nextInt(10);
		}while(com[2] == com[0] || com[2] == com[1]);
	}
	public static void main(String[] args) {
		Chebun cb = new Chebun();
		int cnt = 0;
		for(int i=0;i<10;i++) {
			cb.nansu();
			System.out.println(++cnt+"회차: "+cb.com[0]+""
							   +cb.com[1]+""+cb.com[2]); //이렇게 건너써도 되는군!
		}
	}

}
