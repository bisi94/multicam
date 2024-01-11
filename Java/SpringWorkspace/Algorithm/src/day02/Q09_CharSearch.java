package day02;

import java.util.Arrays;
import java.util.Scanner;

public class Q09_CharSearch {

	public int solution(String str, char c){
		int cnt=0; //검색된 문자 개수
		char chArr[]=str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(chArr[i]==c) {
				cnt+=1;
			}//if-----------
		}//for--------------
		System.out.println("'"+c+"'를 "+cnt+"개 찾았습니다");
		return cnt;
	}//--------------------------------------
	
	public int solution2(String str, char c){
		int cnt=0; //검색된 문자 개수
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
		
		System.out.println("문자열을 입력하세요");
		String str=sc.nextLine();
		
		System.out.println("검색할 문자(1자)를 입력하세요");
		String ch=sc.next();
		char c=ch.charAt(0);
		//solution() 호출하기
		Q09_CharSearch app=new Q09_CharSearch();
		int cnt=app.solution2(str,c);
		System.out.println(c+"는 "+cnt+"개 있습니다");
		
		
	}
	

}
