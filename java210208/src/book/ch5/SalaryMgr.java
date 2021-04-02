package book.ch5;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.vo.DeptVO;
import com.vo.EmpVO;

public class SalaryMgr implements ActionListener {	//Salary Manager(급여 관리 프로그램)를 만들어보자~!
//선언부-생성자-화면처리부; 크게 세 부분으로 나누고 시작하는 연습하자!!
	//선언부	--선언부에서는 선언만 해보자.
	JFrame				jf_sal			= null;	//이전에는 한 번에 인스턴스화 했는데, 이번에는 선언과 생성을 분리해보자.(차이: 메모리로딩/점유)
	//DefaultTableModel(DataSet역할: data[][], header[])> 이것이 JTable과 합쳐졌을 때 비로소 테이블로서의 역할을 함
	String				cols[]		    = {"사원명","부서명"};	//header
	String 				data[][]	    = new String[0][2];	//테이블 안에 header 값이 포함되어 있다. (0행에 header들어감)
	JTable 				jtb_sal		    = null;				//화면, 양식, 폼(form)을 그린다.
	DefaultTableModel 	dtm_sal		    = null;
	JScrollPane			jsp_sal		    = null;
	JTableHeader		jth_sal		    = null;
	JButton				jbtns[]			= null;
	JButton				jbtn			= null;
	String				jbtns_label[]	= {"조회","입력","수정","삭제","종료"};	//버튼에 들어갈 내용을 담음
	JPanel				jp_north		= null;
	
	//생성자	//생성자도 메소드 오버로딩의 규칙을 준수한다. 즉, 파라미터 개수나 타입이 다를 수 있다.
	public SalaryMgr() {	//학습목표: '생성자의 목적은 전역변수의 초기화다'라는 것을 몸으로 익히기.
		jf_sal	 	= new JFrame();
		//테이블 안의 면(cell)에 데이터를 매칭시키기 위해 필요한 클래스의 생성임(DataSet의 역할을 함; SELECT문 또는 저장 프로시저와 연계할 클래스)
		//웹(web)에서는 JSON의 역할을 수행하는 클래스.
		dtm_sal		= new DefaultTableModel(data,cols);
		//테이블의 양식을 작성하는 클래스 생성임.
		jtb_sal	 	= new JTable(dtm_sal);
		//JTable에 스크롤바를 생성해줄 속지 클래스 생성임.
		jsp_sal	 	= new JScrollPane(jtb_sal);
		//테이블의 헤더(header)를 그려줄 클래스 생성임.
		jth_sal	 	= jtb_sal.getTableHeader();
		jp_north	= new JPanel();
		jp_north.setLayout(new GridLayout(1, 4, 3, 3));
		jbtns		= new JButton[jbtns_label.length];
		for(int i=0; i<jbtns_label.length; i++) {
			jbtn 	 = new JButton(jbtns_label[i]);
			jbtns[i] = jbtn;	//버튼 객체 배열과 동기화 시키기
			jp_north.add(jbtn);
			//이벤트 소스와 이벤트 처리 핸들러 메소드 매칭
			jbtns[i].addActionListener(this);	//this는 현재 활성화된 객체의 주소번지(즉, SalaryMgr의 주소번지)
		}
		initDisplay();
	}
	
	//화면처리부
	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
		jth_sal.setBackground(Color.green);
		//컬럼(column)의 순서가 바뀌지 않도록 설정함.
		jth_sal.setReorderingAllowed(false);
		jf_sal.add("North",jp_north);
		jf_sal.add("Center",jsp_sal);
		jf_sal.setTitle("급여 명세서");
		jf_sal.setSize(400,300);
		jf_sal.setVisible(true);
	}
	
	//main
	public static void main(String[] args) {
		new SalaryMgr();
	}
	
	//급조한 로직
	public void getEmpdetail(int pempno) {
		EmpVO eVO	= new EmpVO();
		eVO.setEname("이순신");
		DeptVO dVO	= new DeptVO();
		dVO.setDname("개발1팀");
		eVO.setdVO(dVO);	//오늘의 핵심코드(issue)
		/*
		//Vector: java.util에서 제공되는 클래스로, 타입에 대한 제약이 없고 늘었다 줄었다 할 수 있다.
		//연관 있는 레코드(record)를 한 번에 밀어 넣기 위해서 사용한다.
		Vector oneRow	= new Vector();
		oneRow.addElement(eVO.getEname());
		oneRow.addElement(eVO.getdVO().getDname());	//두 번 접근(도트 연산자 두 개)
		dtm_sal.addRow(oneRow);
		*/
		//row를 추가하는 작업
		
		Vector oneRow = new Vector();
		oneRow.addElement("");
		oneRow.addElement("");	//column이 2개라서 두 번 작성
		dtm_sal.addRow(oneRow);
		
		for(int r=0; r<1; r++) {		//row > r
			//for(int c=0; c<2; c++) {	//column > c
			//bound error가 발생하는 이유: setValueAt이 작동하도록 하려면 그 만한 표가 생성되어 있어야 함
			//예를 들어서, String data[][] = new String[0][2];이 아닌 String[1][2];이면 한 줄 추가 가능함
			dtm_sal.setValueAt(eVO.getEname(), r, 0);	// 값을 하나씩 넣어야 함(효율적이지는 X)
			dtm_sal.setValueAt(eVO.getdVO().getDname(), r, 1);	// 값을 하나씩 넣어야 함(효율적이지는 X)
			//}
	}////////////////////////////////end of getEmpDetail

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj		= ae.getSource();			//버튼의 주소번지를 출력함 (ex- @abcd1234)
		String command	= ae.getActionCommand();	//버튼의 <라벨값>을 출력함
		//종료버튼이 눌러졌는가?
		if("종료".equals(command)) {
		//if(jbtns[4]==obj){	//이렇게 쓰는 건 윗줄보다 좋지 않다. 4라는 숫자는 직관적이지 않기 때문에.
			//자바 가상머신과의 연결고리를 끊어줌. 가비지 컬렉터에 의해 Candidate 상태로 빠짐
			System.out.println("종료버튼 이벤트 감지됨.");
			System.exit(0);
			//System.gc();	//쓰레기값을 청소하는 작업. 호출하더라도 즉시 처리되지 않고 스레드(thread)에 의해 순서대로 처리됨.
		}
		else if("조회".equals(command)) {
			System.out.println("조회버튼 이벤트 감지됨.");
			getEmpDetail(7566);
		}//////////////////////////////////////end of actionPerformed
	}
}
