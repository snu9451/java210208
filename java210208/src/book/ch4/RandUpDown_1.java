package book.ch4;

import java.util.Random;
import java.util.Scanner;

public class RandUpDown_1 {

	public static void main(String[] args) {
		int dap = 0;
		int guess = 0;
		Random rand = new Random();
		//0이상 10미만의 임의의 정수 생성하고 담기
		dap = rand.nextInt(10);
		//System.out.println(dap);
		System.out.println("Let's play <UpDownGame>! (기회는 최대 3번입니다.)");
		Scanner scan = new Scanner(System.in);
		//
		while(true) {
			for (int trial=1;trial<3;trial++) {
				System.out.println("["+trial+"번째 시도]");
				guess = scan.nextInt();
				if((guess>=0)&&(guess<=9)) {
					if(guess == dap) {
						System.out.println("축하합니다. "+trial+"번째 만에 정답 "+dap+"을(를) 맞추셨습니다.");
						return;
					}else if(guess < dap){
						System.out.println("UP!");
					}else {
						System.out.println("DOWN!");
					}
				}else {
					System.out.println("[ErrorMessage]0부터 9까지의 수만 입력하세요!");	
					//return;
					break;
				}
			}
			if((guess>=0)&&(guess<=9)) {
				System.out.println("넌 바보!");
				return;
			}
		}
	}

}
