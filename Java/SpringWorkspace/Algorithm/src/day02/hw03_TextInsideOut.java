package day02;

import java.util.Scanner;

public class hw03_TextInsideOut {
	
	/*
	 * ���ڿ� my_string�� ���� s, e�� �Ű������� �־��� ��, my_string���� 
	 * �ε��� s���� �ε��� e������ ������ ���ڿ��� return �ϴ� solution �Լ��� �ۼ��� �ּ���.
	 * 
	 * my_string�� ���ڿ� ���ĺ����θ� �̷���� �ֽ��ϴ�.
		1 �� my_string�� ���� �� 1,000
		0 �� s �� e < my_string�� ����
	 */

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("���ڿ��� �Է��ϼ��� : ");
		String my_string=sc.nextLine();
		System.out.println("������ ���ڿ��� ���� index : ");
		int s=sc.nextInt();
		System.out.println("������ ���ڿ��� �� index : ");
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




