package day02;

import java.util.Scanner;

public class hw02_A_Emphasize {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("문자열을 입력하세요 : ");
		String myString =sc.nextLine();
		
		Solution app=new Solution();
		String result = app.solution2(myString);
		
		System.out.println("result: "+result);
	}

}

class Solution {
    public String solution2(String myString) {
    	/*
    	 * 문자열 myString이 주어집니다. myString에서 알파벳 "a"가 등장하면 전부 "A"로 변환하고, 
    	 * "A"가 아닌 모든 대문자 알파벳은 소문자 알파벳으로 변환하여 return 하는 solution 함수를 완성하세요.
    	 * */
        char chArr[]=myString.toCharArray();
        
        for(int i=0; i<chArr.length; i++) {
        	if(Character.isUpperCase(chArr[i])) {
        		chArr[i]=Character.toLowerCase(chArr[i]);
        	}
        	if(chArr[i]=='a' && Character.isLowerCase(chArr[i])) {
        		chArr[i]=Character.toUpperCase(chArr[i]);
        	}
        	
        }//for()----------------------------------
        
        String answer = new String(chArr);
        
        return answer;
    }
}
