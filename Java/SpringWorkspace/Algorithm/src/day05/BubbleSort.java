package day05;
import java.util.*;
public class BubbleSort {
	
	//앞에서부터 버블정렬하기 ==> 1회 반복돌고 나면 가장 큰수가 오른쪽에 가있다
	public static void bubble(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1 -i; j++) {				
				if(arr[j] > arr[j+1]) {
					int tmp=arr[j];
					arr[j]= arr[j+1];
					arr[j+1] = tmp;
				}//if-----
			}//for----
		}//for----
	}//--------------------------------
	//뒤에서부터 버블정렬하기 ==> 1회 반복돌고 나면 가장 작은수가 왼쪽에 가있다
	public static void bubble2(int[] arr) {
		int cnt=0;
		for(int i=0;i<arr.length-1;i++) {
			int k=0;
			for(int j=arr.length-1;j>i;j--) {
				if(arr[j-1] > arr[j]) {
					//int tmp=arr[j-1];
					//arr[j-1]=arr[j];
					//arr[j]=tmp;
					swap(arr, j-1, j);
					k++;//교환횟수를 세보자. swap이 일어나면 k를 1씩 증가
					
				}//if---
				cnt++;
				//System.out.println("k: "+k);
			}//for---
			if(k==0) break;
			//k가 0이라면 교환이 더이상 없다는 의미 => 정렬을 이미 마쳤다는 의미
		}//for------
		System.out.println("bubble2() cnt: "+cnt);
	}//------------------------------
	
	public static void swap(int[] arr, int m, int n) {
		int tmp=arr[m];
		arr[m]=arr[n];
		arr[n]=tmp;
	}//------------------------------------
	

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("배열 크기 입력: ");
		int n=sc.nextInt();
		
		int[] arr=new int[n];
		Random ran=new Random();
		for(int i=0;i<n;i++) {
			arr[i]=ran.nextInt(15)+1; //1<= r < 16
		}
		int[] arr2= {1, 3, 6, 4, 7, 8, 9};
		System.out.println(Arrays.toString(arr2));
		System.out.println("----버블정렬 후---------------");
		//bubble(arr);
		//bubble2(arr);
		bubble2(arr2);
		
		System.out.println(Arrays.toString(arr2));

	}

}





