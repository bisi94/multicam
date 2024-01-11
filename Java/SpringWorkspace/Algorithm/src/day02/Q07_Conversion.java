package day02;

import java.util.Scanner;

public class Q07_Conversion {
	
	//2������ ��ȯ�ϴ� �޼ҵ�
	public static String conv_binary(int num) {
		//num�� 2���� ���ڿ��� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ带 �����غ�����
		String answer="";
		int[] arr=new int[32];
		int cnt=0; //�迭 �ε����� ���
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
		System.out.println("������ �ƴ� ����(���) �Է�: ");
		int num=sc.nextInt();
		
		System.out.println("��ȯ�� ������ �����ϼ���(2~16) : ");
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














