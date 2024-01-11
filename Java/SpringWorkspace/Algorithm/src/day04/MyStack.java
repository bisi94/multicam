package day04;

public class MyStack<T> {
	
	private Node<T> top=null;
	
	class Node<T>{
		private T data;
		private Node<T> next=null;
		
		public Node(T data) {
			this.data=data;
		}
		
	}//inner class//////////
	
	public void push(T item) {
		Node<T> newNode=new Node(item);//새로 들어오는 노드
		newNode.next=top;//꼭대기에 있던 이전 노드의 주소값을 newNode의 next에 할당
		top=newNode;//top에 새로 들어온 노드의 주소값을 할당
	}//-------------------------
	public T pop() {
		if(top==null) throw new EmptyException();
		T delData=top.data;
		top=top.next;//top.next는 이전노드의 주소값을 가지고 있음
		return delData;
	}//-------------------
	
	public T peek() {
		if(top==null) throw new EmptyException();
		return top.data;
	}//---------------------
	
	public boolean isEmpty() {
		return top==null;
	}//-----------------------

}/////////////////////////////////
