package day06;

import java.util.Stack;

public class Graph_DFS_Stack {

	static int [][] graph= { {}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2} };
	
	static boolean visited[]=new boolean[9];//����Ʈ���� ����Ǿ� �ִ�. false
	//���ÿ� ������ �� �湮�ߴٴ� �ǹ̷� true���� �־��� ����
	
	static Stack<Integer> sk=new Stack<>();
	
	
	public static void main(String[] args) {
		sk.push(1);//���� ��� ����
		visited[1]=true;//���ÿ� ������ ��� �湮 ó��
		
		while(!sk.isEmpty()) {
			//���ÿ��� ��带 �ϳ� ������
			int i=sk.pop();
			System.out.print(i+"->");
			//���� ���� ������ ��带 ã�´� �湮���� �������� ����� ���ÿ� �־��ش�(push)
			//graph[1][0], graph[1][1], graph[1][2]
			for(int node:graph[i]) {
				if(!visited[node]) {//�湮���� ���� �����
					sk.push(node);
					//�湮ó��
					visited[node]=true;
				}//if------------
				
			}//for---------------
				
		}//while------------------
		
	}//main()----------------------

}///////////////////////////////////
