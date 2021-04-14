package ui.hanbit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

// West에 붙을 패널 클래스============================================================================================
class WestPanel1 extends JPanel {
	JButton	jbtn_login	= null;
	JButton	jbtn_search	= null;
	JButton	jbtn_yeme	= null;
	public WestPanel1() {
		initDisplay();
	}
	public void initDisplay() {
		// 생성부
		jbtn_login = new JButton("로그인");
		jbtn_search = new JButton("영화검색");
		jbtn_yeme = new JButton("영화예매");
		JButton[] jbtn_arr = { jbtn_login, jbtn_search, jbtn_yeme };
		// WestPanel의 버튼들에 actionListener 장착하기
		jbtn_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[로그인] 버튼 호출 성공");
			}
		});
		jbtn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[영화검색] 버튼 호출 성공");
			}
		});
		jbtn_yeme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[영화예매] 버튼 호출 성공");
			}
		});
		// WestPanel의 세부사항 설정
		this.setLayout(new GridLayout(10, 1, 0, 3));
		this.setBackground(Color.white);
		for (JButton jbtn : jbtn_arr) {
//			jbtn.setHorizontalAlignment(JButton.LEFT);	//글자가 정렬됨
			jbtn.setBackground(Color.white);
			this.add(jbtn);
		}

	}
}
// Center에 붙을 패널 클래스==========================================================================================
class CenterPanel1 extends JPanel {

	// Layout 구성: ①(JFrame) - ②JPanel - ③JScrollPane - ④JTable - ⑤JTable에 들어갈 데이터를 담는 DefaultTableModel
	// 생성 순서는 위와 반대로 진행 - 왜? 담아야 하니까.
//	String[]			cols			= { "순위", "영화명", "상영시간", "상영관", "잔여 좌석수" };
	String[]			cols			= { "순위", "포스터 & 영화명"};
	DefaultTableModel	dtm				= null;
	JTable				jtb				= null;
	JScrollPane			jsp				= null;
	
	// 테이블에 들어갈 항목과 관련된 선언
//	JLabel				jlb_geumja		= new JLabel("친절한 금자씨", geumja_image, JLabel.CENTER);
	Vector				oneMovie		= new Vector();
	String				image_path		= "src\\ui\\hanbit\\";
	ImageIcon			geumja_image	= new ImageIcon(image_path+"geumja.jpg");
	JLabel				jlb_movie_image	= null;

	// imsi 선언
	JButton				jbtn_add_movie	= null;
	
	// 생성자
	public CenterPanel1() {
		initDisplay();
	}

