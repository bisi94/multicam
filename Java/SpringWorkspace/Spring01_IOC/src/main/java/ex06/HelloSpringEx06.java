package ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringEx06 {

	public static void main(String[] args) {
		// FileSystemXmlApplicationContext ==> xml설정시
		// ClassPathXmlApplicationContext ==> xml설정시
		// AnnotationConfigApplicationContext ==> 자바로 설정시
		ApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class);
		Emp e1=ctx.getBean("e1", Emp.class);
		System.out.println(e1.getName()+"/"+e1.getDept()+"/"+e1.getSal());
		
		Emp e2=ctx.getBean("empInfo2", Emp.class);
		System.out.println(e2.getName()+"/"+e2.getDept()+"/"+e2.getSal());
		
		//service bean을 룩업해서 info()를 호출하세요
		System.out.println("**************************");
		Service service=ctx.getBean("service", Service.class);
		service.info();
		
		
	}

}
