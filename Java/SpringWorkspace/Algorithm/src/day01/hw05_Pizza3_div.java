package day01;

import java.util.Scanner;

public class hw05_Pizza3_div {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("���� ���� �Է��ϼ���: ");
		int slice=sc.nextInt();
		System.out.println("�ο����� �Է��ϼ���: ");
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