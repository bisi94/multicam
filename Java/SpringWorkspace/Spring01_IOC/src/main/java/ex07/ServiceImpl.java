package ex07;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/* @Value :기본자료형, String 유형을 주입할 때 사용
 * @Autowired : byType으로 주입한다. 자료유형이 같은 객체가 있으면 주입한다. 찾는순서: 자료형> @Qualifier("빈이름")
 * @Resource  : byName으로 주입한다. pom.xml에 라이브러리를 등록해야 사용 가능하다.
 * @Inject : ==>pom.xml에 라이브러리를 등록해야 사용 가능하다. byType> @Qualifier
 * 
 * */

public class ServiceImpl implements Service {
	
	//property
	@Autowired //Emp객체를 주입해준다. Emp타입의 객체가 있으면 넣어준다
	@Qualifier("emp")
	private Emp emp;
	
	//@Inject //byType으로 주입 @Autowired와 비슷함
	
	@Resource(name="member")//byName으로 주입한다. 즉 빈의 이름이 member인 객체가 있으면 주입.
	private Member user;
	
	
	@Override
	public void info1() {
		System.out.printf("Name: %s\nDept: %s\nSalary: %d\n", emp.getName(), emp.getDept(), emp.getSal());
	}
	
	@Override
	public void info2() {
		user.showInfo();
	}

}
