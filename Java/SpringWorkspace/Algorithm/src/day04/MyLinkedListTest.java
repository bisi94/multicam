package day04;

import day04.MyLinkedList.Node;

public class MyLinkedListTest {

	public static void main(String[] args) {
		MyLinkedList<String> list=new MyLinkedList<>();
		list.addFirst("A");
		list.addLast("B");
		list.printCurrentNode();//B
		list.addLast("C");
		list.addLast("D");
		list.printCurrentNode();//D
		
		list.printAllNode();//A->B->C->D
		
		list.addFirst("E");
		list.printAllNode();//E->A->B->C->D
		list.printCurrentNode();//E
		
		list.removeLast();
		list.printAllNode();//E->A->B->C
		list.printCurrentNode();//C
		
		list.addFirst("F");
		list.printCurrentNode();//F
		list.printAllNode();//F->E->A->B->C
		
		list.next();
		list.printCurrentNode();//E
		list.next();
		list.next();
		
		list.removeCurrentNode();
		list.printAllNode();//F->E->A->C
		
		
		MyLinkedList.Node<String> delNode=new MyLinkedList.Node<>("A", null);
		list.remove(delNode);//주소값이 다르므로 삭제처리가 되지 않는다
		list.printAllNode();
		
		list.clear();
		list.printCurrentNode();
		list.printAllNode();
		
		
	}

}








