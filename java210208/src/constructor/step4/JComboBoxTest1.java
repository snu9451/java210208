package constructor.step4;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

//오라클 연동 없이 콤보박스 대/중/소분류를 만들어보자
//생성자 파라미터 활용 연습
/*
[★인스턴스화 이전에 반드시 <생성자>를 확인할 것! - API(Application Programming Interface)를 통해서★]
new JFrame();
new JFrame("타이틀");
----------------------
new JComboBox();
new JComboBox(String[]);
new JComboBox(String);
----------------------
대분류가 결정되어야 중분류가 결정되고, 중분류가 결정되어야 소분류가 결정된다.
따라서 중분류와 소분류는 미리 설정할 수 없다. Default를 깔아야 한다.
*/
public class JComboBoxTest1 extends JFrame implements ItemListener {
	//선언부
	JPanel 		jp_north 		= new JPanel();
	JComboBox	jcb_top			= null;
	JComboBox	jcb_mid			= null;
	JComboBox	jcb_bottom		= null;
	String 		totals[] 		= {"전체"};
	String		top				= null;	//가전|컴퓨터|모바일
	String		mid				= null;	//주방가전|생활가전|계절가전
	String		bottom			= null;	//냉장고|전기밥솥|에어프라이어
	String 		top_item[] 		= {"전체", "가전", "컴퓨터", "모바일"};
	String 		mid_item0[] 	= {"전체","주방가전","생활가전","계절가전"};
	String 		mid_item1[] 	= {"전체","노트북","브랜드PC","모니터"};
	String 		mid_item2[] 	= {"전체","태블릿","휴대폰","스마트워치"};
	String 		bottom_item0[] 	= {"전체","냉장고","전기밥솥","에어프라이어"};
	String 		bottom_item1[] 	= {"전체","세탁기","옷건조기","청소기"};
	String 		bottom_item2[] 	= {"전체","온수매트","선풍기","에어컨"};
//	String[] combo_default_jung = {"전체"};
//	String[] combo_default_so = {"전체"};
//	JComboBox<String> jcb_de = new JComboBox<>(combo_de);
//	JComboBox<String> jcb_default_jung = new JComboBox<>(combo_default_jung);
//	JComboBox<String> jcb_default_so = new JComboBox<>(combo_default_so);

	//생성자
	public JComboBoxTest1(){
		super("대중소분류 실습");
		jp_north 	= new JPanel();
		jcb_top 	= new JComboBox(top_item);
		jcb_mid 	= new JComboBox(totals);
		jcb_bottom 	= new JComboBox(totals);
		initDisplay();
//		super("대중소분류 실습 2021");	//생성자의 가장 첫 부분에 와야 함★★★★★
//		initDisplay();
//		super("대중소분류 실습 2021");	//Error
		
	}
	
