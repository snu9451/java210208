package ui.hanbit;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookManager extends JFrame {

	Logger				logger	= LogManager.getLogger(BookManager.class);
	// 선언부
//	JFrame				jf		= new JFrame();								// new Window();
//	static JFrame		bm		= new BookManager();						// new JFrame();
//	static BookManager	bm2		= new BookManager();						// new JFrame();
	static BookManager	bm3		= null;
	// 생성자

	public BookManager() {
//		bm.initDisplay();
//		jf.initDisplay();
//		initDisplay();
		// this가 가리키는 것: BookManager
//		this.setSize(500, 300);
		// super가 가리키는 것: JFrame
//		this.setSize(600, 700);
		// 우리는 현재 setVisible에 대해서 "오버라이딩" 하지 않았으므로, [부모의 메소드]가 호출되었다.
//		bm.setVisible(true);
//		bm2.setVisible(true);
//		bm3.setSize(600, 700);	//bm3 - null
//		bm3.setVisible(true);	//bm3 - null
		this.setSize(600, 700);	//위 두 코드에서는 null이 뜨는데, 지금은 화면이 뜬다. 이 차이를 아니? ★★★★★
		this.setVisible(true);
	}

	// 화면처리부
	public void initDisplay() {
		logger.info("initDisplay() 호출 성공");
	}

	public static void main(String[] args) {
//		new BookManager();
//		bm2.initDisplay();
		bm3 = new BookManager();
		bm3.initDisplay();
	}

}
