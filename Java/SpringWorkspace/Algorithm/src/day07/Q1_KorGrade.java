package day07;

import java.util.Arrays;
import java.util.Scanner;

public class Q1_KorGrade {

	public int [] solution(int n, int[] kor) {
		int grade[]=new int[n]; //각 성적의 등수를 저장
		for(int i=0; i<n; i++) {
			grade[i]=1;
			for(int j=0; j<n; j++) {
				if(kor[i] < kor[j] ) {
					grade[i]++; //등수 증가
				}
			}
		}
		
		
		return grade;
	}
	
	public static void main(String[] args) {
		Q1_KorGrade app=new Q1_KorGrade();
		Scanner sc=new Scanner(System.in);
		System.out.println("학생 수 입력");
		int n=sc.nextInt();
		int arr[]=new int[n];
		System.out.println("국어 성적 "+n+"개 입력: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println(Arrays.toString(arr));
		//solution() 호출해서 반환되는 배열 출력
		
		int sol[]=app.solution(n, arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("sol: "+Arrays.toString(sol));
		
	}

}///////////////////////////////////////
