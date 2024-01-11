package ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		String config = "src/main/java/ex04/appContext.xml";
		
		//스프링 컨테이너
		ApplicationContext ctx = new FileSystemXmlApplicationContext(config);
		
		//DL
		MessageBean mb1 =ctx.getBean("mb2", MessageBean.class);
		mb1.sayHello();
		mb1.sayHi("뉴진스","방탄소년단","르세라핌");
		System.out.println("***************************");
		
		MessageBean mb3=(MessageBean)ctx.getBean("mb3");
		mb3.sayHello();
		mb3.sayHi("M");
		
	}

}
