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
		//nxn의 2차원 배열
		System.out.println("배열 크기 입력: ");
		int n=sc.nextInt();
		//2차원 배열 생성
		int []arr[]=new int[n][n];
		
		System.out.println("저장할 정수값 입력: ");
		//2차원 배열에 저장할 값 입력받기
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j]=sc.nextInt();
			}
		}//for---------------------
		//System.out.println(Arrays.toString(arr));
		
		System.out.println("저장된 값은 다음과 같습니다--------");
		for(int[] brr:arr) {
			for(int val:brr) {
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
		
		
		int maxSum=app.solution(n, arr);
		System.out.println("대각선을 포함한 각 라인의 합 중 가장 큰 합계는 maxSum: "+maxSum);
	}//-----------------

	private int solution(int n, int[][] arr) {
		int maxSum=-999;
		
		int sumx;//행의 합
		int sumy;//열의 합
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
		System.out.println("열의 합과 행의 합 중 maxSum: "+maxSum);
		
		//대각선1, 대각선2의 합 구하는 for루프
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
