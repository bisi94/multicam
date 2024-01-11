package day05;
import java.util.*;

public class Fibonacci {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("n을 입력: ");//n: 항
		int n=sc.nextInt();
		//fibo(n);
		//fibo2(n);
		/*for(int i=1;i<=n;i++) {
			System.out.print(fibo2(i)+" ");
		}
		System.out.println("\n-------------------");
		*/
		arr=new int[n+1]; 
		//fibo3(n);
		fibo4(n);
		
		for(int i=1;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		
	}//---------------------
	
	public static int fibo3(int n) {
		if(n==1) return arr[n]=1;
		else if(n==2) return arr[n]=1;
		else
			return arr[n]=fibo3(n-1)+fibo3(n-2);
		//n번째 항의 앞과 앞의앞을 더해서 반환
	}//------------------------
	//똑같은 연산을 반복수행하지 않도록 연산결과를 기록으로 남겨 재사용하도록 개선해보자=> 메모이제이션
	public static int fibo4(int n) {
		///////////////////////////
		if(arr[n]>0) return arr[n];
		//////////////////////////
		if(n==1) return arr[n]=1;
		else if(n==2) return arr[n]=1;
		else 
			return arr[n]=fibo4(n-1)+fibo4(n-2);
		//n번째 항의 앞과 앞의앞을 더해서 반환
	}//------------------------
	
	
	//1 1 2 3 5 8 13 21 34 55 ....
	//피보나치수열을 재귀함수로 구현해보자
	public static int fibo2(int n) {
		if(n==1) return 1;
		else if(n==2) return 1;
		else
			return fibo2(n-1)+fibo2(n-2);
		//n번째 항의 앞과 앞의앞을 더해서 반환
	}//------------------------
	//반복문을 이용해 피보나치수열을 구현해보자
	public static void fibo(int n) {
		int num1=1;
		int num2=1;
		int num3=0;
		System.out.print(num1+" "+num2+" ");
		for(int i=0;i<n-2;i++) {
			num3=num1+num2;
			System.out.print(num3+" ");
			num1=num2;
			num2=num3;
		}
	}//-----------------------

}
