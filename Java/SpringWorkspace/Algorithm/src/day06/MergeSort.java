package day06;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int arr[]= {5,6,4,8,3,7,9,0,1,5,2,3};
		System.out.println(Arrays.toString(arr));
		partition(arr,0,arr.length-1);
		System.out.println("----병합 정렬 이후---------");
		System.out.println(Arrays.toString(arr));
	}//-------------------------------------

	private static void partition(int[] arr, int left, int right) {
		if(left == right) return;//종료조건. 분할하다 보면 left값이 right와 같아지는 순간에 종료
		
		int mid=(left+right)/2; //중간값 구하기
		partition(arr, left, mid); //전반부 분할
		partition(arr, mid+1, right); //후반부 분할
		merge(arr, left, right); //2개로 나눈 배열을 병합해주는 메소드를 호출
		
	}//-----------------------------------

	private static void merge(int[] arr, int left, int right) {
		int tmp[]=new int[arr.length];
		int index=0;//tmp배열에서 사용할 인덱스
		
		int pc=(left+right)/2;
		int pl=left;
		int pr=pc+1;
		index=left;
		
		while(pl<=pc && pr<=right) {
			//두 배열 중 작은 값을 tmp배열에 넣어준다
			
			/*if(arr[pl] < arr[pr]) {
				tmp[index++]=arr[pl++];
			}else {
				tmp[index++]=arr[pr++];
			}*/
			tmp[index++]=(arr[pl] < arr[pr])? arr[pl++]:arr[pr++];
		}//while--------------------------
		
		//남은 것들이 있으면 옮기자
		int i;
		if(pl>pc) {//왼쪽 포인터가 중앙을 넘어섰다면 == 오른쪽에 데이터가 남은경우
			for(i=pr; i<=right; i++) {
				tmp[index++]=arr[i];
			}
		}else {//왼쪽에 데이터가 남은 경우
			for(i=pl; i<=pc; i++) {
				tmp[index++]=arr[i];
			}
		}
		for(i=left; i<=right; i++) {
			arr[i]=tmp[i];
		}
		System.out.println(left+"/"+right);
		System.out.println(Arrays.toString(tmp));
		System.out.println(Arrays.toString(arr));
		System.out.println("************************");
	}//-----------------------------------
	
	
}








