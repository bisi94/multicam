package day01;

import java.util.Scanner;

public class hw03_Pizza_div {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("인원수를 입력하세요: ");
		int n=sc.nextInt();
		
		Solution3 sol=new Solution3();
		int result=sol.solution(n);
		System.out.println("result: "+result);
				
	}

}

class Solution3 {
    public int solution(int n) {
       int pizza = (n+6)/7;
       int answer = pizza;
        
        return answer;
    }
}