	public void initDisplay() {

		// CenterPanel의 가장 밑바닥에 있는 (상속된)패널의 Layout을 BorderLayout으로 변경
		this.setLayout(new BorderLayout());
		/***************************************************************
		 * [JTable 관련 알아두면 좋을만한 메소드]
		 * 1. 테이블의 컬럼 간 이동을 불가능하도록 설정
		 * 	jtb.getTableHeader().setReorderingAllowed(false);
		 * 2. 테이블의 컬럼 사이즈(width)를 변경할 수 없도록 설정
		 * 	jtb.getTableHeader().setResizingAllowed(false);
		 * 3. 테이블의 로우를 한 개만 선택할 수 있도록 설정
		 * 	jtb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 * 
		 * 4. 테이블의 로우 삭제하기
		 * 	- 하나의 로우만 삭제하기: jtb.removeRow(jtb.getSelectedRow());
		 * 	- 전체 로우 삭제하기: jtb.removeAll();
		 ***************************************************************/
		// [STEP1] 생성
		dtm = new DefaultTableModel(new Object[0][2], cols) {
			// 더블 클릭 시 Cell을 변경할 수 있는 것이 default이다. 그 설정을 변경하는 작업: false > true
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		// [STEP2] 테이블에 데이터모델 추가하기
		jtb = new JTable(dtm);

		/***********************************************
		 * @first_param: 다루고 있는 컬럼의 인덱스
		 ***********************************************/
//		TableColumnModel tc = jtb.getColumnModel();
//		for (int index = 0; index < jtb.getColumnCount(); index++) {
//			tc.getColumn(index).setCellRenderer(dcr);
//			System.out.println("랜더러 추가중");
//		}
		// [STEP3] 스크롤페인에 테이블 추가하기 및 스크롤 설정
		jsp = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
								 , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// 테이블 상세설정
		jtb.setPreferredSize(new Dimension(500, 1000));
		jtb.setOpaque(true);
		jtb.setBackground(Color.white);
		jtb.getTableHeader().setReorderingAllowed(false);
		jtb.getTableHeader().setBackground(Color.orange);
		// [STEP4] 패널에 스크롤 페인 넣기
		jbtn_add_movie = new JButton("영화 추가");
		jbtn_add_movie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[영화 추가] 버튼 호출됨");
				movieAdd();
			}
		});
		this.add("East", jbtn_add_movie);
		this.add("Center", jsp);
	}

	public void movieAdd() {
		while (dtm.getRowCount() > 0) {
			dtm.removeRow(0);
		}
		// 테이블에 들어갈 자료의 준비
		jlb_movie_image = new JLabel(geumja_image);
		oneMovie.add("1");
		oneMovie.add(jlb_movie_image);
//		System.out.println(jlb_movie_image.isDisplayable()); -- false
//		System.out.println(jlb_movie_image.isLightweight()); -- false
//		System.out.println(jlb_movie_image.isOpaque()); -- false
//		System.out.println(jlb_movie_image.isShowing()); -- false
		System.out.println(jlb_movie_image.isVisible());
		System.out.println(jlb_movie_image.isValidateRoot());
		System.out.println(jlb_movie_image.getIcon());
//		System.out.println(jlb_movie_image.isShowing());
//		System.out.println(jlb_movie_image.isShowing());
		dtm.addRow(oneMovie);
		// 테이블에  renderer 달아주기
		// 타입을 DefaultTableCellRenderer로 할 것인가? TableCellRenderer로 할 것인가? ==> 선택해야 함.
//		TableCellRenderer dtcr1 = new DefaultTableCellRenderer() {
//			@Override
//			public Component getTableCellRendererComponent
//			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//			{
//				Component comp = null;
//				if(column == 1) {
//					System.out.println("column 1");
//					comp = jlb_movie_image;
//				}
//				return comp;
//			}
//		};
//		jtb.getColumnModel().getColumn(1).setCellRenderer(dtcr1);
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
		{
			int x = 0;
			public Component getTableCellRendererComponent  // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus
					, int row, int column)
			{
				JLabel jlb 		= new JLabel();
//				if(i<3) {/////////////////
//	     		ImageIcon originIcon = new ImageIcon(imgPath+"lion11.png");  
	     		ImageIcon originIcon = new ImageIcon(image_path+"geumja.jpg");  
	    		//ImageIcon에서 Image를 추출
	    		Image originImg = originIcon.getImage(); 
	    		//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
	    		Image changedImg= originImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH );
	    		//새로운 Image로 ImageIcon객체를 생성
	    		ImageIcon Icon = new ImageIcon(changedImg);
	    	//		image.setImage(new ImageIcon(path+img).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	    	//png는 잘되고 gif는 안된다. 왜지?	
	    		jlb.setIcon(Icon);				
//	    		i++;
//				}//////////////////// table cell renderer는 editable한 jtextfield checkbox.... 상위의 클래스를 씀으로써 component
				return jlb;
			}
		};
		jtb.setRowHeight(geumja_image.getIconHeight());
		jtb.getColumn("순위").setCellRenderer(dcr);
	}

	// 테이블 column의 너비 자동조절
//	public void resizeColumnWidth(JTable table) {
//		final TableColumnModel columnModel = table.getColumnModel();
//		for (int column = 0; column < table.getColumnCount(); column++) {
//			int width = 2; // Min width
//			for (int row = 0; row < table.getRowCount(); row++) {
//				TableCellRenderer renderer = table.getCellRenderer(row, column);
//				Component comp = table.prepareRenderer(renderer, row, column);
//				width = Math.max(comp.getPreferredSize().width +1 , width); 
//			}
//			columnModel.getColumn(column).setPreferredWidth(width);
//		}
//	}

}
// 프레임과 메인메소드를 갖는 메인 클래스===================================================================================
public class MovieManager2 extends JFrame {

	JPanel		jp_center	= null;
	WestPanel1	wp			= null;
	CenterPanel1	cp			= null;

	public MovieManager2() {

		jp_center = new JPanel();
		wp = new WestPanel1();
		cp = new CenterPanel1();

		jp_center.setLayout(new BorderLayout());

		jp_center.add("West", wp);
		jp_center.add("Center", cp);
		this.add("Center", jp_center);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("영화검색기");
		this.setSize(800, 1000);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MovieManager2();
	}

}
