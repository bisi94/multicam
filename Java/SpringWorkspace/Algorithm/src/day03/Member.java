package day03;
//��ü�� ���� �� �ֵ��� ����� ���ؼ��� Comparable�������̽��� ��ӹ޾� compareTo()�޼��带 �������ؾ� �Ѵ�
//�Ǵ� Comparator�������̽��� ��ӹ޾� compare()�޼��带 �������ؾ� �Ѵ�.
/* Comparable�� Comparator�� ���̴�?
[1] Comparable�� compareTo(T o) �޼ҵ� 
[2] Comparator�� compare(T o1, T o2) �޼ҵ�

- Comparable�� "�ڱ� �ڽŰ� �Ű����� ��ü�� ��"�ϴ� ���̰�, 
- Comparator�� "�� �Ű����� ��ü�� ��"�Ѵٴ� ���̴�.

���� �����ڸ�, Comparable�� �ڱ� �ڽŰ� �Ķ���ͷ� ������ ��ü�� ���ϴ� ���̰�, 
Comparator�� �ڱ� �ڽ��� ���°� ��� ������� �Ķ���ͷ� ������ �� ��ü�� 
���ϴ� ���̴�. ��, ���������� ���Ѵٴ� �� ��ü�� ������, �� ����� �ٸ��ٴ� ���̴�.
* */
public class Member implements Comparable<Member> {
	private String name;
	private int age;
	private int height;
	
	public Member() {
		
	}
	public Member(String n, int a, int h) {
		//setter���� : ���� ���� �� Member(String n, int a, int h)������ �����ڸ� ȣ��
		name=n; age=a; height=h;
	}
	
	//ȸ���� ���̸� �������� �������� ����
	//this.age�� �Ű������� ���� obj.age�� ���ٸ� 0�� ��ȯ
	@Override
	public int compareTo(Member obj) {
		//����� ��ȯ�ϸ� �񱳴����� �ڸ��� �ٲ۴�. ������ �ε�
		if(this.age==obj.age) {
			return 0;
		}else if(this.age > obj.age) { //this.age > obj.age�϶� �����ȯ => �������� ����
			return 1;
		}else {
			return -1;
		}
		
		//this.age-obj.age; => �������� (this�� ������ ����ü)
		
		//�̸� ������ ������ �����ϰ� �ʹٸ� => return this.name.compareTo(obj.name);
		//return this.name.compareTo(obj.name)*-1;//�̸� ������ ������������ ����
		
	}//----------------------------------------
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member user=(Member)obj;
			if(this.name.equals(user.name) 
				&& this.age==user.age
				&& this.height==user.height) {
				return true;
			}
				
			
		}//if--------------------------
		return false;
	}//--------------------------------
	
	@Override
	public String toString() {//getter����
		return "Member [name=" + name + ", age=" + age + ", height=" + height + "]";
	}


}//////////////////////









