package day01;

import java.util.Scanner;

public class hw01_각도기 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("angle을 입력하세요: ");
		int angle=sc.nextInt();
		
		Solution1 st=new Solution1();
		int result=st.solution(angle);
		System.out.println("result: "+result);
		
	}

}/////////////////////////////////////////

class Solution1 {
	
    public int solution(int angle) {
    	int answer = 0;  
        if(angle>0 && angle<90){
            answer = 1;
        }else if(angle==90){
            answer = 2;    
        }else if(angle>90 && angle<180){
            answer = 3;    
        }else if(angle==180){
            answer = 4;    
        }
        return answer;
    }
}










