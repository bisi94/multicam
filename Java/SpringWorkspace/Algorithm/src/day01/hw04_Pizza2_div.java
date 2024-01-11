package day01;

import java.util.Scanner;

public class hw04_Pizza2_div {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("인원수를 입력하세요: ");
		int n=sc.nextInt();
		
		Solution4 sol=new Solution4();
		int result=sol.solution(n);
		System.out.println("result: "+result);
				
	}

}
class Solution4 {
    public int solution(int n) {
    	int pizza=0;
		if(n%6==0) {
			pizza = n/6;
		}else if(n%3==0) {
			pizza = n*2/6;
		}else if(n%2==0) {
			pizza = n*3/6;
		}else {
			pizza = n*6/6;
		}
		
		int answer = pizza;
        return answer;
    }
}



/*
 * 
 *
 * 
 * */
 