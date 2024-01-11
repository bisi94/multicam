package ex07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //현재 클래스를 환경설정으로 사용하겠다는 의미
public class Config { //=> 컨테이너
	
	//Emp bean을 반환하는 메소드 구현하기 id는 emp1
	@Bean(name="emp1")
	@Scope(value="prototype")
	public Emp getEmp() {
		
		return new Emp("Name", "Dept", 6000);
	}
	
	@Bean(name="emp2")
	public Emp getEmp2() {
		Emp e=this.getEmp();
		e.setName("Eman");
		return e;
	}
	
	//Member빈을 반환하는 메서드 구현하기 빈 id는 member주세요
	@Bean
	public Member member() {
		return new Member();
	}
	
	//ServiceImpl빈을 반환하는 메서드 구현하기 빈 id는 service주세요
	@Bean
	public ServiceImpl service() {
		return new ServiceImpl();
	}
	
}/////////////////////















