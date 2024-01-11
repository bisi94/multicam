package day04;

public class MyQueueTest {

	public static void main(String[] args) {
		MyQueue q=new MyQueue(5);
		
		q.enque("Hello");
		q.enque("Dog");
		q.enque("Cat");
		System.out.println(q.peek());//Hello
		q.deque();//front++
		System.out.println(q.peek());//Dog
		System.out.println(q.size());
		
		System.out.println(q.deque());//Dog
		System.out.println(q.peek());//Cat
		System.out.println(q.size());//1
		
		q.enque("Hi");
		q.enque("Bye");
		q.enque("Duck");
		System.out.println(q.size());//4
		
		q.printQueue();//Cat,Hi,Bye,Duck
		

	}

}
