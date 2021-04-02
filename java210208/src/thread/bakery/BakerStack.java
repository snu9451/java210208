package thread.bakery;

import java.util.Vector;

public class BakerStack {
	private Vector<String> v = new Vector<>();
	
	//인터셉트를 하지 못하도록 하기 위해서는 synchronized를 넣어준다.
	public synchronized String pop() {
		String bread = null;
		if(v.size()==0) {
			try {
				System.out.println("빵이 더이상 없습니다. 잠시만 기다려 주세요.");
				/*
				쓰레드 데드락
				데드락이란, 둘 이상의 쓰레드가 lock 을 획득하기 위해 기다리는데, 이 lock 을 잡고 있는 쓰레드도
				똑같이 다른 lock 을 기다리며 서로 블록 상태에 놓이는 것을 말한다. 데드락은 다수의 쓰레드가 같은 lock을,
				동시에, 다른 명령에 의해, 획득하려 할 때 발생할 수 있다.
				*/
				this.wait();	//데드락 상태.....어쩌구..
			} catch (Exception e) {
				System.out.println("Exception: "+e.toString());
			}
		}
		bread = v.remove(v.size()-1);	//벡터에서는 지우고 동시에 값으로 반환도 해줌
		return bread;
	}
	public synchronized void push(String bread) {
		System.out.println("기다려 주셔서 감사합니다. 빵 나왔으니 가져가세요.");
		this.notify(); 	//잠자고 있는 스레드를 깨움
		v.add(bread);
	}
}
