package day02;

import java.util.Scanner;

public class hw03_TextInsideOut {
	
	/*
	 * 문자열 my_string과 정수 s, e가 매개변수로 주어질 때, my_string에서 
	 * 인덱스 s부터 인덱스 e까지를 뒤집은 문자열을 return 하는 solution 함수를 작성해 주세요.
	 * 
	 * my_string은 숫자와 알파벳으로만 이루어져 있습니다.
		1 ≤ my_string의 길이 ≤ 1,000
		0 ≤ s ≤ e < my_string의 길이
	 */

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("문자열을 입력하세요 : ");
		String my_string=sc.nextLine();
		System.out.println("뒤집을 문자열의 시작 index : ");
		int s=sc.nextInt();
		System.out.println("뒤집을 문자열의 끝 index : ");
		int e=sc.nextInt();
		
		Solution3 sol=new Solution3();
		String result=sol.solution(my_string, s, e);
		
		System.out.println(result);
	}

}

class Solution3 {
    public String solution(String my_string, int s, int e) {
        char stArr[]=my_string.toCharArray();
        char resArr[]=my_string.toCharArray();
        
        String answer="";
        
    	for(int i=0; i<=e-s; i++) {
    		resArr[s+i]=stArr[e-i];
    	}
    	
    	for(int i=0; i<my_string.length(); i++) {
        	answer += ""+resArr[i];
        }
    	
        return answer;
    }
}

// 		s=3, e=6   
// 0 1 (2 3 4 5) 6 7 8 9 10 index
// 0 1 (5 3 4 5) 6 7 8 9 10 i=0;
// 0 1 (5 4 4 5) 6 7 8 9 10 i=1; 
// 0 1 (5 4 3 5) 6 7 8 9 10 i=2; 
// 0 1 (5 4 3 2) 6 7 8 9 10 i=3; 




