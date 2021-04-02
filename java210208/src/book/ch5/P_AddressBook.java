package book.ch5;

import javax.swing.JFrame;

public class P_AddressBook extends JFrame {	//부모창(P_AddressBook)	//창이 하나만 뜸
	//선언부
	
	
	//생성자
	public P_AddressBook() {
		initDisplay();
	}
	
	
	//화면처리부
	public void initDisplay() {
		this.setTitle("주소록 ver1.0");
		this.setSize(500, 400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		P_AddressBook aBook = new P_AddressBook();
		aBook.initDisplay();
	}

}
