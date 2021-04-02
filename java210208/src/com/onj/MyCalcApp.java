package com.onj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCalcApp implements ActionListener {

	// 생성자
	public MyCalcApp() {
		initDisplay();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	// main메소드
	public static void main(String[] args) {
		new MyCalcView();
	}
	
}
