package day05;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int arr[]= {6,4,8,3,1,9,7};
		System.out.println(Arrays.toString(arr));
		//selectionSort(arr);
		selectionSortDesc(arr);
		System.out.println("----선택정렬 후----------------");
		System.out.println(Arrays.toString(arr));
	}//main()---------------------------
	//선택정렬: 최소(또는 최대) 값을 선택해서 해당 값을 앞 위치부터 가져다 두는 형태
	public static void selectionSort(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			//최소값이 있는곳의 인덱스번호를 알아내자
			int min=i;
			for(int j=i+1;j<arr.length;j++) {
				//최소값이 있는 위치를 찾아내자
				if(arr[min]>arr[j]) {
					min=j;
				}
			}//for-------
			int tmp=arr[min];
			arr[min]=arr[i];
			arr[i]=tmp;
		}//for-------------------
	}//---------------------------------
	
	//선택정렬을 하되 내림차순으로 정렬하세요
	public static void selectionSortDesc(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			//최대값이 있는곳의 인덱스번호를 알아내자
			int max=i;
			for(int j=i+1;j<arr.length;j++) {
				//최소값이 있는 위치를 찾아내자
				if(arr[max]<arr[j]) {
					max=j;
				}
			}//for-------
			int tmp=arr[max];
			arr[max]=arr[i];
			arr[i]=tmp;
		}//for-------------------
	}//---------------------------------

}////////////////////////////////












