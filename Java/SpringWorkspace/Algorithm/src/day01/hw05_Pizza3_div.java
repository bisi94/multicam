package day01;

import java.util.Scanner;

public class hw05_Pizza3_div {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("조각 수를 입력하세요: ");
		int slice=sc.nextInt();
		System.out.println("인원수를 입력하세요: ");
		int n=sc.nextInt();
		
		Solution5 sol=new Solution5();
		int result=sol.solution(slice, n);
		System.out.println("result: "+result);
				
	}

}
class Solution5 {
    public int solution(int slice, int n) {
    	 int pizza = (n+slice-1)/slice;
         int answer = pizza;
          
          return answer;
    }
}