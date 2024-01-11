package ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		//������ �����̳ʸ� ���� �ʿ��� ��ü�� DL(Dependency Lookup) �غ���
		String config="src/main/java/ex03/applicationContext.xml";
		
		//������ �����̳� ����
		ApplicationContext ctx=new FileSystemXmlApplicationContext(config);
		
		//�ʿ��� ��ü�� lookup�ؼ� ã�´�
		MessageBean mb=(MessageBean)ctx.getBean("mbKo");
		mb.sayHello("Spring");
		
	}

}