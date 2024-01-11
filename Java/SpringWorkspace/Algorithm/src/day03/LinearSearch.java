package day03;
import java.util.*;
public class LinearSearch {

	public static int search(int[] arr, int n, int key) {
		//검색한 값이 저장되어 있는 인덱스 번호 반환
		//검색한 값이 없을 경우는 -1을 반환
		int idx=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==key) {
				idx=i;
			}else if(i==arr.length-1) {
				idx=-1;
			}
		}
		
		return idx;
	}
	public static int search1(int[] arr, int n, int key) {
		int idx=0;
		
		for(; idx<n;idx++) {
			if(arr[idx]==key) {
				return idx;
			}
		}//for------------------
		idx=-1;
		
		return idx;
	}
	
	
	public static int search2(int[] arr, int n, int key) {
		//검색한 값이 저장되어 있는 인덱스 번호 반환
		//검색한 값이 없을 경우는 -1을 반환
		int idx=0;
		while(true) {
			if(idx==n) { //판단1 : 검색한 값을 발견하지 못하고 배열의 끝을 지날 때
				return -1;
			}
			
			if(arr[idx]==key) { //판단2 : 검색한 값을 발견한 경우
				return idx;
			}
			idx++;
		}//while----------------------
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("배열 크기 입력: ");
		int n=sc.nextInt();
		
		int arr[]=new int[n];
		System.out.println("저장할 정수값 입력: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}//for----------------------------------
		System.out.println(Arrays.toString(arr));
		
		System.out.println("검색할 정수값 입력: ");
		int key=sc.nextInt();
		
		int idx=search2(arr, n, key);
		
		String msg=(idx<0)? key+"값은 배열에 없습니다":key+"값은 배열 인덱스 "+idx+"번에 있습니다";
		System.out.println(msg);
		
	}

}
