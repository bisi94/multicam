package day01;

import java.util.Scanner;

public class hw6_ClothingStore {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int price=sc.nextInt();
		
		Solution6 sol=new Solution6();
		int totalPrice=sol.solution(price);
		System.out.println(totalPrice+"¿ø");
	}
}

class Solution6 {
    public int solution(int price) {
        int answer = 0;
        if(price<100000) {
        	answer = price;
        }else if(price>=100000 && price<300000) {
        	answer=price*95/100;
        }else if(price>=300000 && price<500000) {
        	answer=price*9/10;
        }else if(price>=500000){
        	answer=price*8/10;
        }
        
        return answer;
    }
}