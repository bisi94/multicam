package day06;

public class �������ڽ� {
	
	class Node{
		String data;//������ ABCDE
		Node left, right;
		
		public Node(String data) {
			this.data=data;
			left=null;
			right=null;
		}
	}///////////////////////
	
	public Node makeTree() {
		Node root=new Node("H");
		
		return root;
	}//-----------------------------
	
	public static void main(String[] args) {
		�������ڽ� app=new �������ڽ�();
		Node root=app.makeTree();
		//
	}

}
