package day06;

public class BinaryTreeDFS {

	Node root;
	
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
	//������ȸ: �θ� -> ���� -> ������
	// 1 2 4 5 3 6 7
	public void preorder(Node root) {
		if(root==null) {
			System.out.println("����");
			return;
		}
		System.out.print(root.data+"->");
		preorder(root.left);
		preorder(root.right);
		
	}//--------------------------------
	//������ȸ: �θ� -> ���� -> ������
		// 4 2 5 1 6 3 7
	private void inorder(Node root) {
		if(root==null) {
			System.out.println("����");
			return;
		}
		inorder(root.left);
		System.out.print(root.data+"->");
		inorder(root.right);
	}
	//������ȸ: �θ� -> ���� -> ������
		// 4 5 2 6 7 3 1
	private void postorder(Node root) {
		if(root==null) {
			System.out.println("����");
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data+"->");
	}
	
	public static void main(String[] args) {
		BinaryTreeDFS app=new BinaryTreeDFS();
		
		Node root=app.makeTree();
		//1. ������ȸ�ϴ� �޼ҵ� ȣ��
		//app.preorder(root);
		
		//2. ������ȸ�ϴ� �޼ҵ� ȣ��
		//app.inorder(root);
		
		//3. ������ȸ�ϴ� �޼ҵ� ȣ��
		app.postorder(root);
		
	}///////////////////////////////////////
	

}////////////////////////////////////////////
