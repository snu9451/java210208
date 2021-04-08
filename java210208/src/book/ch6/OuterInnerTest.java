package book.ch6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

class Outer {
	Logger	logger	= LogManager.getLogger(Outer.class);
	int		i		= 5;

	Outer() {
		System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "log4j.xml");
	}

	class Inner {
		Logger	logger	= LogManager.getLogger(Inner.class);
		int		j		= 10;

		public void go() {
			logger.info("go() 호출 성공");
		}
	}

	public void print() {
		logger.info("print() 호출 성공");
	}
}

public class OuterInnerTest {

	//static으로 하면 console에 아무것도 안 뜨는데 이유는?
//	static Logger logger = LogManager.getLogger(OuterInnerTest.class);
	Logger logger = LogManager.getLogger(OuterInnerTest.class);
	public void methodA() {
		logger.info("methodA() 호출 성공");
	}

	public static void main(String[] args) {
		// 로그에 대한 포맷(형식)을 잡아주는 log4j.xml
		System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "log4j.xml");
		OuterInnerTest oit = new OuterInnerTest();
		oit.methodA();
		Outer outer = new Outer();
		outer.print();
		Outer.Inner inner = outer.new Inner();
		inner.go();
	}

}
