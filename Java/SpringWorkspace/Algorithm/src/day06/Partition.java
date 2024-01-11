package day06;

import java.util.Arrays;

/*퀵 정렬 알고리즘을 살펴보기 위해 피벗을 하나 정해 2그룹으로 나누어 데이터를 교환하는 작업을 수행해보자.
 * */
public class Partition {

	public static void main(String[] args) {
		int[] arr= {1, 8, 7, 4, 5, 2, 6, 3, 9};
		System.out.println(Arrays.toString(arr));
		partition(arr);
		System.out.println("----파티션 1번 수행한 후------------");
		System.out.println(Arrays.toString(arr));
	}//-------------------------

	private static void partition(int[] arr) {
		int pl=0;//커서, 포인터, 왼쪽 커서는 1씩 증가  ==> (pl<=pr)조건을 충족할때 까지
		int pr=arr.length-1;//오른쪽 커서는 1씩 감소  ==>(pl<=pr)조건을 충족할때 까지
		int pivot=arr[arr.length/2];
		do {
			//먼저 왼쪽 커서를 먼저 이동시키자 
			while(arr[pl] <  pivot) pl++;
			//오른쪽 커서도 이동시킨다
			while(arr[pr] >pivot) pr--;
			if(pl<=pr) {
				swap(arr, pl, pr);//데이터 교환
				pl++;//데이터 교환후 계속 커서 이동
				pr--;
			}
			System.out.println("pivot값: "+pivot);
		}while(pl<=pr);
	}//---------------------------

	private static void swap(int[] arr, int pl, int pr) {
		int tmp=arr[pl];
		arr[pl]=arr[pr];
		arr[pr]=tmp;
	}//-----------------------------------

}//////////////////////////////////////





