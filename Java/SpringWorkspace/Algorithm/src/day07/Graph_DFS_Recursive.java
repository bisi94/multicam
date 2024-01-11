package day07;

public class Graph_DFS_Recursive {

						   //0 		1 		2 		3 		4		 5	  6 	 7	    8 
	static int [][] graph= { {}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2} };
	
	static boolean visited[]=new boolean[9];//디폴트값이 저장되어 있다. false
	//스택에 저장한 뒤 방문했다는 의미로 true값을 넣어줄 예정
	
	public static void main(String[] args) {
		dfs(1);	
	}//main()-------------------------------------
	
	static void dfs(int nodeIndex) {
		//방문처리
		visited[nodeIndex]=true;
		//방문노드 출력
		System.out.print(nodeIndex+"->");
		for(int node:graph[nodeIndex]) {
			//노드값 방문여부 체크
			if(!visited[node]) {
				//인접노드를 방문한 적이 없다면 dfs() 수행
				dfs(node);//재귀호출
			}//if-----------
		}//for---------------
	}//dfs()--------------------------------------

}
