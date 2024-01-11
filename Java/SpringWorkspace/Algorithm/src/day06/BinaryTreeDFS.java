package day06;

public class BinaryTreeDFS {

	Node root;
	
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
	//전위순회: 부모 -> 왼쪽 -> 오른쪽
	// 1 2 4 5 3 6 7
	public void preorder(Node root) {
		if(root==null) {
			System.out.println("종료");
			return;
		}
		System.out.print(root.data+"->");
		preorder(root.left);
		preorder(root.right);
		
	}//--------------------------------
	//중위순회: 부모 -> 왼쪽 -> 오른쪽
		// 4 2 5 1 6 3 7
	private void inorder(Node root) {
		if(root==null) {
			System.out.println("종료");
			return;
		}
		inorder(root.left);
		System.out.print(root.data+"->");
		inorder(root.right);
	}
	//후위순회: 부모 -> 왼쪽 -> 오른쪽
		// 4 5 2 6 7 3 1
	private void postorder(Node root) {
		if(root==null) {
			System.out.println("종료");
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data+"->");
	}
	
	public static void main(String[] args) {
		BinaryTreeDFS app=new BinaryTreeDFS();
		
		Node root=app.makeTree();
		//1. 전위순회하는 메소드 호출
		//app.preorder(root);
		
		//2. 중위순회하는 메소드 호출
		//app.inorder(root);
		
		//3. 후위순회하는 메소드 호출
		app.postorder(root);
		
	}///////////////////////////////////////
	

}////////////////////////////////////////////
