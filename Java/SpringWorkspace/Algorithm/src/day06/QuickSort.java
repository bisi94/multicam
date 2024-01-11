package day06;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr= {1, 8, 7, 4, 5, 2, 6, 3, 9};
		int[] arr2= {88, 39, 93, 55, 11, 32};
		System.out.println(Arrays.toString(arr));
		quickSort(arr2, 0, arr2.length-1);
		System.out.println("----------Äü Á¤·Ä ÀÌÈÄ-------------");
		System.out.println(Arrays.toString(arr2));
	}//-------------------------------

	private static void printProcess(int[] arr, int left, int right, int pivot) {
		System.out.println("pivot: "+pivot);
		System.out.printf("arr[%d ~ arr[%d] : {", left, right);
		for(int i=left; i<right; i++) {
			System.out.printf("%d, ", arr[i]);
		}
		System.out.printf("%d}%n", arr[right]);
	}//------------------------------
	
	private static void quickSort(int[] arr, int left, int right) { //(0,8)
		int pl=left; //0
		int pr=right; //8
		int pivot=arr[(pl+pr)/2];//arr[4]=5,
		
		printProcess(arr, left, right, pivot);
		
		do {
			while(arr[pl] < pivot) pl++;
			while(arr[pr] > pivot) pr--;
			if(pl<=pr) {
				swap(arr, pl++, pr--);
			}
			
		}while(pl<=pr);
		/////////////////////
		if(left<pr) quickSort(arr, left, pr);//Àç±ÍÈ£Ãâ
		if(pl<right) quickSort(arr, pl, right);//Àç±ÍÈ£Ãâ
		/////////////////////
		
	}//-----------------------------------
	
	private static void swap(int[] arr, int pl, int pr) {
		int tmp=arr[pl];
		arr[pl]=arr[pr];
		arr[pr]=tmp;
	}//-----------------------------------

}////////////////////////////////////
