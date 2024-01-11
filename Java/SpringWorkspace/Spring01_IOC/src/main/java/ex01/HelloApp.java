package ex01;

public class HelloApp {

	public static void main(String[] args) {
		// MessageBeanEn객체 생성해서 sayHello()호출해보기
		
		//MessageBeanEn mb=new MessageBeanEn();
		MessageBeanKo mb=new MessageBeanKo();
		
		mb.sayHello("Peter");
		
		/* HelloApp이 MessageBeanEn객체를 사용(use)한다
		 * =>HelloApp이 MessageBeanEn에 의존한다
		 * : 이 때 의존성있는 객체들의 결합도가 중요하다
		 *   결합도가 강하면 향후 객체를 교환하고자 할 때 문제가 될 수 있다.
		 * 
		 * */

	}

}
