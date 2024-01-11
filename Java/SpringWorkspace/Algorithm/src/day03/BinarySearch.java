package day03;

import java.util.Arrays;
import java.util.Scanner;

/*이진검색
 * - 리스트, 배열을 이용
 * - 정렬되어 있는 자료구조로 해야 함
 * - O(log n)
 * - 100개의 데이터가 있다면 O(log 100) ==>최악의 경우 7번만에 검색을 마칠 수 있음
 * */
public class BinarySearch {

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
		
		int idx=binarySearch(arr, n, key);
		System.out.println("검색한 "+key+"의 인덱스: "+idx);
	}//------------------------------------------
	
	public static int binarySearch(int[] arr, int n, int key) {
		Arrays.sort(arr);//오름차순으로 정렬하고 시작하기
		System.out.println("------------오름차순 정렬 후-----------");
		System.out.println(Arrays.toString(arr));
		System.out.println("----------------------------------");
		int left=0; //배열 맨 앞 인덱스 할당
		int right=n-1; //배열 끝 인덱스 할당
		int center=(left+right)/2; //중앙의 인덱스 구하는 용도
		int cnt=0;
		while(left<=right) {
			cnt++;
			//검색하면 해당 인덱스번호 반환
			if(arr[center]==key) {
				System.out.println(cnt+"번 만에 맞췄습니다");
				return center;
			}else if(arr[center] < key) {
				left=center+1;
			}else if(arr[center] > key) {
				right=center-1;
			}
			center=(left+right)/2;
		}//while-----------------------------------
		return -1;
	}//--------------------------------------------

}////////////////////////////////////////////////////////////









