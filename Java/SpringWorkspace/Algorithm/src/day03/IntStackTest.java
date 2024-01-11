package day03;

public class IntStackTest {
	public static void main(String[] args) {
		IntStack sk=new IntStack(5); //�ʱ�뷮 : 5
		sk.push(10);
		sk.push(20);
		sk.push(30);
		sk.push(40);
		sk.push(50);
		
		System.out.println(sk.peek()); //����� ��� : 50
		int delVal=sk.pop(); //����� �����
		System.out.println("delVal : "+delVal); 
		System.out.println(sk.peek()); // 40
		System.out.println("���ÿ� ����� �����ͼ�1: "+sk.size());
		sk.pop();
		System.out.println("���ÿ� ����� �����ͼ�2: "+sk.size());
		sk.printStack();
		
		sk.clear();//���� ����
		System.out.println("sk.empty() : "+sk.empty());
		System.out.println("sk.isFull() : "+sk.isFull());
		
		for(int i=1; i<6; i++) {
			sk.push(i*100);
		}
		System.out.println("sk.empty() 2: "+sk.empty());
		System.out.println("sk.isFull() 2: "+sk.isFull());
		
		System.out.println(sk.peek());
		
		int idx=sk.search(200);
		System.out.println("200�� idx: "+idx);
		
		sk.printStack();
		sk.pop();
		sk.pop();
		sk.pop();
		System.out.println("-----------------------");
		sk.printStack();
		
	}
}
