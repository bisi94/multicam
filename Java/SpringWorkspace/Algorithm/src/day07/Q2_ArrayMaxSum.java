package day07;

import java.util.Arrays;
import java.util.Scanner;

/*
 
 10 18 41 23 62
 25 78 35 12 28
 23 17 28 16 39
 78 45 16 34 17
 52 21 18 30 10
  
*/

public class Q2_ArrayMaxSum {

	public static void main(String[] args) {
		Q2_ArrayMaxSum app=new Q2_ArrayMaxSum();
		Scanner sc=new Scanner(System.in);
		//nxn�� 2���� �迭
		System.out.println("�迭 ũ�� �Է�: ");
		int n=sc.nextInt();
		//2���� �迭 ����
		int []arr[]=new int[n][n];
		
		System.out.println("������ ������ �Է�: ");
		//2���� �迭�� ������ �� �Է¹ޱ�
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j]=sc.nextInt();
			}
		}//for---------------------
		//System.out.println(Arrays.toString(arr));
		
		System.out.println("����� ���� ������ �����ϴ�--------");
		for(int[] brr:arr) {
			for(int val:brr) {
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
		
		
		int maxSum=app.solution(n, arr);
		System.out.println("�밢���� ������ �� ������ �� �� ���� ū �հ�� maxSum: "+maxSum);
	}//-----------------

	private int solution(int n, int[][] arr) {
		int maxSum=-999;
		
		int sumx;//���� ��
		int sumy;//���� ��
		for(int i=0; i<n; i++) {
			sumx=0;
			sumy=0;
			for(int j=0; j<n; j++) {
				sumx+=arr[i][j];
				sumy+=arr[j][i];
			}//for---------------------
			//System.out.println(sumy);
			if(sumx>maxSum) {
				maxSum=sumx;
			}
			if(sumy>maxSum) {
				maxSum=sumy;
			}
		}//for-------------------------
		System.out.println("���� �հ� ���� �� �� maxSum: "+maxSum);
		
		//�밢��1, �밢��2�� �� ���ϴ� for����
		int sumxy=0, sumyx=0;
		for(int i=0; i<n; i++) {
			sumxy+=arr[i][i];
			sumyx+=arr[i][n-1-i];
		}
		System.out.println("sumxy="+sumxy+" sumyx="+sumyx);
		if(sumxy>maxSum) {
			maxSum=sumxy;
		}
		if(sumyx>maxSum) {
			maxSum=sumyx;
		}

		return maxSum;
	}

	
}
