package day02;

import java.util.Scanner;

public class hw01_TextShuple {
	/*
	���̰� ���� �� ���ڿ� str1�� str2�� �־����ϴ�.
	�� ���ڿ��� �� ���ڰ� �տ������� ���� �����ư��鼭 �� ���� �����ϴ� ���ڿ��� ����� return �ϴ� solution �Լ��� �ϼ��� �ּ���.
	
	���ѻ���:
	1 �� str1�� ���� = str2�� ���� �� 10
	str1�� str2�� ���ĺ� �ҹ��ڷ� �̷���� ���ڿ��Դϴ�.
	 * */
	
	public static class Solution {
	    public String solution1(String str1, String str2) {
	        String answer = "";
	        
	        char chArra[]=str1.toCharArray();
	        char chArrb[]=str2.toCharArray();
	        
	        for(int i=0; i<str1.length();i++) {
	        	answer+=chArra[i];
	        	answer+=chArrb[i];
	        }
	        System.out.println(answer);
	        return (String)answer;
	    }
	}	

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("a���ڿ��� �Է��ϼ���:");
		String str1=sc.nextLine();
		System.out.println("b���ڿ��� �Է��ϼ���:");
		String str2=sc.nextLine();
		
		Solution app=new Solution();
		app.solution1(str1, str2);
	}

}
