package day04;

import java.util.Comparator;

//���ʸ� ��ũ�帮��Ʈ
/*head --->[A:next]--->[B:next]--->[C:next]-->[D:next]-->null
* 
* [1] ��尡 1�� �ִ��� �Ǻ���?: head --->[A:next]-->null
* 		  (head.next==null)?
* [2] ��尡 2�� �ִ��� �Ǻ���?: head --->[A:next]--->[B:next]--->null
* 		  (head.next.next==null)?
* [3] ������ ��尡 ����������� �Ǻ���?
*        (�����ѳ��.next==null)?
* */
public class MyLinkedList<T> {
	
	Node<T> head=null;
	Node<T> currentN=null;//���� ��带 ����

	static class Node<T>{
		T data;//������
		Node<T> next;//���� ����� �ּҰ��� ����
		
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}//inner class/////////
	//��带 �� �տ� �����ϴ� �޼���
	public void addFirst(T newData) {
		Node<T> ptr=head;//�Ӹ���带 �����ϴ� ������
		
		Node<T> newNode=new Node<>(newData, ptr);
		//head=newNode;
		//currentN=newNode;
		head=currentN=newNode;
	}//----------------------
	
	//���ο� ��带 �������� �����̴� �޼���
	public void addLast(T newData) {
		if(head==null) {//���� ��尡 ���ٸ�
			addFirst(newData);
		}else {
			Node<T> end=new Node(newData, null);//���� ���� �����
			Node<T> ptr= head;
			while(ptr.next!=null) {
				ptr=ptr.next;//��带 ��� Ž�� ==> ������ ��带 ����������
			}
			ptr.next=currentN = end;
		}
	}//----------------------
	
	//�Ӹ���带 �����ϴ� �޼���
	public void removeFirst() {
		if(head!=null) {
			head=currentN=head.next;//�ι�° ��带 �Ӹ����� �����
		}
	}//-----------------------------
	/*head --->[A:next]--->[B:next]--->[C:next]-->[D:next]-->null
	* 
	* [1] ��尡 1�� �ִ��� �Ǻ���?: head --->[A:next]-->null
	* 		  (head.next==null)?
	* [2] ��尡 2�� �ִ��� �Ǻ���?: head --->[A:next]--->[B:next]--->null
	* 		  (head.next.next==null)?
	* [3] ������ ��尡 ����������� �Ǻ���?
	*        (�����ѳ��.next==null)?
	* */
	//������带 �����ϴ� �޼���
	public void removeLast() {
		if(head!=null) {
			if(head.next==null) {//��尡 �ϳ��� �ִٸ� => �Ӹ���� ����
				removeFirst();
			}else {//��尡 ���� �� �ִ� ���
				Node<T> ptr=head;
				while(ptr.next.next!=null) {
					ptr=ptr.next;
				}
				ptr.next=null;
				currentN=ptr;
			}
		}//if-----
	}//------------------
	//head --->[A:next]--->[B:next]--->[C:next]-->[D:next]-->null
	//Ư�� ��带 �����ϴ� �޼���
	public void remove(Node<T> p) {
		if(head!=null) {
			if(p==head) {
				removeFirst();
			}else {
				Node<T> ptr=head;
				while(ptr.next!=p) {
					ptr=ptr.next;
					if(ptr==null) return;//������ ���µ� ã�����ߴٸ� ����
				}//while-----
				ptr.next=p.next;
				//������ ��� p�� ���� ��带 ã�Ҵٸ� p�� ���� �ּҰ��� ����Ʈ����� next�� �����Ѵ�
				currentN=ptr;
			}
		}//if-----
	}//--------------------------
	//������ ��带 �����ϴ� �޼���
	public void removeCurrentNode() {
		remove(currentN);
	}//-------------------------------
	//������ ��带 ���
	public void printCurrentNode() {
		System.out.println("-------------------");
		if(currentN==null) {
			System.out.println("������ ��尡 �����ϴ�");
		}else {
			System.out.println("������ ��� ->"+currentN.data);
		}
	}//-------------------------------
	//��ü ��带 ���
	public void printAllNode() {
		System.out.println("*******************");
		Node<T> ptr=head;//�Ӹ���忡�� ����
		while(ptr!=null && ptr.next!=null) {
			System.out.print(ptr.data+"->");//=> �����ϴ� �����͸� ���
			ptr=ptr.next;//��ĭ�� �̵�
		}//while----
		if(ptr!=null) {
			System.out.println(ptr.data);//������ ����� �����Ͱ� ���
		}
		
	}//---------------------------------
	
	//��ü ��带 �����ϴ� �޼���
	public void clear() {
		while(head!=null) {
			removeFirst();
		}
		currentN=null;
	}//----------------------------------
		
	//���� ��带 �ϳ� �������� �����Ű�� �޼���
	public boolean next() {
		if(currentN==null||currentN.next==null) {
			return false;
		}
		currentN=currentN.next;
		return true;
	}//-----------------------------
	
	//Ư�� ��带 �˻��ϴ� �޼���
	public T search(T obj, Comparator<T> comparator) {
		Node<T> ptr=head;
		while(ptr!=null) {
			if(comparator.compare(obj, ptr.data)==0) {
				//�˻��� �����ߴٸ�
				currentN=ptr;
				return ptr.data;
			}//if----
			ptr=ptr.next;
		}//while-------
		return null;
	}//-----------------------------------
	

}/////////////////////////////














