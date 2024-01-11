package day02;
import java.util.*;
public class Q08_SumInterval {
	
	static Scanner sc=new Scanner(System.in);
	
	public static int[] solution(int n, int m) {
		int answer[]=new int[m];//������ ���� ������ �迭
		
		int[] arr=new int[n];
		System.out.println("�迭�� ������ ������ �Է��ϼ���=>");
		for(int i=0;i<arr.length;i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println("������ ����index, ��index�� �Է��ϼ���");
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
		int answer[]=new int[m];//������ ���� ������ �迭
		
		int[] arr=new int[n];
		int[] sum=new int[n+1];
		System.out.println("�迭�� ������ ������ �Է��ϼ���=>");
		for(int i=0;i<arr.length;i++) {
			arr[i]=sc.nextInt();
			sum[i+1]=sum[i]+arr[i];
		}
		System.out.println("������ ����index, ��index�� �Է��ϼ���");
		for(int i=0;i<m;i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			int result=sum[end]-sum[start-1];
			answer[i]=result;
		}//for----------------------------------
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println("�迭 ũ�� �Է�=> ");
		int n=sc.nextInt();
		System.out.println("��� ������ ���� ���ұ��? =>");
		int m=sc.nextInt();
		
		int arr[]=solution2(n,m);
		System.out.println(Arrays.toString(arr));
	}

}
