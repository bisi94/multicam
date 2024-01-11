package day06;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBFS {

	class Node{
		int data;//������
		Node left, right;//���� �ڽĳ��, ������ �ڽĳ�带 ������ ����
		
		public Node(int num) {
			data=num;
			left=null;
			right=null;
		}
		
	}///////////////////////////////////////
	//2��Ʈ���� �����
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
		//���� �޼ҵ� : add(), offer()
		//������(����) �޼ҵ� : remove(), poll()
		//�˻�: peek() = front�� �ִ� ��带 �˻��� �� ���
		
		q.offer(root);
		int level=0;
		while(!q.isEmpty()) {
			System.out.print("L"+level+": ");
			int size=q.size();
			for(int i=0; i<size; i++) {
				//ť���� ��带 ������ �����͸� ���
				Node item=q.poll();
				System.out.print(item.data+"->");
				if(item.left!=null) q.offer(item.left); //���� �ڽĳ�带 ť�� �߰�
				if(item.right!=null) q.offer(item.right); //������ �ڽĳ�带 ť�� �߰�
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
