package book.ch4;

import java.util.Random;
import java.util.Scanner;

public class RandUpDown_2 {

	int dap; //랜덤하게 정해진 수를 담음
	int guess; //사용자가 추측하여 입력하는 정수를 담음
	int su; //최대 시도 횟수를 담음
	
	//두 수를 비교하는 메소드
	String BGyo(int pdap, int pguess) {
		if(pdap>pguess) {
			return "UP";
		}else if(pdap==pguess) {
			return "CORRECT";
		}else {
			return "DOWN";
		}
	}
	
	//조건에 부합하는 수를 입력하였는지 체크하는 메소드 - 리턴타입: boolean
	//입력된 값이 정수인 경우
	boolean Buhap(int pguess) {
		if(pguess>=0 && pguess<10) {
			return true;
		}else {
			return false;
		}
	}

	//for문을 돌릴 횟수를 입력받아 게임을 진행하는 메소드
	void Game(int su) {
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		dap = rand.nextInt(10);		//dap으로 쓰나 인스턴스화 해서 쓰나 this.dap으로 쓰나 여기서 쓰는 건 무관. 그냥 dap으로 쓰면 됨
		//main메소드를 사용하지 말라는 이유가 여기 있는데, main은 static 공간(?)을 사용하기 때문에 '내 안에 있는 것'이라도 인스턴스화를 해서 메모리에 올려야 한다.
		//외부에서 쓰거나 값을 다르게 사용하고 싶다 하면 인스턴스화 해서 사용하면 되는 것
		//System.out.println(r2.dap); //주석처리할 예정
		System.out.println("Let's play <UpDownGame>! 기회는 최대 "+su+"번!");
		System.out.println("0부터 9까지의 정수 중 하나를 입력하세요.");
		boolean isStop = false; //while(true)로 쓰지 않도록 하자! true는 상수임//보통 while이 등장하면 boolean체크함
		while(!isStop)
		{
			for(int i=0;i<su;i++) 
			{
				System.out.println("["+(i+1)+"번째 시도]");
				guess = scan.nextInt();
				if(Buhap(guess)) 
				{	//입력된 수가 조건에 부합하는지 체크
					String imsi = BGyo(dap, guess);
					if(imsi == "CORRECT") 
					{
						System.out.println("축하축하~ "+(i+1)+"번째 만에 정답!");
						return;
					}
					else 
					{
						System.out.println(imsi);
					}
				}
				else 
				{
					System.out.println("[ErrorMessage]0부터 9까지의 수만 입력하세요!");
					break;
				}
				
			}
			if(Buhap(guess)) 
			{
				System.out.println("넌 바보!");
				return;
			}
		}
	}
	
	
	public static void main(String[] args) {
		RandUpDown_2 r2 = new RandUpDown_2();
		r2.su = 3;
		r2.Game(r2.su);		
	}

}
