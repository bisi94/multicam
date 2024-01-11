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
		System.out.println("삭제된 데이터: "+item);
		System.out.println("sk1의 현재 top: "+sk1.peek());
		sk1.pop();
		sk1.pop();
		System.out.println("sk1의 현재 top2: "+sk1.peek());
		System.out.println(sk1.isEmpty());
		sk1.pop();
		System.out.println(sk1.isEmpty());
		if(!sk1.isEmpty())
			sk1.pop();
		System.out.println("----------------");
		//Integer유형을 저장할 MyStack을 생성해서 정수값 3개 저장해보세요
		MyStack<Integer> sk2=new MyStack<>();
		sk2.push(10);
		sk2.push(20);
		sk2.push(30);
		//반복문 이용해서 sk2에 저장된 값을 출력해보기
		while(!sk2.isEmpty()) {
			Integer val=sk2.pop();
			System.out.println(val);
		}
		
		//Member객체 3개 생성해서 스택에 저장해보세요
		//그런뒤 반복문 이용해서 회원정보를 한꺼번에 출력해 보세요
		Member m1=new Member("이길동",22,175);
		Member m2=new Member("최민영",23,163);
		Member m3=new Member("고영자",20,168);
		
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


