package ex06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //현재 클래스를 환경설정으로 사용하겠다는 의미
public class Config { //=> 컨테이너
	
	//스프링은 기본적으로 bean(객체)을 단일객체(Singletone)로 생성해서 관리한다
	@Bean(name="e1") //<bean id="e1" class="ex06.Emp"/>와 동일
	//@Scope(value = "singleton") //디폴트==> 단일 객체를 사용
	@Scope(value = "prototype")
	public Emp empInfo() {
		
		return new Emp("Ward", "Sales", 3500);
	}
	
	
	//@Bean에 name속성을 주지 않으면 메소드 이름이 Bean의 이름이 됨
	@Bean //<bean id="empInfo2" class="ex06.Emp"/> 와 동일
	public Emp empInfo2() {
		Emp e=this.empInfo();
		e.setName("Scott");
		e.setDept("Operation");
		e.setSal(4500);
		return e;
	}
	
	@Bean
	public ServiceImpl service() {
		//Emp객체를 Injection => setter injection
		ServiceImpl svc=new ServiceImpl();
		svc.setEmp(this.empInfo2());
		
		return svc;
	}
}















