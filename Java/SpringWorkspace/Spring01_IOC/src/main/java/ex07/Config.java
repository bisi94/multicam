package ex07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //���� Ŭ������ ȯ�漳������ ����ϰڴٴ� �ǹ�
public class Config { //=> �����̳�
	
	//Emp bean�� ��ȯ�ϴ� �޼ҵ� �����ϱ� id�� emp1
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
	
	//Member���� ��ȯ�ϴ� �޼��� �����ϱ� �� id�� member�ּ���
	@Bean
	public Member member() {
		return new Member();
	}
	
	//ServiceImpl���� ��ȯ�ϴ� �޼��� �����ϱ� �� id�� service�ּ���
	@Bean
	public ServiceImpl service() {
		return new ServiceImpl();
	}
	
}/////////////////////















