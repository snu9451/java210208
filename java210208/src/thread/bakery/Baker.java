package thread.bakery;

public class Baker extends Thread {
	BakerStack bs = null;
	public Baker(BakerStack bs) {
		this.bs = bs;
	}
	
	//(오버라이드된)run메소드가 빵을 진열하는 작업을 수행하도록 하자.
	@Override
	public void run() {
		String bread = null;
		bread = getBread();
		bs.push(bread);
		try {
			Thread.sleep(3000);	//3초 동안 정지
		} catch (InterruptedException ie) {
			System.out.println("누구신가요~?");
		}
	}
	
	
	public String getBread() {
		String bread = null;
		switch ((int)(Math.random()*3)) {	//어떤 빵이 만들어질지 예측할 수 없도록 난수이용.
		case 0:
			bread = "소보로";
			break;
		case 1:
			bread = "샌드위치";
			break;
		case 2:
			bread = "도너츠";
			break;
		}
		return bread;
	}
}
