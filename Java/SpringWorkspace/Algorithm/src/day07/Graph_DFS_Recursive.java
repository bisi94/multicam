package day07;

public class Graph_DFS_Recursive {

						   //0 		1 		2 		3 		4		 5	  6 	 7	    8 
	static int [][] graph= { {}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2} };
	
	static boolean visited[]=new boolean[9];//����Ʈ���� ����Ǿ� �ִ�. false
	//���ÿ� ������ �� �湮�ߴٴ� �ǹ̷� true���� �־��� ����
	
	public static void main(String[] args) {
		dfs(1);	
	}//main()-------------------------------------
	
	static void dfs(int nodeIndex) {
		//�湮ó��
		visited[nodeIndex]=true;
		//�湮��� ���
		System.out.print(nodeIndex+"->");
		for(int node:graph[nodeIndex]) {
			//��尪 �湮���� üũ
			if(!visited[node]) {
				//������带 �湮�� ���� ���ٸ� dfs() ����
				dfs(node);//���ȣ��
			}//if-----------
		}//for---------------
	}//dfs()--------------------------------------

}
