package ex07;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/* @Value :�⺻�ڷ���, String ������ ������ �� ���
 * @Autowired : byType���� �����Ѵ�. �ڷ������� ���� ��ü�� ������ �����Ѵ�. ã�¼���: �ڷ���> @Qualifier("���̸�")
 * @Resource  : byName���� �����Ѵ�. pom.xml�� ���̺귯���� ����ؾ� ��� �����ϴ�.
 * @Inject : ==>pom.xml�� ���̺귯���� ����ؾ� ��� �����ϴ�. byType> @Qualifier
 * 
 * */

public class ServiceImpl implements Service {
	
	//property
	@Autowired //Emp��ü�� �������ش�. EmpŸ���� ��ü�� ������ �־��ش�
	@Qualifier("emp")
	private Emp emp;
	
	//@Inject //byType���� ���� @Autowired�� �����
	
	@Resource(name="member")//byName���� �����Ѵ�. �� ���� �̸��� member�� ��ü�� ������ ����.
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
