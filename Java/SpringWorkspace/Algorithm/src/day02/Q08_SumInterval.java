package day02;
import java.util.*;
public class Q08_SumInterval {
	
	static Scanner sc=new Scanner(System.in);
	
	public static int[] solution(int n, int m) {
		int answer[]=new int[m];//구간의 합을 저장할 배열
		
		int[] arr=new int[n];
		System.out.println("배열에 저장할 정수를 입력하세요=>");
		for(int i=0;i<arr.length;i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println("구간의 시작index, 끝index를 입력하세요");
		for(int i=0;i<m;i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			for(int j=start;j<=end;j++) {
				answer[i]+=arr[j-1];
			}
		}//for----------------------------------\
		return answer;
	}
		
	public static int[] solution2(int n, int m) {
		int answer[]=new int[m];//구간의 합을 저장할 배열
		
		int[] arr=new int[n];
		int[] sum=new int[n+1];
		System.out.println("배열에 저장할 정수를 입력하세요=>");
		for(int i=0;i<arr.length;i++) {
			arr[i]=sc.nextInt();
			sum[i+1]=sum[i]+arr[i];
		}
		System.out.println("구간의 시작index, 끝index를 입력하세요");
		for(int i=0;i<m;i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			int result=sum[end]-sum[start-1];
			answer[i]=result;
		}//for----------------------------------
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println("배열 크기 입력=> ");
		int n=sc.nextInt();
		System.out.println("몇개의 구간의 합을 구할까요? =>");
		int m=sc.nextInt();
		
		int arr[]=solution2(n,m);
		System.out.println(Arrays.toString(arr));
	}

}
