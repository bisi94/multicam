package day02;

import java.util.Scanner;

public class Q12_Pallindrome {

	public static String solution(String str) {
		String answer="";
		
		int err=0;
		
		char chArr[]=str.toCharArray();
		for(int i=0; i<chArr.length/2;i++) {
			if(chArr[i]!=chArr[chArr.length-1-i]) {
				err=0;
			}else {
				err=1;
			}
		}
		if(err==0) {
			answer="It isn't Pallindrome@@@";
		}else if(err==1){
			answer="It's Pallindrome!!!";
		}
		return answer;
	}
	
	public static String solution1(String str) {
		String answer="";
		str=str.toLowerCase();
		char[] ch=str.toCharArray();
		for(int i=0;i<ch.length;i++) {
			char c1=ch[i];
			char c2=ch[ch.length-1-i];
			if(c1!=c2) {
				answer="Pallindrome NO";
				return answer;
			}
		}//for------------------------------
		answer="Pallindrome OK";
		return answer;
	}
	
	public static String solution2(String str) {
		str=str.toUpperCase();
		StringBuilder buf=new StringBuilder(str);
		String tmp=buf.reverse().toString();
		if(str.equalsIgnoreCase(tmp)) {
			return "OK";
		}
		return "NO";
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("문자열을 입력하세요 : ");
		String str=sc.next();
		System.out.println(solution2(str));
	}
}
