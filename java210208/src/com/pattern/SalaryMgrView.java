package com.pattern;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*학습목표: 클래스 쪼개기를 통해 생성자에 대한 활용능력을 키워보자.
 * static 사용은 <생성자 공부>라는 목표에 맞지 않으므로 지양.
 * 클래스 쪼개기 연습을 위해서는 최소한 3개로 쪼개보자. (예를 들어, View계층-업무처리계층-EventHandler)
 * Q) 누가 누구를 인스턴스화 해야 하는가? (주입의 문제) (판단의 기준을 근거로 들 수 있어야 함)
 * Q) 생성자의 파라미터 자리에 뭘 써야 할까? (필요한 기초 지식: 생성자 오버로딩)
 *		ㄴSalaryMgrView -this
 *  	ㄴSalaryMgrHandler
 *  	ㄴSalaryMgrLogic
 * 3개의 클래스를 그림으로 표현한 뒤, 화살표를 통해서 <객체 주입 관계>를 완성해보자.
 */
public class SalaryMgrView {
	//선언부
	SalaryMgrEventHandler	smEH			= null;
	SalaryMgrLogic			smL				= null;
	JFrame					jf_sal			= null;
	String					cols[]		    = {"사원명","부서명"};
	String 					data[][]	    = new String[0][2];
	JTable 					jtb_sal		    = null;
	DefaultTableModel 		dtm_sal		    = null;
	JScrollPane				jsp_sal		    = null;
	JTableHeader			jth_sal		    = null;
	JButton					jbtns[]			= null;
	JButton					jbtn			= null;
	String					jbtns_label[]	= {"조회","입력","수정","삭제","종료"};
	JPanel					jp_north		= null;
   //생성자
   public SalaryMgrView() {
	  smEH		= new SalaryMgrEventHandler(this);	//this가 가리키는 것은 View
	  smL		= new SalaryMgrLogic(this);	//MN
      jf_sal    = new JFrame();
      dtm_sal   = new DefaultTableModel(data,cols);
      jtb_sal 	= new JTable(dtm_sal);
      jsp_sal 	= new JScrollPane(jtb_sal);
      jth_sal 	= jtb_sal.getTableHeader();
      jp_north 	= new JPanel();
      jp_north.setLayout(new GridLayout(1,4,3,3));	//new GridLayout() 여기서만 사용할 거라서 어디 담지 않았음
      jbtns 	= new JButton[jbtns_label.length];
      for(int i=0;i<jbtns_label.length;i++) {
         jbtn 		= new JButton(jbtns_label[i]);
         jbtns[i] 	= jbtn;
         jp_north.add(jbtn);
         jbtns[i].addActionListener(smEH);
      }
      initDisplay();
   }   
   //화면처리부		//화면을 통해 나타나는 화면의 색상, 제목, 크기 등을 시각적인 내용을 설정(혹은 초기화)하는 부분
   public void initDisplay() {
      System.out.println("initDisplay호출 성공");
      jth_sal.setBackground(Color.GREEN);
      jf_sal.add("North",jp_north);
      jf_sal.add("Center",jsp_sal);
      jf_sal.setTitle("급여 명세서");
      jf_sal.setSize(400,300);
      jf_sal.setVisible(true);
   }
   //main
   public static void main(String[] args) {
      new SalaryMgrView();
   }   
}	

