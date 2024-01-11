package day04;

import day03.Member;

public class MyStackTest {

	public static void main(String[] args) {
		MyStack<String> sk1=new MyStack<>();
		sk1.push("Java");
		sk1.push("HTML");
		sk1.push("SQL");
		sk1.push("Spring");
		System.out.println(sk1.peek());
		String item=sk1.pop();
		System.out.println("������ ������: "+item);
		System.out.println("sk1�� ���� top: "+sk1.peek());
		sk1.pop();
		sk1.pop();
		System.out.println("sk1�� ���� top2: "+sk1.peek());
		System.out.println(sk1.isEmpty());
		sk1.pop();
		System.out.println(sk1.isEmpty());
		if(!sk1.isEmpty())
			sk1.pop();
		System.out.println("----------------");
		//Integer������ ������ MyStack�� �����ؼ� ������ 3�� �����غ�����
		MyStack<Integer> sk2=new MyStack<>();
		sk2.push(10);
		sk2.push(20);
		sk2.push(30);
		//�ݺ��� �̿��ؼ� sk2�� ����� ���� ����غ���
		while(!sk2.isEmpty()) {
			Integer val=sk2.pop();
			System.out.println(val);
		}
		
		//Member��ü 3�� �����ؼ� ���ÿ� �����غ�����
		//�׷��� �ݺ��� �̿��ؼ� ȸ�������� �Ѳ����� ����� ������
		Member m1=new Member("�̱浿",22,175);
		Member m2=new Member("�ֹο�",23,163);
		Member m3=new Member("����",20,168);
		
		MyStack<Member> sk3=new MyStack<>();
		
		sk3.push(m1);
		System.out.println(sk3.peek());
		sk3.push(m2);
		System.out.println(sk3.peek());
		sk3.push(m3);
		System.out.println("--------------");
		
		while(!sk3.isEmpty()) {
			Member user=sk3.pop();
			System.out.println(user);
		}
		
		
		
		
	}

}


