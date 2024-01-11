package day03;

public class IntStackTest {
	public static void main(String[] args) {
		IntStack sk=new IntStack(5); //초기용량 : 5
		sk.push(10);
		sk.push(20);
		sk.push(30);
		sk.push(40);
		sk.push(50);
		
		System.out.println(sk.peek()); //꼭대기 출력 : 50
		int delVal=sk.pop(); //꼭대기 지우기
		System.out.println("delVal : "+delVal); 
		System.out.println(sk.peek()); // 40
		System.out.println("스택에 저장된 데이터수1: "+sk.size());
		sk.pop();
		System.out.println("스택에 저장된 데이터수2: "+sk.size());
		sk.printStack();
		
		sk.clear();//스택 비우기
		System.out.println("sk.empty() : "+sk.empty());
		System.out.println("sk.isFull() : "+sk.isFull());
		
		for(int i=1; i<6; i++) {
			sk.push(i*100);
		}
		System.out.println("sk.empty() 2: "+sk.empty());
		System.out.println("sk.isFull() 2: "+sk.isFull());
		
		System.out.println(sk.peek());
		
		int idx=sk.search(200);
		System.out.println("200의 idx: "+idx);
		
		sk.printStack();
		sk.pop();
		sk.pop();
		sk.pop();
		System.out.println("-----------------------");
		sk.printStack();
		
	}
}
