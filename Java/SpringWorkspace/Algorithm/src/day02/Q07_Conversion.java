package day02;

import java.util.Scanner;

public class Q07_Conversion {
	
	//2진수로 변환하는 메소드
	public static String conv_binary(int num) {
		//num을 2진수 문자열로 변환하여 반환하는 메소드를 구현해보세요
		String answer="";
		int[] arr=new int[32];
		int cnt=0; //배열 인덱스로 사용
		do {
			int mod=num%2;
			arr[cnt++]=mod;
			num=num/2;
		}while(num!=0);

		for(int i=0;i<cnt;i++) {
			answer+=arr[cnt-1-i];
		}
		return answer;
	}//---------------------------------------------
	
	public static String conv_binary2(int num) {
		StringBuffer buf=new StringBuffer("");
		do {
			int mod=num%2;
			buf.append(mod);
			num/=2;
		}while(num!=0);
		
		return buf.reverse().toString();
	}//---------------------------------------------
	public static String conv_radix(int num, int radix) {
		String answer="";
		String str="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char arr[]=new char[32];
		int cnt=0;
		do {
			int mod=num%radix;
			char ch=str.charAt(mod);
			arr[cnt++]=ch;
			num/=radix;
		}while(num!=0);
		
		for(int i=cnt-1;i>=0;i--) {
			answer+=arr[i];
		}
		return answer;
	}//---------------------------------------------
	
	public static void printRadix() {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("음수가 아닌 정수(양수) 입력: ");
		int num=sc.nextInt();
		
		System.out.println("변환할 진수를 선택하세요(2~16) : ");
		int radix=sc.nextInt();
		
		String result=conv_radix(num,radix);
		System.out.println(result);
	}//---------------------------------------------
	
	public static void main(String[] args) {
		
		String str=conv_binary(45);
		System.out.println(str);
		
		str=conv_binary2(5);
		System.out.println(str);
		
		printRadix();
		
	}

}














