package day02;

import java.util.Scanner;

public class hw02_A_Emphasize {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("���ڿ��� �Է��ϼ��� : ");
		String myString =sc.nextLine();
		
		Solution app=new Solution();
		String result = app.solution2(myString);
		
		System.out.println("result: "+result);
	}

}

class Solution {
    public String solution2(String myString) {
    	/*
    	 * ���ڿ� myString�� �־����ϴ�. myString���� ���ĺ� "a"�� �����ϸ� ���� "A"�� ��ȯ�ϰ�, 
    	 * "A"�� �ƴ� ��� �빮�� ���ĺ��� �ҹ��� ���ĺ����� ��ȯ�Ͽ� return �ϴ� solution �Լ��� �ϼ��ϼ���.
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