	//화면 처리부
	public void initDisplay() {
		jcb_top.addItemListener(this);
		jcb_mid.addItemListener(this);
		jcb_bottom.addItemListener(this);
		jp_north.setBackground(Color.orange);
		jp_north.add(jcb_top);
		jp_north.add(jcb_mid);
		jp_north.add(jcb_bottom);
//		jp_north.add(jcb_de);
//		jp_north.add(jcb_default_jung);
//		jp_north.add(jcb_default_so);
//		jcb_de.addItemListener(this);
//		jcb_default_jung.addItemListener(this);
//		jcb_default_so.addItemListener(this);
		this.add("North", jp_north);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	
	//메인 메소드 선언
	public static void main(String[] args) {
		new JComboBoxTest1();
	}

	//ItemListener라는 인터페이스(Interface)에 정의된 '추상메소드'이다.
	//어떤 컴포넌트(component)에 사용될지 알 수 없으므로 구현할 수 없다. 따라서 구현체 클래스에서 <재정의>하여 사용한다.
	//인터페이스는 보통 <공통 메소드>를 정의하여 <재사용성>과 <다형성>을 지원한다.
	//결합도를 낮춰 단위테스트 및 통합테스트를 가능하게 하면서도 재사용성을 높이므로 중요한 개발방법이다.
	@Override
	public void itemStateChanged(ItemEvent ie) {	//콜백 메소드이다. 시스템 레벨에서 이벤트가 감지되었을 때, 호출되어진다.
		//이벤트가 감지된 컴포넌트(component)의 주소번지를 얻어서 obj에 담는다.
		System.out.println(ie.getItem());
		Object obj = ie.getSource();
		//이벤트 감지된 곳이 혹시 콤보박스의 대분류니?
		if(obj == jcb_top) {
//			if(ie.getStateChange() == 0) {}	//직관적이지가 않다. BAD!
//			if(ie.getStateChange() == ItemEvent.SELECTED) {	//<타입.속성명>으로 표현되는 것은 '하나(일체)'이다.	//ItemEvent.SELECTED=1; ItemEvent.DESELECTED=2
				top = top_item[jcb_top.getSelectedIndex()];	//0: 전체, 1: 가전, 2: 컴퓨터, 3: 모바일
				jcb_mid.removeAllItems(); 					//기존에 totals에 있던 항목 전체를 삭제하고, 새로운 아이템으로 변경할 준비를 함.
				if("전체".equals(top)) {
					jcb_mid.addItem(totals[0]);
				}
				else if("가전".equals(top)) {
					for(int i=0; i<mid_item0.length; i++) {
						jcb_mid.addItem(mid_item0[i]); 	//String도 Object이다.
					}
				}
//				if(top.equals("가전")) {}						//이렇게 쓰지 않도록 하자. NullPointerException 위험이 있음. 상수를 앞에 쓰자. 적어도 ""는 NULL이 아님.
				else if("컴퓨터".equals(top)) {
					for(int i=0; i<mid_item1.length; i++) {
						jcb_mid.addItem(mid_item1[i]);
					}
				}
				else if("모바일".equals(top)) {
					for(int i=0; i<mid_item2.length; i++) {
						jcb_mid.addItem(mid_item2[i]);
					}
				}
			}
//		}
		if(obj == jcb_mid) {
			if(ie.getStateChange() == ItemEvent.SELECTED) {
				mid = mid_item0[jcb_mid.getSelectedIndex()];
				if("가전".equals(top)) {
					jcb_bottom.removeAllItems();
					if("전체".equals(mid)) {
						jcb_bottom.addItem(totals[0]);
					}
					else if("주방가전".equals(mid)) {
						for(int i=0; i<bottom_item0.length; i++) {
							jcb_bottom.addItem(bottom_item0[i]);
						}
					}
					else if("생활가전".equals(mid)) {
						for(int i=0; i<bottom_item1.length; i++) {
							jcb_bottom.addItem(bottom_item1[i]);
						}
					}
					else if("계절가전".equals(mid)) {
						for(int i=0; i<bottom_item2.length; i++) {
							jcb_bottom.addItem(bottom_item2[i]);
						}
					}
				}
			}
		}
//		if((String)e.getItem() == "전체" && e.getSource() == jcb_de) {
//			jcb_default_jung.removeAllItems();
//			jcb_default_jung.addItem("전체");
//		}
//		//////////////////////대분류 선택 시 소분류 콤보박스 설정//////////////////////
//		else if((String)e.getItem() == "가전" && e.getSource() == jcb_de) {
//			
//			jcb_default_jung.removeAllItems();
//			jcb_default_jung.addItem("전체");
//			jcb_default_jung.addItem("주방가전");
//			jcb_default_jung.addItem("생활가전");
//			jcb_default_jung.addItem("계절가전");
//			
//		}
//		else if((String)e.getItem() == "컴퓨터") {
//			jcb_default_jung.removeAllItems();
//			jcb_default_jung.addItem("전체");
//			jcb_default_jung.addItem("노트북");
//			jcb_default_jung.addItem("조립PC");
//			jcb_default_jung.addItem("모니터");
//		}
//		else if((String)e.getItem() == "모바일") {
//			jcb_default_jung.removeAllItems();
//			jcb_default_jung.addItem("전체");
//			jcb_default_jung.addItem("휴대폰");
//			jcb_default_jung.addItem("태블릿");
//			jcb_default_jung.addItem("스마트워치");
//		}
//		//////////////////////중분류 선택 시 소분류 콤보박스 설정//////////////////////
//		//가전
//		else if((String)e.getItem() == "주방가전" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("냉장고");
//			jcb_default_so.addItem("전기밥솥");
//			jcb_default_so.addItem("에어프라이어");
//		}
//		else if((String)e.getItem() == "생활가전" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("세탁기");
//			jcb_default_so.addItem("건조기");
//			jcb_default_so.addItem("청소기");
//		}
//		else if((String)e.getItem() == "계절가전" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("공기청정기");
//			jcb_default_so.addItem("에어컨");
//			jcb_default_so.addItem("가습기");
//		}
//		//컴퓨터
//		else if((String)e.getItem() == "노트북" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("애플 노트북");
//			jcb_default_so.addItem("AMD 노트북");
//			jcb_default_so.addItem("Intel 노트북");
//		}
//		else if((String)e.getItem() == "조립PC" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("게이밍PC");
//			jcb_default_so.addItem("일체형PC");
//			jcb_default_so.addItem("미니PC");
//		}
//		else if((String)e.getItem() == "모니터" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("게이밍 모니터");
//			jcb_default_so.addItem("4K UHD 모니터");
//			jcb_default_so.addItem("전문가용 모니터");
//		}
//		//모바일
//		else if((String)e.getItem() == "휴대폰" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("SKT");
//			jcb_default_so.addItem("KT");
//			jcb_default_so.addItem("LG U+");
//		}
//		else if((String)e.getItem() == "태블릿" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("안드로이드OS");
//			jcb_default_so.addItem("IOS");
//			jcb_default_so.addItem("윈도우OS");
//		}
//		else if((String)e.getItem() == "스마트워치" && e.getSource() == jcb_default_jung) {
//			jcb_default_so.removeAllItems();
//			jcb_default_so.addItem("전체");
//			jcb_default_so.addItem("스마트워치");
//			jcb_default_so.addItem("스마트밴드");
//			jcb_default_so.addItem("액세서리");
//		}
	}

}
