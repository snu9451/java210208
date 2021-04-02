package network.step1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JLabel;

import book.ch5.P_AddressBook2;

public class TimeClient extends Thread{
	String 			timeStr 	= null;
	public JLabel 	jlb_time 	= null;
	TimeClient(){}
	public TimeClient(JLabel jlb_time){	//다른 패키지에서도 접근할 수 있도록 하려면 public을 붙여줘야 함.
		this.jlb_time = jlb_time;
	}
	@Override
	public void run() {	//콜백메소드 - 사용자가 아니라, 시스템 레벨에서 자동 호출되는 메소드이다.
		System.out.println("run 호출 성공!");
		Socket			socket	= null;
		PrintWriter		out		= null;
		BufferedReader 	br 		= null;
		boolean			isFlag	= false;
		try {
			socket = new Socket("192.168.0.29", 1996);	//local port 1996
			out = new PrintWriter(socket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(!isFlag) {
				while((timeStr=br.readLine())!=null) {
					System.out.println(timeStr);
					jlb_time.setText(timeStr);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("누구?");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("연결실패!");
		}
	}
//	public static void main(String[] args) {		//단위테스트 끝났으면 빠이~~~ 나는 P_AdrressBook2한테 입양간다.
//		TimeClient tc = new TimeClient();
//		tc.start();
//	}

}
