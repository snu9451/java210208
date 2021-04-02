package com.pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener: 엄밀히 클래스는 아니고 이벤트 처리를 담당하는 인터페이스이다.
public class SalaryMgrEventHandler implements ActionListener{
	SalaryMgrView smView = null;
	//new ... 하면 복제본이 생기므로, 하지 않는다. 그레야 뭔본을 바꿀 수 있음.
	public SalaryMgrEventHandler(SalaryMgrView smView) {
		this.smView = smView;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj		= ae.getSource();			//버튼의 주소번지를 출력함 (ex- @abcd1234)
		String command	= ae.getActionCommand();	//버튼의 라벨값을 출력함
		//종료버튼이 눌러졌는가?
		//if("종료".equals(command)) {
		if(smView.jbtns[4]==obj){	//이렇게 쓰는 건 윗줄보다 좋지 않다. 4라는 숫자는 직관적이지 않기 때문에.
			//자바 가상머신과의 연결고리를 끊어줌. 가비지 컬렉터에 의해 Candidate 상태로 빠짐
			System.out.println("종료버튼 이벤트 감지됨.");
			System.exit(0);
			//System.gc();	//쓰레기값을 청소하는 작업. 호출하더라도 즉시 처리되지 않고 스레드(thread)에 의해 순서대로 처리됨.
		}
		else if("조회".equals(command)) {
			System.out.println("조회버튼 이벤트 감지됨.");
			smView.smL.getEmpDetail(7566);
		}
	}
}
