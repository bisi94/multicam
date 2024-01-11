package day06;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBFS {

	class Node{
		int data;//데이터
		Node left, right;//왼쪽 자식노드, 오른쪽 자식노드를 참조할 변수
		
		public Node(int num) {
			data=num;
			left=null;
			right=null;
		}
		
	}///////////////////////////////////////
	//2진트리를 만든다
	public Node makeTree() {
		Node root=new Node(1);
		
		root.left=new Node(2);
		root.right=new Node(3);
		
		root.left.left=new Node(4);
		root.left.right=new Node(5);
		
		root.right.left=new Node(6);
		root.right.right=new Node(7);
		
		return root;
	}//---------------------------
	
	public void BFS(Node root) {
		Queue<Node> q=new LinkedList<>();
		//삽입 메소드 : add(), offer()
		//꺼내기(삭제) 메소드 : remove(), poll()
		//검사: peek() = front에 있는 노드를 검사할 때 사용
		
		q.offer(root);
		int level=0;
		while(!q.isEmpty()) {
			System.out.print("L"+level+": ");
			int size=q.size();
			for(int i=0; i<size; i++) {
				//큐에서 노드를 꺼내서 데이터를 출력
				Node item=q.poll();
				System.out.print(item.data+"->");
				if(item.left!=null) q.offer(item.left); //왼쪽 자식노드를 큐에 추가
				if(item.right!=null) q.offer(item.right); //오른쪽 자식노드를 큐에 추가
			}//for()---------------
			level++;
			System.out.println("level: "+level);
		}//while()---------------
		
	}//------------------------
	
	public static void main(String[] args) {
		BinaryTreeBFS app=new BinaryTreeBFS();
		Node root=app.makeTree();
		app.BFS(root);
		
		
	}///////////////////////////////////////
	

}
