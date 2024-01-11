package ex06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //���� Ŭ������ ȯ�漳������ ����ϰڴٴ� �ǹ�
public class Config { //=> �����̳�
	
	//�������� �⺻������ bean(��ü)�� ���ϰ�ü(Singletone)�� �����ؼ� �����Ѵ�
	@Bean(name="e1") //<bean id="e1" class="ex06.Emp"/>�� ����
	//@Scope(value = "singleton") //����Ʈ==> ���� ��ü�� ���
	@Scope(value = "prototype")
	public Emp empInfo() {
		
		return new Emp("Ward", "Sales", 3500);
	}
	
	
	//@Bean�� name�Ӽ��� ���� ������ �޼ҵ� �̸��� Bean�� �̸��� ��
	@Bean //<bean id="empInfo2" class="ex06.Emp"/> �� ����
	public Emp empInfo2() {
		Emp e=this.empInfo();
		e.setName("Scott");
		e.setDept("Operation");
		e.setSal(4500);
		return e;
	}
	
	@Bean
	public ServiceImpl service() {
		//Emp��ü�� Injection => setter injection
		ServiceImpl svc=new ServiceImpl();
		svc.setEmp(this.empInfo2());
		
		return svc;
	}
}















