package ex01;

public class HelloApp {

	public static void main(String[] args) {
		// MessageBeanEn��ü �����ؼ� sayHello()ȣ���غ���
		
		//MessageBeanEn mb=new MessageBeanEn();
		MessageBeanKo mb=new MessageBeanKo();
		
		mb.sayHello("Peter");
		
		/* HelloApp�� MessageBeanEn��ü�� ���(use)�Ѵ�
		 * =>HelloApp�� MessageBeanEn�� �����Ѵ�
		 * : �� �� �������ִ� ��ü���� ���յ��� �߿��ϴ�
		 *   ���յ��� ���ϸ� ���� ��ü�� ��ȯ�ϰ��� �� �� ������ �� �� �ִ�.
		 * 
		 * */

	}

}
