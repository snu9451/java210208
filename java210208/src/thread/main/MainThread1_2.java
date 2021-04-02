package thread.main;

class MyRunningTwo implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" running");
		System.out.println("run");
		first();
	}
	
	public void first() {
		System.out.println(Thread.currentThread().getName()+" running");
		System.out.println("first");
		second();
	}
	
	public void second() {
		System.out.println(Thread.currentThread().getName()+" running");
		System.out.println("second");
	}
	
}

public class MainThread1_2 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+" start");
		Runnable r1 = new MyRunningTwo();
		Thread th = new Thread(r1);
		th.start();
		try {				//그냥 하면 main end 줄이 먼저 실행되므로 join을 걸어서 순서를 보장해주자.
			System.out.println(Thread.currentThread().getName()+" running");
			th.join();			
			System.out.println(Thread.currentThread().getName()+" running");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" main end");
	}

}
