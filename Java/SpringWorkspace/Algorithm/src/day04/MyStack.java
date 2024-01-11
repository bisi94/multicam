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
		Node<T> newNode=new Node(item);//���� ������ ���
		newNode.next=top;//����⿡ �ִ� ���� ����� �ּҰ��� newNode�� next�� �Ҵ�
		top=newNode;//top�� ���� ���� ����� �ּҰ��� �Ҵ�
	}//-------------------------
	public T pop() {
		if(top==null) throw new EmptyException();
		T delData=top.data;
		top=top.next;//top.next�� ��������� �ּҰ��� ������ ����
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
