package thread.basic;

import javax.sound.midi.SysexMessage;

import oracle.net.aso.a;
import oracle.net.aso.b;

class A extends Thread{
	
	@Override //같은 이름의 메소드를 여러 개 사용할 수 있다.(오버로딩) 그런데 서로 구현체 또는 상속 관계이기까지 하다면 오버라이딩
	public void run() {
		for(int i=0; i<1; i++) {
			System.out.print("-");
		}
		System.out.println("[[ A스레드 run ]]");
	}
}
class B extends Thread{
	@Override
	public void run() {
		for(int i=0; i<1; i++) {
			System.out.print("|");
		} 
		System.out.println("[[ B스레드 run ]]");
	}
}



public class ThreadEx13 {

	static long startTime = 0;
	//메인메서드 - 메인스레드 - 디폴트스레드
	/*
	public static void main(String[] args) {	: main thread 시작
	A th1 = new A();							: 스레드 로딩 - ready
	th1.start();								: - go - 대기중(자리가 나면 들어간다, Runnable 상태): A run call
	th2.start();								: B run call
	
	public void run() {
	for(int i=0; i<300; i++) {
	System.out.print("-");						: (-------||||||||------|||||| ....) 이렇게 번갈아 가며 출력될 것이다.
	
	public void run() {
	for(int i=0; i<300; i++) {
	System.out.print("|");						: (|||||||||||||||||||||------|||||||||||| ...)
	
	System.out.println("소요시간: "+(System.currentTimeMillis() - ThreadEx13.startTime));
	*/
	
	public static void main(String[] args) {
		A th1 = new A();
		B th = new B();
		Thread th2 = new Thread(th);
		th1.start();	//콜백 메소드인 run을 호출한다.
		th2.start();
		//시간을 계산함(스레드가 뛰는?운영되는?작동하는? 시간)
		startTime = System.currentTimeMillis();	//Millis 밀리세컨 단위
//		try {
//			th1.join();	//th1의 작업이 끝날 때까지 기다린다.
//			th2.join();	//th2의 작업이 끝날 때까지 기다린다.
//		} catch (InterruptedException e) {}
		System.out.println("소요시간: "+(System.currentTimeMillis() - ThreadEx13.startTime));	//MN: 현재시간에서 아까구한 현재시간 뺌; 소요시간
		}//end of main

}
