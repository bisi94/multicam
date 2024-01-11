package day05;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		int arr[]= {6, 4, 1, 7, 3, 9, 8};
		System.out.println(Arrays.toString(arr));
		insertSort2(arr);
		System.out.println("----삽입정렬 후------------");
		System.out.println(Arrays.toString(arr));
	}//--------------------------

	private static void insertSort(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int tmp=arr[i];//임시변수에 보관 ==>끼어들 자리를 찾아본다
			int j=i-1;
			for(;j>=0;j--) {
				if(arr[j] >  tmp) {
					arr[j+1]=arr[j];//뒤로 밀어내기
				}else {
					break;
				}
			}//for---
			arr[j+1]=tmp;//tmp값을 삽입
			//삽입할 위치를 찾았다면 삽입
		}//for----------------
	}//-----------------------------
	
	private static void insertSort2(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int tmp=arr[i];//임시변수에 보관 ==>끼어들 자리를 찾아본다
			int j=i-1;
			for(;j>=0 && arr[j]>tmp  ;j--) {				
				arr[j+1]=arr[j];//뒤로 밀어내기				
			}//for---
			arr[j+1]=tmp;//tmp값을 삽입
			//삽입할 위치를 찾았다면 삽입
		}//for----------------
	}//-----------------------------
	
	

}///////////////////////////////////////
