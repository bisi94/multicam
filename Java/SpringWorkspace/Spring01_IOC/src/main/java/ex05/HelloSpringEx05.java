package ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpringEx05 {

	public static void main(String[] args) {
		String config="src/main/java/ex05/emp.xml";
		ApplicationContext ctx
		=new FileSystemXmlApplicationContext(config);
		
		Emp e2=ctx.getBean("e2", Emp.class);
		e2.info1();
		System.out.println("--------------");
		e2.info3();
		
		System.out.println("--------------");
		ctx.getBean("e3", Emp.class).info3();
		
		System.out.println("--------------");
		ctx.getBean("e4", Emp.class).info3();
		
		System.out.println("--------------");
		ctx.getBean("e5", Emp.class).info4();
	}

}
