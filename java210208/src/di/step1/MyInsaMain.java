package di.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyInsaMain {

	public static void main(String[] args) {
		ApplicationContext context
		= new ClassPathXmlApplicationContext
			("di\\step1\\insaBean.xml");
		InsaMap insaMap = (InsaMap)context.getBean("insaMap");
		System.out.println(insaMap);
		System.out.println(insaMap.mapBean);
		for(String s: insaMap.mapBean.values()) {
			System.out.println(s);
		}
	}

}
