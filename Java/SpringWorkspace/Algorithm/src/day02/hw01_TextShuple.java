package day02;

import java.util.Scanner;

public class hw01_TextShuple {
	/*
	길이가 같은 두 문자열 str1과 str2가 주어집니다.
	두 문자열의 각 문자가 앞에서부터 서로 번갈아가면서 한 번씩 등장하는 문자열을 만들어 return 하는 solution 함수를 완성해 주세요.
	
	제한사항:
	1 ≤ str1의 길이 = str2의 길이 ≤ 10
	str1과 str2는 알파벳 소문자로 이루어진 문자열입니다.
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
		System.out.println("a문자열을 입력하세요:");
		String str1=sc.nextLine();
		System.out.println("b문자열을 입력하세요:");
		String str2=sc.nextLine();
		
		Solution app=new Solution();
		app.solution1(str1, str2);
	}

}
