package di.step1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
/*
 * spring-core.jar는 IoC를 제공하는 역할을 하였음.
 * IoC는 Inversion of conversion의 약자이다. 번역하면 역제어 또는 제어 역행이라고 한다.
 * 최근에 사용되는 모든 컨테이너들이 공통으로 사용하고 있는 개념이다.
 * 기존 방식
 * 자바 기반으로 어플리케이션을 개발할 때 자바 객체를 생성하고 서로 간의 의존관계를 연결 시키는 작업에 대한 제어권은 보통 개발되는 어플리케이션에 있음.
 * 
 * 문제 제기
 * 컴포넌트 간의 결합도가 높아서 컴포넌트의 확장 및 재사용이 어려운 문제점이 발생함.
 * 
 * 해결 방법은?
 * IoC사용
 * 제어권이 Container에게 넘어가 객체의 생명 주기를 Container가 전담하게 됨.
 * 
 * 해결내용
 * 컴포넌트 간의 결합도가 낮아져 컴포넌트의 재사용 및 확장이 쉽고 컴포넌트의 의존관계 변경이 쉬움.
 * 
 */
public class HelloMain {

	public static void main(String[] args) {
		//ClassPathXmlApplicationcontext는 ApplicationContext의 구현체 클래스임.
		//생성자의 파라ㅏ미터로 xml문서를 스캔한다.
		ApplicationContext context	//MN; 'javadoc없음'으로 떠서 API 볼 수가 없다.
			= new ClassPathXmlApplicationContext
				("di\\step1\\helloBean.xml");
		ApplicationContext context2
			= new ClassPathXmlApplicationContext
				("di\\step1\\sonataBean.xml");
		Sonata himCar = (Sonata)context2.getBean("himCar");
		Sonata herCar = (Sonata)context2.getBean("herCar");
		System.out.println(himCar);
		System.out.println(herCar);
		Resource resource = new FileSystemResource("E:\\git_java210208\\java210208\\java210208\\src\\di\\step1\\helloBean.xml");	//상대경로를 줄 수 없음. 전체 경로를 다 적어야만 함.
		BeanFactory factory = new XmlBeanFactory(resource);
		HelloBean helloBean2 = (HelloBean)factory.getBean("helloBean222");
		HelloBean helloBean = (HelloBean)context.getBean("helloBean222");	//캐스팅 연산자를 이용한 다운캐스팅
		System.out.println(helloBean.getGreeting("Hi~~"));
		System.out.println(helloBean2.getGreeting("Hi~~"));
	}

}
