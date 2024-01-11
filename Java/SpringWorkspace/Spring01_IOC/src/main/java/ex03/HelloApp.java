package ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		//스프링 컨테이너를 통해 필요한 객체를 DL(Dependency Lookup) 해보자
		String config="src/main/java/ex03/applicationContext.xml";
		
		//스프링 컨테이너 생성
		ApplicationContext ctx=new FileSystemXmlApplicationContext(config);
		
		//필요한 객체를 lookup해서 찾는다
		MessageBean mb=(MessageBean)ctx.getBean("mbKo");
		mb.sayHello("Spring");
		
	}

}