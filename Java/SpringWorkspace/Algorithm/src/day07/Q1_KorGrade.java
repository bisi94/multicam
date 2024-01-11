package day07;

import java.util.Arrays;
import java.util.Scanner;

public class Q1_KorGrade {

	public int [] solution(int n, int[] kor) {
		int grade[]=new int[n]; //�� ������ ����� ����
		for(int i=0; i<n; i++) {
			grade[i]=1;
			for(int j=0; j<n; j++) {
				if(kor[i] < kor[j] ) {
					grade[i]++; //��� ����
				}
			}
		}
		
		
		return grade;
	}
	
	public static void main(String[] args) {
		Q1_KorGrade app=new Q1_KorGrade();
		Scanner sc=new Scanner(System.in);
		System.out.println("�л� �� �Է�");
		int n=sc.nextInt();
		int arr[]=new int[n];
		System.out.println("���� ���� "+n+"�� �Է�: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println(Arrays.toString(arr));
		//solution() ȣ���ؼ� ��ȯ�Ǵ� �迭 ���
		
		int sol[]=app.solution(n, arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("sol: "+Arrays.toString(sol));
		
	}

}///////////////////////////////////////
