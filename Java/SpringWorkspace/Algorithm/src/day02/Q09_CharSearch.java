package day02;

import java.util.Arrays;
import java.util.Scanner;

public class Q09_CharSearch {

	public int solution(String str, char c){
		int cnt=0; //�˻��� ���� ����
		char chArr[]=str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(chArr[i]==c) {
				cnt+=1;
			}//if-----------
		}//for--------------
		System.out.println("'"+c+"'�� "+cnt+"�� ã�ҽ��ϴ�");
		return cnt;
	}//--------------------------------------
	
	public int solution2(String str, char c){
		int cnt=0; //�˻��� ���� ����
		str=str.toLowerCase();
		c=Character.toLowerCase(c);
		
		char[] chArr=str.toCharArray();
		for(char ch:chArr) {
			if(ch==c) {
				cnt++;
			}
		}
		
		return cnt;
	}//--------------------------------------
	
	public static void main(String[] args) {
		/*String str="Hello World~";
		char chArr[]=str.toCharArray();
		System.out.println(Arrays.toString(chArr));*/
		Scanner sc=new Scanner(System.in);
		
		System.out.println("���ڿ��� �Է��ϼ���");
		String str=sc.nextLine();
		
		System.out.println("�˻��� ����(1��)�� �Է��ϼ���");
		String ch=sc.next();
		char c=ch.charAt(0);
		//solution() ȣ���ϱ�
		Q09_CharSearch app=new Q09_CharSearch();
		int cnt=app.solution2(str,c);
		System.out.println(c+"�� "+cnt+"�� �ֽ��ϴ�");
		
		
	}
	

}
