package day02;

import java.util.Arrays;
import java.util.Scanner;

public class Q11_FindLongestWord {

	public String solution(String str) {
		String answer="";
		
		String word[]=str.split(" ");
		
		for(int i=0;i<word.length;i++) {
			if(word[0].length()<word[i].length()) {
				answer=word[i];
				}
		}
		
		return answer;
	}//-----------------------------------------
	
	public String solution2(String str) {
		String answer="";
		String tokens[]=str.split(" ");
		System.out.println(Arrays.toString(tokens));
		int max=answer.length();
		for(String word: tokens) {
			if(word.length() > max) {
				max=word.length();
				answer=word;
			}
		}
		
		return answer;
	}//-----------------------------------------
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("문자열을 입력하세요: ");
		String str=sc.nextLine();
		
		//solution() 호출해서 반환값 출력하기
		Q11_FindLongestWord app=new Q11_FindLongestWord();
		app.solution(str);
		String result=app.solution2(str);
		System.out.println(result);
	}

}
