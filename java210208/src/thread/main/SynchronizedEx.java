package thread.main;

public class SynchronizedEx {

	public static void main(String[] args) {
		ATM atm = new ATM();
		Thread ey = new Thread(atm, "은영");	//ey를 먼저 썼다고 먼저 실행되리라는 보장은 없음. 둘 다 가중치는 5
		Thread ht = new Thread(atm, "희태");
		ey.start();
		ht.start();
	}

}
