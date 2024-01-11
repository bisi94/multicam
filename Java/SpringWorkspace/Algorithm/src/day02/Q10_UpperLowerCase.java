package day02;

import java.util.Scanner;

public class Q10_UpperLowerCase {
	
	public String solution1(String my_string) {
        String answer = "";
        char chArr[]=my_string.toCharArray();

        for(char ch:chArr) {
        	if(ch>='a' && ch<='z') { //�ҹ����� ���
        		answer+=(char)(ch-32);
        	}else { //�빮���� ���
        		answer+=(char)(ch+32);
        	}
        }
        
        return answer;
    }//-------------------------------------------------
	
	public String solution2(String my_string) {
		String answer="";
		char[] chArr=my_string.toCharArray();
		for(char ch:chArr) {
			if(Character.isUpperCase(ch)) {//�빮���� ���
				answer+=Character.toLowerCase(ch);
			}else{
				answer+=Character.toUpperCase(ch);
			}
		}
		return answer;
	}
	
    public String solution(String my_string) {
        String answer = "";
        
        char chArr[]=my_string.toCharArray();
        
        for(int i=0; i<my_string.length(); i++) {
        	int convert[] = new int[my_string.length()];
        	int asciiValue = (int) chArr[i];
        	
        	if(asciiValue>64 && asciiValue<91) {
        		convert[i]=asciiValue+32; //�빮��>�ҹ���
        	}else if(asciiValue>96 && asciiValue<123) {
        		convert[i]=asciiValue-32; //�ҹ���>�빮��
        	}
        	answer=convert.toString();
        }
        
        return answer;
    }//-------------------------------------------------
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("���ĺ� ���ڿ��� �Է��ϼ���");
		String str=sc.next();
		
		//solution() ȣ���ϱ�
		Q10_UpperLowerCase app=new Q10_UpperLowerCase();
		String result=app.solution2(str);
		System.out.println(result);
	}

}
