package thread.main;

public class ATM extends Thread {
	
	private int depositedMoney = 10000;
	
	public void run() {
		synchronized(this) {	//하위 코드블럭에 대해 문을 걸어잠근 거. 그래서 중간에 끼어들 수가 없음.
			for(int i=0; i<10; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				withDraw(1000);
			}//////////////////end of for
		}//////////////////////end of synchronized
	}//////////////////////////end of run

	private void withDraw(int howMuch) {
//		if(depositedMoney > 0) {
		if(depositedMoney >= howMuch) {
			depositedMoney -= howMuch;
			System.out.print(Thread.currentThread().getName()+", ");
			System.out.printf("잔액: %,d 원 %n", depositedMoney); //%d: 10진수, 앞에 콤마(,)	//%n: 개행처리
		} else {
			System.out.print(Thread.currentThread().getName()+", ");
			System.out.println("잔액이 부족합니다.");
		}
	}/////////////////////////end of withDraw
}
