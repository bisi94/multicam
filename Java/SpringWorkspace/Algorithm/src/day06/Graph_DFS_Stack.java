package day06;

import java.util.Stack;

public class Graph_DFS_Stack {

	static int [][] graph= { {}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2} };
	
	static boolean visited[]=new boolean[9];//디폴트값이 저장되어 있다. false
	//스택에 저장한 뒤 방문했다는 의미로 true값을 넣어줄 예정
	
	static Stack<Integer> sk=new Stack<>();
	
	
	public static void main(String[] args) {
		sk.push(1);//시작 노드 저장
		visited[1]=true;//스택에 저장한 노드 방문 처리
		
		while(!sk.isEmpty()) {
			//스택에서 노드를 하나 꺼낸다
			int i=sk.pop();
			System.out.print(i+"->");
			//꺼낸 노드와 인접한 노드를 찾는다 방문하지 ㅇㅏㄶ은 노드라면 스택에 넣어준다(push)
			//graph[1][0], graph[1][1], graph[1][2]
			for(int node:graph[i]) {
				if(!visited[node]) {//방문하지 않은 노드라면
					sk.push(node);
					//방문처리
					visited[node]=true;
				}//if------------
				
			}//for---------------
				
		}//while------------------
		
	}//main()----------------------

}///////////////////////////////////
