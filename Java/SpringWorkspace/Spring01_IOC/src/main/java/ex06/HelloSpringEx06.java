package ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringEx06 {

	public static void main(String[] args) {
		// FileSystemXmlApplicationContext ==> xml������
		// ClassPathXmlApplicationContext ==> xml������
		// AnnotationConfigApplicationContext ==> �ڹٷ� ������
		ApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class);
		Emp e1=ctx.getBean("e1", Emp.class);
		System.out.println(e1.getName()+"/"+e1.getDept()+"/"+e1.getSal());
		
		Emp e2=ctx.getBean("empInfo2", Emp.class);
		System.out.println(e2.getName()+"/"+e2.getDept()+"/"+e2.getSal());
		
		//service bean�� ����ؼ� info()�� ȣ���ϼ���
		System.out.println("**************************");
		Service service=ctx.getBean("service", Service.class);
		service.info();
		
		
	}

}
