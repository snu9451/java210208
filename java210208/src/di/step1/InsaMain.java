package di.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsaMain {

	public static void main(String[] args) {
		ApplicationContext context2
		= new ClassPathXmlApplicationContext
			("di\\step1\\insaBean.xml");
		InsaList insaList = (InsaList)context2.getBean("insa");
		System.out.println(insaList);
		System.out.println(insaList.insaBean);
		for(String s: insaList.insaBean) {
			System.out.println(s);
		}
		InsaList insaList2 = new InsaList();
		System.out.println(insaList2);
		System.out.println(insaList2.insaBean); 	//null찍힘 - 객체주입 못 받고 있다...
	}

}
